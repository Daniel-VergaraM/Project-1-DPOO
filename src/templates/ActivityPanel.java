package templates;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class ActivityPanel extends JPanel {

	
	private static final long serialVersionUID = 1L;

	protected DoTemplate main;

	public ActivityPanel(DoTemplate main) {
		super();
		this.main = main;
		
		setLayout(new BorderLayout(10, 10));
		setBorder(new TitledBorder("Activity"));
	}
}
