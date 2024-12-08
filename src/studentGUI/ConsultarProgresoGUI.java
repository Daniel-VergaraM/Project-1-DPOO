package studentGUI;

import controller.StudentController;
import tracker.ActivityTracker;
import tracker.ProgressTracker;

import javax.swing.*;
import java.awt.*;

public class ConsultarProgresoGUI {
    private JFrame frame;

    public ConsultarProgresoGUI(StudentController studentController) {
        frame = new JFrame("Consultar Progreso");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Título
        JLabel titleLabel = new JLabel("Consultar Progreso de un Learning Path");
        JPanel panelSupa = new JPanel();
        panelSupa.setLayout(new BorderLayout());
        panelSupa.setBackground(Color.pink);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        panelSupa.add(titleLabel, BorderLayout.NORTH);
        frame.add(panelSupa, BorderLayout.NORTH);
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(176,242,180));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        if (studentController.getStudent().getProgressTrackers().isEmpty()) {
            JLabel noLPLabel = new JLabel("No tienes Learning Paths inscritos actualmente.");
            noLPLabel.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
            noLPLabel.setForeground(Color.RED);
            mainPanel.add(noLPLabel);
        } else {
            JLabel lpLabel = new JLabel("Selecciona un Learning Path:");
            JComboBox<String> lpComboBox = new JComboBox<>();
            for (ProgressTracker tracker : studentController.getStudent().getProgressTrackers()) {
                lpComboBox.addItem(tracker.getLearningpath().getTitle());
            }
            mainPanel.add(lpLabel);
            mainPanel.add(lpComboBox);
            JPanel progressPanel = new JPanel();
            progressPanel.setLayout(new BoxLayout(progressPanel, BoxLayout.Y_AXIS));
            progressPanel.setBorder(BorderFactory.createTitledBorder("Detalles de Progreso"));
            JLabel progressLabel = new JLabel();
            JTextArea progressDetails = new JTextArea();
            progressDetails.setEditable(false);
            progressDetails.setFont(new Font("Monospaced", Font.PLAIN, 12));
            progressDetails.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            progressPanel.add(progressLabel);
            progressPanel.add(new JScrollPane(progressDetails));

            lpComboBox.addActionListener(e -> {
                int selectedIndex = lpComboBox.getSelectedIndex();
                if (selectedIndex >= 0) {
                    ProgressTracker selectedTracker = studentController.getStudent().getProgressTrackers().get(selectedIndex);

                    double progressPercentage = selectedTracker.getProgress();
                    progressLabel.setText("Progreso: " + String.format("%.2f", progressPercentage) + "%");

                    StringBuilder details = new StringBuilder();
                    details.append("Actividades completadas:\n");

                    for (ActivityTracker tracker : selectedTracker.getActivityTrackers()) {
                        if (tracker.isCompleta()) {
                            details.append("- ").append(tracker.getActivity().getTitle()).append("\n");
                        }
                    }

                    details.append("\nActividades pendientes:\n");

                    for (ActivityTracker tracker : selectedTracker.getActivityTrackers()) {
                        if (!tracker.isCompleta()) {
                            details.append("- ").append(tracker.getActivity().getTitle()).append("\n");
                        }
                    }
                    progressDetails.setText(details.toString());
                }
            });

            mainPanel.add(progressPanel);
        }

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
