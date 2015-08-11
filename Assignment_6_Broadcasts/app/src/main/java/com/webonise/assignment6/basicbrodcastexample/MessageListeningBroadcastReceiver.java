package com.webonise.assignment6.basicbrodcastexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import java.util.Objects;

/**
 * Created by webonise on 11/8/15.
 */
public class MessageListeningBroadcastReceiver extends BroadcastReceiver {
    final SmsManager smsManager = SmsManager.getDefault();

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle intentExtras = intent.getExtras();
        if (intentExtras != null) {
            Object[] sms = (Object[]) intentExtras.get(Constants.SMS_BUNDLE);
            String smsMassageString = "";

            for (int index = 0; index < sms.length; index++) {
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) sms[index]);

                String smsBody = smsMessage.getMessageBody().toString();
                String smsSender = smsMessage.getOriginatingAddress();
                Log.v(Constants.SENDER, smsSender);
                Log.v(Constants.MSG, smsBody);
            }
        }
    }


}
