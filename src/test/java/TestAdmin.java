import api.IAdmin;
import api.core.impl.Admin;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by seest on 3/2/2017.
 */
public class TestAdmin {
    private IAdmin admin;

    @Before
    public void setup() {
        this.admin = new Admin();
    }

    /**
     * Adjust the capacity (maximum number of students) of class {@code className} to new capacity {@code capacity}
     *
     * @param className Name of the class for which capacity should be changed
     * @param year Year in which this class is taught
     * @param capacity New capacity of this class, must be at least equal to the number of students enrolled
     */
    @Test
    public void testCreateClass(){
        this.admin.createClass();
        assertTrue
    }
}
