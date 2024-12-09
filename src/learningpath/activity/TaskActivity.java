package learningpath.activity;

import java.util.concurrent.TimeUnit;

public class TaskActivity extends Activity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String toDo;
	public String TYPE = "task";


	public TaskActivity(String title, String description, String objective, int expectedDuration, boolean mandatory,
			String toDo) {
		super(title, description, objective, expectedDuration, mandatory);
		this.toDo = toDo;
	}

	public String getToDo() {
		return toDo;
	}

	public void setToDo(String toDo) {
		this.toDo = toDo;
	}
	
	public void doActivity() {
		System.out.println("Task Activity: " + this.title);
		System.out.println("Description: " + this.description);
		System.out.println("Objective: " + this.objective);
		System.out.println("Expected Duration: " + this.expectedDuration);
		System.out.println("To do: " + this.toDo);
		try {
			System.out.println("Doing task...");
			TimeUnit.SECONDS.sleep(5);
			System.out.println("Task done.");
		} catch (InterruptedException e) {
			System.out.println("Error doing task.");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
