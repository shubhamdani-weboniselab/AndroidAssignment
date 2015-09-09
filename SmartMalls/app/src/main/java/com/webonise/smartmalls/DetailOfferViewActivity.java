package com.webonise.smartmalls;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Locale;

/**
 * Created by webonise on 1/9/15.
 */
public class DetailOfferViewActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imageView;
    private TextView tvDescription;
    private TextView tvDiscount;
    private Button btnNavigation;
    private LocationManager manager;
    private String provider;
    private Location location;
    private boolean isGpsEnable;
    private boolean isNetworkEnable;
    private double userLongitude;
    private double userLatitude;
    private double destinationLongitude;
    private double destinationLatitude;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_offer_view_activity);
        initializeView();
        Bundle givenDataByPreviousActivity = getIntent().getExtras();
        populateView(givenDataByPreviousActivity);

    }

    private void initializeView() {
        imageView = (ImageView) findViewById(R.id.detailImage);
        tvDescription = (TextView) findViewById(R.id.tvDetailText);
        tvDiscount = (TextView) findViewById(R.id.tvDiscount);
        btnNavigation = (Button) findViewById(R.id.btnNavigate);
        btnNavigation.setOnClickListener(this);
    }

    private void populateView(Bundle givenDataByPreviousActivity) {
        tvDiscount.setText(givenDataByPreviousActivity.getString("Discount"));
        tvDescription.setTextSize(20);
        tvDescription.setText(givenDataByPreviousActivity.getString("Description").toString());
        Log.d("description ", "" + givenDataByPreviousActivity.getString("Description"));
        destinationLatitude = givenDataByPreviousActivity.getDouble("latitude");
        destinationLongitude = givenDataByPreviousActivity.getDouble("longitude");
        Picasso.with(this).load(givenDataByPreviousActivity.getString("url")).into(imageView);
    }

    public void openGoogleMapsForNavigation(Context context) {

        manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        isGpsEnable = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        isNetworkEnable = manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (!isGpsEnable && !isNetworkEnable) {
        } else {
            if (isNetworkEnable) {
                if (manager != null) {
                    location = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    if (location != null) {
                        userLongitude = location.getLongitude();
                        userLatitude = location.getLatitude();
                    }
                }


            }
            if (isGpsEnable) {
                if (location == null) {
                    Log.d("GPS Enabled", "GPS Enabled");
                    if (manager != null) {
                        location = manager
                                .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if (location != null) {
                            userLatitude = location.getLatitude();
                            userLongitude = location.getLongitude();
                        }
                    }
                }
            }
        }

        String uri = String.format(Locale.ENGLISH,
         "http://maps.google.com/maps?saddr=%f,%f(%s)&daddr=%f,%f(%s)",
                (float) userLatitude, (float) userLongitude, "Your Location", destinationLatitude, destinationLongitude ,"Mall");
        Log.d("long",""+destinationLongitude);
        Log.d("latitude",""+destinationLatitude);
        Log.d("userlatitude",""+userLatitude);
        Log.d("userlong",""+userLongitude);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        openGoogleMapsForNavigation(getApplicationContext());
    }
}

