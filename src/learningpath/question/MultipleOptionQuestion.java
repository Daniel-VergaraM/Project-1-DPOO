package learningpath.question;

import java.io.Serializable;
import java.util.LinkedList;


import java.io.Serializable;
import java.util.LinkedList;

public class MultipleOptionQuestion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String question;
	private String answer;
	private Option[] options;
	private Option selectedOption;

	public MultipleOptionQuestion(String question, Option[] options) {
		this.question = question;
		this.answer = "";
		this.options = new Option[4];
		if (options != null) {
			for (int i = 0; i < options.length && i < 4; i++) {
				this.options[i] = options[i];
			}
		}
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Option[] getOptions() {
		return options;
	}

	public void setOptions(Option[] options) {
		this.options = new Option[4];
		if (options != null) {
			for (int i = 0; i < options.length && i < 4; i++) {
				this.options[i] = options[i];
			}
		}
	}
	
	public Option getSelectedOption() {
		return selectedOption;
	}
	
	public String getSelectedOptionText() {
		if (selectedOption != null) {
			return selectedOption.getText();
		}
		return "";
	}
	
	public void setSelectedOption(String text) {
		for (Option option : options) {
			if (option != null && option.getText().equals(text)) {
				selectedOption = option;
				return;
			}
		}
		
		throw new IllegalArgumentException("Option not found.");
	}

	public void addOption(Option option) throws NullPointerException, IllegalArgumentException {
		if (option == null) {
			throw new NullPointerException("Option can not be null.");
		}
		if (this.containsOption(option)) {
			throw new IllegalArgumentException("Option already added.");
		}

		add(option);
	}

	public void removeOption(Option option) throws NullPointerException, IllegalArgumentException {
		if (option == null) {
			throw new NullPointerException("Option can not be null.");
		}
		if (!this.containsOption(option)) {
			throw new IllegalArgumentException("Option not found.");
		}

		remove(option);
	}

	public boolean containsOption(Option option) {
		for (Option o : options) {
			if (o != null && o.equals(option)) {
				return true;
			}
		}
		return false;
	}

	public void showOptions() {
		for (Option option : options) {
			if (option != null) {
				System.out.println(option.getText());
			}
		}
	}

	private void add(Option option) {
		for (int i = 0; i < options.length; i++) {
			if (options[i] == null) {
				options[i] = option;
				return;
			}
		}
		throw new IllegalStateException("Cannot add more than 4 options.");
	}

	private void remove(Option option) {
		for (int i = 0; i < options.length; i++) {
			if (options[i] != null && options[i].equals(option)) {
				options[i] = null;
				return;
			}
		}
		throw new IllegalArgumentException("Option not found.");
	}

	public double rate(int questionRate) {
		double rate = 0;
		int correctCount = 0;

		for (Option option : options) {
			if (option != null && option.isCorrect()) {
				rate += questionRate;
				correctCount++;
			}
		}
		return correctCount > 0 ? rate / correctCount : 0;
	}
}