import api.IAdmin;
import api.IInstructor;
import api.IStudent;
import api.core.impl.Admin;
import api.core.impl.Instructor;
import api.core.impl.Student;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.*;

/**
 * Created by seest on 3/4/2017.
 */
public class TestInstructor {
    private IInstructor instructor;

    @Before
    public void setup(){this.instructor = new Instructor();}

    @Test
    public void testAddHomework(){ //Checks if can add homework when no class exists, should be false
       this.instructor.addHomework("Instructor", "Class", 2017, "Assignment", "Description");
       assertFalse(this.instructor.homeworkExists("Class", 2017, "Assignment"));
    }

    @Test
    public void testAddHomework2(){ //Checks given a class if assigned instructor can add homework, should be true
        IAdmin admin = new Admin();
        admin.createClass("Class", 2017, "Instructor", 15);
        this.instructor.addHomework("Instructor", "Class", 2017, "Assignment", "Description");
        assertTrue(this.instructor.homeworkExists("Class", 2017, "Assignment"));
    }

    @Test
    public void testAddHomework3(){ //Checks given a class if not assigned instructor can add homework, should be false
        IAdmin admin = new Admin();
        admin.createClass("Class", 2017, "Instructor2", 15);
        this.instructor.addHomework("Instructor", "Class", 2017, "Assignment", "Description");
        assertFalse(this.instructor.homeworkExists("Class", 2017, "Assignment"));
    }

    @Test
    public void testAddHomework4(){ //Checks given a class if assigned instructor adds homework to class with wrong year, should fail
        IAdmin admin = new Admin();
        admin.createClass("Class", 2017, "Instructor", 15);
        this.instructor.addHomework("Instructor", "Class", 2016, "Assignment", "Description");
        assertFalse(this.instructor.homeworkExists("Class", 2016, "Assignment"));
    }

    @Test
    public void testAddHomework5(){//Checks given a class if assigned instructor can add multiple homework of the same name, should fail
        IAdmin admin = new Admin();
        admin.createClass("Class", 2017, "Instructor", 15);
        this.instructor.addHomework("Instructor", "Class", 2017, "Assignment", "Description");
        this.instructor.addHomework("Instructor", "Class", 2017, "Assignment", "Description");
        assertFalse(this.instructor.homeworkExists("Class", 2017, "Assignment"));
    }

    @Test
    public void testAssignGrade(){//Checks given an assigned instructor if can assign grade to enrolled student who submitted homework, should be not null
        IAdmin admin = new Admin();
        admin.createClass("Class", 2017, "Instructor", 15);
        IStudent student = new Student();
        student.registerForClass("Student", "Class", 2017);
        this.instructor.addHomework("Instructor", "Class", 2017, "Homework", "Description");
        student.submitHomework("Student", "Homework", "Answer", "Class", 2017);
        this.instructor.assignGrade("Instructor", "Class", 2017, "Homework", "Student", 90);
        assertNotNull(this.instructor.getGrade("Class", 2017, "Homework", "Student"));
    }

    @Test
    public void testAssignGrade2(){//Checks given an assigned instructor if can assign grade to enrolled student who has not submitted homework, should be null
        IAdmin admin = new Admin();
        admin.createClass("Class", 2017, "Instructor", 15);
        IStudent student = new Student();
        student.registerForClass("Student", "Class", 2017);
        this.instructor.addHomework("Instructor", "Class", 2017, "Homework", "Description");
        this.instructor.assignGrade("Instructor", "Class", 2017, "Homework", "Student", 90);
        assertNull(this.instructor.getGrade("Class", 2017, "Homework", "Student"));
    }

    @Test
    public void testAssignGrade3(){//Checks given a not assigned instructor if can assign grade to enrolled student who submitted homework, should be null
        IAdmin admin = new Admin();
        admin.createClass("Class", 2017, "Instructor2", 15);
        IStudent student = new Student();
        student.registerForClass("Student", "Class", 2017);
        this.instructor.addHomework("Instructor2", "Class", 2017, "Homework", "Description");
        student.submitHomework("Student", "Homework", "Answer", "Class", 2017);
        this.instructor.assignGrade("Instructor", "Class", 2017, "Homework", "Student", 90);
        assertNull(this.instructor.getGrade("Class", 2017, "Homework", "Student"));
    }

    @Test
    public void testAssignGrade4(){//Checks given an assigned instructor if can assign grade to a not enrolled student, should be null
        IAdmin admin = new Admin();
        admin.createClass("Class", 2017, "Instructor", 15);
        IStudent student = new Student();
        student.registerForClass("Student", "Class2", 2017);
        this.instructor.addHomework("Instructor", "Class", 2017, "Homework", "Description");
        student.submitHomework("Student", "Homework", "Answer", "Class", 2017);
        this.instructor.assignGrade("Instructor", "Class2", 2017, "Homework", "Student", 80);
        assertNull(this.instructor.getGrade("Class2", 2017, "Homework", "Student"));
    }

    @Test
    public void testAssignGrade5(){//Checks given an assigned instructor if can assign grade to an enrolled student who submitted for a homework that doesn't exist, should be null
        IAdmin admin = new Admin();
        admin.createClass("Class", 2017, "Instructor", 15);
        IStudent student = new Student();
        student.registerForClass("Student", "Class", 2017);
        this.instructor.addHomework("Instructor", "Class", 2017, "Homework", "Description");
        student.submitHomework("Student", "Homework2", "Answer", "Class",2017);
        this.instructor.assignGrade("Instructor", "Class", 2017, "Homework2", "Student", 80);
        assertNull(this.instructor.getGrade("Class", 2017, "Homework2", "Student"));
    }

    @Test
    public void testAssignGrade6(){//Checks given an assigned instructor if can assign grade to a not enrolled student who submits homework, should be null
        IAdmin admin = new Admin();
        admin.createClass("Class", 2017, "Instructor", 15);
        IStudent student = new Student();
        this.instructor.addHomework("Instructor", "Class", 2017, "Homework", "Description");
        student.submitHomework("Student", "Homework", "Answer", "Class", 2017);
        this.instructor.assignGrade("Instructor", "Class", 2017, "Homework", "Student", 80);
        assertNull(this.instructor.getGrade("Class", 2017, "Homework", "Student"));
    }
}
