package learningpath.activity;

import java.util.concurrent.TimeUnit;

public class ResourceActivity extends Activity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String url;
	public String TYPE = "resource";


	public ResourceActivity(String title, String description, String objective, int expectedDuration, boolean mandatory,
			String url) {
		super(title, description, objective, expectedDuration, mandatory);
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public void doActivity() {
		System.out.println("Resource Activity: " + this.title);
        System.out.println("Description: " + this.description);
        System.out.println("Objective: " + this.objective);
        System.out.println("Expected Duration: " + this.expectedDuration);
        System.out.println("URL: " + this.url);
        try {
        	System.out.println("Reading resource...");
			TimeUnit.SECONDS.sleep(5);
			System.out.println("Resource read.");
		} catch (InterruptedException e) {
			System.out.println("Error reading resource.");
			e.printStackTrace();
		}
	}

}
