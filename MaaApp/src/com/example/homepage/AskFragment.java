package com.example.homepage;

import com.example.firstapp.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class AskFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
    	//setting up the view 
        View subFragmentView = inflater.inflate(R.layout.askfragment_view, container, false);
        Bundle args = getArguments();
        
        return subFragmentView;
    }
}