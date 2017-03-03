package com.olk.ilya.letyshopsapp.data;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import static com.olk.ilya.letyshopsapp.data.Constants.FILENAME;

/**
 * Created by Илья on 02.03.2017.
 */

public class Alarm extends BroadcastReceiver{

    SaveAndLoad mSaveAndLoad = new SaveAndLoad();
    @Override
    public void onReceive(Context context, Intent intent) {
       mSaveAndLoad.saveFile(context, FILENAME);
    }

    public void setAlarm(Context context){
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, Alarm.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }
}
