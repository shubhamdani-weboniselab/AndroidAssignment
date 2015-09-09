package com.webonise.smartmalls;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import utils.CoOdrinates;

/**
 * Created by webonise on 1/9/15.
 */
public class AllOfferCardView extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener,ResultCallback<Status>{
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private String jsonResponse;
    private GsonBuilder builder;
    private Gson gson;
    private List<Geofence> geofenceList;
    private PendingIntent geofencePendingIntent;
    private GoogleApiClient googleApiClient;

    /*Call Back Methods*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_offer_card_view);
        initializeViews();
        buildGoogleApiClient();
        setAdapter();
        setToolBar();
    }

    private void setToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getTitle());
        setSupportActionBar(toolbar);
    }


    /* functions */
    private void initializeViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        jsonResponse = getIntent().getExtras().getString("ServerData");
        builder = new GsonBuilder();
        gson = builder.create();
    }

    protected synchronized void buildGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.start_listening_beacon) {
            Intent beaconIntent = new Intent(this,BeaconListenerActivity.class);
            startActivity(beaconIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void setAdapter() {
        JsonDataParser jsonDataParser = new JsonDataParser();
        jsonDataParser = gson.fromJson(jsonResponse, JsonDataParser.class);
        final int arraySize = jsonDataParser.getOffers().size();
        String[] urlArray = new String[arraySize];
        String[] DescriptionArray = new String[arraySize];
        String[] DiscountArray = new String[arraySize];
        CoOdrinates[] coOrdinateArray = new CoOdrinates[arraySize];
        geofenceList = new ArrayList<>();

        for (int i = 0; i < jsonDataParser.getOffers().size(); i++) {
            urlArray[i] = jsonDataParser.getOffers().get(i).getUrl().trim();
            DescriptionArray[i] = jsonDataParser.getOffers().get(i).getDescription();
            DiscountArray[i] = jsonDataParser.getOffers().get(i).getDiscountPercentage();
            coOrdinateArray[i] = jsonDataParser.getOffers().get(i).getCoOrdinates();
            geofenceList.add(new Geofence.Builder()
                            .setRequestId(jsonDataParser.getOffers().get(i).getName())
                            .setCircularRegion(coOrdinateArray[i].getLatitude(), coOrdinateArray[i].getLongitude(), 20)
                            .setExpirationDuration(Geofence.NEVER_EXPIRE).setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER | Geofence.GEOFENCE_TRANSITION_EXIT)
                            .build()

            );

        }

        adapter = new TempAdapter(DiscountArray, urlArray, DescriptionArray, coOrdinateArray, this);
        recyclerView.setAdapter(adapter);

    }

    private PendingIntent getGeofencePendingIntent() {

        if (geofencePendingIntent != null) {
            return geofencePendingIntent;
        } else {
            Intent intent = new Intent(this, GeofenceTransitionService.class);
            return PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        }
    }

    private GeofencingRequest getGeofencingRequest() {
        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
        builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER);
        builder.addGeofences(geofenceList);
        return builder.build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        googleApiClient.disconnect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d("","onConnectionFailed");

    }

    @Override
    public void onConnected(Bundle bundle) {
    Log.d("","onConnected, Bundle :" +bundle);
        LocationServices.GeofencingApi.addGeofences(googleApiClient,getGeofencingRequest(),getGeofencePendingIntent()).setResultCallback(this);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d("","onConnectionSuspended");

    }
    @Override
    public void onResult(Status status) {

        Log.d("OnReSult:" ,"" +status);

    }
}

