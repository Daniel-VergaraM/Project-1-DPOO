package professorGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.*;
import controller.ProfessorController;
public class CreateLearningPath extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JTextField titleField, descriptionField, objectiveField, difficultyField, tagsField;
    private JButton createButton;
    ProfessorController controller;
    public CreateLearningPath(ProfessorController controller) {
        super("Create Learning Path");
        this.controller = controller;
        this.setLayout(new GridLayout(6, 2, 10, 10));
        this.add(new JLabel("Title:"));
        titleField = new JTextField();
        this.add(titleField);
        this.add(new JLabel("Description:"));
        descriptionField = new JTextField();
        this.add(descriptionField);
        this.add(new JLabel("Objectives (comma separated):"));
        objectiveField = new JTextField();
        this.add(objectiveField);
        this.add(new JLabel("Difficulty Level:"));
        difficultyField = new JTextField();
        this.add(difficultyField);
        this.add(new JLabel("Tags (comma separated):"));
        tagsField = new JTextField();
        this.add(tagsField);
        createButton = new JButton("Create");
        createButton.addActionListener(this);
        this.add(new JLabel());
        this.add(createButton);
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        centreWindow();
        this.setVisible(true);
    }
    private void centreWindow() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createButton) {
            String title = titleField.getText();
            String description = descriptionField.getText();
            String objectivesText = objectiveField.getText();
            String difficultyText = difficultyField.getText();
            String tagsText = tagsField.getText();
            if (title.isEmpty() || description.isEmpty() || objectivesText.isEmpty() || difficultyText.isEmpty() || tagsText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                return;
            }
            if (!difficultyText.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "Difficulty level must be a number.");
                return;
            }
            int difficultyLevel = Integer.parseInt(difficultyText);
            LinkedList<String> objectives = new LinkedList<>();
            for (String objective : objectivesText.split(",")) {
                objectives.add(objective.trim());
            }
            LinkedList<String> tags = new LinkedList<>();
            for (String tag : tagsText.split(",")) {
                tags.add(tag.trim());
            }
            controller.createLearningPath(title, description, objectives, difficultyLevel, tags, controller.getProfessor());
            JOptionPane.showMessageDialog(this, String.format(
                    "Learning Path Created:\nTitle: %s\nDescription: %s\nObjectives: %s\nDifficulty Level: %d\nTags: %s",
                    title, description, objectivesText, difficultyLevel, tagsText));
        }
    }
}