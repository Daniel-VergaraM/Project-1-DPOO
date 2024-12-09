package show;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class IconDisplayPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel lblIcon;
	private TitledBorder border;

	private static List<String> types = List.of("resource", "quiz", "form", "task", "truefalse", "exam");

	public IconDisplayPanel(String type) {
		super();
		this.setBackground(new Color(176, 242, 180));
		lblIcon = new JLabel();
		lblIcon.setLayout(new BorderLayout());
		lblIcon.setPreferredSize(new Dimension(311, 290));
		lblIcon.setBackground(Color.WHITE);
		lblIcon.setOpaque(true);
		border = new TitledBorder("");
		setImage(type);
		add(lblIcon, BorderLayout.CENTER);
		
		repaint();

	}

	public void setImage(String type) {
		if (!types.contains(type)) {
			type = "none";
		}
		ImageIcon icon = new ImageIcon("images/" + type + ".png");
		icon.setImage(icon.getImage().getScaledInstance(300, 266, Image.SCALE_SMOOTH));
		lblIcon.setIcon(icon);
		
		border.setTitle("Tipo de actividad: " + toTitleCase(type));
		lblIcon.setBorder(border);
		repaint();
	}
	
	private String toTitleCase(String s) {
		String[] words = s.split(" ");
		StringBuilder sb = new StringBuilder();
		for (String word : words) {
			sb.append(Character.toUpperCase(word.charAt(0)));
			sb.append(word.substring(1));
			sb.append(" ");
		}
		return sb.toString().trim();
	}

}