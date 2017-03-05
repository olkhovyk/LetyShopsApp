package com.olk.ilya.letyshopsapp.presentation;

import android.support.v4.app.Fragment;

public class LoadFileActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new LoadFragment();
    }
}
