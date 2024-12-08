package users;

import learningpath.LearningPath;
import tracker.ActivityTracker;
import tracker.ProgressTracker;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import consola.StudentConsola;
import controller.StudentController;

public class Student extends User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private LinkedList<String> interests;
    private LinkedList<ProgressTracker> progressTrackers;
    private LearningPath learningPathStudent;
    public final static String ROLE = "STUDENT";
    private HashMap<LearningPath, String> resenas = new HashMap<>();
    public static HashMap<String, LearningPath> mapaLearningPaths = new HashMap<>();;
    
    public Student(String username, String password) {
        super(username, password);
        this.interests = new LinkedList<>();
        this.progressTrackers = new LinkedList<>();
        StudentController.arrayStudents.add(this);
    }

    public LinkedList<String> getInterests() {
        return interests;
    }

    public LinkedList<ProgressTracker> getProgressTrackers() {
        return progressTrackers;
    }

    public ProgressTracker getProgressTrackerByIndex(int index) {
        return progressTrackers.get(index);
    }
    
    public ProgressTracker getProgressTrackerByLearningPath(LearningPath learningPath) {
        for (ProgressTracker tracker : progressTrackers) {
            if (tracker.getLearningpath().equals(learningPath)) {
                return tracker;
            }
        }
        return null;
    }

    public List<ActivityTracker> getActivityTrackers(ProgressTracker progressTracker) {
        return progressTracker.getActivityTrackers();
    }

    public ActivityTracker getActivityTrackerByIndex(ProgressTracker progressTracker, int index) {
        return progressTracker.getActivityTrackerByIndex(index);
    }

    @Override
    public String getRole() {
        return ROLE;
    }
    
    public void addInterest(String interest) {
        interests.add(interest);
    }
    
    public void removeInterest(int index) {
        if (index >= 0 && index < interests.size()) {
            interests.remove(index);
        }
    }
    
    public void addResena(LearningPath learningPath, String reseña) {
        if (learningPath != null && reseña != null && !reseña.trim().isEmpty()) {
            resenas.put(learningPath, reseña);
            System.out.println("Reseña agregada exitosamente para el Learning Path: " + learningPath.getTitle());
        } else {
            System.out.println("Datos inválidos. Asegúrate de seleccionar un Learning Path y escribir una reseña válida.");
        }
    }
    
    public void enrollInLearningPath(LearningPath learningPath) {
        ProgressTracker progressTracker = new ProgressTracker(this.username, learningPath);
        progressTrackers.add(progressTracker);
        learningPath.addProgressTracker(progressTracker);
    }
    
    public float getProgress(ProgressTracker tracker) {
        return tracker.getProgress();
    }
    
    
	public LearningPath getLearningPathStudent() {
		return learningPathStudent;
	}

	public void setLearningPathStudent(LearningPath learningPathStudent) {
		this.learningPathStudent = learningPathStudent;
	}
    
    
    
}
	
