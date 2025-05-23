import org.eclipse.ice.data.IDataElementFactory;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;


/**
 * This is a factory for creating Test instances.
 */
@ApplicationScoped
public class TestFactory implements IDataElementFactory<Test> {
	
	/**
	 * This function produces a default (i.e., nullary-constructed) instance
	 * of Test. 
	 */
	@Produces public Test build() throws Exception {
		return TestImplementation.builder().build();
	}

	/**
	 * This function returns the configurable builder that can be used to
	 * create customized instances of Test.
	 */	
	@Produces public TestImplementation.TestImplementationBuilder builder() {
		return TestImplementation.builder();
	}
}