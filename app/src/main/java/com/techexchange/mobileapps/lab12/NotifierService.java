package com.techexchange.mobileapps.lab12;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.app.Service;
import android.os.IBinder;

public class NotifierService extends Service{//IntentService {
    private static final String TAG = "NotifierService";
    static final String CHANNEL_ID = NotifierService.class.getName() + "pingchannel";
    private static final String CHANNEL_NAME = "30-sec ping";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        createNotificationChannel();
        NotifierThread thread = new NotifierThread(this);
        thread.start();
        thread.getLooper();
        return START_STICKY;//START_NOT_STICKY;//super.onStartCommand(intent,flags,startId);
    }

    @Override
    public IBinder onBind(Intent intent){
        return null;
    }

    static Intent newIntent(Context context){
        return new Intent(context, NotifierService.class);
    }

    private void createNotificationChannel(){
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID,CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
        channel.setDescription("This is the main channel for the thirty-second timer");
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }
//    @Override
//    protected void onHandleIntent(Intent intent){
//        if(intent==null)
//            return;
//        Log.d(TAG, "Received Intent!");
//
//    }
//
    //    public NotifierService(){
//        super(TAG);
//    }



}
