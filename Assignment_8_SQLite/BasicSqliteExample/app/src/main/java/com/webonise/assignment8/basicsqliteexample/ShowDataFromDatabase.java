package com.webonise.assignment8.basicsqliteexample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

/**
 * Created by webonise on 13/8/15.
 */
public class ShowDataFromDatabase extends Activity {
    TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showdatafromdatabase);
        initializeView();

    }
    public void initializeView() {

         tvData = (TextView) findViewById(R.id.tvData);
        FormDBHelper formDBHelper = new FormDBHelper(this);
        List<FormDB> formDBList = formDBHelper.getDetail();

        for( FormDB form : formDBList) {
            tvData.append(getString(R.string.id)+" : " +form.getId());
            tvData.append(getString(R.string.name)+" : " +form.getName());
            tvData.append(getString(R.string.age)+" : " +form.getAge());
            tvData.append(getString(R.string.weight)+" : " +form.getWeight());
            tvData.append(getString(R.string.height)+" : " +form.getHeight() +" \n\n");
        }

    }
}
