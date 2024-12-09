package learningpath.activity;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Scanner;

import learningpath.question.TrueFalseQuestion;

public class TrueFalseActivity extends Activity implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;
	private LinkedList<TrueFalseQuestion> questions;
	public String TYPE = "truefalse";

	
	public TrueFalseActivity(String title, String description, String objective, int expectedDuration, boolean mandatory,
			LinkedList<TrueFalseQuestion> questions) {
		super(title, description, objective, expectedDuration, mandatory);
		this.questions = questions != null ? questions : new LinkedList<>();
	}

	public LinkedList<TrueFalseQuestion> getQuestions() {
		return questions;
	}

	public void setQuestions(LinkedList<TrueFalseQuestion> questions) {
		this.questions = questions;
	}
	
	public void addQuestion(TrueFalseQuestion question) throws NullPointerException, IllegalArgumentException{
		if (question == null) {
			throw new NullPointerException("La pregunta no puede ser nula");
		}
		
		if (this.containsQuestion(question)) {
			throw new IllegalArgumentException("La pregunta ya existe");
		}
		
		questions.add(question);
	}
	
	public void removeQuestion(TrueFalseQuestion question) throws NullPointerException, IllegalArgumentException {
		if (question == null) {
			throw new NullPointerException("La pregunta no puede ser nula");
		}
		
		if (!this.containsQuestion(question)) {
			throw new IllegalArgumentException("La pregunta no existe");
		}

		questions.remove(question);
	}
	
	public boolean containsQuestion(TrueFalseQuestion question) {
		return questions.contains(question);
	}
	
	public void doActivity() {
		System.out.println("True / False Activity: " + this.title);
		System.out.println("Description: " + this.description);
		System.out.println("Objective: " + this.objective);
		System.out.println("Expected Duration: " + this.expectedDuration);
		System.out.println("Mandatory: " + this.mandatory);
		System.out.println("Questions: ");
		try (Scanner scanner = new Scanner(System.in)) {
			for (TrueFalseQuestion q : questions) {
			    System.out.println(q.getQuestion());
			    q.showOptions();
			    System.out.println("Answer: ");
			    String answer = scanner.nextLine();
			    q.setAnswer(answer);
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
		
	}

}
