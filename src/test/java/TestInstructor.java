import api.IAdmin;
import api.IInstructor;
import api.core.impl.Admin;
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


    @Test
    public void testAddHomework(){ //Checks if can add class when no class exists, should fail
       this.instructor.addHomework("Instructor", "Class", 2017, "Assignment", "Description");
       assertTrue(this.instructor.homeworkExists("Class", 2017, "Assignment"));
    }

    @Test
    public void testAddHomework2(){ //Checks given a class if assigned instructor adds homework, should pass
        IAdmin admin = new Admin();
        admin.createClass("Class", 2017, "Instructor", 15);
        this.instructor.addHomework("Instructor", "Class", 2017, "Assignment", "Description");
        assertTrue(this.instructor.homeworkExists("Class", 2017, "Assignment"));
    }

    @Test
    public void testAddHomework3(){ //Checks given a class if not assigned instructor adds homework, should fail
        IAdmin admin = new Admin();
        admin.createClass("Class", 2017, "Instructor2", 15);
        this.instructor.addHomework("Instructor", "Class", 2017, "Assignment", "Description");
        assertFalse(this.instructor.homeworkExists("Class", 2017, "Assignment"));
    }
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
    public void testAddHomework4(){ //Checks given a class if assigned instructor adds homework to class with wrong year, should fail
        IAdmin admin = new Admin();
        admin.createClass("Class", 2017, "Instructor", 15);
        this.instructor.addHomework("Instructor", "Class", 2016, "Assignment", "Description");
        assertFalse(this.instructor.homeworkExists("Class", 2016, "Assignment"));
    }
}
