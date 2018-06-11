package com.example.dksingh.servicedemo;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.example.dksingh.servicedemo.service.Constants;
import com.example.dksingh.servicedemo.service.ForgroundServiceDemo;


public class Activity_ForgroundService extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forground_service);
    }

  public void startForgroundService(View view)
    {
        Intent service = new Intent(Activity_ForgroundService.this, ForgroundServiceDemo.class);
        if (!ForgroundServiceDemo.IS_SERVICE_RUNNING) {
            service.setAction(Constants.ACTION.STARTFOREGROUND_ACTION);
            ForgroundServiceDemo.IS_SERVICE_RUNNING = true;
            startService(service);
        }
    }
    public    void stopForgroundService(View view)
    {
        Intent service = new Intent(Activity_ForgroundService.this, ForgroundServiceDemo.class);
        service.setAction(Constants.ACTION.STOPFOREGROUND_ACTION);
        ForgroundServiceDemo.IS_SERVICE_RUNNING = false;
        startService(service);
    }

}
