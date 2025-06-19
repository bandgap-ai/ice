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

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.eclipse.ice.tasks.IActionType;

/**
 * This is a unit test for the ActionProcessor and friends. It performs a simple
 * test using an @Action-annotated mock. Basically, if it can pull data from the
 * mock after calling the Action to update those values, then the processor must 
 * be working correctly.
 * 
 * It also attempts to do the same with a DataElement where the results are
 * returned by overwriting the input payload.
 * 
 * In both cases, it attempts to use the secondary copy constructor instead of
 * the nullary constructor.
 * 
 * @author Jay Jay Billings
 */
class ActionProcessorTest {

	/**
	 * Do the simple mock test.
	 */
	@Test
	void testWithBasicMock() {
		
		// Check the initial message state
		MessageMock mock = new MessageMock();
		assertEquals(mock.getMessage(), "None");
		
		// Get the MessageMockAction and run it
		MessageMockAction mockAction = new MessageMockAction(mock);
		String testMessage = "SClub7 - I Really Miss You (Rachel's Solo)";
		assertTrue(mockAction.run(testMessage));
		assertEquals(testMessage,mock.getMessage());
		
		// Check the ActionType - Should be the default IActionType.FUNCTION.JAVA.
		assertEquals(IActionType.FUNCTION.JAVA,mockAction.getType());
		
	}

	/**
	 * This test ensures that the generated Action can work with a generated
	 * type T, which is just a TestDataElement in this case.
	 */
	@Test
	void testWithGeneratedMock() {
		
		TestDataFactory factory = new TestDataFactory();
		try {
			
			// Setup the handler that the Action is generated from
			ActionProcessorTestHandler handler = new ActionProcessorTestHandler();
			assertNull(handler.getData());
			
			// Setup the test data
			TestData data = factory.build();
			int testId = 5;
			data.setId(testId);
			
			// Setup the action
			ActionProcessorTestHandlerAction action = new ActionProcessorTestHandlerAction(handler);
			// Make sure it the handler still reports null for the initial state
			assertNull(handler.getData());
			
			// Run it with our TestData
			assertTrue(action.run(data));
			// Check that the value has changed
			assertEquals(testId,handler.getData().getId());
			
			// Run it with null to make sure the return value is false. This
			// indicates that the Action is taking its return value from the
			// proxied class.
			assertFalse(action.run(null));
			
			// Check the ActionType
			assertEquals(IActionType.EXECUTABLE.LOCAL,action.getType());
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
	}
	
}
