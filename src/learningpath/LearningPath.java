package learningpath;

import java.util.Date;
import java.util.LinkedList;

/**
 * The LearningPath class represents a structured learning path with various attributes
 * such as title, description, objectives, difficulty level, duration, rating, professor,
 * creation date, update date, version, and a list of activities.
 *
 * @param <Professor> The type representing a professor.
 * @param <Activity> The type representing an activity.
 */
public class LearningPath<Professor, Activity> {
    // Attributes of the LearningPath
    private final int id; // Unique identifier of the learning path
    private String title; // Title of the learning path
    private String description; // Description of the learning path
    private LinkedList<String> objectives; // Objectives of the learning path
    private int difficultyLevel; // Difficulty level of the learning path
    private int duration; // Duration of the learning path in hours
    private float rating; // Rating of the learning path
    private LinkedList<String> tags; // Tags associated with the learning path
    private final Professor professor; // Professor associated with the learning path
    private final Date creationDate; // Date when the learning path was created
    private Date modificationDate; // Date when the learning path was last updated
    private int version; // Version of the learning path
    private LinkedList<Activity> activities; // List of activities in the learning path

    /**
     * Constructor to initialize a LearningPath object with the given parameters.
     *
     * @param id The unique identifier of the learning path.
     * @param title Title of the learning path.
     * @param description Description of the learning path.
     * @param objectives Objectives of the learning path.
     * @param difficultyLevel Difficulty level of the learning path.
     * @param tags Tags associated with the learning path.
     * @param professor Professor associated with the learning path.
     */
    public LearningPath(int id, String title, String description, LinkedList<String> objectives, int difficultyLevel, LinkedList<String> tags, Professor professor) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.objectives = objectives;
        this.difficultyLevel = difficultyLevel;
        this.duration = 0;
        this.rating = 0; // Initial rating is set to 0
        this.tags = tags;
        this.professor = professor;
        this.creationDate = new Date(); // Set creation date to current date
        this.modificationDate = new Date(); // Set update date to current date
        this.version = 1; // Initial version is set to 1
        this.activities = new LinkedList<Activity>(); // Initialize the list of activities
    }

    // Getter and setter methods for each attribute

    /**
     * Gets the title of the learning path.
     * @return the title of the learning path.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the learning path.
     * @param title the new title of the learning path.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the description of the learning path.
     * @return the description of the learning path.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the learning path.
     * @param description the new description of the learning path.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the objectives of the learning path.
     * @return the objectives of the learning path.
     */
    public LinkedList<String> getObjectives() {
        return objectives;
    }

    /**
     * Sets the objectives of the learning path.
     * @param objectives the new objectives of the learning path.
     */
    public void setObjectives(LinkedList<String> objectives) {
        this.objectives = objectives;
    }

    /**
     * Gets the difficulty level of the learning path.
     * @return the difficulty level of the learning path.
     */
    public int getDifficultyLevel() {
        return difficultyLevel;
    }

    /**
     * Sets the difficulty level of the learning path.
     * @param difficultyLevel the new difficulty level of the learning path.
     */
    public void setDifficultyLevel(int difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    /**
     * Gets the duration of the learning path.
     * @return the duration of the learning path in hours.
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Gets the rating of the learning path.
     * @return the rating of the learning path.
     */
    public float getRating() {
        return rating;
    }

    /**
     * Gets the tags associated with the learning path.
     * @return the tags associated with the learning path.
     */
    public LinkedList<String> getTags() {
        return tags;
    }

    /**
     * Sets the tags associated with the learning path.
     * @param tags the new tags associated with the learning path.
     */
    public void setTags(LinkedList<String> tags) {
        this.tags = tags;
    }

    /**
     * Gets the update date of the learning path.
     * @return the update date of the learning path.
     */
    public Date getModificationDate() {
        return modificationDate;
    }

    /**
     * Sets the update date of the learning path.
     * @param modificationDate the new update date of the learning path.
     */
    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    /**
     * Gets the version of the learning path.
     * @return the version of the learning path.
     */
    public int getVersion() {
        return version;
    }

    /**
     * Sets the version of the learning path.
     * @param version the new version of the learning path.
     */
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * Gets the list of activities in the learning path.
     * @return the list of activities.
     */
    public LinkedList<Activity> getActivities() {
        return activities;
    }

    /**
     * Adds an activity to the learning path.
     * @param activity the activity to be added.
     */
    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    /**
     * Adds an activity to the learning path at a specific index.
     * @param activity the activity to be added.
     * @param index the index at which the activity should be added.
     */
    public void addActivityByIndex(Activity activity, int index) {
        activities.add(index, activity);
    }

    /**
     * Moves an activity within the learning path from one index to another.
     * @param currentIndex the current index of the activity.
     * @param newIndex the new index of the activity.
     */
    public void moveActivity(int currentIndex, int newIndex) {
        Activity activity = activities.remove(currentIndex);
        activities.add(newIndex, activity);
    }

    /**
     * Removes an activity from the learning path.
     * @param index the index of the activity to be removed.
     */
    public void removeActivityByIndex(int index) {
        activities.remove(index);
    }

    /**
     * Updates the version of the learning path.
     */
    public void updateVersion() {
        this.version++;
    }

    /**
     * Updates the modification date of the learning path to the current date.
     */
    public void updateModificationDate() {
        this.modificationDate = new Date();
    }
}
