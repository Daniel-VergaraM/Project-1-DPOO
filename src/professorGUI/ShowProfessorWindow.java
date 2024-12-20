package professorGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import controller.ProfessorController;

public class ShowProfessorWindow extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private ProfessorController controller;
    private JButton option1, option2, option3;
    
    public ShowProfessorWindow( ProfessorController controller) {
        super("Menu Window");

        // Set layout
        setLayout(new BorderLayout());
        // Create welcome label
        JLabel welcomeLabel = new JLabel("Bienvenido profesor " + controller.getProfessor().getUsername(), JLabel.CENTER);
        welcomeLabel.setOpaque(true);
        welcomeLabel.setBackground(Color.decode("#febfc9"));
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Set larger font size
        add(welcomeLabel, BorderLayout.NORTH);
        JPanel optionsPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        // Create buttons
        option1 = new JButton("Manejar Learning Paths");
        option2 = new JButton("Manejar Actividades");
        option3 = new JButton("Logout");
        Dimension buttonSize = new Dimension(200, 50);
        option1.setPreferredSize(buttonSize);
        option2.setPreferredSize(buttonSize);
        option3.setPreferredSize(buttonSize);
        option1.addActionListener(this);
        option2.addActionListener(this);
        option3.addActionListener(this);
        optionsPanel.add(option1);
        optionsPanel.add(option2);
        optionsPanel.add(option3);
        add(optionsPanel, BorderLayout.CENTER);
        setSize(600, 400); // Set the desired size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == option1) {
            new ManageLearningPathsWindow(controller);
        } else if (e.getSource() == option2) {
            new ManageActivitiesWindow(controller);
        } else if (e.getSource() == option3) {
            dispose();
        }
    }
   
}