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
package org.eclipse.ice.data;

/**
 * This is a simple wrapper generic to support using Data Elements with classes
 * that lack a public nullary constructor. It allows clients to get around the
 * otherwise "NonNull" requirements of DataElements.
 * 
 * Clients should subclass the delegate with something like:
 *  
 *  <pre>
 *  public class ConcreteDelegate extends Delegate<Type> {
 *  
 *  	public ConcreteDelegate () {
 *  		// Special initialization technique for Type
 *  		data = ...
 *  	}
 *  
 *  }
 *  </pre>
 *  
 *  Clients can access the wrapped class with Delegate's public accessors.
 *  
 *  This is a simple enough class to implement on one's own, but the benefit to
 *  using Delegate is that it is under test and already used in ICE. Thus, it
 *  has some degree of dependability and responsibility, so to say.
 */
public class Delegate<T> {
	
	/**
	 * An instance of the class that should be wrapped an inti
	 */
	protected T data;
	
	public T get() {
		return data;
	}
	
	public void set(T otherData) {
		data = otherData;
		return;
	}

}
