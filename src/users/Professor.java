package users;

import java.io.Serializable;
import java.util.LinkedList;

import consola.ProfessorConsola;
import controller.ProfessorController;
import learningpath.*;
import learningpath.activity.*;
import learningpath.question.*;
import persistencia.CentralPersistencia;

public class Professor extends User implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    public  LinkedList<LearningPath> createdLearningPaths;
    private LinkedList<Activity> createdActivities;
    private final static String ROLE = "PROFESSOR";
    public transient static CentralPersistencia centralPersistencia = new CentralPersistencia();
    private ProfessorConsola viewProfessor;

    public Professor(String username, String password) {
        super(username, password);
        this.createdLearningPaths = new LinkedList<>();
        this.createdActivities = new LinkedList<>();
    }

    public LinkedList<LearningPath> getCreatedLearningPaths() {
        return createdLearningPaths;
    }

    public LearningPath getLearningPathByIndex(int index) {
        return createdLearningPaths.get(index);
    }

    public void setCreatedLearningPaths(LinkedList<LearningPath> createdLearningPaths) {
        this.createdLearningPaths = createdLearningPaths;
    }

    public LinkedList<Activity> getCreatedActivities() {
        return createdActivities;
    }

    public void setCreatedActivities(LinkedList<Activity> createdActivities) {
        this.createdActivities = createdActivities;
    }

    public Activity getCreatedActivityByIndex(int index) {
        return createdActivities.get(index);
    }

    @Override
    public String getRole() {
        return ROLE;
    }

    // Learning path management methods

    public LearningPath createLearningPath(String title, String description, LinkedList<String> objectives, int difficultyLevel, LinkedList<String> tags) {
        LearningPath learningPath = new LearningPath(title, description, objectives, difficultyLevel, tags, this);
        createdLearningPaths.add(learningPath);
        return learningPath;
    }

    /**
     * Edits the title of an existing learning path.
     *
     * @param title The new title of the learning path.
     */
    public void editLearningPathTitle(LearningPath learningPath, String title) {
        learningPath.setTitle(title);
        learningPath.updateVersion();
        learningPath.updateModificationDate();
    }

    /**
     * Edits the description of an existing learning path.
     *
     * @param description The new description of the learning path.
     */
    public void editCurrentLearningPathDescription(LearningPath learningPath, String description) {
        learningPath.setDescription(description);
        learningPath.updateVersion();
        learningPath.updateModificationDate();
    }

    /**
     * Edits the objectives of an existing learning path.
     *
     * @param objectives The new objectives of the learning path.
     */
    public void editLearningPathObjectives(LearningPath learningPath, LinkedList<String> objectives) {
        learningPath.setObjectives(objectives);
        learningPath.updateVersion();
        learningPath.updateModificationDate();
    }

    /**
     * Edits the difficulty level of an existing learning path.
     *
     * @param difficultyLevel The new difficulty level of the learning path.
     */
    public void editLearningPathDifficultyLevel(LearningPath learningPath, int difficultyLevel) {
        learningPath.setDifficultyLevel(difficultyLevel);
        learningPath.updateVersion();
        learningPath.updateModificationDate();
    }

    /**
     * Edits the tags of an existing learning path.
     *
     * @param tags The new tags of the learning path.
     */
    public void editLearningPathTags(LearningPath learningPath, LinkedList<String> tags) {
        learningPath.setTags(tags);
        learningPath.updateVersion();
        learningPath.updateModificationDate();
    }

    /**
     * Adds an activity to a learning path.
     */
    public void addActivityToLearningPath(LearningPath learningPath, Activity activity) {
        learningPath.addActivity(activity);
    }

    /**
     * Adds an activity to a learning path at a specific index.
     *
     * @param pos The position at which the activity should be added.
     */
    public void addActivityToLearningPathInPos(LearningPath learningPath, Activity activity, int pos) {
        learningPath.addActivityInPos(activity, pos);
    }

    /**
     * Moves an activity within a learning path from one index to another.
     *
     * @param currentIndex The current index of the activity.
     * @param finalIndex The final index of the activity.
     */
    public void moveActivityInLearningPath(LearningPath learningPath, int currentIndex, int finalIndex) {
        learningPath.moveActivity(currentIndex, finalIndex);
    }

    /**
     * Removes an activity from a learning path by its index.
     *
     * @param index The index of the activity to be removed.
     */
    public void removeActivityByIndexFromLearningPath(LearningPath learningPath, int index) {
        learningPath.removeActivityByIndex(index);
    }

    // Activity management methods
    
    public Activity createExamActivity(String title, String description, String objective,
                                       int expectedDuration, boolean mandatory,
                                       LinkedList<OpenQuestion> oquestion, LinkedList<MultipleOptionQuestion> moquestion) {

        Activity activity = new ExamActivity(title, description, objective, expectedDuration, mandatory, oquestion, moquestion);
        createdActivities.add(activity);
        return activity;
    }

    public Activity createFormActivity(String title, String description, String objective,
                                       int expectedDuration, boolean mandatory,
                                       LinkedList<OpenQuestion> question) {

        Activity activity = new FormActivity(title, description, objective, expectedDuration, mandatory, question);
        createdActivities.add(activity);
        return activity;
    }

    public Activity createResourceActivity(String title, String description, String objective,
                                           int expectedDuration, boolean mandatory, String url) {

        Activity activity = new ResourceActivity(title, description, objective, expectedDuration, mandatory, url);
        createdActivities.add(activity);
        return activity;
    }

    public Activity createTaskActivity(String title, String description, String objective,
                                       int expectedDuration, boolean mandatory, String toDo) {

        Activity activity = new TaskActivity(title, description, objective, expectedDuration, mandatory, toDo);
        createdActivities.add(activity);
        return activity;
    }

    public Activity createQuizActivity(String title, String description, String objective,
                                       int expectedDuration, boolean mandatory,
                                       LinkedList<MultipleOptionQuestion> question, double minscore) {

        Activity activity = new QuizActivity(title, description, objective, expectedDuration, mandatory, question, minscore);
        createdActivities.add(activity);
        return activity;
    }
    


    public void editActivityTitle(Activity activity, String title) {
        activity.setTitle(title);
    }

    public void editActivityDescription(Activity activity, String description) {
        activity.setDescription(description);
    }

    public void editActivityObjective(Activity activity, String objective) {
        activity.setObjective(objective);
    }

    public void editActivityExpectedDuration(Activity activity, int expectedDuration) {
        activity.setExpectedDuration(expectedDuration);
    }

    public void addPrerequisiteToActivityByIndex(LearningPath learningPath, Activity activity, int index) {
        Activity prerequisite = learningPath.getActivityByIndex(index);
        activity.addPrerequisite(prerequisite);
    }

    public void removePrerequisiteFromActivityByIndex(Activity activity, int index) {
        activity.removePrerequisiteByIndex(index);
    }

    public void addFollowUpActivityToActivityByIndex(LearningPath learningPath, Activity activity, int index) {
        Activity followUpActivity = learningPath.getActivityByIndex(index);
        activity.addFollowUp(followUpActivity);
    }

    public void removeFollowUpActivityFromActivityByIndex(Activity activity, int index) {
        activity.removeFollowUp(index);
    }


	public void setViewProfessor(ProfessorConsola viewProfessor) {
		this.viewProfessor = viewProfessor;
	}

	public ProfessorConsola getViewProfessor() {
		return viewProfessor;
	}
	
	public Activity createTrueFalseActivity(String title, String description, String objective, int expectedDuration,
			boolean mandatory, LinkedList<TrueFalseQuestion> question) {

		Activity activity = new TrueFalseActivity(title, description, objective, expectedDuration, mandatory, question);
		createdActivities.add(activity);
		return activity;
	}



    
}
