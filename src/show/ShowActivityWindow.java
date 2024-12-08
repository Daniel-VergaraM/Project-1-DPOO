package show;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.Controller;
import learningpath.activity.Activity;
import learningpath.activity.ExamActivity;
import learningpath.activity.FormActivity;
import learningpath.activity.QuizActivity;
import learningpath.activity.ResourceActivity;
import learningpath.activity.TaskActivity;
import learningpath.activity.TrueFalseActivity;
import learningpath.question.MultipleOptionQuestion;
import learningpath.question.OpenQuestion;
import learningpath.question.Option;
import learningpath.question.TrueFalseQuestion;


// TODO: Terminar de linkear con las otras ventanas

public class ShowActivityWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private MainPanel mainPanel;
	private ButtonPanel buttonPanel;

	private LinkedList<Activity> activities = new LinkedList<>();

	private Activity previousActivity;
	public static int previousActivityIndex = 1;

	private Activity currentActivity;
	public static int currentActivityIndex = 1;

	private Activity nextActivity;
	public static int nextActivityIndex = 1;

	private Controller controller;

	
	public ShowActivityWindow(Controller controller) {
		super();
		this.controller = controller;
		loadActivities();
		currentActivity = activities.get(currentActivityIndex);
		mainPanel = new MainPanel(this);
		buttonPanel = new ButtonPanel(this);

		add(buttonPanel, BorderLayout.SOUTH);
		add(mainPanel);
		if (currentActivity != null) {
			setTitle("Bloque Neon | Ver Actividad - " + currentActivity.getId());
		} else {
			setTitle("Bloque Neon | Ver Actividad");
		}

		setPreferredSize(new Dimension(800, 600));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		pack();
		repaint();
		centreWindow();
		setResizable(false);
		setVisible(true);
	}

	private void loadActivities() {
		controller.getActivityHashMap().values().forEach(activity -> {
			activities.add(activity);
		});
		if (activities.isEmpty()) {
			return;
		}
		if (activities.size() == 1) {
			previousActivity = activities.get(--previousActivityIndex);
			currentActivity = activities.get(--currentActivityIndex);
			nextActivity = activities.get(--nextActivityIndex);
			return;
		} else if (activities.size() == 2) {
			previousActivity = activities.get(--previousActivityIndex);
			currentActivity = activities.get(currentActivityIndex);
			nextActivity = activities.get(--nextActivityIndex);
			return;
		}
		previousActivity = activities.get(--previousActivityIndex);
		currentActivity = activities.get(currentActivityIndex);
		nextActivity = activities.get(nextActivityIndex++);
		loadFollowUps(currentActivity);
		loadPrev(currentActivity);
	}

	public Activity getActivity() {
		return currentActivity;
	}

	public void setActivity(Activity activity) {
		this.currentActivity = activity;
	}
	
	public Controller getController() {
		return controller;
	}
	
	public void setController(Controller controller) {
		this.controller = controller;
	}


	private void loadFollowUps(Activity activity) {
	    if (activity == null || activities.contains(activity)) {
	        return;
	    }

	    Stack<Activity> stack = new Stack<>();
	    stack.push(activity);

	    while (!stack.isEmpty()) {
	        Activity current = stack.pop();

	        if (current == null || activities.contains(current)) {
	            continue;
	        }

	        activities.add(current);

	        for (Activity followUp : current.getFollowUpActivities()) {
	            if (followUp != null && !activities.contains(followUp)) {
	                stack.push(followUp);
	            }
	        }
	    }
	}

	
	public void loadPrev(Activity activity) {
		if (activity == null || activities.contains(activity)) {
			return;
		}
		
		Stack<Activity> stack = new Stack<>();
		stack.push(activity);
		
		while (!stack.isEmpty()) {
			Activity current = stack.pop();

			if (current == null || activities.contains(current)) {
				continue;
			}

			activities.addFirst(current);

			for (Activity followUp : current.getPrerequisites()) {
				if (followUp != null && !activities.contains(followUp)) {
					stack.push(followUp);
				}
			}
		}
	}

	public void addActivity(Activity activity) {
		activities.add(activity);
	}

	public void previousActivity() {
		if (currentActivityIndex > 0 && !activities.isEmpty()) {
			nextActivityIndex = currentActivityIndex;
			currentActivityIndex = previousActivityIndex;
			previousActivityIndex = currentActivityIndex - 1;

			nextActivity = activities.get(nextActivityIndex);
			currentActivity = activities.get(currentActivityIndex);
			previousActivity = previousActivityIndex >= 0 ? activities.get(previousActivityIndex) : null;

			setTitle("Bloque Neon | Actividad - " + currentActivity.getId());
			mainPanel.updateDetails();
			repaint();
		} else {
			JOptionPane.showMessageDialog(this, "No hay una actividad anterior.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void nextActivity() {
		if (currentActivityIndex < activities.size() - 1 && !activities.isEmpty()) {
			previousActivityIndex = currentActivityIndex;
			currentActivityIndex = nextActivityIndex;
			nextActivityIndex = currentActivityIndex + 1;

			previousActivity = activities.get(previousActivityIndex);
			currentActivity = activities.get(currentActivityIndex);
			nextActivity = nextActivityIndex < activities.size() ? activities.get(nextActivityIndex) : null;

			setTitle("Bloque Neon | Actividad - " + currentActivity.getId());
			mainPanel.updateDetails();
			repaint();
		} else {
			JOptionPane.showMessageDialog(this, "No hay una actividad siguiente.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void updateData() {
		mainPanel.updateDetails();
	}

	public void close() {
		this.dispose();
	}

	private void centreWindow() {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
		this.setLocation(x, y);
	}

	public static void main(String[] args) {
		String object = new String();
		ResourceActivity resourceActivity = new ResourceActivity("Titulo", "Descripción", "Objetivo", 900, false,
				"https://google.com");
		ResourceActivity resourceActivity2 = new ResourceActivity("Titulo 2", "Descripción2", "Objetivo2", 900, true,
				"https://youtube.com");
		ResourceActivity resourceActivity3 = new ResourceActivity("Titulo 3", "Descripción3", "Objetivo3", 900, true,
				"https://bing.com");
		TaskActivity taskActivity = new TaskActivity("Titulo 4", "Descripción", "Objetivo", 900, true, "URL");
		ExamActivity exam = new ExamActivity("Titulo 5", "Descripción", "Objetivo", 900, true, null, null);
		exam.addOpenQuestion(new OpenQuestion("Mondongo"));
		MultipleOptionQuestion multipleOptionQuestion = new MultipleOptionQuestion("Mondongo", null);
		Option[] options = new Option[4];
		options[0] = new Option("Mondongo1", true, "xd");
		options[1] = new Option("Mondongo2", false, "xd");
		options[2] = new Option("Mondongo3", false, "xd");
		options[3] = new Option("Mondongo4", false, "xd");
		multipleOptionQuestion.setOptions(options);
		exam.addMOQuestion(multipleOptionQuestion);
		resourceActivity.addFollowUp(resourceActivity2);
		resourceActivity2.addPrerequisite(resourceActivity3);
		resourceActivity3.addFollowUp(resourceActivity2);
		resourceActivity.addFollowUp(resourceActivity3);
		resourceActivity.addFollowUp(taskActivity);
		taskActivity.addFollowUp(exam);
		exam.addPrerequisite(taskActivity);
		FormActivity formActivity = new FormActivity("Titulo", "Descripción", "Objetivo", 900, true, null);
		formActivity.addQuestion(new OpenQuestion("Mondongo"));
		formActivity.addQuestion(new OpenQuestion("Mondongo2"));
		formActivity.addQuestion(new OpenQuestion("Mondongo3"));
		formActivity.addQuestion(new OpenQuestion("Mondongo4"));
		//exam.addFollowUp(formActivity);
		//formActivity.addPrerequisite(exam);
		QuizActivity quiz = new QuizActivity("Titulo", "Descripción", "Objetivo", 900, true, null, 3.0);
		quiz.addQuestion(multipleOptionQuestion);
		TrueFalseActivity trueFalseActivity = new TrueFalseActivity("Titulo", "Descripción", "Objetivo", 900, true, null);
		TrueFalseQuestion question = new TrueFalseQuestion("Mondongo", null, null);
		question.setOptions(options[0], options[1]);
		trueFalseActivity.addQuestion(question);
		TrueFalseQuestion question2 = new TrueFalseQuestion("Pan con huevo", null, null);
		Option[] options2 = new Option[2];
		options2[0] = new Option("Si", true, "xd");
		options2[1] = new Option("No", false, "xd");
		question2.setOptions(options2[0], options2[1]);
		trueFalseActivity.addQuestion(question2);
		//ShowActivityWindow showActivityWindow = new ShowActivityWindow(object, trueFalseActivity);
	}

}