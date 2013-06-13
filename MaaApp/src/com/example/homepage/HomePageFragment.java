package com.example.homepage;
 

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.firstapp.R;

public class HomePageFragment extends Fragment{

	
	String[] qs = {"What's the definition of a Group?", "What's the meaning of life?", "Is 1 + 1 actually 2?"};
	
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
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		 ListView l = (ListView) getView().findViewById(android.R.id.list);
         ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
        		 android.R.layout.simple_list_item_1, qs);
         if(l != null)
 		l.setAdapter(adapter);
	}
	
}
