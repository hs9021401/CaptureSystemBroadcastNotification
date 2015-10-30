package com.foxlinkimage.alex.capturesystembroadcastnotification;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

public class MyNotificationListerner extends NotificationListenerService {
    Context context;
    public static final String LOG_TAG = "HAHA";
    public static final String PKGNAME_FB = "com.facebook.orca";
    public static final String PKGNAME_LINE = "jp.naver.line.android";

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        String pack = sbn.getPackageName();
        String ticker = sbn.getNotification().tickerText.toString();
        Bundle bundle = sbn.getNotification().extras;

        Log.i(LOG_TAG, "Package name = " + pack + ", ticker = " + ticker);
        for(String key:bundle.keySet())
        {
            Log.i(LOG_TAG,  "Key = " + key + ", Value = " + bundle.getString(key));
        }

        if(pack.equals(PKGNAME_FB) || pack.equals(PKGNAME_LINE))
        {
            Intent intent = new Intent("Msg");
            intent.putExtra("ticker", ticker);
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        }
    }

}
