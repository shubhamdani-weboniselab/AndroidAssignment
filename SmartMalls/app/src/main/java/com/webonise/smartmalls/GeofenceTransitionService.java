package com.webonise.smartmalls;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.util.List;

/**
 * Created by webonise on 3/9/15.
 */
public class GeofenceTransitionService extends IntentService {

    private NotificationManager notificationManager;

    public GeofenceTransitionService() {
        super("GeofenceTransitionService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Log.w("Service CREATEAD", "");

    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.w("Service START", "");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Log.w("Service started onhandle", "");
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        if (geofencingEvent.hasError()) {
            Log.d("Service Error on GEofencing ", "" + geofencingEvent.getErrorCode());
        }
        int geofenceTransition = geofencingEvent.getGeofenceTransition();

        if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER || geofenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT) {

            Log.d("Service Entering Occured", "");
            List triggeringGeofences = geofencingEvent.getTriggeringGeofences();

            String detailOfTransition = triggeringGeofences.get(0).toString();

            sendNotification(detailOfTransition);
        }

    }

    private void sendNotification(String detailOfTransition) {
        Log.d("Service inside sending Notificaiton","");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle(detailOfTransition)
                .setContentText("helllo").setSmallIcon(R.drawable.ic_launcher)
                .setAutoCancel(true);

        Intent intent = new Intent(this, AllOfferCardView.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(AllOfferCardView.class);
        stackBuilder.addNextIntent(intent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        notificationManager.notify((int)System.currentTimeMillis(), builder.build());
    }

}
