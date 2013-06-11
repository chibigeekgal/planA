package beans;

public class Question_bean {

	private String owner;
	private int index;
	private int bestAnswer;
	private String title;
	private String content;

	public Question_bean(String owner, String title, String content, int index,
			int bestAnswer) {
		this.index = index;
		this.title = title;
		this.content = content;
		this.owner = owner;
		this.bestAnswer = bestAnswer;
	}

	public String getOwner() {
		return owner;
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

	public int getBestAnswer() {
		return bestAnswer;
	}

	@Override
	public String toString() {
		return null;
	}

}
