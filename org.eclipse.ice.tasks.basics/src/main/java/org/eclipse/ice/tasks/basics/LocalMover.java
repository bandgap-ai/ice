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

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.eclipse.ice.tasks.annotations.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.eclipse.ice.tasks.basics.MoveData;

/**
 * This is a basic action to move a file locally.
 */
public class LocalMover {
	
	/**
	 * Logging tool
	 */
	private static final Logger logger = LoggerFactory.getLogger(LocalMover.class);

	/**
	 * This is the runner that will move a file locally.
	 * @param moveData the data describing what should be moved
	 * @return true if the move was successful, false otherwise
	 */
	@Action(actionType="IActionType.BASIC.MOVE_FILE")
	public boolean move(MoveData moveData) {
		
		boolean retVal = false;
		
		try {
			URI src = moveData.getSrcDelegate().get();
			URI dest = moveData.getDestDelegate().get();
			Files.move(Paths.get(src), Paths.get(dest));
			retVal = true;
		} catch (IOException e) {
			// Complain
			logger.error("Failed to move file: " + e.getMessage(),e);
		}
		
		return retVal;
	}

}
