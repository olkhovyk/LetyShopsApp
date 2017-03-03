package com.olk.ilya.letyshopsapp.presentation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.olk.ilya.letyshopsapp.R;
import com.olk.ilya.letyshopsapp.data.SaveAndLoad;
import static com.olk.ilya.letyshopsapp.data.Constants.FILENAME;

/**
 * Created by Илья on 02.03.2017.
 */

public class LoadFragment extends Fragment {

    private TextView mTextView;
    private SaveAndLoad mSaveAndLoad;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_load_file, container, false);
        mTextView = (TextView) view.findViewById(R.id.load_from_file);
        mTextView.setMovementMethod(new ScrollingMovementMethod());
        mSaveAndLoad = new SaveAndLoad();
        mSaveAndLoad.openFile(getContext() ,mTextView, FILENAME);
        return view;
    }
}
