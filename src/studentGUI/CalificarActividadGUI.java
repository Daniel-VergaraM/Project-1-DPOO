package studentGUI;

import javax.swing.*;
import controller.StudentController;
import learningpath.activity.Activity;
import tracker.ActivityTracker;
import tracker.ProgressTracker;

import java.awt.*;
	import java.util.LinkedList;

	public class CalificarActividadGUI {
	    private JFrame frame;
	    public CalificarActividadGUI(StudentController studentController) {
	        frame = new JFrame("Calificar una Actividad");
	        frame.setSize(600, 500);
	        frame.setBackground(Color.PINK);
	        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        frame.setLayout(new BorderLayout());
	        JLabel titleLabel = new JLabel("Calificar una Actividad");
	        titleLabel.setForeground(Color.WHITE);
	        JPanel paneSupa = new JPanel();
	        paneSupa.setBackground(Color.PINK);
	        titleLabel.setFont(new Font("Arial", Font.ITALIC, 14));
	        paneSupa.add(titleLabel, BorderLayout.NORTH);
	        frame.add(paneSupa, BorderLayout.NORTH);
	        JPanel mainPanel = new JPanel();
	        mainPanel.setBackground(new Color(176,242,180));
	        
	        mainPanel.setLayout(new GridLayout(6,1));
	        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	        try {
	        
	        if (studentController.getStudent().getLearningPathStudent().getActivities().isEmpty()) {
	            JLabel noLPLabel = new JLabel("No tienes actividades disponibles para calificar. Asegúrate de estar inscrito en un Learning Path.");
	            noLPLabel.setFont(new Font("Arial", Font.ITALIC, 14));
	            noLPLabel.setForeground(Color.RED);
	            mainPanel.add(noLPLabel);
	        } else {
	            JLabel lpLabel = new JLabel("Tu LearningPath actual es: ");
	            JComboBox<String> lpComboBox = new JComboBox<>();
	            String title = studentController.getStudent().getLearningPathStudent().getTitle();
	            lpComboBox.addItem(title);
	               
	            mainPanel.add(lpLabel);
	            mainPanel.add(lpComboBox);
	            JPanel activityPanel = new JPanel();
	            activityPanel.setBackground(new Color(176,242,180));
	            activityPanel.setLayout(new GridLayout(2, 1, 10, 10));
	            activityPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
	            JLabel activityLabel = new JLabel("Actividades disponibles:");
	            JComboBox<String> activityComboBox = new JComboBox<>();
	            activityPanel.add(activityLabel);
	            for(Activity element : studentController.getStudent().getLearningPathStudent().getActivities()) {
	            	activityComboBox.addItem("El titulo es: "+ element.getTitle() + " Con id: "+ element.getId());
	            }
	            activityPanel.add(activityComboBox);

	            lpComboBox.addActionListener(e -> {
	                int selectedIndex = lpComboBox.getSelectedIndex();
	                if (selectedIndex >= 0) {
	                    ProgressTracker selectedTracker = studentController.getStudent().getProgressTrackers().get(selectedIndex);
	                    LinkedList<ActivityTracker> activities = selectedTracker.getActivityTrackers();
	                    activityComboBox.removeAllItems();
	                    for (ActivityTracker activityTracker : activities) {
	                        activityComboBox.addItem(activityTracker.getActivity().getTitle());
	                    }
	                }
	            });
	            JPanel ratingPanel = new JPanel();
	            ratingPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
	            JLabel ratingLabel = new JLabel("Calificación (1-5):");
	            JTextField ratingField = new JTextField(5);
	            ratingPanel.add(ratingLabel);
	            ratingPanel.add(ratingField);
	            JButton rateButton = new JButton("Calificar");
	            rateButton.setBackground(Color.PINK);
	            rateButton.setForeground(Color.WHITE);
	            JLabel resultLabel = new JLabel("", SwingConstants.CENTER);
	            resultLabel.setFont(new Font("Arial", Font.ITALIC, 12));
	            rateButton.addActionListener(e -> {
	                int lpIndex = lpComboBox.getSelectedIndex();
	                int activityIndex = activityComboBox.getSelectedIndex();
	                String ratingText = ratingField.getText().trim();
	                if (lpIndex < 0 || activityIndex < 0 || ratingText.isEmpty()) {
	                    resultLabel.setText("Por favor selecciona un Learning Path, una actividad y escribe la calificación.");
	                    resultLabel.setForeground(Color.RED);
	                    return;
	                }
	                try {
	                    int rating = Integer.parseInt(ratingText);
	                    if (rating < 1 || rating > 5) {
	                        resultLabel.setText("La calificación debe estar entre 1 y 5.");
	                        resultLabel.setForeground(Color.RED);
	                        return;
	                    }
	                    ProgressTracker selectedTracker = studentController.getStudent().getProgressTrackers().get(lpIndex);
	                    ActivityTracker selectedActivity = selectedTracker.getActivityTrackers().get(activityIndex);
	                    selectedActivity.addRating(rating);

	                    resultLabel.setText("Has calificado exitosamente la actividad.");
	                    resultLabel.setForeground(new Color(0, 128, 0)); 
	                    ratingField.setText("");
	                } catch (NumberFormatException ex) {
	                    resultLabel.setText("Por favor ingresa un número válido para la calificación.");
	                    resultLabel.setForeground(Color.RED);
	                }
	            });
	            mainPanel.add(activityPanel);
	            mainPanel.add(ratingPanel);
	            mainPanel.add(rateButton);
	            mainPanel.add(resultLabel);
	        }
	        }
	        catch(Exception e) 
	        { JLabel noLPLabel = new JLabel("No tienes actividades disponibles para calificar. Asegúrate de estar inscrito en un Learning Path.");
            noLPLabel.setFont(new Font("Arial", Font.ITALIC, 14));
            noLPLabel.setForeground(Color.RED);
            mainPanel.add(noLPLabel);}
	        frame.add(mainPanel, BorderLayout.CENTER);
	        frame.setVisible(true);
	    }
	}

