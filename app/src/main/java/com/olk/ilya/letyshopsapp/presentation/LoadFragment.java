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
import com.olk.ilya.letyshopsapp.data.dagger.App;

import javax.inject.Inject;

import static com.olk.ilya.letyshopsapp.data.Constants.FILENAME;

public class LoadFragment extends Fragment {

    @Inject SaveAndLoad mSaveAndLoad;

    private TextView mTextView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        App.getComponent().inject(this);
        View view = inflater.inflate(R.layout.activity_load_file, container, false);
        initTextView(view);
        return view;
    }

    private void initTextView(View v){
        mTextView = (TextView) v.findViewById(R.id.load_from_file);
        mTextView.setMovementMethod(new ScrollingMovementMethod());
        String s = mSaveAndLoad.openFile(getContext(), FILENAME);
        if(s.length() > 0){
            mTextView.setText(s);
        } else {
            mTextView.setText("File is empty");
        }
    }
}
