package studentGUI;


import controller.StudentController;
import learningpath.LearningPath;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class LearningPathGUI {
    private JFrame frame;

    public LearningPathGUI(StudentController studentController) {
        frame = new JFrame("Learning Paths Disponibles");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        JPanel titleSup = new JPanel();
        titleSup.setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Learning Paths Disponibles", SwingConstants.CENTER);
        titleSup.setBackground(Color.pink);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleSup.add(titleLabel, BorderLayout.NORTH);
        frame.add(titleSup, BorderLayout.NORTH);
        JPanel learningPathPanel = new JPanel();
        learningPathPanel.setLayout(new BoxLayout(learningPathPanel, BoxLayout.Y_AXIS));

        LinkedList<LearningPath> allLearningPaths = LearningPath.allLearningPath;

        if (allLearningPaths.isEmpty()) {
            JLabel noDataLabel = new JLabel("No hay Learning Paths disponibles.", SwingConstants.CENTER);
            noDataLabel.setFont(new Font("Arial", Font.ITALIC, 14));
            learningPathPanel.add(noDataLabel);
        } else {
            for (LearningPath lp : allLearningPaths) {
                JPanel pathPanel = new JPanel();
                pathPanel.setBackground(Color.WHITE);
                pathPanel.setLayout(new BoxLayout(pathPanel, BoxLayout.Y_AXIS));
                pathPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                JLabel title = new JLabel("Título: " + lp.getTitle());
                JLabel description = new JLabel("Descripción: " + lp.getDescription());
                JLabel rating = new JLabel("Rating: " + lp.getRating());
                JLabel id = new JLabel("ID: " + lp.getId());       
                title.setFont(new Font("Arial", Font.BOLD, 14));
                description.setFont(new Font("Arial", Font.PLAIN, 12));
                rating.setFont(new Font("Arial", Font.PLAIN, 12));
                id.setFont(new Font("Arial", Font.PLAIN, 12));
                pathPanel.add(title);
                pathPanel.add(description);
                pathPanel.add(rating);
                pathPanel.add(id);
                pathPanel.add(Box.createRigidArea(new Dimension(0, 10)));
                learningPathPanel.add(pathPanel);
                learningPathPanel.add(Box.createRigidArea(new Dimension(0, 15)));
            }
        }

        JScrollPane scrollPane = new JScrollPane(learningPathPanel);
        frame.add(scrollPane, BorderLayout.CENTER);
        JButton closeButton = new JButton("Cerrar");
        closeButton.setBackground(Color.PINK);
        closeButton.setForeground(Color.WHITE);
        closeButton.addActionListener(e -> frame.dispose());
        frame.add(closeButton, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
