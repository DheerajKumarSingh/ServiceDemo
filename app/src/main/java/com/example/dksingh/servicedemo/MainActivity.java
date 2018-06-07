package com.example.dksingh.servicedemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.dksingh.servicedemo.service.MyLocalBinderService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    Intent intent;
    public void startService(View view)
    {
        Intent intent=new Intent(this, MyLocalBinderService.class);
        startService(intent);
    }
    public void stopService(View view)
    {
        Intent intent=new Intent(this, MyLocalBinderService.class);
        stopService(intent);
    }

    private ServiceConnection mServiceConnection;
    MyLocalBinderService.MyService myService;
    private boolean isServiceConnected;
    public void bindService()
    {
        if(null==mServiceConnection)
        {
            mServiceConnection=new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    isServiceConnected=true;
                    myService=(MyLocalBinderService.MyService)service;
                }
                @Override
                public void onServiceDisconnected(ComponentName name) {
                    isServiceConnected=false;
                }
            };
        }

    }

    public void unBindService()
    {
        if(isServiceConnected)
        {
            isServiceConnected=false;
            mServiceConnection=null;
        }
    }
}
