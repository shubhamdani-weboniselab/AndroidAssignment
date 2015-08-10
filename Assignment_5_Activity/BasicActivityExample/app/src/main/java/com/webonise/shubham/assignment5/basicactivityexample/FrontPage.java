package com.webonise.shubham.assignment5.basicactivityexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by webonise on 10/8/15.
 */
public class FrontPage extends Activity{
    private final String TAG = getClass().getSimpleName();
    private TextView tvWelcome;
    private Button btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.front_page);
        initializeView();

    }
    public void initializeView() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String username = bundle.getString("username");
        tvWelcome = (TextView) findViewById(R.id.tvWelcome);
        tvWelcome.append(username);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.v(TAG ,"Activity started");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.v(TAG ,"Activity restarted");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG ,"Activity resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TAG ,"Activity paused");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG ,"Activity destroyed");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(TAG ,"Activity stop");
    }
}
