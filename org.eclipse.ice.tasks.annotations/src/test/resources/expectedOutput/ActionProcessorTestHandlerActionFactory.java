package org.eclipse.ice.tests.tasks.annotations;

import org.eclipse.ice.tasks.IActionFactory;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import org.eclipse.ice.tests.tasks.annotations.TestData;

/**
 * This is a factory for creating ActionProcessorTestHandlerAction instances.
 *
 * It implements IActionFactory and provides the required method, and it also
 * provides an @Produces-annotated method for building the class that
 * implements IActionFactory. In general, the build() operation should be
 * preferred and the buildImplementation operation should be left alone for
 * auto-wiring with service frameworks and testing.
 *
 */
@ApplicationScoped
public class ActionProcessorTestHandlerActionFactory implements IActionFactory<ActionProcessorTestHandlerAction> {
	
	/**
	 * This function produces a default (i.e., nullary-constructed) instance
	 * of ActionProcessorTestHandlerAction.
	 *  
	 * @return the instance
	 */
	@Produces public ActionProcessorTestHandlerAction build() throws Exception {
		return new ActionProcessorTestHandlerAction();
	}

	/**
	 * This function returns an instance of the client class (ActionProcessorTestHandler)
	 * that was annotated with @Action to produce the Action. This method is not
	 * on the IActionFactory interface, and it is implemented here mostly as a
	 * means to auto-wire dependencies through @Produces.
	 *
	 * @return an instance of the underlying implementation class
	 */	
	@Produces public ActionProcessorTestHandler buildImplementation() {
		return new ActionProcessorTestHandler();
	}
}