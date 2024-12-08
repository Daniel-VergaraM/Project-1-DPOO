package studentGUI;


import controller.StudentController;
import learningpath.LearningPath;
import learningpath.activity.Activity;
import tracker.ProgressTracker;
import users.Student;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.util.LinkedList;

public class LearningPathInscribirGUI {
    private JFrame frame;

    public LearningPathInscribirGUI(StudentController studentController) {
        frame = new JFrame("Inscribir Learning Path");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Inscribirse a un Learning Path", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        JPanel panelSup = new JPanel();
        panelSup.setLayout(new BorderLayout());
        panelSup.add(titleLabel, BorderLayout.NORTH);
        panelSup.setBackground(Color.pink);
        panelSup.setForeground(Color.white);

        frame.add(panelSup, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel instructionLabel = new JLabel("Introduce el ID del Learning Path que deseas inscribir:");
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        mainPanel.add(instructionLabel);

        JTextField idField = new JTextField();
        mainPanel.add(idField);

        JButton enrollButton = new JButton("Inscribirse");
        enrollButton.setBackground(Color.pink);
        enrollButton.setForeground(Color.white);
        mainPanel.add(enrollButton);

        frame.add(mainPanel, BorderLayout.CENTER);

        JLabel resultLabel = new JLabel("", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        frame.add(resultLabel, BorderLayout.SOUTH);

        enrollButton.addActionListener(e -> {
            String idInput = idField.getText().trim();

            if (idInput.isEmpty()) {
                resultLabel.setText("Por favor, introduce un ID válido.");
                resultLabel.setForeground(Color.RED);
                return;
            }

            LearningPath selectedPath = Student.mapaLearningPaths.get(idInput);

            if (selectedPath != null) {
                studentController.setCurrentLearningPath(selectedPath);
                studentController.getStudent().setLearningPathStudent(selectedPath);
                for(Activity elemento : selectedPath.getActivities()) 
                {        studentController.activityHashMap.put(elemento.getId(), elemento);
}
                ProgressTracker progreso = new ProgressTracker(studentController.getStudent().getUsername(), selectedPath);
                studentController.setCurrentProgressTracker(progreso);
                studentController.getCurrentProgressTracker().setLearningPath(selectedPath);
                studentController.getStudent().getProgressTrackers().add(progreso);
                
                if (true) {
                    resultLabel.setText("Te has inscrito correctamente a: " + selectedPath.getTitle());
                    resultLabel.setForeground(new Color(0, 128, 0));
                } else {
                    resultLabel.setText("Ya estás inscrito en este Learning Path.");
                    resultLabel.setForeground(Color.ORANGE);
                }
            } else {
                resultLabel.setText("No se encontró un Learning Path con ese ID.");
                resultLabel.setForeground(Color.RED);
            }
        });

        // Mostrar ventana
        frame.setVisible(true);
    }
}
