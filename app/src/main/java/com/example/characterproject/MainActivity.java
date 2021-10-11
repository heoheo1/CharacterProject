package com.example.characterproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    int dateEndY, dateEndM, dateEndD;
    int ddayValue = 0;
    SharedPreferences sharedPreferences;
    MyReceiver receiver;
    // 현재 날짜를 알기 위해 사용
    Calendar calendar;
    int currentYear, currentMonth, currentDay;

    // Millisecond 형태의 하루(24 시간)
    private final int ONE_DAY = 24 * 60 * 60 * 1000;

    TextView edit_endDateBtn, edit_result;
    ImageView datePicker;
    Long d_result;
    ImageView simple_CharMan,simple_CharWoman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //시작일, 종료일 데이터 저장
        calendar = Calendar.getInstance();
        currentYear = calendar.get(Calendar.YEAR);
        currentMonth = (calendar.get(Calendar.MONTH));
        currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        simple_CharMan =findViewById(R.id.simple_CharMan);
        simple_CharWoman=findViewById(R.id.simple_CharWoman);
        simple_CharWoman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,WomanActivity.class);
                startActivity(intent);
            }
        });

//        sharedPreferences =getSharedPreferences("pref",MODE_PRIVATE);
//        SharedPreferences.Editor editor=sharedPreferences.edit();
//        int s=sharedPreferences.getInt("image",0);
//        woman_hair1.setImageResource(s);

        datePicker = findViewById(R.id.datePicker);
        edit_endDateBtn = (TextView) findViewById(R.id.edit_endDateBtn);
        edit_result = (TextView) findViewById(R.id.edit_result);

        sharedPreferences =getSharedPreferences("pref",MODE_PRIVATE);
        d_result=sharedPreferences.getLong("result",0);

        Intent serviceIntent =new Intent(getApplicationContext(),MyService.class);
        serviceIntent.putExtra("re",d_result);
        Log.d("datePicker",String.valueOf(d_result));
        startService(serviceIntent);

        receiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.example.broadcast.show");
        registerReceiver(receiver, filter);

        Locale.setDefault(Locale.KOREAN);

        // 디데이 날짜 입력
        edit_endDateBtn.setText("우리가 만난 날짜 : " +currentYear + "년 " + (currentMonth + 1) + "월 " + currentDay + "일");

        //datePicker : 디데이 날짜 입력하는 버튼, 클릭시 DatePickerDialog 띄우기
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this, endDateSetListener, (currentYear), (currentMonth), currentDay).show();
            }

        });
    }
    public void setImage(int d){
        sharedPreferences =getSharedPreferences("pref",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("image",R.drawable.woman_hair1);
        editor.commit();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){

        }

    }
    /** @brief endDateSetListener
     *  @date 2016-02-18
     *  @detail DatePickerDialog띄우기, 종료일 저장, 기존에 입력한 값이 있으면 해당 데이터 설정후 띄우기
     *
     *
     */

    private DatePickerDialog.OnDateSetListener endDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            edit_endDateBtn.setText("우리가 만난 날짜 : " +year + "년 " + (monthOfYear + 1) + "월 " + dayOfMonth + "일");
            ddayValue = ddayResult_int(dateEndY, dateEndM, dateEndD);
            edit_result.setText(getDday(year, monthOfYear, dayOfMonth));
        }
    };


    /** @brief getDday
     *  @date 2016-02-18
     *  @param mYear : 설정한 디데이 year, mMonthOfYear : 설정한 디데이 MonthOfYear, mDayOfMonth : 설정한 디데이 DayOfMonth
     *  @detail D-day 반환
     */
    private String getDday(int mYear, int mMonthOfYear, int mDayOfMonth) {

        // D-day 설정
        final Calendar ddayCalendar = Calendar.getInstance();
        ddayCalendar.set(mYear, mMonthOfYear, mDayOfMonth);

        // D-day 를 구하기 위해 millisecond 으로 환산하여 d-day 에서 today 의 차를 구한다.
        final long dday = ddayCalendar.getTimeInMillis() / ONE_DAY;
        final long today = Calendar.getInstance().getTimeInMillis() / ONE_DAY;
        long result = dday - today;
        // 출력 시 d-day 에 맞게 표시
        String strFormat;
        if (result > 0) {
            strFormat = "D-%d";
        } else if (result == 0) {
            strFormat = "Today";
        } else {
            result *= -1;
            strFormat = "우리가 사랑 한지 : D+%d";
        }

        final String strCount = (String.format(strFormat, result));
        return strCount;
    }

    /** @brief onPhotoDialog
     *  @date 2016-02-18
     *  @detail 디데이 값 계산
     *  */
    public int onCalculatorDate (int dateEndY, int dateEndM, int dateEndD) {
        try {
            Calendar today = Calendar.getInstance(); //현재 오늘 날짜
            Calendar dday = Calendar.getInstance();

            //시작일, 종료일 데이터 저장
            Calendar calendar = Calendar.getInstance();
            int cyear = calendar.get(Calendar.YEAR);
            int cmonth = (calendar.get(Calendar.MONTH) + 1);
            int cday = calendar.get(Calendar.DAY_OF_MONTH);

            today.set(cyear, cmonth, cday);
            dday.set(dateEndY, dateEndM, dateEndD);// D-day의 날짜를 입력합니다.

            long day = dday.getTimeInMillis() / 86400000;
            // 각각 날의 시간 값을 얻어온 다음
            //( 1일의 값(86400000 = 24시간 * 60분 * 60초 * 1000(1초값) ) )

            long tday = today.getTimeInMillis() / 86400000;
            long count = tday - day; // 오늘 날짜에서 dday 날짜를 빼주게 됩니다.
            return (int) count; // 날짜는 하루 + 시켜줘야합니다.
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /** @brief ddayResult_int
     *  @date 2016-02-18
     *  @detail 디데이 값 계산한 값 결과값 출력
     *  Todo 함수 오류 수정
     *  */
    public int ddayResult_int(int dateEndY, int dateEndM, int dateEndD) {
        int result = 0;

        result = onCalculatorDate(dateEndY, dateEndM, dateEndD);

        return result;
    }


    class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Long mResult = intent.getLongExtra("re", 0);
            if(intent != null){
                Log.d("intent",mResult+"");
                edit_result.setText("우리가 사랑한지 D+ : "+mResult);
                sharedPreferences =getSharedPreferences("pref",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putLong("result",mResult);
                editor.commit();
                Toast.makeText(getApplicationContext(), "myReceiver", Toast.LENGTH_SHORT).show();
            }


        }
    }

}