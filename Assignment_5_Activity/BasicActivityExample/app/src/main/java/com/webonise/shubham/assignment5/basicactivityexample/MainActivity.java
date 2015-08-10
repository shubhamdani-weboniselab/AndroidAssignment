package com.webonise.shubham.assignment5.basicactivityexample;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Stack;


public class MainActivity extends Activity implements View.OnClickListener {

    private final String TAG = getClass().getSimpleName();
    private Button btnSubmit;
    private EditText etUsername;
    private EditText etPassword;
    private final String username = "shubham";
    private final String password = "12345";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeView();
        Log.v(TAG, "Activity created");
    }

    public void initializeView() {
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etUsername = (EditText) findViewById(R.id.etUsername);
        btnSubmit.setOnClickListener(this);
    }

    public String getStringResource(String message) {
        String str = "";
        switch (message) {
            case "username":
                str = getString(R.string.username);
                break;
            case "password":
                str = getString(R.string.password);
                break;
            case "enterValid":
                str = getString(R.string.enterValid);
                break;
        }

        return str;
    }

    @Override
    public void onClick(View v) {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        switch (v.getId()) {
            case R.id.btnSubmit:
                if (validateData(username, password)) {
                    Intent intent = new Intent(MainActivity.this, FrontPage.class);
                    intent.putExtra("username", username);
                    intent.putExtra("password", password);
                    etUsername.setText("");
                    etPassword.setText("");
                    startActivity(intent);
                }

                break;
        }
    }

    public boolean validateData(String username, String password) {
        if (!TextUtils.isEmpty(username)) {
            if (!TextUtils.isEmpty(password)) {
                if (username.equals(this.username) && password.equals(this.password)) {
                    return true;
                }else {
                    Toast.makeText(this,getStringResource("enterValid"),Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, getStringResource("password"), Toast.LENGTH_LONG).show();
                return false;
            }
        } else {
            Toast.makeText(this, getStringResource("username"), Toast.LENGTH_LONG).show();
            return false;
        }
        return false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(TAG, "Activity started");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.v(TAG, "Activity restarted");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG, "Activity resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TAG, "Activity paused");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "Activity destroyed");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(TAG, "Activity stop");
    }
}
