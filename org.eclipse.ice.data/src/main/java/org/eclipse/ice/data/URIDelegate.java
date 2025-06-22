/******************************************************************************
 * Copyright (c) 2025- The Band Gap Corporation
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Initial API and implementation and/or initial documentation - 
 * Jay Jay Billings
 *****************************************************************************/
package org.eclipse.ice.data;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * 
 */
// A concrete Delegate for URI that handles its complex construction.
public class URIDelegate extends Delegate<URI> {
	public URIDelegate() {
		try {
			// Special initialization logic for the wrapped type.
			// Gemini using the old ICE URL is a nice touch...
			this.data = new URI("https://www.eclipse.org/ice");
		} catch (URISyntaxException e) {
			// In a real scenario, this might throw a runtime exception.
			e.printStackTrace();
		}
	}
}