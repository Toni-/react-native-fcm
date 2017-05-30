package com.evollu.react.fcm;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.List;
import java.util.Map;

/**
 * Created by nahkaton on 29/05/2017.
 */

public class Util {
    public static final int PENDING_INTENT_REQUEST_CODE = 0;

    public static boolean appIsRunning(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> processInfoList = activityManager.getRunningAppProcesses();
        final String appPackageName = context.getPackageName();

        if (processInfoList != null) {
            for (ActivityManager.RunningAppProcessInfo processInfo : processInfoList) {
                if (processInfo.processName.equals(appPackageName) && processInfo.importance
                        != ActivityManager.RunningAppProcessInfo.IMPORTANCE_SERVICE) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void buildNotification(Context context, String title, String id, String message, String type) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_notification)
                .setPriority(Notification.PRIORITY_MAX)
                .setAutoCancel(true)
                .setContentTitle(title)
                .setContentText(message)
                .setVibrate(new long[]{0, 300})
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentIntent(getNotificationIntent(context, type, id, title));
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());
    }

    private static PendingIntent getNotificationIntent(Context context, String type, String id, String name) {
        PackageManager packageManager = context.getPackageManager();

        // Intent for this application
        Intent intent = packageManager.getLaunchIntentForPackage(context.getPackageName());
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // This data needs to be handled in MainActivity.java
        intent.putExtra("notificationType", type);
        intent.putExtra("senderId", id);
        intent.putExtra("senderName", name);

        return PendingIntent.getActivity(
                context,
                PENDING_INTENT_REQUEST_CODE,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
    }
}
