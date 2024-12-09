package learningpath.activity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Scanner;

import learningpath.question.OpenQuestion;
import learningpath.question.MultipleOptionQuestion;

public class ExamActivity extends Activity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LinkedList<OpenQuestion> openQuestions;
	private LinkedList<MultipleOptionQuestion> MOQuestions;
	public static String TYPE = "exam";


	public ExamActivity(String title, String description, String objective, int expectedDuration, boolean mandatory,
			LinkedList<OpenQuestion> openQuestions, LinkedList<MultipleOptionQuestion> MOQuestions) {

		super(title, description, objective, expectedDuration, mandatory);
		this.openQuestions = openQuestions != null ? openQuestions : new LinkedList<>();
		this.MOQuestions = MOQuestions != null ? MOQuestions : new LinkedList<>();
	}
	
	public LinkedList<OpenQuestion> getOpenQuestions() {
		return openQuestions;
	}

	public LinkedList<MultipleOptionQuestion> getMOQuestions() {
		return MOQuestions;
	}

	public void setMOQuestion(LinkedList<MultipleOptionQuestion> mOQuestion) {
		MOQuestions = mOQuestion;
	}

	public void setOpenQuestions(LinkedList<OpenQuestion> openQuestions) {
		this.openQuestions = openQuestions;
	}

	public void addOpenQuestion(OpenQuestion question) throws NullPointerException, IllegalArgumentException {
		if (question == null) {
			throw new NullPointerException("Question can not be null.");
		}
		if (this.containsOpenQuestion(question)) {
			throw new IllegalArgumentException("Question already added.");
		}

		this.openQuestions.add(question);
	}

	public void addMOQuestion(MultipleOptionQuestion q) throws NullPointerException, IllegalArgumentException {
		if (q == null) {
			throw new NullPointerException("Question can not be null.");
		}
		if (this.containsMOQuestion(q)) {
			throw new IllegalArgumentException("Question already added.");
		}

		this.MOQuestions.add(q);
	}

	public void removeOpenQuestion(OpenQuestion question) throws IllegalArgumentException {

		if (question == null) {
			throw new NullPointerException("Question can not be null.");
		}
		if (!this.containsOpenQuestion(question)) {
			throw new IllegalArgumentException("Question not found.");
		}
		this.openQuestions.remove(question);
	}

	public void removeMOQuestion(MultipleOptionQuestion question) throws IllegalArgumentException {
		if (question == null) {
			throw new NullPointerException("Question can not be null.");
		}

		if (!this.containsMOQuestion(question)) {
			throw new IllegalArgumentException("Question not found.");
		}

		this.MOQuestions.remove(question);
	}

	public boolean containsOpenQuestion(OpenQuestion question) {
		return this.openQuestions.contains(question);
	}

	public boolean containsMOQuestion(MultipleOptionQuestion q) {
		return this.MOQuestions.contains(q);
	}

	public double rateMultipleOption(int questionRate) {
		double score = 0.0;
		if (MOQuestions.isEmpty()) {
			return 0.0;
		}
		for (MultipleOptionQuestion q : MOQuestions) {
			score += q.rate(questionRate);
		}
		return score / MOQuestions.size();
	}

	public void doActivity() {
		System.out.println("Exam Activity: " + this.title);
		System.out.println("Description: " + this.description);
		System.out.println("Objective: " + this.objective);
		System.out.println("Expected Duration: " + this.expectedDuration);
		System.out.println("Mandatory: " + this.mandatory);
		System.out.println("Questions: ");
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Doing Exam Activity");
			for (OpenQuestion q : openQuestions) {
				System.out.println(q.getText());
				System.out.println("Answer: ");
				String answer = scanner.nextLine();
				q.setAnswer(answer);
			}
			for (MultipleOptionQuestion q : MOQuestions) {
				System.out.println(q.getQuestion());
				q.showOptions();
				String answer = scanner.nextLine();
				q.setAnswer(answer);
			}

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
