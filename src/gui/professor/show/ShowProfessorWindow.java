package gui.professor.show;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import controller.Controller;

public class ShowProfessorWindow extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private Controller controller;

    private JButton option1, option2, option3;

    public ShowProfessorWindow() {
        super("Menu Window");

        // Set layout
        setLayout(new BorderLayout());

        // Create welcome label
        JLabel welcomeLabel = new JLabel("Bienvenido profesor!", JLabel.CENTER);
        welcomeLabel.setOpaque(true);
        welcomeLabel.setBackground(Color.decode("#febfc9"));
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Set larger font size
        add(welcomeLabel, BorderLayout.NORTH);

        // Create panel for buttons
        JPanel optionsPanel = new JPanel(new GridLayout(3, 1, 10, 10));

        // Create buttons
        option1 = new JButton("Manejar Learning Paths");
        option2 = new JButton("Manejar Actividades");
        option3 = new JButton("Logout");

        // Set preferred size for buttons
        Dimension buttonSize = new Dimension(200, 50);
        option1.setPreferredSize(buttonSize);
        option2.setPreferredSize(buttonSize);
        option3.setPreferredSize(buttonSize);

        // Add action listeners to buttons
        option1.addActionListener(this);
        option2.addActionListener(this);
        option3.addActionListener(this);

        // Add buttons to the panel
        optionsPanel.add(option1);
        optionsPanel.add(option2);
        optionsPanel.add(option3);

        // Add options panel to the frame
        add(optionsPanel, BorderLayout.CENTER);

        // Set frame properties
        setSize(600, 400); // Set the desired size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedOption = "";
        if (e.getSource() == option1) {
            selectedOption = "Option 1";
        } else if (e.getSource() == option2) {
            selectedOption = "Option 2";
        } else if (e.getSource() == option3) {
            selectedOption = "Option 3";
        }
        JOptionPane.showMessageDialog(this, "You selected: " + selectedOption);
    }

    public static void main(String[] args) {
        new ShowProfessorWindow();
    }
}