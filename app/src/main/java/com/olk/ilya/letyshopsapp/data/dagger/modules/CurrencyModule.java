package com.olk.ilya.letyshopsapp.data.dagger.modules;

import com.olk.ilya.letyshopsapp.domain.Currency;

import dagger.Module;
import dagger.Provides;


@Module
public class CurrencyModule {

    @Provides
    Currency providesCurrenc(){
        return new Currency();
    }
}
