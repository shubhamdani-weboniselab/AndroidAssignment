package com.webonise.assignment7.basicsharedprefrence;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by webonise on 12/8/15.
 */
public class FileFromData extends Activity {
      TextView tvData;
      String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filefromdata);
        tvData = (TextView)findViewById(R.id.tvData);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        data = bundle.getString(Constents.DATA_FROM_FILE);
        tvData.setText(data);
    }
}
