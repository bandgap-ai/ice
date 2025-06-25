package org.eclipse.ice.tests.tasks.annotations;

import org.eclipse.ice.tests.tasks.annotations.ActionProcessorTestHandler;
import org.eclipse.ice.tasks.IAction;
import org.eclipse.ice.tasks.IActionType;
import java.io.Serializable;
import jakarta.inject.Inject;
import jakarta.enterprise.context.Dependent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.eclipse.ice.tests.tasks.annotations.TestData;

/**
 * A generated implementation of IAction to run ActionProcessorTestHandler.${method}.
 */
public class ActionProcessorTestHandlerAction implements IAction<org.eclipse.ice.tests.tasks.annotations.TestData>, Serializable {

	/**
	 * Logger
	 */
	private static final Logger logger = LoggerFactory.getLogger(ActionProcessorTestHandlerAction.class);

	/**
	 * Delegate class (@link ActionProcessorTestHandler) that implements the business logic of run().
	 */
	ActionProcessorTestHandler actionImpl;

	/**
	 * Default nullary constructor.
	 */
	public ActionProcessorTestHandlerAction() {
		actionImpl = new ActionProcessorTestHandler();
	}

	/**
	 * Copy constructor primarily used for testing. It chain-calls the nullary
	 * constructor for completeness.
	 */
	public ActionProcessorTestHandlerAction(ActionProcessorTestHandler otherImpl) {
		actionImpl = otherImpl;
	}

	@Override
	public IActionType getType() {
		return IActionType.EXECUTABLE.LOCAL;
	}

	@Override
	public boolean run(org.eclipse.ice.tests.tasks.annotations.TestData data) {
		boolean retVal = false;
		
		try {
			retVal = actionImpl.setData(data);
		} catch (Exception e) {
			String msg = "Execution of ActionProcessorTestHandler.setData by ActionProcessorTestHandlerAction failed.";
		    logger.error(msg, e);
		}
		
		return retVal;
	}
}
