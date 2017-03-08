import api.IAdmin;
import api.IStudent;
import api.core.impl.Admin;
import api.core.impl.Student;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.*;

/**
 * Created by seest on 3/2/2017.
 */
public class TestAdmin {
    private IAdmin admin;

    @Before
    public void setup() {
        this.admin = new Admin();
    }

    @Test
    public void testCreateClass(){ //Checks if can create class with valid year, should be true
        this.admin.createClass("Test", 2017, "Instructor", 15);
        assertTrue(this.admin.classExists("Test", 2017));
    }

    @Test
    public void testCreateClass2(){//Checks if can create class with invalid year, should be false
        this.admin.createClass("Test", 2016, "Instructor", 15);
        assertFalse(this.admin.classExists("Test", 2016));
    }

    @Test
    public void testCreateClass3(){//Checks if can create class for instructor that's already assigned to 2 classes, should be false
        this.admin.createClass("Class", 2017, "Instructor", 15);
        this.admin.createClass("Class2", 2017, "Instructor", 15);
        this.admin.createClass("Class3", 2017, "Instructor", 15);
        assertFalse(this.admin.classExists("Class3", 2017));
    }

    @Test
    public void testCreateClass4(){//Checks if can create class with capacity of 0, should be false
        this.admin.createClass("Class", 2017, "Instructor", 0);
        assertFalse(this.admin.classExists("Class", 2017));
    }

    @Test
    public void testCreateClass5(){// Checks if can create class of same name with different instructor, should be false
        this.admin.createClass("Class", 2017, "Instructor", 15);
        this.admin.createClass("Class", 2017, "Instructor2", 15);
        assertEquals("Instructor", this.admin.getClassInstructor("Class", 2017));
    }

    @Test
    public void testCreateClass6(){//Checks if can create class of negative number
        this.admin.createClass("Class", 2017, "Instructor", -5);
        assertFalse(this.admin.classExists("Class", 2017));
    }

    @Test
    public void testChangeCapacity(){//Checks if can change capacity to be larger than enroll size of class, should be true
        this.admin.createClass("Class", 2017, "Instructor", 5);
        IStudent student = new Student();
        student.registerForClass("Student", "Class", 2017);
        IStudent student2 = new Student();
        student.registerForClass("Student2", "Class", 2017);
        IStudent student3 = new Student();
        student.registerForClass("Student3", "Class", 2017);
        IStudent student4 = new Student();
        student.registerForClass("Student4", "Class", 2017);
        IStudent student5 = new Student();
        student.registerForClass("Student5", "Class", 2017);
        this.admin.changeCapacity("Class", 2017, 7);
        assertTrue(7 >= this.admin.getClassCapacity("Class", 2017));
    }

    @Test
    public void testChangeCapacity2(){//Checks if can change capacity to be smaller than enroll size of class, should be false
        this.admin.createClass("Class", 2017, "Instructor", 5);
        IStudent student = new Student();
        student.registerForClass("Student", "Class", 2017);
        IStudent student2 = new Student();
        student.registerForClass("Student2", "Class", 2017);
        IStudent student3 = new Student();
        student.registerForClass("Student3", "Class", 2017);
        IStudent student4 = new Student();
        student.registerForClass("Student4", "Class", 2017);
        IStudent student5 = new Student();
        student.registerForClass("Student5", "Class", 2017);
        this.admin.changeCapacity("Class", 2017, 4);
        assertFalse(4 <= this.admin.getClassCapacity("Class", 2017));
    }

    @Test
    public void testChangeCapacity3(){//Checks if can change capacity of a class to the same size, should be true
        this.admin.createClass("Class", 2017, "Instructor", 5);
        IStudent student = new Student();
        student.registerForClass("Student", "Class", 2017);
        IStudent student2 = new Student();
        student.registerForClass("Student2", "Class", 2017);
        IStudent student3 = new Student();
        student.registerForClass("Student3", "Class", 2017);
        IStudent student4 = new Student();
        student.registerForClass("Student4", "Class", 2017);
        IStudent student5 = new Student();
        student.registerForClass("Student5", "Class", 2017);
        this.admin.changeCapacity("Class", 2017, 5);
        assertEquals(5, this.admin.getClassCapacity("Class", 2017));
    }

    @Test
    public void testChangeCapacity4(){//Checks if can change capacity of a class to the 0, should be false
        this.admin.createClass("Class", 2017, "Instructor", 5);
        IStudent student = new Student();
        student.registerForClass("Student", "Class", 2017);
        IStudent student2 = new Student();
        student.registerForClass("Student2", "Class", 2017);
        IStudent student3 = new Student();
        student.registerForClass("Student3", "Class", 2017);
        IStudent student4 = new Student();
        student.registerForClass("Student4", "Class", 2017);
        IStudent student5 = new Student();
        student.registerForClass("Student5", "Class", 2017);
        this.admin.changeCapacity("Class", 2017, 0);
        assertFalse(0 < this.admin.getClassCapacity("Class", 2017));
    }

    @Test
    public void testChangeCapacity5(){//Checks if can change capacity of a class that does not exist, should be null
        this.admin.changeCapacity("Test", 2017, 53);
        assertNull(this.admin.getClassCapacity("Test", 2017));
    }

    @Test
    public void testChangeCapacity6(){//Checks if can change capacity of class to negative, should be null
        this.admin.createClass("Test", 2017, "Instructor", 4);
        this.admin.changeCapacity("Test", 2017, -1);
        assertFalse(-1 < this.admin.getClassCapacity("Test", 2017));
    }
}
