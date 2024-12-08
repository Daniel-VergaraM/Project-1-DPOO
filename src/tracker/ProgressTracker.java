package tracker;

import learningpath.LearningPath;
import learningpath.activity.Activity;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;

public class ProgressTracker implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String studentUsername;
	private LearningPath learningpath;
	private LinkedList<ActivityTracker> activityTrackers;
	private Date startDate;
	private Date completionDate;
	private float progress;
	private boolean completionStatus;
	
	
	public ProgressTracker(String studentUsername, LearningPath learningpath) {
		
		this.studentUsername = studentUsername;
		this.learningpath = learningpath;
		this.activityTrackers = new LinkedList<>();
		for (Activity activity: learningpath.getActivities()) {
			activityTrackers.add(new ActivityTracker(activity));
		}
		this.startDate = new Date();
		this.completionDate = null;
		this.progress = 0.0f;
		this.completionStatus = false;
	}

	public String getStudentUsername() {
		return studentUsername;
	}

	public void setStudentUsername(String studentUsername) {
		this.studentUsername = studentUsername;
	}

	public LearningPath getLearningpath() {
		return learningpath;
	}

	public void setLearningPath(LearningPath learningpath) {
		this.learningpath = learningpath;
	}

	public LinkedList<ActivityTracker> getActivitiesTracker() {
		return activityTrackers;
	}

	public ActivityTracker getActivityTrackerByIndex(int index) {
		return activityTrackers.get(index);
	}

	public void setActivityTrackers(LinkedList<ActivityTracker> activityTrackers) {
		this.activityTrackers = activityTrackers;
	}

	public LinkedList<ActivityTracker> getActivityTrackers() {
		return activityTrackers;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}

	public float getProgress() {
		return progress;
	}

	public Boolean getCompletionStatus() {
		return completionStatus;
	}
	
	public void calculateProgress() {
		int totalMandatory = 0;
		int totalMandatoryCompleted = 0;
		boolean isCompleted;
		boolean isMandatory;

		for (ActivityTracker activitytracker: this.activityTrackers) {

			isCompleted = activitytracker.getCompletionStatus().equals("Completed");
			isMandatory = activitytracker.getActivity().isMandatory();

			if (isMandatory){

				totalMandatory++;

				if (isCompleted) {
					totalMandatoryCompleted++;
				}
			}
		}
		progress = (float) totalMandatoryCompleted /totalMandatory * 100;

		if (progress == 100.0f) {
			completionStatus = true;
			completionDate = new Date();
		}
	}

	public void recordActivityStart(ActivityTracker activityTracker) {
		activityTracker.recordActivityStart();
	}

	public void recordActivityCompletion(ActivityTracker activityTracker) {
		activityTracker.recordActivityCompletion();
		calculateProgress();
	}

	public void setProgress(float progress) {
		this.progress = progress;
	}
	
	


}
