package setactividadgui;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import controller.Controller;
import controller.ProfessorController;

public class CreateActivity extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JTextField titleField, descriptionField, objectiveField, durationField;
	private JCheckBox mandatoryCheckBox;
	private JComboBox<String> typeComboBox;
	private JButton createButton;
	ProfessorController controller;
	
	// TODO: Terminar de linkear con las otras ventanas

	public CreateActivity(ProfessorController controller) {
		super("Create Activity");
		this.controller = controller;

		this.setLayout(new GridLayout(7, 2, 10, 10));

		this.add(new JLabel("Title:"));
		titleField = new JTextField();
		this.add(titleField);

		this.add(new JLabel("Description:"));
		descriptionField = new JTextField();
		this.add(descriptionField);

		this.add(new JLabel("Objective:"));
		objectiveField = new JTextField();
		this.add(objectiveField);

		this.add(new JLabel("Expected Duration (in minutes):"));
		durationField = new JTextField();
		this.add(durationField);

		this.add(new JLabel("Mandatory:"));
		mandatoryCheckBox = new JCheckBox();
		this.add(mandatoryCheckBox);

		this.add(new JLabel("Type:"));
		String[] activityTypes = { "Exam", "Quiz", "True or False", "Form", "To-Do", "Check Resource" };
		typeComboBox = new JComboBox<>(activityTypes);
		this.add(typeComboBox);

		createButton = new JButton("Create");
		createButton.addActionListener(this);
		this.add(new JLabel());
		this.add(createButton);

		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		centreWindow();
		this.setVisible(true);
	}

	private void centreWindow() {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
		this.setLocation(x, y);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == createButton) {
			String title = titleField.getText();
			String description = descriptionField.getText();
			String objective = objectiveField.getText();
			String duration = durationField.getText();
			boolean mandatory = mandatoryCheckBox.isSelected();
			String type = (String) typeComboBox.getSelectedItem();

			if (title.isEmpty() || description.isEmpty() || objective.isEmpty() || duration.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Please fill in all fields.");
				return;
			}

			if (!duration.matches("\\d+")) {
				JOptionPane.showMessageDialog(this, "Duration must be a number.");
				return;
			}

			if (Integer.parseInt(duration) <= 0) {
				JOptionPane.showMessageDialog(this, "Duration must be a positive number.");
				return;
			}

			if (type.equals("Exam")) {

				controller.createExamActivity(title, description, objective, Integer.parseInt(duration), mandatory,
						null, null);
			} else if (type.equals("Quiz")) {
				controller.createQuizActivity(title, description, objective, ABORT, mandatory, null, 3.0);
			} else if (type.equals("True or False")) {
				controller.createTrueFalseActivity(title, description, objective, Integer.parseInt(duration), mandatory,
						null);
			} else if (type.equals("Form")) {
				controller.createFormActivity(title, description, objective, Integer.parseInt(duration), mandatory,
						null);
			} else if (type.equals("To-Do")) {
				controller.createTaskActivity(title, description, objective, Integer.parseInt(duration), mandatory,
						null);
			} else if (type.equals("Check Resource")) {
				controller.createResourceActivity(title, description, objective, Integer.parseInt(duration), mandatory,
						null);
			} else {
				JOptionPane.showMessageDialog(this, "Invalid activity type.");
				return;
			}

			JOptionPane.showMessageDialog(this, String.format(
					"Activity Created:\nTitle: %s\nDescription: %s\nObjective: %s\nDuration: %s minutes\nMandatory: %b\nType: %s",
					title, description, objective, duration, mandatory ? "Yes" : "No", type));
		}
	}
}