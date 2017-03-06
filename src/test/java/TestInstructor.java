import api.IInstructor;
import api.core.impl.Instructor;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by seest on 3/4/2017.
 */
public class TestInstructor {
    private IInstructor instructor;

    @Before
    public void setup(){this.instructor = new Instructor();}

    /**
     * Add homework to class {@code className}, taught in year {@code year}, with title {@code homeworkName}, provided this instructor has been assigned to this class.
     *
     * @param instructorName The name of the instructor who assigns the homework
     * @param className Class for which the homework should be added
     * @param year Year of class
     * @param homeworkName Name of the homework assignment to be added
     * @param homeworkDescription Description of homework
     */
    @Test
    public void testAddHomework(){
       this.instructor.addHomework("Instructor", "Class", 2017, "Assignment", "Description");
       assertFalse(this.instructor.homeworkExists("Class", 2017, "Assignment"));
    }

    @Test
    public void testAddHomework2(){
        this.instructor.addHomework("Instructor", "Class", 2017, "Assignment", "Description");
        assertTrue(this.instructor.homeworkExists("Class", 2017, "Paper"));
    }
}
