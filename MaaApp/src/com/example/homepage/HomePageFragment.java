package com.example.homepage;
 

import com.example.firstapp.R;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class HomePageFragment extends Fragment{

	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View homePageView = inflater.inflate(R.layout.homepage_structure, container, false);
        
        
        //select a picture 
        homePageView.findViewById(R.id.personal_profile)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), PersonalProfile.class);
                        startActivity(intent);
                    }
                });

        
   
        
        return homePageView;
    }

	
}
