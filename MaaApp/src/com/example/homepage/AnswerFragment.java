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

	public static final String[] titles = new String[] { "Strawberry",
			"Banana", "Orange", "Mixed" };

	public static final String[] descriptions = new String[] {
			"It is an aggregate accessory fruit",
			"It is the largest herbaceous flowering plant", "Citrus Fruit",
			"Mixed Fruits" };

	public static final Integer[] images = { R.drawable.icon, R.drawable.icon,
			R.drawable.icon, R.drawable.icon };

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
		String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
				"Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
				"Linux", "OS/2" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				R.layout.row_layout_view, R.id.label, values);
		setListAdapter(adapter);
	}
}

/*
 * View subFragmentView; List<RowItem> rowItems;
 * 
 * @Override public View onCreateView(LayoutInflater inflater, ViewGroup
 * container, Bundle savedInstanceState) { // setting up the view View
 * subFragmentView = (View) inflater.inflate( R.layout.answerfragment_view,
 * container, false);
 * 
 * 
 * 
 * return subFragmentView;
 * 
 * }
 * 
 * 
 * @Override public void onActivityCreated(Bundle savedInstanceState) {
 * 
 * rowItems = new ArrayList<RowItem>(); for (int i = 0; i < titles.length; i++)
 * { RowItem item = new RowItem(images[i], titles[i], descriptions[i]);
 * rowItems.add(item); }
 * 
 * 
 * MyViewAdapter adapter = new MyViewAdapter(getActivity(), R.layout.list_item,
 * rowItems); setListAdapter(adapter); }
 * 
 * 
 * @Override public void onListItemClick(ListView l, View v, int position, long
 * id) { // Do something with the data
 * 
 * } }
 */

/*
 * @Override public View onCreateView(LayoutInflater inflater, ViewGroup
 * container, Bundle savedInstanceState) { // setting up the view View
 * subFragmentView = inflater.inflate(R.layout.answerfragment_view, container,
 * false); Bundle args = getArguments();
 * 
 * return subFragmentView; }
 * 
 * @Override public void onActivityCreated(Bundle savedInstanceState) {
 * super.onActivityCreated(savedInstanceState); String[] values = new String[] {
 * "Android", "iPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu",
 * "Windows7", "Max OS X", "Linux", "OS/2" }; ArrayAdapter<String> adapter = new
 * ArrayAdapter<String>(getActivity(), R.layout.row_layout_view,R.id.label,
 * values); setListAdapter(adapter); }
 * 
 * }
 */

