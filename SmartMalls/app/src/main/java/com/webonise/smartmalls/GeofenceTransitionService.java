package com.webonise.smartmalls;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.util.List;

/**
 * Created by webonise on 3/9/15.
 */
public class GeofenceTransitionService extends IntentService {
    public GeofenceTransitionService(String name) {
        super(name);
    }


    @Override
    protected void onHandleIntent(Intent intent) {

        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        if(geofencingEvent.hasError()) {
            Log.d("Error on GEofencing ","" + geofencingEvent.getErrorCode());
        }
        int geofenceTransition = geofencingEvent.getGeofenceTransition();

        if(geofenceTransition== Geofence.GEOFENCE_TRANSITION_ENTER || geofenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT){

            List triggeringGeofences = geofencingEvent.getTriggeringGeofences();

            String detailOfTransition = triggeringGeofences.get(0).toString();

            sendNotification(detailOfTransition);
        }

    }

    private void sendNotification(String detailOfTransition) {
        NotificationManager notificationManager = (NotificationManager)getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification.Builder(this)
                .setContentTitle(detailOfTransition)
                .setContentText("Hell Yeah")
                .build();
        notificationManager.notify(0,notification);
    }

}
