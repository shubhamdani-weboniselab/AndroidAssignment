package com.webonise.smartmalls;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by webonise on 3/9/15.
 */
public class GeofenceController {

    private final String TAG = GeofenceController.class.getName();
    private Context context;
    private GoogleApiClient googleApiClient;
    private Gson gson;
    private SharedPreferences preferences;

    private List<GeoFence> geoFenceList;

    private static GeofenceController INSTANCE;

    public static GeofenceController getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new GeofenceController();
        }
        return INSTANCE;
    }
    public List<GeoFence> getGeoFenceList() {
        return geoFenceList;
    }

    public void init(Context context) {
        this.context = context.getApplicationContext();
        geoFenceList = new ArrayList<>();
        gson = new Gson();
        preferences = this.context.getSharedPreferences("StoredGeoFences",Context.MODE_PRIVATE);

    }
    private void connectWithCallBacks(GoogleApiClient.ConnectionCallbacks callbacks) {

        googleApiClient = new GoogleApiClient.Builder(context)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(callbacks)
                .addOnConnectionFailedListener(connectionFailedListener)
                .build();
        googleApiClient.connect();

    }
    private GoogleApiClient.ConnectionCallbacks connectionAddListener =
            new GoogleApiClient.ConnectionCallbacks() {
                @Override
                public void onConnected(Bundle bundle) {

                }

                @Override
                public void onConnectionSuspended(int i) {

                }
            };

    private GoogleApiClient.OnConnectionFailedListener connectionFailedListener =
            new GoogleApiClient.OnConnectionFailedListener() {
                @Override
                public void onConnectionFailed(ConnectionResult connectionResult) {

                }
            };

    private GeofencingRequest getGeofencingRequest( List<Geofence> mGeofenceList) {
        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
        builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER);
        builder.addGeofences(mGeofenceList);
        return builder.build();
    }



}
