package com.olk.ilya.letyshopsapp.presentation;


import android.support.v4.app.Fragment;


/**
 * Created by Илья on 02.03.2017.
 */

public class LoadFileActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {
        return new LoadFragment();
    }
}
