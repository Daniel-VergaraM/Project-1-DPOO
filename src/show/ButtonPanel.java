package show;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serial;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel implements ActionListener, KeyListener {
	
	@Serial
	private static final long serialVersionUID = 1L;

	private static final String NEXT = "Next";
	private static final String PREVIOUS = "Previous";
	private static final String CLOSE = "Close";

	private final ShowActivityWindow main;

	public ButtonPanel(ShowActivityWindow main) {
		super();
		this.main = main;
		this.setBackground(new Color(176, 242, 180));
		setLayout(new FlowLayout(FlowLayout.CENTER, 150, 10));
		JButton btnPrevious = new JButton("Previous");
		btnPrevious.setSize(67, 33);
		btnPrevious.setActionCommand(PREVIOUS);

		JButton btnClose = new JButton("Close");
		btnClose.setBackground(new Color(244, 85, 85));
		btnClose.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnClose.setSize(67, 33);
		btnClose.setActionCommand(CLOSE);

		JButton btnNext = new JButton("Next");
		btnNext.setSize(67, 33);
		btnNext.setActionCommand(NEXT);

		btnPrevious.addActionListener(this);
		btnPrevious.addKeyListener(this);
		btnNext.addActionListener(this);
		btnNext.addKeyListener(this);
		btnClose.addActionListener(this);
		btnClose.addKeyListener(this);

		add(btnPrevious, FlowLayout.LEFT);
		add(btnClose, FlowLayout.CENTER);
		add(btnNext, FlowLayout.RIGHT);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		switch (cmd) {
		case NEXT -> main.nextActivity();
		case PREVIOUS -> main.previousActivity();
		case CLOSE -> main.close();
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			main.previousActivity();
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			main.nextActivity();
		} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			main.close();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

}