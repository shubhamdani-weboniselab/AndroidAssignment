package com.webonise.assignment7.basicsharedprefrence;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import java.io.OutputStreamWriter;


public class MainScreenActivity extends Activity implements View.OnClickListener {

    Button btnEditFile;
    Button btnDeleteFile;
    Button btnShowDataFile;
    Button btnAppendFile;
    Button btnCreateFile;
    EditText etInputFiled;
    EditText tvEditPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        initializeView();
    }


    private void initializeView() {
        btnAppendFile = (Button) findViewById(R.id.btnAppend);
        btnEditFile = (Button) findViewById(R.id.btnEdit);
        btnDeleteFile = (Button) findViewById(R.id.btnDelete);
        btnShowDataFile = (Button) findViewById(R.id.btnShowData);
        btnCreateFile = (Button) findViewById(R.id.btnCreate);
        etInputFiled = (EditText) findViewById(R.id.etInputField);
        tvEditPanel = (EditText) findViewById(R.id.tvEditPanel);


        btnAppendFile.setOnClickListener(this);
        btnDeleteFile.setOnClickListener(this);
        btnShowDataFile.setOnClickListener(this);
        btnEditFile.setOnClickListener(this);
        btnCreateFile.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (isEditTextEmpty()) {
            String filename = getFileNameFromEditText();
            switch (viewId) {
                case R.id.btnCreate:
                    createThisFile(filename);
                    break;
                case R.id.btnEdit:
                    editThisFile(filename);
                    break;
                case R.id.btnDelete:
                    deleteThisFile(filename);
                    break;
                case R.id.btnShowData:
                    showDataFromFile(filename);
                    break;
                case R.id.btnAppend:
                    appendThisFile(filename);
                    break;
            }
        } else {
            Toast.makeText(this, getString(R.string.editTextEmpty), Toast.LENGTH_LONG).show();
        }

    }

    private void createThisFile(String filename) {

        if (fileExistCheck(filename)) {
            Toast.makeText(this, getString(R.string.fileExist), Toast.LENGTH_LONG).show();
        } else {
            try {
                File newFile = new File(Environment.getExternalStorageDirectory() + File.separator + filename);
                newFile.createNewFile();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void deleteThisFile(String filename) {
        if (fileExistCheck(filename)) {
            try {
                File file = new File(Environment.getExternalStorageDirectory() + File.separator + filename);
                file.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, getString(R.string.filenotpresent), Toast.LENGTH_LONG).show();
        }
    }

    private void editThisFile(String filename) {
        if (fileExistCheck(filename)) {
            try {
                File file = new File(Environment.getExternalStorageDirectory() + File.separator + filename);
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                outputStreamWriter.write(getTextFromPanel());
                outputStreamWriter.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            createThisFile(filename);
            editThisFile(filename);
        }
    }

    private void appendThisFile(String filename) {
        if (fileExistCheck(filename)) {
            try {
                File file = new File(Environment.getExternalStorageDirectory() + File.separator + filename);
                FileOutputStream fileOutputStream = new FileOutputStream(file, true);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                outputStreamWriter.append(getTextFromPanel());
                outputStreamWriter.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            createThisFile(filename);
            appendThisFile(filename);
        }

    }

    private void showDataFromFile(String filename) {
        String data = "";
        String line = "";
        if (fileExistCheck(filename)) {
            try {
                File file = new File(Environment.getExternalStorageDirectory() + File.separator + filename);
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                try {
                    while ((line = bufferedReader.readLine()) != null) {
                        data += line;

                    }
                    Toast.makeText(this, data, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainScreenActivity.this, FileFromData.class);
                    intent.putExtra(Constents.DATA_FROM_FILE, data);
                    startActivity(intent);
                } catch (Exception e) {

                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            Toast.makeText(this, getString(R.string.filenotpresent), Toast.LENGTH_LONG).show();
        }
    }

    private boolean isEditTextEmpty() {
        String str;
        str = etInputFiled.getText().toString();

        if (TextUtils.isEmpty(str)) {

            return false;
        } else {
            return true;
        }

    }

    private String getFileNameFromEditText() {
        String filename = "";
        filename = etInputFiled.getText().toString();
        return filename;
    }

    private boolean fileExistCheck(String filename) {
        File file = new File(Environment.getExternalStorageDirectory() + File.separator + filename);
        return file.exists();
    }

    private String getTextFromPanel() {
        String text = "";
        text = tvEditPanel.getText().toString();
        if (TextUtils.isEmpty(text)) {
            Toast.makeText(this, getString(R.string.EmptyPanel), Toast.LENGTH_LONG).show();
        }
        return text;
    }


}
