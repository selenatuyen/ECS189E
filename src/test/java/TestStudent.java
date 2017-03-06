import api.IStudent;
import api.core.impl.Student;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;


/**
 * Created by seest on 3/5/2017.
 */
public class TestStudent {
    private IStudent student;

    @Before
    public void setup(){this.student = new Student();}

    @Test
    public void testRegisterForClass(){
        this.student.registerForClass("Name", "Class", 2017);
        assertTrue(this.student.isRegisteredFor("Name", "Class", 2017));
    }
}
