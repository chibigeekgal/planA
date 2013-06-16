package beans;

public class Answer_bean {
	private int answerIndex, questionIndex;
	private String contributor, content;

	public Answer_bean(int questionIndex, int answerIndex, String contributor,
			String content) {
		super();
		this.answerIndex = answerIndex;
		this.questionIndex = questionIndex;
		this.contributor = contributor;
		this.content = content;
	}

	public int getAnswerIndex() {
		return answerIndex;
	}

	public int getQuestionIndex() {
		return questionIndex;
	}

	public String getContributor() {
		return contributor;
	}

	public String getContent() {
		return content;
	}

}
