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

import org.eclipse.ice.tasks.annotations.Action;

/**
 * This is a mock class with an Action annotation. It is used for unit testing
 * the @Action annotation with a generated data element, which in this case is
 * the TestData generated from TestDataSpec.
 * 
 * @author Jay Jay Billings
 */
public class ActionProcessorTestHandler {

	/**
	 * Globally stored message for testing.
	 */
	static TestData data;

	/**
	 * Constructor. Default message is "None".
	 */
	public ActionProcessorTestHandler() {
		data = null;
	}

	/**
	 * This is the routine called by the Action generated from the processor.
	 * Unlike the MessageMock class, this version can return either true or 
	 * false and needs to be tested with both real and null values.
	 * 
	 * @param newMsg the new message that should be stored, overwriting the 
	 * 				current message.
	 * @param true if successful, false otherwise.
	 */
	@Action(actionType="IActionType.EXECUTABLE.LOCAL")
	public boolean setData(TestData otherData) {
		if (otherData != null) {
			data = otherData;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This is the mock shortcut for checking the stored message.
	 */
	public TestData getData() {
		return data;
	}
}