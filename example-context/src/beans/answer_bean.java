package beans;
public class answer_bean {
	
	private Question_bean question;
	private String contributor;
	private String content;

	public answer_bean(Question_bean question, String contributor,
			String content) {
		this.question = question;
		this.contributor = contributor;
		this.content = content;
	}

	public String getContributor() {
		return contributor;
	}

	public Question_bean getQuestion() {
		return question;
	}

	public String getContent() {
		return content;
	}
}
