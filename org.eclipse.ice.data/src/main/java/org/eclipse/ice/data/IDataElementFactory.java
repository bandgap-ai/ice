/******************************************************************************
 * Copyright (c) 2025- The Band Gap Corporation. All rights reserved. This 
 * program and the accompanying materials are made available under the terms of 
 * the Eclipse Public License v1.0 which accompanies this distribution, and is 
 * available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Jay Jay Billings - Initial Implementation
 ******************************************************************************/
package org.eclipse.ice.data;

/**
 * This is a simple factory interface for Data Elements. Data Elements of type
 * T can be constructed in a default state by calling build().
 */
public interface IDataElementFactory<T extends IDataElement<T>> {

	public T build() throws Exception;
	
}
