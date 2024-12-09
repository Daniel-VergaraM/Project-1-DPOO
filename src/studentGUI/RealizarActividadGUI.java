package studentGUI;
 

	
import javax.swing.*;

import controller.StudentController;
import show.ShowActivityWindow;
import tracker.ActivityTracker;
import tracker.ProgressTracker;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class RealizarActividadGUI {
    private JFrame frame;
    private JComboBox<String> learningPathComboBox;
    private JComboBox<String> activityComboBox;
    private JButton performButton;
    private JTextArea activityDetailsTextArea;

    public RealizarActividadGUI(StudentController studentCont) {
        frame = new JFrame("Realizar Actividad");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());
        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        learningPathComboBox = new JComboBox<>();
        LinkedList<ProgressTracker> progressTrackers = studentCont.getStudentProgressTrackers();
        for (ProgressTracker tracker : progressTrackers) {
            learningPathComboBox.addItem(tracker.getLearningpath().getTitle());
        }
        topPanel.setBackground(Color.PINK);
        JLabel labelSupa = new JLabel("Selecciona un Learning Path:");
        labelSupa.setForeground(Color.WHITE);
        topPanel.add(labelSupa);
        topPanel.add(learningPathComboBox);

        JPanel middlePanel = new JPanel(new GridLayout(2, 1));
        middlePanel.setBackground(new Color(176, 242, 180));
        activityComboBox = new JComboBox<>();
        middlePanel.add(new JLabel("Selecciona una Actividad:"));
        middlePanel.add(activityComboBox);

        activityDetailsTextArea = new JTextArea(8, 30);
        activityDetailsTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(activityDetailsTextArea);
        performButton = new JButton("Realizar Actividad");
        performButton.setEnabled(false);

        learningPathComboBox.addActionListener(e -> {
            activityComboBox.removeAllItems();
            int selectedIndex = learningPathComboBox.getSelectedIndex();
            if (selectedIndex >= 0) {
                ProgressTracker selectedTracker = progressTrackers.get(selectedIndex);
                for (ActivityTracker activityTracker : selectedTracker.getActivityTrackers()) {
                    activityComboBox.addItem(activityTracker.getActivity().getTitle());
                }
                performButton.setEnabled(true);
            }
        });

        activityComboBox.addActionListener(e -> {
            int learningPathIndex = learningPathComboBox.getSelectedIndex();
            int activityIndex = activityComboBox.getSelectedIndex();
            if (learningPathIndex >= 0 && activityIndex >= 0) {
                ProgressTracker selectedTracker = progressTrackers.get(learningPathIndex);
                ActivityTracker selectedActivityTracker = selectedTracker.getActivityTrackers().get(activityIndex);
                activityDetailsTextArea.setText("Título: " + selectedActivityTracker.getActivity().getTitle() + "\n" +
                        "Descripción: " + selectedActivityTracker.getActivity().getDescription() + "\n" +
                        "Tipo: " + selectedActivityTracker.getActivity().getTYPE());
            }
        });

        performButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int learningPathIndex = learningPathComboBox.getSelectedIndex();
                int activityIndex = activityComboBox.getSelectedIndex();
                if (learningPathIndex >= 0 && activityIndex >= 0) {
                    ProgressTracker selectedTracker = progressTrackers.get(learningPathIndex);
                    ActivityTracker selectedActivityTracker = selectedTracker.getActivityTrackers().get(activityIndex);
                    String activityType = selectedActivityTracker.getActivity().getTYPE();
                    new ShowActivityWindow(studentCont);
                    switch (activityType) {
                        case "exam":
                            JOptionPane.showMessageDialog(frame, "Iniciando Cuestionario...");
                            break;
                        case "form":
                            JOptionPane.showMessageDialog(frame, "Mostrando encuesta... ");
                            break;
                        case "quiz":
                            JOptionPane.showMessageDialog(frame, "Se iniciará en breve el quiz...");
                            break;
                        case "resource":
                            JOptionPane.showMessageDialog(frame, "Mostrando Contenido...");
                            break;
                        case "task":
                            JOptionPane.showMessageDialog(frame, "Mostrando tarea: ");
                            break;
                        case "truefalse":
                            JOptionPane.showMessageDialog(frame, "Mostrando verdadero Falso: ");
                            break;      
                        default:
                            JOptionPane.showMessageDialog(frame, "Tipo de actividad desconocido.");
                    }
                }
            }
        });
        

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(middlePanel, BorderLayout.CENTER);
        frame.add(scrollPane, BorderLayout.SOUTH);
        frame.add(performButton, BorderLayout.EAST);

        frame.setVisible(true);
    }
}

