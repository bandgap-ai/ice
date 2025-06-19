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

import java.net.URI;

import org.eclipse.ice.dev.annotations.DataElement;
import org.eclipse.ice.dev.annotations.DataField;

/**
 * This is a basic data element for storing data for moving files.
 * @author Jay Jay Billings
 */
@DataElement(name="MoveData")
public class MoveDataSpec {

	/**
	 * Source location
	 */
	@DataField
	private URI src;
	
	/**
	 * Destination Location
	 */
	@DataField
	private URI dest;
	
}
