package actividadGUI;


import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.LinkedList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import learningpath.activity.TrueFalseActivity;
import learningpath.question.TrueFalseQuestion;
import show.ShowActivityWindow;
import templates.DoTemplate;



public class DoTrueFalseActivity extends DoTemplate {

	private static final long serialVersionUID = 1L;

	private JLabel questionLabel;
	private JRadioButton optionTrue, optionFalse;
	private ButtonGroup answerGroup;
	private JButton previousButton, nextButton, sendButton;

	private int currentQuestionIndex;
	private final LinkedList<TrueFalseQuestion> questions;
	private final LinkedList<String> answers;

	public DoTrueFalseActivity(ShowActivityWindow main) {
		super(main, false, false, 0);
		this.questions = ((TrueFalseActivity) activity).getQuestions();
		this.answers = new LinkedList<>();
		this.currentQuestionIndex = 0;

		for (int i = 0; i < questions.size(); i++) {
			answers.add("");
		}

		setSize(600, 250);
		centreWindow();

		initializeCustomPanel();
		displayCurrentQuestion();
	}

	private void initializeCustomPanel() {
		JPanel questionPanel = new JPanel(new BorderLayout(10, 10));

		questionLabel = new JLabel("", JLabel.CENTER);
		questionLabel.setFont(new Font("Arial", Font.BOLD, 18));
		questionPanel.add(questionLabel, BorderLayout.NORTH);

		JPanel answerPanel = new JPanel(new GridLayout(2, 1, 10, 10));
		answerGroup = new ButtonGroup();

		optionTrue = new JRadioButton();
		optionFalse = new JRadioButton();

		answerGroup.add(optionTrue);
		answerGroup.add(optionFalse);

		answerPanel.add(optionTrue);
		answerPanel.add(optionFalse);

		questionPanel.add(answerPanel, BorderLayout.CENTER);

		add(questionPanel, BorderLayout.CENTER);

		initializeButtonPanel();
	}

	private void initializeButtonPanel() {
		JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));

		previousButton = new JButton("Previous");
		nextButton = new JButton("Next");
		sendButton = new JButton("Send");

		previousButton.addActionListener(e -> navigateToPreviousQuestion());
		nextButton.addActionListener(e -> navigateToNextQuestion());
		sendButton.addActionListener(e -> submitActivity());

		buttonPanel.add(previousButton);
		buttonPanel.add(nextButton);
		buttonPanel.add(sendButton);

		add(buttonPanel, BorderLayout.SOUTH);
	}

	private void displayCurrentQuestion() {
		// Get the current question
		TrueFalseQuestion currentQuestion = questions.get(currentQuestionIndex);

		// Update question label and radio button text
		questionLabel.setText(currentQuestion.getQuestion());
		optionTrue.setText(currentQuestion.getOptions()[0].getText());
		optionFalse.setText(currentQuestion.getOptions()[1].getText());

		// Restore the previously saved answer if any
		answerGroup.clearSelection();
		String savedAnswer = answers.get(currentQuestionIndex);
		if (savedAnswer.equals(optionTrue.getText())) {
			optionTrue.setSelected(true);
		} else if (savedAnswer.equals(optionFalse.getText())) {
			optionFalse.setSelected(true);
		}

		updateButtonStates();
	}

	private void navigateToPreviousQuestion() {
		saveAnswer();
		if (currentQuestionIndex > 0) {
			currentQuestionIndex--;
			displayCurrentQuestion();
		}
	}

	private void navigateToNextQuestion() {
		saveAnswer();
		if (currentQuestionIndex < questions.size() - 1) {
			currentQuestionIndex++;
			displayCurrentQuestion();
		}
	}

	private void saveAnswer() {
		if (optionTrue.isSelected()) {
			answers.set(currentQuestionIndex, optionTrue.getText());
		} else if (optionFalse.isSelected()) {
			answers.set(currentQuestionIndex, optionFalse.getText());
		}
	}

	private void submitActivity() {
		saveAnswer();

		for (int i = 0; i < questions.size(); i++) {
			questions.get(i).setAnswer(answers.get(i));
		}

		((TrueFalseActivity) activity).setDone(true);
		main.updateData();

		System.out.println("Activity submitted!");
		dispose();
	}

	private void updateButtonStates() {
		previousButton.setEnabled(currentQuestionIndex > 0);
		nextButton.setEnabled(currentQuestionIndex < questions.size() - 1);
	}
}