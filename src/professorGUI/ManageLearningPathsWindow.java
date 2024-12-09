package professorGUI;

import controller.ProfessorController;
import learningpath.LearningPath;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
public class ManageLearningPathsWindow extends JFrame implements ActionListener {
	private JComboBox<String> pathTypeComboBox;
    private JList<String> learningPathsList;
    private DefaultListModel<String> listModel;
    private JButton selectButton, editButton, createButton;
    private JLabel titleLabel, descriptionLabel, objectivesLabel, difficultyLabel, durationLabel, ratingLabel;
    private ProfessorController controller;
    
    public ManageLearningPathsWindow(ProfessorController controller) {

    	this.controller = controller;
        setLayout(new BorderLayout(10, 10));
        // Create combo box for selecting path type
        String[] pathTypes = {"Professor Learning Paths", "Global Learning Paths"};
        pathTypeComboBox = new JComboBox<>(pathTypes);
        pathTypeComboBox.addActionListener(this);
        // Create list model and list for displaying learning paths
        listModel = new DefaultListModel<>();
        learningPathsList = new JList<>(listModel);
        // Create select button
        selectButton = new JButton("Select");
        selectButton.addActionListener(this);
        // Create panel for learning path details
        JPanel detailsPanel = new JPanel(new GridLayout(8, 1, 5, 5));
        titleLabel = new JLabel("Title: ");
        descriptionLabel = new JLabel("Description: ");
        objectivesLabel = new JLabel("Objectives: ");
        difficultyLabel = new JLabel("Difficulty Level: ");
        durationLabel = new JLabel("Duration: ");
        ratingLabel = new JLabel("Rating: ");
        detailsPanel.add(titleLabel);
        detailsPanel.add(descriptionLabel);
        detailsPanel.add(objectivesLabel);
        detailsPanel.add(difficultyLabel);
        detailsPanel.add(durationLabel);
        detailsPanel.add(ratingLabel);
        // Create edit and create buttons
        editButton = new JButton("Edit Learning Path");
        createButton = new JButton("Create Learning Path");
        editButton.addActionListener(this);
        createButton.addActionListener(this);
        detailsPanel.add(editButton);
        detailsPanel.add(createButton);
        // Add components to the frame
        add(pathTypeComboBox, BorderLayout.NORTH);
        add(new JScrollPane(learningPathsList), BorderLayout.CENTER);
        add(selectButton, BorderLayout.SOUTH);
        add(detailsPanel, BorderLayout.EAST);
        // Set frame properties
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pathTypeComboBox) {
            String selectedType = (String) pathTypeComboBox.getSelectedItem();
            updateLearningPathsList(selectedType);
        } else if (e.getSource() == selectButton) {
            String selectedPath = learningPathsList.getSelectedValue();
            if (selectedPath != null) {
                System.out.println("Selected Learning Path: " + selectedPath);
                String[] parts = selectedPath.split(" - ");
                LearningPath path = controller.learningPathHashMap.get(parts[1]);
                controller.setCurrentLearningPath(path);
                updateLearningPathDetails();
            } else {
                JOptionPane.showMessageDialog(this, "Please select a learning path from the list.");
            }
        } else if (e.getSource() == editButton) {
            System.out.println("Edit Learning Path");
            // Implement the functionality for editing a learning path here
        } else if (e.getSource() == createButton) {
            new CreateLearningPath(controller);
        }
    }
    private void updateLearningPathsList(String pathType) {
        listModel.clear();
        LinkedList<LearningPath> learningPaths = getLearningPaths(pathType);
        for (LearningPath path : learningPaths) {
            listModel.addElement(path.getTitle() + " - " + path.getId());
        }
    }
    private LinkedList<LearningPath> getLearningPaths(String pathType) {
        LinkedList<LearningPath> learningPaths = null;
        if (pathType.equals("Professor Learning Paths")) {
            learningPaths = controller.getProfessorLearningPaths();
        } else if (pathType.equals("Global Learning Paths")) {
            learningPaths.addAll(controller.getGlobalLearningPaths());
        }
        return learningPaths;
    }
    private void updateLearningPathDetails() {
        titleLabel.setText(controller.getCurrentLearningPath().getTitle());
        descriptionLabel.setText(controller.getCurrentLearningPath().getDescription());
        String objectives = "";
        for (String objective : controller.getCurrentLearningPath().getObjectives()) {
            objectives += objective + "\n";
        }
        objectivesLabel.setText(objectives);
        difficultyLabel.setText("" + controller.getCurrentLearningPath().getDifficultyLevel());
        durationLabel.setText("" + controller.getCurrentLearningPath().getDuration());
        ratingLabel.setText("" + controller.getCurrentLearningPath().getRating());
    }
}