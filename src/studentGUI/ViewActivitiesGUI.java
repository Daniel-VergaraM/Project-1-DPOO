package studentGUI;

import controller.StudentController;
import learningpath.LearningPath;
import learningpath.activity.Activity;
import users.Student;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ViewActivitiesGUI {
    private JFrame frame;

    public ViewActivitiesGUI(StudentController studentController) {
        frame = new JFrame("Ver Actividades del Learning Path");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Ver Actividades del Learning Path", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 12));
        titleLabel.setForeground(Color.WHITE);
        JPanel panelSupa = new JPanel();
        panelSupa.setLayout(new GridLayout(4,1));
        panelSupa.add(titleLabel, BorderLayout.NORTH);
        panelSupa.setBackground(new Color(176,242,180));
        frame.add(panelSupa, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2,1));

        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel idLabel = new JLabel("Introduce el ID del Learning Path:");
        idLabel.setFont(new Font("Arial", Font.PLAIN, 14));//al fin omg
        JTextField idField = new JTextField(20);
        JButton viewButton = new JButton("Ver actividades");
        viewButton.setBackground(Color.PINK);
        viewButton.setForeground(Color.white);
        panelSupa.add(idLabel);
        panelSupa.add(idField);
        panelSupa.add(viewButton);
        
        mainPanel.add(inputPanel, BorderLayout.NORTH);

        JTextArea activitiesArea = new JTextArea();
        activitiesArea.setEditable(false);
        activitiesArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(activitiesArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        frame.add(mainPanel, BorderLayout.CENTER);
        JLabel resultLabel = new JLabel("", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        frame.add(resultLabel, BorderLayout.SOUTH);

        viewButton.addActionListener(e -> {
            String idInput = idField.getText().trim();

            if (idInput.isEmpty()) {
                resultLabel.setText("Por favor, introduce un ID válido.");
                resultLabel.setForeground(Color.RED);
                return;
            }

            LearningPath selectedPath =  Student.mapaLearningPaths.get(idInput);

            if (selectedPath != null) {
                List<Activity> activities = selectedPath.getActivities();
                if (activities.isEmpty()) {
                    activitiesArea.setText("No hay actividades disponibles en este Learning Path.");
                } else {
                    StringBuilder sb = new StringBuilder();
                    for (Activity activity : activities) {
                        sb.append("Título: ").append(activity.getTitle()).append("\n")
                                .append("Descripción: ").append(activity.getDescription()).append("\n")
                                .append("Duración: ").append(activity.getExpectedDuration()).append(" horas\n")
                                .append("------------------------------------\n");
                    }
                    activitiesArea.setText(sb.toString());
                }
                resultLabel.setText("Actividades cargadas correctamente.");
                resultLabel.setForeground(new Color(0, 128, 0));
            } else {
                activitiesArea.setText("");
                resultLabel.setText("No se encontró un Learning Path con ese ID.");
                resultLabel.setForeground(Color.RED);
            }
        });

        frame.setVisible(true);
    }
}
