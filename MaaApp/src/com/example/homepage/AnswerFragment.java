package com.example.homepage;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.firstapp.R;

public class AnswerFragment extends ListFragment {

	public static final String[] titles = new String[] { "dummy",
			"dummy", "dummy", "dummy" };

	public static final String[] descriptions = new String[] {
			"It is an Q",
			"It is an Q", "It is an Q",
			"It is an Q" };

	public static final Integer[] images = { R.drawable.icon, R.drawable.icon,
			R.drawable.icon, R.drawable.icon };
	
	ListView listView;
    List<RowItem> rowItems;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View subFragmentView = inflater.inflate(R.layout.answerfragment_view,
				container, false);

		return subFragmentView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	
		
		 rowItems = new ArrayList<RowItem>();
	        for (int i = 0; i < titles.length; i++) {
	            RowItem item = new RowItem(images[i], titles[i], descriptions[i]);
	            rowItems.add(item);
	        }
	        
	    listView = (ListView) getView().findViewById(android.R.id.list);
	    MyViewAdapter adapter = new MyViewAdapter(getActivity(),R.layout .list_item,rowItems);
	    listView.setAdapter(adapter);
	    
		
	}
}


