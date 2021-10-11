package com.example.characterproject;

import android.app.AlarmManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyService extends Service {

    Long mResult;
    public MyService() {
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent!=null){
            mResult = intent.getLongExtra("re",0);
            Log.d("intent",mResult+"");
            if(mResult !=null){
                //자정 시간
                Calendar resetCal = Calendar.getInstance();
                resetCal.setTimeInMillis(System.currentTimeMillis());
                resetCal.set(Calendar.HOUR_OF_DAY, 0);
                resetCal.set(Calendar.MINUTE,0);
                resetCal.set(Calendar.SECOND, 0);

                SimpleDateFormat format = new SimpleDateFormat("kk:mm");
                String setResetTime = format.format(new Date(resetCal.getTimeInMillis()+ AlarmManager.INTERVAL_DAY));

                long now = System.currentTimeMillis();
                Date nowDate =new Date(now);
                long d=nowDate.getTime();
                SimpleDateFormat dateFormat =new SimpleDateFormat("hh:mm");
                String getTime =dateFormat.format(d);
                String a ="03:46";
                Log.d("service",getTime+"a");

                if(getTime.equals(a)){
                 mResult+=1;
                    Log.d("service",a+"a");
                    Intent activityIntent =new Intent();
                    activityIntent.setAction("com.example.broadcast.show");
                    activityIntent.putExtra("re",mResult);
                    sendBroadcast(activityIntent);
                }else{
                    Intent activityIntent =new Intent();
                    activityIntent.setAction("com.example.broadcast.show");
                    activityIntent.putExtra("re",mResult);
                    sendBroadcast(activityIntent);
                    Toast.makeText(getApplicationContext(), "실패", Toast.LENGTH_SHORT).show();
                }
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}