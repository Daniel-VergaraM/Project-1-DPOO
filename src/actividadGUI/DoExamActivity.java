package actividadGUI;


import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import learningpath.activity.ExamActivity;
import learningpath.question.MultipleOptionQuestion;
import learningpath.question.OpenQuestion;
import learningpath.question.Option;
import show.ShowActivityWindow;
import templates.DoTemplate;



public class DoExamActivity extends DoTemplate {

	private static final long serialVersionUID = 1L;

	private JLabel questionLabel;
	private JRadioButton optionA, optionB, optionC, optionD;
	private ButtonGroup answerGroup;
	private JTextField openAnswerField;
	private JButton previousButton, nextButton, sendButton;

	private ExamActivity examActivity;
	private int currentQuestionIndex;
	private boolean isOpenQuestion;

	public DoExamActivity(ShowActivityWindow main) {
		super(main, false, false, 0);
		this.examActivity = (ExamActivity) activity;
		this.currentQuestionIndex = 0;
		setSize(600, 300);
		centreWindow();

		initializeCustomPanel();
		displayCurrentQuestion();
	}

	private void initializeCustomPanel() {

		JPanel questionPanel = new JPanel(new BorderLayout(10, 10));

		questionLabel = new JLabel("", JLabel.CENTER);
		questionLabel.setFont(new Font("Arial", Font.BOLD, 18));
		questionPanel.add(questionLabel, BorderLayout.NORTH);

		JPanel answerPanel = new JPanel(new GridLayout(4, 1, 10, 10));
		answerGroup = new ButtonGroup();

		optionA = new JRadioButton();
		optionB = new JRadioButton();
		optionC = new JRadioButton();
		optionD = new JRadioButton();

		answerGroup.add(optionA);
		answerGroup.add(optionB);
		answerGroup.add(optionC);
		answerGroup.add(optionD);

		answerPanel.add(optionA);
		answerPanel.add(optionB);
		answerPanel.add(optionC);
		answerPanel.add(optionD);

		questionPanel.add(answerPanel, BorderLayout.CENTER);

		openAnswerField = new JTextField();
		openAnswerField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					navigateToNextQuestion();
				}
			}
		});
		openAnswerField.setVisible(false);
		questionPanel.add(openAnswerField, BorderLayout.SOUTH);

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
		sendButton.addActionListener(e -> submitExam());

		buttonPanel.add(previousButton);
		buttonPanel.add(nextButton);
		buttonPanel.add(sendButton);

		add(buttonPanel, BorderLayout.SOUTH);
	}

	private void displayCurrentQuestion() {

		answerGroup.clearSelection();

		LinkedList<OpenQuestion> openQuestions = ((ExamActivity) activity).getOpenQuestions();
		LinkedList<MultipleOptionQuestion> moQuestions = ((ExamActivity) activity).getMOQuestions();

		if (currentQuestionIndex < openQuestions.size()) {

			isOpenQuestion = true;
			OpenQuestion question = openQuestions.get(currentQuestionIndex);
			questionLabel.setText(question.getText());

			openAnswerField.setVisible(true);
			openAnswerField.setText(question.getAnswer());
			optionA.setVisible(false);
			optionB.setVisible(false);
			optionC.setVisible(false);
			optionD.setVisible(false);

		} else {
			isOpenQuestion = false;
			MultipleOptionQuestion question = moQuestions.get(currentQuestionIndex - openQuestions.size());
			questionLabel.setText(question.getQuestion());

			openAnswerField.setVisible(false);

			Option[] options = question.getOptions();
			optionA.setVisible(options[0] != null);
			optionB.setVisible(options[1] != null);
			optionC.setVisible(options[2] != null);
			optionD.setVisible(options[3] != null);

			optionA.setText(options[0] != null ? options[0].getText() : "");
			optionB.setText(options[1] != null ? options[1].getText() : "");
			optionC.setText(options[2] != null ? options[2].getText() : "");
			optionD.setText(options[3] != null ? options[3].getText() : "");

			String savedAnswer = question.getAnswer();
			if (savedAnswer.equals(optionA.getText()))
				optionA.setSelected(true);
			else if (savedAnswer.equals(optionB.getText()))
				optionB.setSelected(true);
			else if (savedAnswer.equals(optionC.getText()))
				optionC.setSelected(true);
			else if (savedAnswer.equals(optionD.getText()))
				optionD.setSelected(true);
		}

		updateButtonStates();
	}

	private void navigateToPreviousQuestion() {
		if (currentQuestionIndex > 0) {
			currentQuestionIndex--;
			displayCurrentQuestion();
		}
	}

	private void navigateToNextQuestion() {
		int totalQuestions = ((ExamActivity) activity).getOpenQuestions().size()
				+ ((ExamActivity) activity).getMOQuestions().size();
		if (currentQuestionIndex < totalQuestions - 1) {
			currentQuestionIndex++;
			displayCurrentQuestion();
		}
	}

	private void updateButtonStates() {
		int totalQuestions = ((ExamActivity) activity).getOpenQuestions().size()
				+ ((ExamActivity) activity).getMOQuestions().size();
		previousButton.setEnabled(currentQuestionIndex > 0);
		nextButton.setEnabled(currentQuestionIndex < totalQuestions - 1);
	}

	private void submitExam() {
		LinkedList<OpenQuestion> openQuestions = ((ExamActivity) activity).getOpenQuestions();
		LinkedList<MultipleOptionQuestion> moQuestions = ((ExamActivity) activity).getMOQuestions();

		if (isOpenQuestion && currentQuestionIndex < openQuestions.size()) {
			OpenQuestion question = openQuestions.get(currentQuestionIndex);
			question.setAnswer(openAnswerField.getText());
		} else if (!isOpenQuestion) {
			MultipleOptionQuestion question = moQuestions.get(currentQuestionIndex - openQuestions.size());
			if (optionA.isSelected())
				question.setAnswer(optionA.getText());
			else if (optionB.isSelected())
				question.setAnswer(optionB.getText());
			else if (optionC.isSelected())
				question.setAnswer(optionC.getText());
			else if (optionD.isSelected())
				question.setAnswer(optionD.getText());
		}

		((ExamActivity) activity).setDone(true);
		main.updateData();

		System.out.println("Exam submitted!");
		dispose();
	}
}