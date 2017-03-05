package com.olk.ilya.letyshopsapp.data.dagger.modules;

import com.olk.ilya.letyshopsapp.data.SaveAndLoad;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class SaveAndLoadModule {

    @Provides
    SaveAndLoad provideSaveAndLoad(){
        return new SaveAndLoad();
    }

}
