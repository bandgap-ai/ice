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
package org.eclipse.ice.tasks.primitives;

import org.eclipse.ice.tasks.Task;
import org.eclipse.ice.tasks.TaskStateData;
import org.eclipse.ice.tasks.primitives.MoveData;

import jakarta.enterprise.context.ApplicationScoped;

/**
 * This is a primitive task for moving files and data.
 * 
 * @author Jay Jay Billings
 */
@ApplicationScoped
public class MoveTask extends Task<MoveData> {

	public MoveTask(TaskStateData stateData) throws Exception {
		super(stateData);
		
		stateData.setId(1);
		stateData.setName("Move Task");
	}
	
}
