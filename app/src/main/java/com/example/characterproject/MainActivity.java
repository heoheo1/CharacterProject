package com.example.characterproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    int dateEndY, dateEndM, dateEndD;
    int ddayValue = 0;
    SharedPreferences sharedPreferences;
    // 현재 날짜를 알기 위해 사용
    Calendar calendar,maxDate;
    int currentYear, currentMonth, currentDay;
    // Millisecond 형태의 하루(24 시간)
    private final int ONE_DAY = 24 * 60 * 60 * 1000;

    TextView edit_endDateBtn, edit_result;
    ImageView datePicker;
    Long d_result;
    ImageView simple_CharMan,man_hair,man_clothes,man_shoes,man_Accessories;
    ImageView simple_CharWoman,woman_hair,woman_clothes,woman_shoes,woman_Accessories;
    TextView char_man,char_Woman;


    public void init( ){
        simple_CharMan =findViewById(R.id.simple_CharMan);
        man_hair=findViewById(R.id.man_hair);
        man_clothes=findViewById(R.id.man_clothes);
        man_shoes=findViewById(R.id.man_shoes);
        man_Accessories=findViewById(R.id.man_Accessories);
        simple_CharWoman=findViewById(R.id.simple_CharWoman);
        woman_hair=findViewById(R.id.woman_hair);
        woman_clothes=findViewById(R.id.woman_clothes);
        woman_shoes=findViewById(R.id.woman_shoes);
        woman_Accessories=findViewById(R.id.woman_Accessories);
        datePicker = findViewById(R.id.datePicker);
        edit_endDateBtn =findViewById(R.id.edit_endDateBtn);
        edit_result =  findViewById(R.id.edit_result);
        char_man =findViewById(R.id.char_man);
        char_Woman=findViewById(R.id.char_Woman);
    }

    public void img_Upload(){
        sharedPreferences =getSharedPreferences("pref",MODE_PRIVATE);
        int womanhair=sharedPreferences.getInt("womanhair",0);
        int womanclothes=sharedPreferences.getInt("womanclothes",0);
        int womanshoes=sharedPreferences.getInt("womanshoes",0);
        int womanaccessories=sharedPreferences.getInt("womanaccessories",0);
        woman_hair.setImageResource(womanhair);
        woman_clothes.setImageResource(womanclothes);
        woman_shoes.setImageResource(womanshoes);
        woman_Accessories.setImageResource(womanaccessories);

        int manhair=sharedPreferences.getInt("manhair",0);
        int manclothes=sharedPreferences.getInt("manclothes",0);
        int manshoes=sharedPreferences.getInt("manshoes",0);
        int manaccessories=sharedPreferences.getInt("manaccessories",0);
        man_hair.setImageResource(manhair);
        man_clothes.setImageResource(manclothes);
        man_shoes.setImageResource(manshoes);
        man_Accessories.setImageResource(manaccessories);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Locale.setDefault(Locale.KOREAN);

        //시작일, 종료일 데이터 저장
        init();
        img_Upload();

        calendar = Calendar.getInstance();
        maxDate = Calendar.getInstance();
        currentYear = calendar.get(Calendar.YEAR);
        currentMonth = (calendar.get(Calendar.MONTH));
        currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        maxDate.set(currentYear,currentMonth,currentDay);

        sharedPreferences =getSharedPreferences("pref",MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPreferences.edit();
        String woman_name =sharedPreferences.getString("woman_name","여자");
        String man_name =sharedPreferences.getString("man_name","남자");
        d_result=sharedPreferences.getLong("result",0);
        int year=sharedPreferences.getInt("year",0);
        int monthOfYear= sharedPreferences.getInt("monthOfYear",0);
        int dayOfMonth=sharedPreferences.getInt("dayOfMonth",0);
        int cyear=sharedPreferences.getInt("cyear",currentYear);
        int cmonth= sharedPreferences.getInt("cmonth",currentMonth+1);
        int cday=sharedPreferences.getInt("cday",currentDay);
         String d_now=cyear+""+cmonth+""+cday+"";
        String now=currentYear+""+(currentMonth+1)+""+currentDay+"";
        char_Woman.setText(woman_name);
        char_man.setText(man_name);





        if (!(d_now.equals(now))){
          d_result+=1;
          editor.putInt("cyear",currentYear);
          editor.putInt("cmonth",currentMonth+1);
          editor.putInt("cday",currentDay);
          editor.putLong("result",d_result);
          editor.commit();
        }

        if(d_result>0) {
            d_result=sharedPreferences.getLong("result",0);
            edit_result.setText("우리가 사랑 한지 : D+" + d_result);
            edit_endDateBtn.setText("우리가 만난 날짜 : " +year + "년 " + monthOfYear  + "월 " + dayOfMonth + "일");
        }

        // 디데이 날짜 입력


        //datePicker : 디데이 날짜 입력하는 버튼, 클릭시 DatePickerDialog 띄우기
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, endDateSetListener, (currentYear), (currentMonth), currentDay);
                dialog.getDatePicker().setMaxDate(maxDate.getTimeInMillis());

                dialog.show();
            }
        });

        simple_CharWoman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,WomanActivity.class);
                startActivity(intent);
            }
        });
        simple_CharMan.setOnClickListener(v -> {
            Intent intent =new Intent(MainActivity.this,ManActivity.class);
            startActivity(intent);
        });
    }

    //DatePickerDialog띄우기, 종료일 저장, 기존에 입력한 값이 있으면 해당 데이터 설정후 띄우기
    private DatePickerDialog.OnDateSetListener endDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            edit_endDateBtn.setText("우리가 만난 날짜 : " +year + "년 " + (monthOfYear + 1) + "월 " + dayOfMonth + "일");
            sharedPreferences =getSharedPreferences("pref",MODE_PRIVATE);
            SharedPreferences.Editor editor =sharedPreferences.edit();
            editor.putInt("year",year);
            editor.putInt("monthOfYear",monthOfYear + 1);
            editor.putInt("dayOfMonth",dayOfMonth);
            editor.commit();
            ddayValue = ddayResult_int(dateEndY, dateEndM, dateEndD);
            edit_result.setText(getDday(year, monthOfYear, dayOfMonth));

        }
    };

    // 설정한 디데이 year, mMonthOfYear : 설정한 디데이 MonthOfYear, mDayOfMonth : 설정한 디데이 DayOfMonth
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
            strFormat = "날짜를 다시 선택해주세요.";
        } else if (result == 0) {
            strFormat = "Today";
        } else {
            result *= -1;
            strFormat = "우리가 사랑 한지 : D+%d";
        }

        final String strCount = (String.format(strFormat, result));
        sharedPreferences =getSharedPreferences("pref",MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putLong("result",result);
        editor.commit();
        return strCount;
    }
    //디데이값 계산
    public int onCalculatorDate (int dateEndY, int dateEndM, int dateEndD) {
        try {
            Calendar today = Calendar.getInstance(); //현재 오늘 날짜
            Calendar dday = Calendar.getInstance();

            //시작일, 종료일 데이터 저장
            Calendar calendar = Calendar.getInstance();
            int cyear = calendar.get(Calendar.YEAR);
            int cmonth = (calendar.get(Calendar.MONTH) + 1);
            int cday = calendar.get(Calendar.DAY_OF_MONTH);

            sharedPreferences =getSharedPreferences("pref",MODE_PRIVATE);
            SharedPreferences.Editor editor =sharedPreferences.edit();
            editor.putInt("cyear",cyear);
            editor.putInt("cmonth",cmonth);
            editor.putInt("cday",cday);
            editor.commit();

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

    public int ddayResult_int(int dateEndY, int dateEndM, int dateEndD) {
        int result = 0;
        result = onCalculatorDate(dateEndY, dateEndM, dateEndD);
        return result;
    }


}