/*******************************************************************************
 * Copyright (c) 2023- Amazon.com
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Initial API and implementation and/or initial documentation - 
 *   Jay Jay Billings
 *******************************************************************************/
package org.eclipse.ice.dev.annotations.processors;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

/**
 * This is a custom Jackson JSON deserializer for Fields to handle special
 * cases, such as when the field name is also the JSON object name that would
 * otherwise be read as the object type.
 */
public class FieldDeserializer extends StdDeserializer<Field> {

	/**
	 * Nullary constructor
	 */
	public FieldDeserializer() {
		this(null);
	}

	/**
	 * Secondary copy constructor
	 * @param vc variable to copy (?)... it was auto-generated...
	 */
	public FieldDeserializer(Class<?> vc) {
		super(vc);
	}

	public Field deserialize(JsonParser jp, DeserializationContext context) {
		return null;
		
	}
	
}
