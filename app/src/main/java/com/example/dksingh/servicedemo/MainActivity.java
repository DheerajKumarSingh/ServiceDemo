package com.example.dksingh.servicedemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.dksingh.servicedemo.service.MyLocalBinderService;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    Intent serviceIntent;
    public void startService(View view)
    {
        serviceIntent=new Intent(this, MyLocalBinderService.class);
        startService(serviceIntent);
    }
    public void stopService(View view)
    {


        serviceIntent=new Intent(this, MyLocalBinderService.class);
        stopService(serviceIntent);
    }

    private ServiceConnection mServiceConnection;
    MyLocalBinderService myService;
    private boolean isServiceConnected;
    public void bindService()
    {
        if(null==mServiceConnection)
        {
            mServiceConnection=new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder iBinder) {
                    isServiceConnected = true;
                    MyLocalBinderService.MyServiceBinder myService1 = (MyLocalBinderService.MyServiceBinder) iBinder;
                    myService = myService1.getMyService();
                }
                @Override
                public void onServiceDisconnected(ComponentName name) {
                    isServiceConnected=false;
                }
            };
        }

        bindService(serviceIntent,mServiceConnection,BIND_AUTO_CREATE);
        isServiceBound=true;
    }

    public void unBindService()
    {
        if(isServiceConnected)
        {
            isServiceConnected=false;
            unbindService(mServiceConnection);
            mServiceConnection=null;
            isServiceBound=false;
        }
    }

boolean isServiceBound;
    public void boundService(View view)
    {
        bindService();

    }
    public void UnBoundedClick(View view)
    {
       unBindService();

    }
    
    
    public void getRandomNumber(View view)
    {
        if(isServiceBound) Log.v(TAG,"fetch random number"+myService.getRandomNumber());
        else  Log.v(TAG,"service is not bounded");
    }
}
