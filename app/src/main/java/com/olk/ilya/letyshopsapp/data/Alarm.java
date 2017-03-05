package com.olk.ilya.letyshopsapp.data;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.olk.ilya.letyshopsapp.data.dagger.App;
import com.olk.ilya.letyshopsapp.domain.CurrencyMap;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import static com.olk.ilya.letyshopsapp.data.Constants.FILENAME;


public class Alarm extends BroadcastReceiver{
    @Inject
    SaveAndLoad mSaveAndLoad;
    private Logger LOGGER = Logger.getLogger(Alarm.class.getName());


    public Alarm() {
        App.getComponent().inject(this);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        mSaveAndLoad.saveFile(context, FILENAME, CurrencyMap.getInstance().getCurrencyMap());
        LOGGER.log(Level.SEVERE, "Starting sсhedule");
    }

    public void setAlarm(Context context){
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, Alarm.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        LOGGER.log(Level.SEVERE, "Alarm manager has been switch on");

    }
}
