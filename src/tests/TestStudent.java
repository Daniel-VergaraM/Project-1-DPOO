package tests;

import learningpath.LearningPath;
import tracker.ActivityTracker;
import tracker.ProgressTracker;
import users.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.LinkedList;
import java.util.List;

class StudentTest {
	private Student student;
    private LearningPath learningPath;

    @BeforeEach
    public void setUp() {
        student = new Student("testuser", "password");
        learningPath = new LearningPath("Programming Basics", "Intro to Programming", new LinkedList<>(), 5, new LinkedList<>(), null);
    }

    @Test
    public void testAddInterest() {
        student.addInterest("Java");
        assertTrue(student.getInterests().contains("Java"), "Interest should be added to the student's interests list.");
    }

    @Test
    public void testEnrollInLearningPath() {
        student.enrollInLearningPath(learningPath);
        ProgressTracker progressTracker = student.getProgressTrackers().getFirst();

        assertNotNull(progressTracker, "A progress tracker should be created upon enrolling.");
        assertEquals(learningPath, progressTracker.getLearningpath(), "The progress tracker should be linked to the correct learning path.");
    }

    @Test
    public void testRemoveInterest() {
        student.addInterest("Java");
        student.removeInterest(0);
        assertFalse(student.getInterests().contains("Java"), "Interest should be removed from the student's interests list.");
    }
}
