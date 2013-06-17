package homepage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.BitmapResultHandler;
import main.ServerConnector;
import model.Question;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.firstapp.R;

public class QuestionViewAdapter extends ArrayAdapter<Question> {
	Map<String,Bitmap> iconmap;
	Context context;

	public QuestionViewAdapter(Context context, int resourceId,
			List<Question> items) {
		super(context, resourceId, items);
		this.context = context;
		iconmap=new HashMap<String,Bitmap>();
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewStruct holder = null;
		Question QuestionItem = getItem(position);

		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.question_item, null);
			holder = new ViewStruct();
			holder.usename = (TextView) convertView.findViewById(R.id.username);
			holder.title = (TextView) convertView.findViewById(R.id.title);
			holder.personal_pic = (ImageView) convertView
					.findViewById(R.id.icon);

			convertView.setTag(holder);
		} else
			holder = (ViewStruct) convertView.getTag();
		
		String username = QuestionItem.getUser();
		holder.usename.setText(username);
		holder.title.setText(QuestionItem.getTitle());
		if(iconmap.containsKey(username)){
			holder.personal_pic.setImageBitmap(iconmap.get(username));
		}else{
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("Request", "get_icon"));
		pairs.add(new BasicNameValuePair("Login", QuestionItem.getUser()));
		new ServerConnector((Activity) context, "/person", pairs,
				new IconResultHandler(username,holder.personal_pic))
				.connect();
		}
		return convertView;
	}
	
	private class ViewStruct {
		ImageView personal_pic;
		TextView title;
		TextView usename;
	}
	
	private class IconResultHandler extends BitmapResultHandler {
		private ImageView icon;
		private String username;
		private IconResultHandler(String username,ImageView icon) {
			super();
			this.icon = icon;
			this.username=username;
		}

		@Override
		protected void processBitmapResults(Bitmap results) {
			icon.setImageBitmap(results);
			iconmap.put(username,results);
		}

	}
}
