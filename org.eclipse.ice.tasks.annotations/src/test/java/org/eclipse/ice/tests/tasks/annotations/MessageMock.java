/*******************************************************************************
 * Copyright (c) 2025- The Band Gap Corporation
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Jay Jay Billings - Initial implementation
 *******************************************************************************/
package org.eclipse.ice.tests.tasks.annotations;

import org.eclipse.ice.tasks.IActionType;
import org.eclipse.ice.tasks.annotations.Action;

/**
 * This is a simple messaging mock class with an Action annotation. It is
 * used for unit testing the @Action annotation.
 * 
 * @author Jay Jay Billings
 */
public class MessageMock {

	/**
	 * Stored message for testing.
	 */
	private String message;
	
	/**
	 * Constructor. Default message is "None".
	 */
	public MessageMock() {
		message = "None";
	}
	
	/**
	 * This is the routine called by the Action generated from the processor.
	 * @param newMsg the new message that should be stored, overwriting the
	 * current message.
	 * @return true if successful, false otherwise.
	 */
	@Action(actionType = "IActionType.FUNCTION.JAVA")
	public boolean setMessage(String newMsg) {
		message = newMsg;
		return true;
	}
	
	/**
	 * This is the mock shortcut for checking the stored message. 
	 */
	public String getMessage() {
		return message;
	}
}