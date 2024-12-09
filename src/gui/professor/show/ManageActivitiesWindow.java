package gui.professor.show;

import controller.ProfessorController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class ManageActivitiesWindow extends JFrame implements ActionListener {

    private JComboBox<String> activityTypeComboBox;
    private JList<String> activitiesList;
    private DefaultListModel<String> listModel;
    private JButton selectButton, editButton, createButton;
    private JLabel titleLabel, descriptionLabel, objectivesLabel, typeLabel;

    public ManageActivitiesWindow(ProfessorController controller) {
        super("Manage Activities");
        setLayout(new BorderLayout(10, 10));

        // Create combo box for selecting activity type
        String[] activityTypes = {"Professor Activities", "Global Activities"};
        activityTypeComboBox = new JComboBox<>(activityTypes);
        activityTypeComboBox.addActionListener(this);

        // Create list model and list for displaying activities
        listModel = new DefaultListModel<>();
        activitiesList = new JList<>(listModel);

        // Create select button
        selectButton = new JButton("Select");
        selectButton.addActionListener(this);

        // Create panel for activity details
        JPanel detailsPanel = new JPanel(new GridLayout(6, 1, 5, 5));
        titleLabel = new JLabel("Title: ");
        descriptionLabel = new JLabel("Description: ");
        objectivesLabel = new JLabel("Objectives: ");
        typeLabel = new JLabel("Type: ");
        detailsPanel.add(titleLabel);
        detailsPanel.add(descriptionLabel);
        detailsPanel.add(objectivesLabel);
        detailsPanel.add(typeLabel);

        // Create edit and create buttons
        editButton = new JButton("Edit Activity");
        createButton = new JButton("Create Activity");
        editButton.addActionListener(this);
        createButton.addActionListener(this);
        detailsPanel.add(editButton);
        detailsPanel.add(createButton);

        // Add components to the frame
        add(activityTypeComboBox, BorderLayout.NORTH);
        add(new JScrollPane(activitiesList), BorderLayout.CENTER);
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
        if (e.getSource() == activityTypeComboBox) {
            String selectedType = (String) activityTypeComboBox.getSelectedItem();
            updateActivitiesList(selectedType);
        } else if (e.getSource() == selectButton) {
            String selectedActivity = activitiesList.getSelectedValue();
            if (selectedActivity != null) {
                System.out.println("Selected Activity: " + selectedActivity);
                updateActivityDetails(selectedActivity);
            } else {
                JOptionPane.showMessageDialog(this, "Please select an activity from the list.");
            }
        } else if (e.getSource() == editButton) {
            System.out.println("Edit Activity");
            // Implement the functionality for editing an activity here
        } else if (e.getSource() == createButton) {
            System.out.println("Create Activity");
            // Implement the functionality for creating an activity here
        }
    }

    private void updateActivitiesList(String activityType) {
        listModel.clear();
        LinkedList<String> activities = getActivities(activityType);
        for (String activity : activities) {
            listModel.addElement(activity);
        }
    }

    private LinkedList<String> getActivities(String activityType) {
        LinkedList<String> activities = new LinkedList<>();
        if (activityType.equals("Professor Activities")) {
            activities.add("Professor Activity 1");
            activities.add("Professor Activity 2");
        } else if (activityType.equals("Global Activities")) {
            activities.add("Global Activity 1");
            activities.add("Global Activity 2");
        }
        return activities;
    }

    private void updateActivityDetails(String activityTitle) {
        // Fetch the activity details based on the title
        // This is a placeholder implementation, replace it with actual data fetching logic
        titleLabel.setText("Title: " + activityTitle);
        descriptionLabel.setText("Description: Sample description for " + activityTitle);
        objectivesLabel.setText("Objectives: Sample objectives for " + activityTitle);
        typeLabel.setText("Type: Sample type for " + activityTitle);
    }

}