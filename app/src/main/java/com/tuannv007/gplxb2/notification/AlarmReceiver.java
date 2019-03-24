package com.tuannv007.gplxb2.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.tuannv007.gplxb2.MainActivity;

/**
 * Created by Jaison on 17/06/17.
 */
public class AlarmReceiver extends BroadcastReceiver {
    String TAG = "AlarmReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        if (intent.getAction() != null && context != null) {
            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
                // Set the alarm here.
                Log.d(TAG, "onReceive: BOOT_COMPLETED");
                NotificationScheduler
                    .setReminder(context, AlarmReceiver.class, 20,
                        10);
                return;
            }
        }
        Log.d(TAG, "onReceive: ");
        //Trigger the notification
        NotificationScheduler.showNotification(context, MainActivity.class,
            "You have 5 unwatched videos", "Watch them now?");
    }
}


