package com.webonise.shubham.android.assignment3.basicuidesign;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

public class LoginActivity extends ActionBarActivity implements View.OnClickListener {
    private EditText etName;
    private EditText etLastName;
    private EditText etMobileNumber;
    private EditText etEmail;
    private RadioGroup rgGender;
    private RadioButton rbutton;
    private CheckBox cbSubscription;
    private Button btnSubmit;
    private Spinner dropdown;
    private String category = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        initializeView();


        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void FormValidation() {
        String name = "", lastname = "", email = "", mobileno = "", gender = "";
        boolean subscription = false;
        if (etName.getText() != null) {
            name = etName.getText().toString();

            if (etLastName.getText() != null) {
                lastname = etLastName.getText().toString();
                if (etEmail.getText() != null) {
                    email = etEmail.getText().toString();
                    if (etMobileNumber.getText() != null) {
                        mobileno = etMobileNumber.getText().toString();
                        if (cbSubscription.isChecked()) {
                            subscription = true;
                            int radioButtonId = rgGender.getCheckedRadioButtonId();
                            if (radioButtonId != 0) {
                                rbutton = (RadioButton) findViewById(radioButtonId);
                                gender = rbutton.getText().toString();
                            }
                        } else {
                            Toast.makeText(getBaseContext(), "Check The Subscription", Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(getBaseContext(), "Enter the Number Properly", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(getBaseContext(), "Enter the Email Properly", Toast.LENGTH_LONG).show();
                }

            } else {
                Toast.makeText(getBaseContext(), "Enter the LastName Properly", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(getBaseContext(), "Enter the Name Properly", Toast.LENGTH_LONG).show();
        }
        Log.d("Result--> ", " Name: " + name + " Lastname: " + lastname + " Email: " + email + " Number: " + mobileno + " gender: " + gender + " category: " + category + " subscribed: " + subscription);

    }

    public void initializeView() {
        etName = (EditText) findViewById(R.id.etName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etMobileNumber = (EditText) findViewById(R.id.etNumber);
        etEmail = (EditText) findViewById(R.id.etEmail);
        rgGender = (RadioGroup) findViewById(R.id.rgGender);
        cbSubscription = (CheckBox) findViewById(R.id.cbSubscription);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        dropdown = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.category, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);
        btnSubmit.setOnClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSubmit:
                FormValidation();
                break;

        }
    }
}
