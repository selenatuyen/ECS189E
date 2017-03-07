import api.IAdmin;
import api.IInstructor;
import api.IStudent;
import api.core.impl.Admin;
import api.core.impl.Instructor;
import api.core.impl.Student;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by seest on 3/5/2017.
 */
public class TestStudent {
    private IStudent student;

    @Before
    public void setup(){this.student = new Student();}

    @Test
    public void testRegisterForClass(){//Check if student can register for class that does not exist, should be false
        this.student.registerForClass("Name", "Class", 2017);
        assertFalse(this.student.isRegisteredFor("Name", "Class", 2017));
    }

    @Test
    public void testRegisterForClass2(){//Check if student can register for class that exists with enrollment space, should be true
        IAdmin admin = new Admin();
        admin.createClass("Class", 2017, "Instructor", 15);
        this.student.registerForClass("Student", "Class", 2017);
        assertTrue(this.student.isRegisteredFor("Student", "Class", 2017));
    }

    @Test
    public void testRegisterForClass3(){//Check if student can register for class that exists without enrollment space, should be false
        IAdmin admin = new Admin();
        admin.createClass("Class", 2017, "Instructor", 3);
        this.student.registerForClass("Name","Class", 2017);
        this.student.registerForClass("Name2","Class", 2017);
        this.student.registerForClass("Name3","Class", 2017);
        this.student.registerForClass("Name4","Class", 2017);
        assertFalse(this.student.isRegisteredFor("Name4", "Class", 2017));
    }

    @Test
    public void testRegisterForClass4(){//Checks if student can register for class twice, should be false
        IAdmin admin = new Admin();
        admin.createClass("Class", 2017, "Instructor", 4);
        this.student.registerForClass("Name", "Class", 2017);
        this.student.registerForClass("Name", "Class", 2017);
        assertFalse(this.student.isRegisteredFor("Name", "Class", 2017));
    }

    @Test
    public void testDropClass(){//Check if registered student can drop class that has not ended, should be false
        IAdmin admin = new Admin();
        admin.createClass("Class", 2017, "Instructor", 4);
        this.student.registerForClass("Name", "Class", 2017);
        this.student.dropClass("Name", "Class", 2017);
        assertFalse(this.student.isRegisteredFor("Name", "Class", 2017));
    }

    @Test
    public void testDropClass2(){//Check if non-registered student can drop class that has not ended, should be false
        IAdmin admin = new Admin();
        admin.createClass("Class", 2017, "Instructor", 4);
        this.student.dropClass("Name", "Class", 2017);
        assertFalse(this.student.isRegisteredFor("Name", "Class", 2017));
    }

    @Test
    public void testDropClass3(){//Check if a registered student can drop a class that has ended, should be false
        IAdmin admin = new Admin();
        admin.createClass("Class", 2017, "Instructor", 4);
        this.student.registerForClass("Name", "Class", 2017);
        this.student.dropClass("Name", "Class", 2016);
        assertFalse(this.student.isRegisteredFor("Name", "Class", 2016));
    }

    @Test
    public void testDropClass4(){//Check if a registered student can drop a class twice, should be false
        IAdmin admin = new Admin();
        admin.createClass("Class", 2017, "Instructor", 4);
        this.student.registerForClass("Name", "Class", 2017);
        this.student.dropClass("Name", "Class", 2017);
        this.student.dropClass("Name", "Class", 2017);
        assertFalse(this.student.isRegisteredFor("Name", "Class", 2017));
    }

    @Test
    public void testSubmitHomework(){//Checks if a registered student can submit homework, should be true
        IAdmin admin = new Admin();
        admin.createClass("Class", 2017, "Instructor", 4);
        this.student.registerForClass("Name", "Class", 2017);
        IInstructor instructor = new Instructor();
        instructor.addHomework("Instructor", "Class", 2017, "Homework", "Description");
        this.student.submitHomework("Name", "Homework", "Answer", "Class", 2017);
        assertTrue(this.student.hasSubmitted("Name","Homework", "Class", 2017 ));
    }

    @Test
    public void testSubmitHomework2(){//Checks if a registered student can submit homework to a homework that does not exist, should be false
        IAdmin admin = new Admin();
        admin.createClass("Class", 2017, "Instructor", 5);
        this.student.registerForClass("Name", "Class", 2017);
        this.student.submitHomework("Name", "Homework", "Answer", "Class", 2017);
        assertFalse(this.student.hasSubmitted("Name", "Homework", "Class", 2017));
    }

    @Test
    public void testSubmitHomework3(){//Checks if a non-registered student can submit homework, should be false
        IAdmin admin = new Admin();
        admin.createClass("Class", 2017, "Instructor", 5);
        IInstructor instructor = new Instructor();
        instructor.addHomework("Instructor", "Class", 2017, "Homework", "Description");
        this.student.submitHomework("Name", "Homework", "Answer", "Class", 2017);
        assertFalse(this.student.hasSubmitted("Name", "Homework", "Class", 2017));
    }

    @Test
    public void testSubmitHomework4(){//Checks if a registered student can submit homework to a past class, should be false
        IAdmin admin = new Admin();
        admin.createClass("Class", 2016, "Instructor", 5);
        this.student.registerForClass("Name", "Class", 2016);
        IInstructor instructor = new Instructor();
        instructor.addHomework("Instructor", "Class", 2016, "Homework", "Description");
        this.student.submitHomework("Name", "Homework", "Answer", "Class", 2016);
        assertFalse(this.student.hasSubmitted("Name", "Homework", "Class", 2017));
    }
}
