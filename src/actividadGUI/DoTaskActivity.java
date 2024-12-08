package actividadGUI;


import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import learningpath.activity.TaskActivity;
import show.ShowActivityWindow;
import templates.DoTemplate;


public class DoTaskActivity extends DoTemplate {

	private static final long serialVersionUID = 1L;

	private JTextField taskDescriptionField;
	private JCheckBox confirmCheckBox;
	private JButton finishButton;

	public DoTaskActivity(ShowActivityWindow main) {
		super(main, true, true, 1);

		setupActivityPanel();
		setupButtonPanel();
		
		setSize(667, 200);
		centreWindow();
	}

	private void setupActivityPanel() {
		if (activityPanel != null) {
			activityPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

			JLabel taskLabel = new JLabel("Task:");
			taskDescriptionField = new JTextField();
			taskDescriptionField.setEditable(false);
			if (activity != null && activity instanceof TaskActivity) {
				taskDescriptionField.setText(((TaskActivity) activity).getToDo());
			}

			confirmCheckBox = new JCheckBox("Mark as done");
			confirmCheckBox.addActionListener(e -> toggleFinishButton());
			confirmCheckBox.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						toggleFinishButton();
					}
				}
			});

			activityPanel.add(taskLabel);
			activityPanel.add(taskDescriptionField);
			activityPanel.add(confirmCheckBox);
		}
	}

	private void setupButtonPanel() {
		if (buttonPanel != null) {
			finishButton = new JButton("Finish");
			finishButton.setEnabled(false);

			finishButton.addActionListener(e -> handleFinishAction());

			buttonPanel.add(finishButton);
		}
	}

	private void toggleFinishButton() {
		finishButton.setEnabled(confirmCheckBox.isSelected());
	}

	private void handleFinishAction() {
		if (activity != null && activity instanceof TaskActivity) {
			TaskActivity taskActivity = (TaskActivity) activity;

			taskActivity.setDone(true);
			taskDescriptionField.setText("Task completed!");
			finishButton.setEnabled(false);
			confirmCheckBox.setEnabled(false);
			dispose();
			main.updateData();
		}
	}
}
