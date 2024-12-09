package show;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import learningpath.activity.Activity;


public class DetailsPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel lblTitle;
	private JLabel lblDescription;
	private JLabel lblObjective;
	private JLabel lblExpectedDuration;
	private JLabel lblMandatory;
	private JLabel lblDone;

	private MainPanel main;

	private static final Font font = new Font("Candara MS", Font.PLAIN, 12);

	public DetailsPanel(MainPanel main) {
		super();
		this.main = main;
		setPreferredSize(new Dimension(400, 600));
		setLayout(new GridLayout(2, 1));

		JLabel cosoTitulo = new JLabel("Title: ");
		JLabel cosoDescripcion = new JLabel("Description: ");
		JLabel cosoObjetivo = new JLabel("Objective: ");
		JLabel cosoDuracion = new JLabel("Expected Duration: ");
		JLabel cosoMandatory = new JLabel("Mandatory: ");
		JLabel cosoDo = new JLabel("Done: ");
		this.setBackground(new Color(176, 242, 180));
		lblTitle = new JLabel();
		lblTitle.setFont(font);

		lblDescription = new JLabel();
		lblDescription.setFont(font);

		lblObjective = new JLabel();
		lblObjective.setFont(font);

		lblExpectedDuration = new JLabel();
		lblExpectedDuration.setFont(font);

		lblMandatory = new JLabel();
		lblMandatory.setFont(font);

		lblDone = new JLabel();
		lblDone.setFont(font);
		
		cosoTitulo.setHorizontalAlignment(JLabel.RIGHT);
		cosoDescripcion.setHorizontalAlignment(JLabel.RIGHT);
		cosoObjetivo.setHorizontalAlignment(JLabel.RIGHT);
		cosoDuracion.setHorizontalAlignment(JLabel.RIGHT);
		cosoMandatory.setHorizontalAlignment(JLabel.RIGHT);
		cosoDo.setHorizontalAlignment(JLabel.RIGHT);

		lblTitle.setHorizontalAlignment(JLabel.LEFT);
		lblDescription.setHorizontalAlignment(JLabel.LEFT);
		lblObjective.setHorizontalAlignment(JLabel.LEFT);
		lblExpectedDuration.setHorizontalAlignment(JLabel.LEFT);
		lblMandatory.setHorizontalAlignment(JLabel.LEFT);
		lblDone.setHorizontalAlignment(JLabel.LEFT);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(6, 2, 10, 5));

		panel.add(cosoTitulo, BorderLayout.EAST);
		panel.add(lblTitle);

		panel.add(cosoDescripcion, BorderLayout.EAST);
		panel.add(lblDescription);

		panel.add(cosoObjetivo, BorderLayout.EAST);
		panel.add(lblObjective);

		panel.add(cosoDuracion, BorderLayout.EAST);
		panel.add(lblExpectedDuration);

		panel.add(cosoMandatory, BorderLayout.EAST);
		panel.add(lblMandatory);

		panel.add(cosoDo, BorderLayout.EAST);
		panel.add(lblDone);

		add(panel, BorderLayout.EAST);

		setBorder(new TitledBorder("Details"));

		setVisible(true);
		repaint();
	}

	public void setActivity() {
		Activity activity = main.getMain().getActivity();
		if (activity == null) {
			lblTitle.setText("?");
			lblDescription.setText("?");
			lblObjective.setText("?");
			lblExpectedDuration.setText("? minutes");
			lblMandatory.setText("?");
			lblDone.setText("?");
			repaint();
			return;
		}
		lblTitle.setText(activity.getTitle());
		lblDescription.setText(activity.getDescription());
		lblObjective.setText(activity.getObjective());
		lblExpectedDuration.setText((int) (activity.getExpectedDuration() / 60) + " minutes");
		lblMandatory.setText(activity.isMandatory() ? "Yes" : "No");
		lblDone.setText(activity.isDone() ? "Yes" : "No");
		if (activity.isDone()) main.getDoButton().setEnabled(false);
        else main.getDoButton().setEnabled(true);

		repaint();
	}

}