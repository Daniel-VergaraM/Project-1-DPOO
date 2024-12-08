package actividadGUI;


import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import learningpath.activity.ResourceActivity;
import show.ShowActivityWindow;
import templates.DoTemplate;



public class DoResourceActivity extends DoTemplate {

	private static final long serialVersionUID = 1L;

	private JLabel lblResource;
	private JButton btnOpenResource;
	private JButton closeWindow;

	public DoResourceActivity(ShowActivityWindow main) {
		super(main, true, true, 1);
		JPanel resourcePanel = new JPanel();
		resourcePanel.setBorder(new EmptyBorder(10, 10, 10, 10));

		if (((ResourceActivity) main.getActivity()).getUrl() == null) {
			lblResource = new JLabel("Resource: No resource available");
		} else {
			lblResource = new JLabel("Resource: " + ((ResourceActivity) main.getActivity()).getUrl());
		}

		btnOpenResource = new JButton("Open Resource");
		btnOpenResource.addActionListener(e -> openResourceURL());
		btnOpenResource.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					openResourceURL();
				}
			}
		});

		closeWindow = new JButton("Close Window");
		closeWindow.setPreferredSize(new Dimension(133, 33));
		closeWindow.addActionListener(e -> this.dispose());
		closeWindow.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DELETE) {
					dispose();
				}
			}
		});

		resourcePanel.add(lblResource);
		resourcePanel.add(btnOpenResource);

		setSize(new Dimension(600, 200));
		centreWindow();
		this.activityPanel.add(resourcePanel);
		this.buttonPanel.add(closeWindow);
	}

	private void openResourceURL() {
		try {

			URI resourceURI = new URI(((ResourceActivity) main.getActivity()).getUrl());
			if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().browse(resourceURI);
				((ResourceActivity) main.getActivity()).setDone(true);
				main.updateData();
			} else {
				JOptionPane.showMessageDialog(null, "Desktop is not supported on this system.");
				System.err.println("Desktop is not supported on this system.");
			}
		} catch (URISyntaxException | IOException ex) {
			JOptionPane.showMessageDialog(null, "Error opening the resource.");
			ex.printStackTrace();
		}
	}
}