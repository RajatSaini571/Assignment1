package com.test.assignment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class BodyFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_body, container, false);

        String strtext = getArguments().getString("body");
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView tv_text = view.findViewById(R.id.tv_text);
        tv_text.setText(strtext);
        return view;
    }
}