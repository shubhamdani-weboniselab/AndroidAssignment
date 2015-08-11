package com.webonise.assignment6.basicserviceexample;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class StartingServiceActivity extends Activity implements View.OnClickListener {

    private Button startService;
    private EditText etInput;
    private String inputData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_service);
        startService = (Button) findViewById(R.id.btnStartService);
        etInput = (EditText) findViewById(R.id.etInput);
        startService.setOnClickListener(this);
    }

    public String getStingResources(String message) {
        String stringResource = "";
        switch (message) {
            case Constents.inputNumber:
                stringResource = getResources().getString(R.string.inputNumber);
                break;
            case Constents.invalid_input:
                stringResource = getResources().getString(R.string.invalid_input);
                break;

        }
        return stringResource;
    }

    @Override
    public void onClick(View v) {
        inputData = etInput.getText().toString();

        if (!TextUtils.isEmpty(inputData)) {
            int data = Integer.parseInt(inputData);
            Intent serviceIntent = new Intent(this, CalculateSumService.class);
            serviceIntent.putExtra(Constents.inputNumber, data);
            startService(serviceIntent);
        } else {
            Toast.makeText(this, getStingResources("invalid_input"), Toast.LENGTH_LONG).show();
        }
    }
}
