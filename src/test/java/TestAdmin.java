import api.IAdmin;
import api.core.impl.Admin;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
    public void testCreateClass(){
        this.admin.createClass("Test", 2017, "Instructor", 15);
        assertTrue(this.admin.classExists("Test", 2017));
    }

    @Test
    public void testCreateClass2(){
        this.admin.createClass("Test", 2016, "Instructor", 15);
        assertFalse(this.admin.classExists("Test", 2016));
    }

    @Test
    public void testChangeCapacity(){
        this.admin.changeCapacity("Test", 2017, 53);
        assertTrue(53 >= this.admin.getClassCapacity("Test", 2017));
    }

    @Test
    public void testChangeCapacity2(){
        this.admin.changeCapacity("Test", 2017, 4);
        assertFalse(4 < this.admin.getClassCapacity("Test", 2017));
    }
}
