package show;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.HashSet;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

import learningpath.activity.Activity;


public class FollowUpDisplayPanel extends JPanel {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	private JScrollPane scrollPane;
	private IconFollowUpPanel main;
	private DefaultListModel<String> model;
	private HashSet<String> followUpActivities = new HashSet<>();
	private JList<String> list;

	public FollowUpDisplayPanel(IconFollowUpPanel main) {
		super();
		this.main = main;
		
		JPanel panel = new JPanel(new GridLayout(1, 1, 100, 100));
		panel.setPreferredSize(new Dimension(8 * 33, 5 * 33));
		panel.setBorder(new TitledBorder("Follow Up Activities"));
		model = new DefaultListModel<>();
		loadFollowUpActivities();
		list = new JList<>(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		scrollPane = new JScrollPane(list);
		scrollPane.setPreferredSize(new Dimension(8 * 33, 5 * 33));
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panel.add(scrollPane);
		add(panel, BorderLayout.WEST);
		repaint();
		setVisible(true);
	}

	public void loadFollowUpActivities() {
		Activity currentActivity = main.getMain().getMain().getActivity();
		if (currentActivity == null) {
			return;
		}
		followUpActivities.clear();
		for (Activity activity : currentActivity.getFollowUpActivities()) {
			followUpActivities.add(activity.getTitle());
		}
		model.clear();
		for (String activity : followUpActivities) {
			model.addElement(activity);
		}
		repaint();
	}
}
