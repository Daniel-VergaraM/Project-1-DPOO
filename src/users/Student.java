package users;

import learningpath.LearningPath;
import learningpath.activity.Activity;
import tracker.ActivityTracker;
import tracker.ProgressTracker;

import java.util.LinkedList;
import java.util.List;

public class Student extends User {
    private LinkedList<String> interests;
    private LinkedList<ProgressTracker> progressTrackers;
    public final static String ROLE = "student";
    
    public Student(String username, String password) {
        super(username, password);
        this.interests = new LinkedList<>();
        this.progressTrackers = new LinkedList<>();
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
    
    public void enrollInLearningPath(LearningPath learningPath) {
        ProgressTracker progressTracker = new ProgressTracker(this.username, learningPath);
        progressTrackers.add(progressTracker);
        learningPath.addProgressTracker(progressTracker);
    }
    
    public void completeActivity(Activity activity) {
        
    }
    
    public float getProgress(ProgressTracker tracker) {
        return tracker.getProgress();
    }
}
	
