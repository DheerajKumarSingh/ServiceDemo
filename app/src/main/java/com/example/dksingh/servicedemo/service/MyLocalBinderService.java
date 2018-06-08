package com.example.dksingh.servicedemo.service;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import java.util.Random;

public class MyLocalBinderService extends Service {

    public static final String TAG="MyLocalBinderService";
    boolean isServiceCreated=true;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.v(TAG,"local binder created");
    }

    public  class MyServiceBinder extends Binder{
        public MyLocalBinderService getMyService()
        {
            return MyLocalBinderService.this;
        }
    }

    private IBinder myIBinder=new MyServiceBinder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.v(TAG,"onBind::::::::::::::");
        return myIBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v(TAG,"onStartCommand::::::::::::::");
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(isServiceCreated)
                {
                    try {
                        Thread.sleep(3000);
                        random=new Random().nextInt(10)+2200;
                        Log.v(TAG,"random number execute"+random);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isServiceCreated=false;
        Log.v(TAG,"service destroy::::::::::::::");
    }

    Number random;
    public int getRandomNumber()
    {
        return (int) random;
    }
}
