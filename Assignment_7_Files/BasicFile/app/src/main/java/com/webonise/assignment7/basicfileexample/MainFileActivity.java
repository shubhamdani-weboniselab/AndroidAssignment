package com.webonise.assignment7.basicfileexample;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainFileActivity extends Activity implements View.OnClickListener {
    private Button btnSubmit;
    private EditText etName;
    private EditText etWeight;
    private EditText etHeight;
    private String name = "";
    private float weight = 0.0f;
    private float height = 0.0f;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_file);
        initializeView();
    }

    public void initializeView() {
        btnSubmit = (Button) findViewById(R.id.btn_submit);
        etHeight = (EditText) findViewById(R.id.etHeight);
        etName = (EditText) findViewById(R.id.etName);
        etWeight = (EditText) findViewById(R.id.etWeight);
        btnSubmit.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        sharedPreferences = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);
        name = sharedPreferences.getString(Constents.NAME, Constents.DEFAULT);
        if (name.equals(Constents.DEFAULT)) {
            Toast.makeText(this, getString(R.string.field_empty), Toast.LENGTH_LONG).show();
            saveDataToSP();

        } else {
            Toast.makeText(this, name + weight + height, Toast.LENGTH_LONG).show();
            saveDataToSP();
        }

    }

    public boolean nullCheck(String string) {
        if (TextUtils.isEmpty(string)) {

            return false;
        } else {
            return true;
        }
    }

    public void saveDataToSP() {
        name = etName.getText().toString();
        if (nullCheck(etWeight.getText().toString()) && nullCheck(etHeight.getText().toString()) && nullCheck(etName.getText().toString())) {
            weight = Float.parseFloat(etWeight.getText().toString());
            height = Float.parseFloat(etHeight.getText().toString());
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(Constents.NAME, name);
            editor.putFloat(Constents.WEIGHT, weight);
            editor.putFloat(Constents.HEIGHT, height);
            editor.commit();
        }

    }
}

