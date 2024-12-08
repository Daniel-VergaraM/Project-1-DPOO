package learningpath.activity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Scanner;

import tracker.ActivityTracker;
import utils.Generator;

public abstract class Activity implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String id;
    protected String title;
    protected String description;
    protected String objective;
    protected int expectedDuration;
    protected boolean mandatory;
    protected LinkedList<ActivityTracker> activityTrackers;
    protected LinkedList<Activity> prerequisites;
    protected LinkedList<Activity> followUpActivities;
	private boolean done = false;
	public static String TYPE = "activity";


    
    public Activity(String title, String description, String objective, int expectedDuration, boolean mandatory) {
        Generator u = Generator.getInstance();
        this.id = u.generateId("Activity");
        this.title = title;
        this.description = description;
        this.objective = objective;
        this.expectedDuration = expectedDuration;
        this.mandatory = mandatory;
        this.activityTrackers = new LinkedList<>();
        this.prerequisites = new LinkedList<>();
        this.followUpActivities = new LinkedList<>();
    }
    
    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public String getObjective() {
        return this.objective;
    }

    public int getExpectedDuration() {
        return this.expectedDuration;
    }

    public boolean isMandatory() {
        return this.mandatory;
    }

    public LinkedList<ActivityTracker> getActivityTrackers() {
        return this.activityTrackers;
    }

    public LinkedList<Activity> getPrerequisites() {
        return this.prerequisites;
    }

    public LinkedList<Activity> getFollowUpActivities() {
        return this.followUpActivities;
    }

    public void setId(String i) {
        this.id = i;
    }

    public void setTitle(String t) {
        this.title = t;
    }

    public void setDescription(String d) {
        this.description = d;
    }

    public void setObjective(String o) {
        this.objective = o;
    }

    public void setExpectedDuration(int i) {
        this.expectedDuration = i;
    }

    public void setMandatory(boolean m) {
        this.mandatory = m;
    }

    public void addActivityTracker(ActivityTracker activityTracker) {
        this.activityTrackers.add(activityTracker);
    }

    public void removeActivityTrackerByIndex(int index) {
        this.activityTrackers.remove(index);
    }

    public void addPrerequisite(Activity activity) {
        this.prerequisites.add(activity);
    }

    public void removePrerequisiteByIndex(int index) {
        this.prerequisites.remove(index);
    }

    public void addFollowUp(Activity activity) {
        this.followUpActivities.add(activity);
    }

    public void removeFollowUp(int index) {
        this.followUpActivities.remove(index);
    }
    
	public boolean isDone() {
		return this.done;
	}
	
	public void setDone(boolean done) {
		this.done = done;
	}
    
	
    
    
    public static String getTYPE() {
		return TYPE;
	}

	public static void setTYPE(String tYPE) {
		TYPE = tYPE;
	}

	public abstract void doActivity();

}
