package actividadGUI;


import javax.swing.*;
import javax.swing.border.EmptyBorder;

import learningpath.activity.FormActivity;
import learningpath.question.OpenQuestion;
import show.ShowActivityWindow;
import templates.DoTemplate;

import java.awt.*;
import java.util.LinkedList;

public class DoFormActivity extends DoTemplate {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LinkedList<JTextField> answerFields;

	public DoFormActivity(ShowActivityWindow main) {
		super(main, false, false, 0);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		answerFields = new LinkedList<>();

		JPanel formPanel = new JPanel();
		formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
		formPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

		LinkedList<OpenQuestion> questions = ((FormActivity) main.getActivity()).getQuestions();

		for (OpenQuestion question : questions) {
			JPanel questionPanel = new JPanel();
			questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
			questionPanel.setBorder(new EmptyBorder(10, 0, 10, 0));

			JLabel questionLabel = new JLabel(question.getText());
			questionLabel.setFont(new Font("Arial", Font.BOLD, 14));
			questionPanel.add(questionLabel);

			JTextField answerField = new JTextField();
			answerField.setPreferredSize(new Dimension(400, 30));
			answerFields.add(answerField);
			questionPanel.add(answerField);

			formPanel.add(questionPanel);
		}

		JScrollPane scrollPane = new JScrollPane(formPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(600, 400));

		add(scrollPane, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel();
		JButton submitButton = new JButton("Send");
		submitButton.addActionListener(e -> submitForm());
		buttonPanel.add(submitButton);
		add(buttonPanel, BorderLayout.SOUTH);

		pack();
		setLocationRelativeTo(main);
		setVisible(true);
	}

	private void submitForm() {
		if (activity instanceof FormActivity) {
			FormActivity formActivity = (FormActivity) activity;

			int index = 0;
			for (OpenQuestion question : formActivity.getQuestions()) {
				String answer = answerFields.get(index).getText();
				question.setAnswer(answer);
				index++;
			}

			JOptionPane.showMessageDialog(this, "Form submitted successfully!", "Success",
					JOptionPane.INFORMATION_MESSAGE);
			((FormActivity) activity).setDone(true);
			main.updateData();
			dispose();
		}
	}

}