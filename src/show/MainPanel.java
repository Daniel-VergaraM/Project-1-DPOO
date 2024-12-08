package show;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import actividadGUI.DoExamActivity;
import actividadGUI.DoFormActivity;
import actividadGUI.DoQuizActivity;
import actividadGUI.DoResourceActivity;
import actividadGUI.DoTaskActivity;
import actividadGUI.DoTrueFalseActivity;
import learningpath.activity.Activity;
import learningpath.activity.ExamActivity;
import learningpath.activity.FormActivity;
import learningpath.activity.QuizActivity;
import learningpath.activity.ResourceActivity;
import learningpath.activity.TaskActivity;
import learningpath.activity.TrueFalseActivity;
import templates.DoTemplate;



public class MainPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private ShowActivityWindow main;

	private DetailsPanel panelIzquierda;
	private IconFollowUpPanel panelDerecha;
	private JButton doButton;
	private static Dimension dim = new Dimension(100, 30);
	private String OPEN_WINDOW = "OPEN_WINDOW";
	private DoTemplate doTemplate;

	public MainPanel(ShowActivityWindow main) {
		super();
		this.main = main;
		panelIzquierda = new DetailsPanel(this);
		panelDerecha = new IconFollowUpPanel(this);
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 25));
		doButton = new JButton("Do");
		doButton.setActionCommand(OPEN_WINDOW);
		doButton.addActionListener(this);
		doButton.setPreferredSize(dim);
		doButton.setSize(dim);
		doButton.setMaximumSize(dim);
		panel.add(doButton);
		panelIzquierda.add(panel, BorderLayout.SOUTH);
		this.updateDetails();
		setLayout(new FlowLayout());
		add(panelIzquierda, BorderLayout.EAST);
		add(panelDerecha, BorderLayout.WEST);

		setVisible(true);
		repaint();
	}

	public void updateDetails() {
		panelIzquierda.setActivity();
		panelDerecha.updateData();
	}

	public ShowActivityWindow getMain() {
		return main;
	}

	public void setMain(ShowActivityWindow main) {
		this.main = main;
	}

	public void close() {
		this.main.dispose();
	}

	public void previousActivity() {
		this.main.previousActivity();
	}

	public void nextActivity() {
		this.main.nextActivity();
	}

	public JButton getDoButton() {
		return doButton;
	}

	public void closeEvent() {
		this.main.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		if (command.equals(OPEN_WINDOW)) {
			Activity activity = main.getActivity();

			if (activity != null) {
				for (Activity a : activity.getPrerequisites()) {
					System.out.println(a.getTitle() + " is done: " + a.isDone());
				}
				boolean prerequisitesFulfilled = activity.getPrerequisites().stream().allMatch(Activity::isDone);

				if (!prerequisitesFulfilled) {
					JOptionPane.showMessageDialog(this,
							"You cannot start this activity because the prerequisites have not been fulfilled.",
							"Prerequisites Not Met", JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (activity instanceof ExamActivity) {
					doTemplate = new DoExamActivity(main);
				} else if (activity instanceof ResourceActivity) {
					doTemplate = new DoResourceActivity(main);
				} else if (activity instanceof TaskActivity) {
					doTemplate = new DoTaskActivity(main);
				} else if (activity instanceof FormActivity) {
					doTemplate = new DoFormActivity(main);
				} else if (activity instanceof QuizActivity) {
					doTemplate = new DoQuizActivity(main);
				} else if (activity instanceof TrueFalseActivity) {
					doTemplate = new DoTrueFalseActivity(main);
				} else {
					JOptionPane.showMessageDialog(this, "Activity not implemented yet.", "Not Implemented",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		}
	}
}