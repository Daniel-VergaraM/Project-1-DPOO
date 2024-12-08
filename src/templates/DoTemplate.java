package templates;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import learningpath.activity.Activity;
import show.ShowActivityWindow;


public class DoTemplate extends JFrame {

	private static final long serialVersionUID = 1L;

	protected ShowActivityWindow main;
	protected ActivityPanel activityPanel;
	protected ButtonPanel buttonPanel;
	protected Activity activity;

	public DoTemplate(ShowActivityWindow main, boolean button, boolean activityP, int cols) {
		super();
		this.main = main;
		activity = main.getActivity();

		setLayout(new BorderLayout(10, 10));

		setSize(800, 600);
		if (activity != null) {
			setTitle("Bloque Neon | Doing activity " + activity.getTitle() + " | " + activity.getId());
		} else {
			setTitle("Bloque Neon | Doing activity ");
		}
		
		if (activityP) {
			activityPanel = new ActivityPanel(this);
			add(activityPanel, BorderLayout.CENTER);
		}


		if (button) {
			buttonPanel = new ButtonPanel(this, cols);
			buttonPanel.setPreferredSize(new Dimension(getWidth(), 50));
			add(buttonPanel, BorderLayout.SOUTH);
		}

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(main);
		setVisible(true);
	}

	public ShowActivityWindow getMain() {
		return main;
	}

	protected void centreWindow() {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
		this.setLocation(x, y);
	}
}