package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import java.util.Random;

public class MyService2 extends Service {
    public MyService2() {
    }

    public class MyBinder extends Binder{
        MyService2 getService() {
            return MyService2.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new MyBinder();
    }

    public String getRandomNumber(){
//        List resArr = new ArrayList();
        String strNumber = "";

//        for (int i=0; i<7; i++){
//            int number = new Random().nextInt(33)+1;
//            if (number < 10){
//                strNumber = "0" + String.valueOf(number);
//            }else{
//                strNumber = String.valueOf(number);
//            }
//            resArr.add(strNumber);
//        }
        int number = new Random().nextInt(33);
//        Toast.makeText(MyService2.this,number,Toast.LENGTH_SHORT).show();
        strNumber = String.valueOf(number);
//        Toast.makeText(MyService2.this,strNumber,Toast.LENGTH_SHORT).show();
        return strNumber;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
