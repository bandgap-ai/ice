/******************************************************************************
 * Copyright (c) 2025- The Band Gap Corporation
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Initial API and implementation and/or initial documentation - 
 *   Jay Jay Billings
 *****************************************************************************/
package org.eclipse.ice.tasks.basics;

import org.eclipse.ice.tasks.Task;
import org.eclipse.ice.tasks.TaskStateData;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Any;
import jakarta.inject.Inject;

/**
 * This is a primitive task for moving files and data.
 * 
 * @author Jay Jay Billings
 */
@Any
public class MoveTask extends Task<MoveData> {

	@Inject
	public MoveTask(TaskStateData stateData) throws Exception {
		super(stateData);
		
		stateData.setId(1);
		stateData.setName("Move Task");
		
		MoveDataFactory factory = new MoveDataFactory();
		MoveData data = factory.build();
	}
	
}
