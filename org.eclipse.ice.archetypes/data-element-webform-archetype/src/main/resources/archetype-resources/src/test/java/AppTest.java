package $package;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

public class AppTest {
   
    @Test
    public void shouldAnswerWithTrue() {
    	Example ex = new ExampleImplementation();
    	assertTrue(ex.getExampleString().equals("This is a string!"));
    	System.out.println("WARNING - This is a generated test stub. Reimplement ASAP.");
    }
}
