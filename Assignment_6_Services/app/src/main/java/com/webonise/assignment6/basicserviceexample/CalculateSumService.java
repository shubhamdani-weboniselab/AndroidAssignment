package com.webonise.assignment6.basicserviceexample;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by webonise on 11/8/15.
 */
public class CalculateSumService extends Service {
    private final String Tag = getClass().getSimpleName();
    private int input;
    private int sum;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle bundle = new Bundle();
        bundle = intent.getExtras();
        input = bundle.getInt(Constents.inputNumber);
        calculateSum(input);
        Log.v(Tag, getStingResources("inputIs") + input);
        Log.v(Tag, getStingResources("sumIs") + sum);
        stopSelf();

        return super.onStartCommand(intent, flags, startId);
    }


    public void calculateSum(int input) {
        for (int index = 1; index <= input; index++) {
            sum += index;
        }

    }

    public String getStingResources(String message) {
        String stringResource = "";
        switch (message) {
            case "inputIs":
                stringResource = getResources().getString(R.string.inputIs);
                break;
            case "sumIs":
                stringResource = getResources().getString(R.string.sumIs);
                break;

        }
        return stringResource;
    }
}
