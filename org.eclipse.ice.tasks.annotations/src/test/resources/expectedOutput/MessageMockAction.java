package org.eclipse.ice.tests.tasks.annotations;

import org.eclipse.ice.tests.tasks.annotations.MessageMock;
import org.eclipse.ice.tasks.IAction;
import org.eclipse.ice.tasks.IActionType;
import java.io.Serializable;
import jakarta.inject.Inject;
import jakarta.enterprise.context.Dependent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.String;

/**
 * A generated implementation of IAction to run MessageMock.setMessage.
 */
@Dependent
public class MessageMockAction implements IAction<java.lang.String>, Serializable {

	/**
	 * Logger
	 */
	private static final Logger logger = LoggerFactory.getLogger(MessageMockAction.class);

	@Inject
	MessageMock actionImpl;

	/**
	 * Default nullary constructor.
	 */
	public MessageMockAction() {}

	/**
	 * Copy constructor primarily used for testing. It chain-calls the nullary
	 * constructor for completeness.
	 */
	public MessageMockAction(MessageMock otherImpl) {
		this();
		actionImpl = otherImpl;
	}

	@Override
	public IActionType getType() {
		return IActionType.FUNCTION.JAVA;
	}

	@Override
	public boolean run(java.lang.String data) {
		boolean retVal = false;
		
		try {
			retVal = actionImpl.setMessage(data);
		} catch (Exception e) {
			String msg = "Execution of MessageMock.setMessage by MessageMockAction failed.";
		    logger.error(msg, e);
		}
		
		return retVal;
	}
}
