package model;

import java.io.Serializable;

import main.Library;
import android.graphics.Bitmap;

import com.google.gson.JsonObject;

public class Question implements Serializable {
	private static final long serialVersionUID = 1L;
	Integer index, bestAnswer;
	private String user, title;
	private Bitmap content;
	private Bitmap icon;

	public Question(int index, int bestAnswer, String user, String title) {
		super();
		this.index = index;
		this.bestAnswer = bestAnswer;
		this.user = user;
		this.title = title;
		getIconFromServer();
	}

	public Question(JsonObject o) {
		user = Library.convertToString(o.get("username"));
		title = Library.convertToString(o.get("title"));
		index = o.get("index").getAsInt();
		bestAnswer = o.get("best_answer").getAsInt();
		getIconFromServer();
	}

	public void getContentFromServer() {

	}

	public Integer getIndex() {
		return index;
	}

	public Integer getBestAnswer() {
		return bestAnswer;
	}

	public String getUser() {
		return user;
	}

	public String getTitle() {
		return title;
	}

	public Bitmap getContent() {
		return content;
	}

	public Bitmap getPersonalIcon() {
		return icon;
	}

	private void getIconFromServer() {

	}
}
