package beans;
import java.util.LinkedList;

public class Question_bean {

	private User_bean owner;
	private int index;
	private int best_answer;
	private String title;
	private String content;
	private LinkedList<answer_bean> answers;

	public Question_bean(User_bean owner, String title, String content,
			int index) {
		this.index = index;
		answers = new LinkedList<answer_bean>();
		this.title = title;
		this.content = content;
	}

	public User_bean getOwner() {
		return owner;
	}

	public LinkedList<answer_bean> getAnswers() {
		return answers;
	}

	public String getContent() {
		return content;
	}

	public String getTitle() {
		return title;
	}

	public int getIndex() {
		return index;
	}

	public void addAnswer(answer_bean answer) {
		answers.add(answer);
	}
}
