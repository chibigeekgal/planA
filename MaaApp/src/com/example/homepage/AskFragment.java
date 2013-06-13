package com.example.homepage;

import com.example.firstapp.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class AskFragment extends Fragment {

	public static Button re;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
    	//setting up the view 
        final View subFragmentView = inflater.inflate(R.layout.askfragment_view, container, false);
        Bundle args = getArguments();
        
        return subFragmentView;
    }
}