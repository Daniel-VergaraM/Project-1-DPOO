package learningpath.activity;

import java.util.LinkedList;
import java.util.Scanner;

import learningpath.question.MultipleOptionQuestion;
import learningpath.question.Option;

public class QuizActivity extends Activity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double minScore;
	private LinkedList<MultipleOptionQuestion> questions;
	public String TYPE = "quiz";


	public QuizActivity(String title, String description, String objective, int expectedDuration, boolean mandatory,
						LinkedList<MultipleOptionQuestion> questions, double minScore) {
		super(title, description, objective, expectedDuration, mandatory);
		this.questions = questions != null ? questions : new LinkedList<>();
		this.minScore = minScore;
	}

	public double getMinScore() {
		return minScore;
	}

	public void setMinScore(double minScore) {
		this.minScore = minScore;
	}

	public LinkedList<MultipleOptionQuestion> getQuestions() {
		return questions;
	}

	public void setQuestions(LinkedList<MultipleOptionQuestion> questions) {
		this.questions = questions;
	}

	public void addQuestion(MultipleOptionQuestion question) throws NullPointerException, IllegalArgumentException {
		if (question == null) {
			throw new NullPointerException("Question can not be null");
		}
		if (this.containsQuestion(question)) {
			throw new IllegalArgumentException("Question already added to the quiz.");
		}
		this.questions.add(question);
	}
	
	public void removeQuestion(MultipleOptionQuestion question) throws NullPointerException, IllegalArgumentException {
		
		if (question == null) {
			throw new NullPointerException("Question can not be null.");
		}
		
		if (!this.containsQuestion(question)) {
			throw new IllegalArgumentException("Question not found.");
		}
		questions.remove(question);
	}
	
	public boolean containsQuestion(MultipleOptionQuestion question) {
		return this.questions.contains(question);
	}
	
	public double calculateScore(int questionRate) {
		double score = 0;
		if (questions.isEmpty()) {
            return 0.0;
		}
		for (MultipleOptionQuestion question : questions) {
			score += question.rate(questionRate);
		}
		return score / questions.size();
	}
	
	public void doActivity() {
		System.out.println("Quiz Activity: " + this.title);
		System.out.println("Description: " + this.description);
		System.out.println("Objective: " + this.objective);
		System.out.println("Expected Duration: " + this.expectedDuration);
		System.out.println("Mandatory: " + this.mandatory);
		System.out.println("Minimum Score: " + this.minScore);
		System.out.println("Questions: ");
		try (Scanner scanner = new Scanner(System.in)) {
			for (MultipleOptionQuestion question : questions) {
			    System.out.println(question.getQuestion());
			    question.showOptions();
			    System.out.println("Answer: ");
			    String answer = scanner.nextLine();
			    question.setAnswer(answer);
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
