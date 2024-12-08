package studentGUI;


import controller.StudentController;
import learningpath.LearningPath;
import users.Student;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.image.ColorModel;

public class AgregarReviewGUI {
    private JFrame frame;

    public AgregarReviewGUI(StudentController studentController) {
        frame = new JFrame("Generar reseña para un Learning Path");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Generar Reseña para un Learning Path", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setForeground(Color.WHITE);
        JPanel panelSup = new JPanel();
        panelSup.setLayout(new BorderLayout());
        panelSup.add(titleLabel, BorderLayout.NORTH);
        panelSup.setBackground(Color.pink);
        frame.add(panelSup, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel idLabel = new JLabel("Introduce el ID del Learning Path:");
        idLabel.setForeground(Color.WHITE);
        JTextField idField = new JTextField();
        mainPanel.add(idLabel);
        mainPanel.add(idField);
        mainPanel.setBackground(new Color(176,242,180));
        JLabel reviewLabel = new JLabel("Escribe tu reseña:");
        reviewLabel.setForeground(Color.WHITE);
        JTextArea reviewArea = new JTextArea(5, 20);
        reviewArea.setLineWrap(true);
        reviewArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(reviewArea);
        mainPanel.add(reviewLabel);
        mainPanel.add(scrollPane);

        JButton saveButton = new JButton("Guardar Reseña");
        saveButton.setBackground(Color.PINK);
        saveButton.setForeground(Color.WHITE);

        mainPanel.add(saveButton);

        frame.add(mainPanel, BorderLayout.CENTER);

        JLabel resultLabel = new JLabel("", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        frame.add(resultLabel, BorderLayout.SOUTH);

        saveButton.addActionListener(e -> {
            String idInput = idField.getText().trim();
            String reviewInput = reviewArea.getText().trim();

            if (idInput.isEmpty() || reviewInput.isEmpty()) {
                resultLabel.setText("Por favor, completa todos los campos.");
                resultLabel.setForeground(Color.RED);
                return;
            }

            LearningPath selectedPath =  Student.mapaLearningPaths.get(idInput);

            if (selectedPath != null) {
                selectedPath.setReseñas(reviewInput);
                resultLabel.setText("Reseña guardada exitosamente.");
                resultLabel.setForeground(new Color(0, 128, 0)); 
                idField.setText("");
                reviewArea.setText("");
            } else {
                resultLabel.setText("No se encontró un Learning Path con ese ID.");
                resultLabel.setForeground(Color.RED);
            }
        });

        // Mostrar ventana
        frame.setVisible(true);
    }
}
