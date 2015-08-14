package com.webonise.assignment8.basicsqliteexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class ContactDetailFormActivity extends Activity implements View.OnClickListener {

    private EditText etName;
    private EditText etAge;
    private EditText etHeight;
    private EditText etWeight;
    private Button btnSave;
    private Button btnRetrieve;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail_form);
        initializeView();
    }

    public void initializeView() {
        btnSave = (Button) findViewById(R.id.btnSave);
        btnRetrieve = (Button) findViewById(R.id.btnRetrieve);
        etName = (EditText) findViewById(R.id.etName);
        etAge = (EditText) findViewById(R.id.etAge);
        etHeight = (EditText) findViewById(R.id.etHeight);
        etWeight = (EditText) findViewById(R.id.etWeight);

        btnRetrieve.setOnClickListener(this);
        btnSave.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        switch (viewId) {
            case R.id.btnRetrieve:

                Intent intent = new Intent(this, ShowDataFromDatabase.class);
                startActivity(intent);
                break;
            case R.id.btnSave:
                if (editTextIsNull()) {
                    saveDataToDB(collectData());
                } else {
                    Toast.makeText(this, getString(R.string.empty_field), Toast.LENGTH_LONG).show();
                }
                break;
        }
    }


    private void saveDataToDB(FormDB form) {
        FormDBHelper formDBHelper = new FormDBHelper(this);
        formDBHelper.addDetail(form);
        formDBHelper.close();
    }

    public boolean editTextIsNull() {
        if (TextUtils.isEmpty(etName.getText().toString())) {
            return false;
        } else if (TextUtils.isEmpty(etAge.getText().toString())) {
            return false;
        } else if (TextUtils.isEmpty(etWeight.getText().toString())) {
            return false;
        } else if (TextUtils.isEmpty(etHeight.getText().toString())) {
            return false;
        } else {
            return true;
        }
    }

    public FormDB collectData() {
        String name = "";
        int age = 0;
        double weight = 0;
        double height = 0;
        FormDB form = new FormDB();

        name = etName.getText().toString();
        age = Integer.parseInt(etAge.getText().toString());
        weight = Double.parseDouble(etWeight.getText().toString());
        height = Double.parseDouble(etHeight.getText().toString());

        form.setName(name);
        form.setAge(age);
        form.setHeight(height);
        form.setWeight(weight);

        return form;
    }
}
