package com.example.homepage;

import com.example.firstapp.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class AskFragment extends Fragment {

    public static final String TITLE = "section_number";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
    	//setting up the view 
        View subFragmentView = inflater.inflate(R.layout.askfragment_view, container, false);
        Bundle args = getArguments();
        
       /* ((TextView) rootView.findViewById(android.R.id.text1)).setText(
                getString(R.string.dummy_section_text, args.getInt(ARG_SECTION_NUMBER)));*/
        return subFragmentView;
    }
}