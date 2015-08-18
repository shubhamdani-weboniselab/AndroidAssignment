package com.webonise.assignment9.basiclistview;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by webonise on 14/8/15.
 */
public class PersonDetailFormActivity extends Activity implements View.OnClickListener {
    EditText etName;
    EditText etAge;
    EditText etWeight;
    EditText etHeight;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_detail_form_activity);
        initializeView();

    }

    public void initializeView() {
        etName = (EditText) findViewById(R.id.etName);
        etAge = (EditText) findViewById(R.id.etAge);
        etHeight = (EditText) findViewById(R.id.etHeight);
        etWeight = (EditText) findViewById(R.id.etWeight);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String name;
        String age;
        String height;
        String weight;

        name = etName.getText().toString();
        age = (etAge.getText().toString());
        height = (etHeight.getText().toString());
        weight = (etWeight.getText().toString());

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, getString(R.string.no_name), Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(age)) {
            Toast.makeText(this, getString(R.string.no_age), Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(height)) {
            Toast.makeText(this, getString(R.string.no_height), Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(weight)) {
            Toast.makeText(this, getString(R.string.no_weight), Toast.LENGTH_LONG).show();
        } else {
            PersonDatabaseHelper personDatabaseHelper = new PersonDatabaseHelper(this);
            PersonDetails personDetails = new PersonDetails();
            personDetails.setName(name);
            personDetails.setHeight(Double.parseDouble(height));
            personDetails.setWeight(Double.parseDouble(weight));
            personDetails.setAge(Integer.parseInt(age));
            personDatabaseHelper.addPersonDetailToDB(personDetails);
            personDatabaseHelper.close();
            Toast.makeText(this, getString(R.string.success), Toast.LENGTH_LONG).show();
        }
    }
}
