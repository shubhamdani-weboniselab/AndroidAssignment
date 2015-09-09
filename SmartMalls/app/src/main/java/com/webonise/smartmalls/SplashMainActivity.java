package com.webonise.smartmalls;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;


public class SplashMainActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_main);
        showProcessDialog();
        JsonObjectRequest jsonObjectRequest = getJsonObjectRequest();
        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }

    private void showProcessDialog() {
        progressDialog = new ProgressDialog(SplashMainActivity.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle(getString(R.string.loading));
        progressDialog.setMessage(getString(R.string.loading_data));
        progressDialog.setCancelable(true);
        progressDialog.setIndeterminate(false);
        progressDialog.setMax(100);
        progressDialog.setProgress(0);
        progressDialog.show();
    }

    private JsonObjectRequest getJsonObjectRequest() {
        return new JsonObjectRequest(Constants.URL, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                //later put the jsonObject in intent
                Intent startMainScreen = new Intent(SplashMainActivity.this, AllOfferCardView.class).putExtra(Constants.SERVER_DATA, Constants.tempJsonResponse);
                startActivity(startMainScreen);
                progressDialog.dismiss();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e(getString(R.string.volly_error), "");
                Toast.makeText(getBaseContext(), getString(R.string.intenet_connectivity_error), Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
                finish();
//                Intent startMainScreen = new Intent(SplashMainActivity.this, AllOfferCardView.class).putExtra(Constants.SERVER_DATA, Constants.tempJsonResponse);
//                startActivity(startMainScreen);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
