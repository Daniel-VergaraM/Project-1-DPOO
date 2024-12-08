package tracker;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;

import learningpath.activity.Activity;

public class ActivityTracker implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Activity activity;
	private int dedicatedTime;
	private String completionStatus;
	protected Date startDate;
	protected Date completionDate;
	private LinkedList<Integer> ratings;
	private boolean completa;
	
	public ActivityTracker(Activity activity) {
		this.activity = activity;
		this.dedicatedTime = 0;
		this.completionStatus = "Not started";
		this.startDate = null;
		this.completionDate = null;
		this.ratings = new LinkedList<>();
		activity.addActivityTracker(this);
	}
	
	
	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public int getDedicatedTime() {
		return dedicatedTime;
	}

	public void setDedicatedTime(int dedicatedTime) {
		this.dedicatedTime = dedicatedTime;
	}

	public String getCompletionStatus() {
		return completionStatus;
	}

	public void setCompletionStatus(String status) {
		this.completionStatus = status;
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
	
	public void addRating(int rating) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("La calificacion debe estar entre 1 y 5");
        }
        ratings.add(rating);
    }
	
	public double getAverageRating() {
	    if (ratings.isEmpty()) {
	        return 0.0;
	    }

	    int sum = 0;
	    for (int rating : ratings) {
	        sum += rating;
	    }
	    return (double) sum / ratings.size();
	}

	
	
	public boolean isCompleta() {
		return completa;
	}


	public void setCompleta(boolean completa) {
		this.completa = completa;
	}


	public void recordActivityStart() {
		this.completionStatus = "In Progress";
		this.startDate = new Date();
	}
	
	public void recordActivityCompletion() {
		this.completionStatus = "Completed";
		this.completionDate = new Date();
	}
	
	

}
