package com.olk.ilya.letyshopsapp.presentation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.olk.ilya.letyshopsapp.R;
import com.olk.ilya.letyshopsapp.data.Alarm;
import com.olk.ilya.letyshopsapp.data.ParserTask;

public abstract class SingleFragmentActivity extends FragmentActivity implements ParserTask.Callback{
    protected abstract Fragment createFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        setTheme(R.style.AppTheme);
        new ParserTask(this).execute();
    }

    @Override
    public void afterLoadCurrency(Void someResult) {
        FragmentManager fm = getSupportFragmentManager();

        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if(fragment == null){
            fragment = createFragment();
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }
    }
}
