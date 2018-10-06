package com.techexchange.mobileapps.lab12;

import android.os.HandlerThread;
import android.os.Handler;
import android.content.Context;

public class NotifierThread extends HandlerThread {
    private Handler handler;
    private Context context;
    private static final String TAG = "NotifierThread";

    NotifierThread(Context context){
        super(TAG);
        this.context = context;
    }

    @Override
    protected void onLooperPrepared(){
        super.onLooperPrepared();
        handler = new NotificationHandler(context);
        handler.obtainMessage(0,null).sendToTarget();
    }
}
