package controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import learningpath.LearningPath;
import learningpath.activity.*;
import learningpath.question.*;
import users.Professor;
import users.User;

public class ProfessorController extends Controller {

	public Professor professor = null;
    private LearningPath currentLearningPath = null;
    private Activity currentActivity = null ;
    public static ArrayList<Professor> arrayProfes = new ArrayList<Professor>();

    /**
     * Constructs a new ProfessorController with the specified hash maps and
     * current user.
     *
     * @param userHashMap A hash map of users.
     * @param learningPathHashMap A hash map of learning paths.
     * @param activityHashMap A hash map of activities.
     * @param currentUser The current user.
     */
    public ProfessorController(HashMap<String, User> userHashMap, HashMap<String, LearningPath> learningPathHashMap, HashMap<String, Activity> activityHashMap, User currentUser) {
        super(userHashMap, learningPathHashMap, activityHashMap, currentUser);
        professor = (Professor) currentUser;
        currentLearningPath = null;
        currentActivity = null;
    }

    // Query methods
    /**
     * Retrieves the learning paths associated with a professor.
     *
     * @param professor The professor whose learning paths are to be retrieved.
     * @return A linked list of learning paths associated with the professor.
     */
    public LinkedList<LearningPath> getProfessorLearningPaths() {
        return professor.getCreatedLearningPaths();
    }
    
    public void setCurrentLearningPath(LearningPath learningPath) {
        currentLearningPath = learningPath;
    }

    public void setCurrentActivity(Activity activity) {
        currentActivity = activity;
    }

    /**
     * Retrieves all global learning paths.
     *
     * @return A collection of all global learning paths.
     */
    public Collection<LearningPath> getGlobalLearningPaths() {
        return learningPathHashMap.values();
    }

    public LearningPath getLearningPathById(String id) {
        currentLearningPath = learningPathHashMap.get(id);
        return currentLearningPath;
    }

    /**
     * Retrieves the activities associated with a professor.
     *
     * @return A linked list of activities associated with the professor.
     */
    public LinkedList<Activity> getProfessorActivities() {
        return professor.getCreatedActivities();
    }

    public Activity getProfessorActivityByIndex(int index) {
        currentActivity = professor.getCreatedActivityByIndex(index);
        return currentActivity;
    }

    public Activity getActivityInLearningPathByIndex(int index) {
        currentActivity = currentLearningPath.getActivityByIndex(index);
        return currentActivity;
    }
    
    public Activity getCurrentActivity() {
        return currentActivity;
    }

    /**
     * Retrieves all global activities.
     *
     * @return A collection of all global activities.
     */
    public Collection<Activity> getGlobalActivities() {
        return activityHashMap.values();
    }

    public Activity getActivityById(String id) {
        currentActivity = activityHashMap.get(id);
        return currentActivity;
    }

    // Learning path management methods
    /**
     * Creates a new learning path and adds it to the hash map.
     *
     * @param title The title of the new learning path.
     * @param description The description of the new learning path.
     * @param objectives The objectives of the new learning path.
     * @param difficultyLevel The difficulty level of the new learning path.
     * @param tags The tags associated with the new learning path.
     * @param professor The professor creating the new learning path.
     */
    public void createLearningPath(String title, String description, LinkedList<String> objectives, int difficultyLevel, LinkedList<String> tags, Professor professor) {
        LearningPath newLearningPath = professor.createLearningPath(title, description, objectives, difficultyLevel, tags);
        learningPathHashMap.put(newLearningPath.getId(), newLearningPath);
    }

    public void editCurrentLearningPathTitle(String title) {
        professor.editLearningPathTitle(currentLearningPath, title);
    }

    public void editCurrentLearningPathDescription(String description) {
        professor.editCurrentLearningPathDescription(currentLearningPath, description);
    }

    public void editCurrentLearningPathObjectives(LinkedList<String> objectives) {
        professor.editLearningPathObjectives(currentLearningPath, objectives);
    }

    public void editCurrentLearningPathDifficultyLevel(int difficultyLevel) {
        professor.editLearningPathDifficultyLevel(currentLearningPath, difficultyLevel);
    }

    public void editCurrentLearningPathTags(LinkedList<String> tags) {
        professor.editLearningPathTags(currentLearningPath, tags);
    }

    public void addCurrentActivityToCurrentLearningPath() {
        professor.addActivityToLearningPath(currentLearningPath, currentActivity);
    }

    public void addCurrentActivityToCurrentLearningPathByIndex(int index) {
        professor.addActivityToLearningPathInPos(currentLearningPath, currentActivity, index);
    }

    public void moveActivityInCurrentLearningPath(int fromIndex, int toIndex) {
        professor.moveActivityInLearningPath(currentLearningPath, fromIndex, toIndex);
    }

    public void removeActivityByIndexFromCurrentLearningPath(int index) {
        professor.removeActivityByIndexFromLearningPath(currentLearningPath, index);
    }

    // Activity management methods
    public void createExamActivity(String title, String description, String objective, int expectedDuration, boolean mandatory, LinkedList<OpenQuestion> oquestions, LinkedList<MultipleOptionQuestion> moquestions) {
        Activity newActivity = professor.createExamActivity(title, description, objective, expectedDuration, mandatory, oquestions, moquestions);
        activityHashMap.put(newActivity.getId(), newActivity);
    }

    public void createFormActivity(String title, String description, String objective, int expectedDuration, boolean mandatory, LinkedList<OpenQuestion> questions) {
        Activity newActivity = professor.createFormActivity(title, description, objective, expectedDuration, mandatory, questions);
        activityHashMap.put(newActivity.getId(), newActivity);
    }

    public void createResourceActivity(String title, String description, String objective, int expectedDuration, boolean mandatory, String url) {
        Activity newActivity = professor.createResourceActivity(title, description, objective, expectedDuration, mandatory, url);
        activityHashMap.put(newActivity.getId(), newActivity);
    }

    public void createQuizActivity(String title, String description, String objective, int expectedDuration, boolean mandatory, LinkedList<MultipleOptionQuestion> questions, double minscore) {
        Activity newActivity = professor.createQuizActivity(title, description, objective, expectedDuration, mandatory, questions, minscore);
        activityHashMap.put(newActivity.getId(), newActivity);
    }

    public void createTaskActivity(String title, String description, String objective, int expectedDuration, boolean mandatory, String toDo) {
        Activity newActivity = professor.createTaskActivity(title, description, objective, expectedDuration, mandatory, toDo);
        activityHashMap.put(newActivity.getId(), newActivity);
    }
    


    public void editCurrentActivityTitle(String title) {
        professor.editActivityTitle(currentActivity, title);
    }

    public void editCurrentActivityDescription(String description) {
        professor.editActivityDescription(currentActivity, description);
    }

    public void editCurrentActivityObjective(String objective) {
        professor.editActivityObjective(currentActivity, objective);
    }

    public void editCurrentActivityExpectedDuration(int expectedDuration) {
        professor.editActivityExpectedDuration(currentActivity, expectedDuration);
    }

    public void addCurrentActivityPrerequisiteByIndex(int index) {
        professor.addPrerequisiteToActivityByIndex(currentLearningPath, currentActivity, index);
    }

    public void removeCurrentActivityPrerequisiteByIndex(int index) {
        professor.removePrerequisiteFromActivityByIndex(currentActivity, index);
    }

    public void addCurrentActivityFollowUpActivityByIndex(int index) {
        professor.addFollowUpActivityToActivityByIndex(currentLearningPath, currentActivity, index);
    }

    public void removeCurrentActivityFollowUpActivityByIndex(int index) {
        professor.removeFollowUpActivityFromActivityByIndex(currentActivity, index);
    }

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
	public void createTrueFalseActivity(String title, String description, String objective, int expectedDuration,
			boolean mandatory, LinkedList<TrueFalseQuestion> questions) {
		Activity newActivity = professor.createTrueFalseActivity(title, description, objective, expectedDuration,
				mandatory, questions);
		activityHashMap.put(newActivity.getId(), newActivity);
	}

	public LearningPath getCurrentLearningPath() {
		return currentLearningPath;
	}

	

}
