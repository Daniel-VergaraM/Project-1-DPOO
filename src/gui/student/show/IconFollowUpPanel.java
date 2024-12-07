package gui.student.show;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import logic.learningpath.activity.*;

public class IconFollowUpPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IconDisplayPanel iconDisplay;
	private FollowUpDisplayPanel followUpDisplay;

	private MainPanel main;

	public IconFollowUpPanel(MainPanel main) {
		super();
		this.main = main;
		iconDisplay = new IconDisplayPanel("task");
		followUpDisplay = new FollowUpDisplayPanel(this);
		setLayout(new GridLayout(2, 1, 66, 33));

		add(iconDisplay);
		add(followUpDisplay);
		repaint();

	}
	
	public MainPanel getMain() {
		return main;
	}

	public void updateData() {
		Map<Class<?>, String> activityIconMap = Map.of(ResourceActivity.class, "resource", TaskActivity.class, "task",
				QuizActivity.class, "quiz", FormActivity.class, "form", TrueFalseActivity.class, "truefalse",
				ExamActivity.class, "exam");

		Object activity = main.getMain().getActivity();
		if (activity == null) {
			iconDisplay.setImage("none");
			return;
		}
		String icon = activityIconMap.get(activity.getClass());

		if (icon != null) {
			iconDisplay.setImage(icon);
		} else {
			iconDisplay.setImage("none");
		}

		followUpDisplay.loadFollowUpActivities();
		repaint();
	}

	public void updateFollowUp() {
		main.updateDetails();
		followUpDisplay.loadFollowUpActivities();
		iconDisplay.setImage(main.getMain().getActivity().getType());
		repaint();
	}

	public void setImage(String type) {
		iconDisplay.setImage(type);
	}

}
