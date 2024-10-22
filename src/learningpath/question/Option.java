package learningpath.question;

public class Option {
	
	private String text;
	private boolean correct;
	private String explanation;

	public Option(String text, boolean correct, String explanation) {
		this.text = text;
		this.correct = correct;
		this.explanation = explanation;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
}
