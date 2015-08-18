package com.webonise.assignment9.basiclistview;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class ListViewActivity extends Activity implements View.OnClickListener, PersonListAdapter.CallBack {
    private Button btnAdd;
    private ListView listView;
    private PersonListAdapter listAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        initializeViews();
    }

    public void initializeViews() {
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, PersonDetailFormActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        PersonDatabaseHelper personDatabaseHelper = new PersonDatabaseHelper(this);
        listView = (ListView) findViewById(R.id.activityListView);
        listAdapter = new PersonListAdapter(this, R.layout.person_list_item, personDatabaseHelper.getPersonDetailFromDB());
        listView.setAdapter(listAdapter);
        personDatabaseHelper.close();

    }

    @Override
    public void onDeleted() {

        PersonDatabaseHelper personDatabaseHelper = new PersonDatabaseHelper(this);
        listView = (ListView) findViewById(R.id.activityListView);
        listAdapter = new PersonListAdapter(this, R.layout.person_list_item, personDatabaseHelper.getPersonDetailFromDB());
        listView.setAdapter(listAdapter);
        personDatabaseHelper.close();

    }
}
