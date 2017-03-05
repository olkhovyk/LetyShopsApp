package com.olk.ilya.letyshopsapp.data.dagger;

import android.app.Application;

public class App extends Application {

    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.create();
    }

    public static AppComponent getComponent(){
        return component;
    }
}
