package com.olk.ilya.letyshopsapp.data.dagger;

import android.support.v4.app.Fragment;

import com.olk.ilya.letyshopsapp.data.Alarm;
import com.olk.ilya.letyshopsapp.data.ParserTask;
import com.olk.ilya.letyshopsapp.data.SaveAndLoad;
import com.olk.ilya.letyshopsapp.data.dagger.modules.AlarmModule;
import com.olk.ilya.letyshopsapp.data.dagger.modules.CurrencyModule;
import com.olk.ilya.letyshopsapp.data.dagger.modules.SaveAndLoadModule;
import com.olk.ilya.letyshopsapp.presentation.CurrencyListFragment;
import com.olk.ilya.letyshopsapp.presentation.LoadFragment;

import dagger.Component;


@Component(modules = {
        SaveAndLoadModule.class,
        AlarmModule.class,
        CurrencyModule.class
})
public interface AppComponent {
    public void inject(Alarm alarm);

    public void inject(CurrencyListFragment c);

    public void inject(LoadFragment l);

    public void inject(ParserTask p);

    public void inject(SaveAndLoad s);
}
