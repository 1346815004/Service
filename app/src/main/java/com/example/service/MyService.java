package com.example.service;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class MyService extends Service {
    MediaPlayer player;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        Toast.makeText(MyService.this,"创建",Toast.LENGTH_SHORT).show();
        player = MediaPlayer.create(this,R.raw.mao);
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
            player.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int i=0;
                while(isRuning()){
                    Log.i("srvice?:", String.valueOf(++i));
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();//开启线程
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(MyService.this,"结束",Toast.LENGTH_SHORT).show();
        player.stop();
        player.release();
        super.onDestroy();
    }

    public boolean isRuning(){
        ActivityManager activityManager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        ArrayList<ActivityManager.RunningServiceInfo> runningService = (ArrayList<ActivityManager.RunningServiceInfo>)activityManager.getRunningServices(10);
                for(int i = 0; i < runningService.size(); i++){
                    if(runningService.get(i).service.getClassName().equals("com.example.service.MyService"))
                        return true;
                }
                return false;
    }
}
