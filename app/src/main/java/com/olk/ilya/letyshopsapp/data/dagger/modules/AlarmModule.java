package com.olk.ilya.letyshopsapp.data.dagger.modules;

import com.olk.ilya.letyshopsapp.data.Alarm;

import dagger.Module;
import dagger.Provides;

@Module
public class AlarmModule {

    @Provides
    Alarm provideAlarm(){
        return new Alarm();
    }
}
