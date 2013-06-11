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

        re = (Button) subFragmentView.findViewById(R.id.reAsk);
		re.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				HomePageActivity.string = ((TextView) subFragmentView.findViewById(R.id.q_content)).getText().toString();
				new Page(HomePageActivity.string).execute("http://146.169.52.3:59999/image");
			}
			
		});
    	HomePageActivity.image = (ImageView) subFragmentView.findViewById(R.id.imageView1);
        return subFragmentView;
    }
}