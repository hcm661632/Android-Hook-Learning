package com.hua.hookbinder;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;

import com.hua.hookbinder.service.TestService;

public class Main2Activity extends AppCompatActivity {

    MyServiceConnection mconn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        Intent intentService = new Intent(this, TestService.class);
        mconn = new MyServiceConnection();

        bindService(intentService,mconn, Context.BIND_AUTO_CREATE);
    }


    class MyServiceConnection implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
