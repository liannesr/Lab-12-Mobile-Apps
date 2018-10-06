package com.techexchange.mobileapps.lab12;

import android.app.Notification;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.content.SharedPreferences;

public class NotificationHandler extends Handler {
    private static final String TAG = "NotificationHandler";
    private static final String SHARED_PREFS_FILE_NAME = "Contacts_SharedPrefs";
    private static String COUNT_KEY="COUNT_KEY";
    private int count;
    private Context context;

    NotificationHandler(Context context){
        this.context = context;

    }

    @Override
    public void handleMessage(Message msg){
        SharedPreferences sharedPrefs = context.getSharedPreferences(SHARED_PREFS_FILE_NAME, Context.MODE_PRIVATE);
        count = sharedPrefs.getInt(COUNT_KEY,0);
        count++;
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putInt(COUNT_KEY,count);
        editor.commit();

        Notification notification = new NotificationCompat.Builder(context, NotifierService.CHANNEL_ID).setSmallIcon(R.drawable.notification_icon)
                .setContentTitle("Ping Notification").setContentText("This is your 30-second reminder count: "+ count).build();
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(0,notification);
        Log.d(TAG, "Received message!");
        Message message = new Message();
        message.copyFrom(msg);
        this.sendMessageDelayed(message, 30000);
    }

}
