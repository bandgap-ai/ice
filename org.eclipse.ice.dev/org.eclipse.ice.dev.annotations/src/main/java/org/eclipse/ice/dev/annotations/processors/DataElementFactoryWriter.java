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
package org.eclipse.ice.dev.annotations.processors;

import java.io.IOException;
import java.io.Writer;

import javax.annotation.processing.Filer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This writer is used for creating Data Element Factories from the Velocity 
 * template.
 * 
 * @author Jay Jay Billings
 */
public class DataElementFactoryWriter extends VelocitySourceWriter implements GeneratedFileWriter {
	
	/**
	 * Context key for package.
	 */
	private static final String PACKAGE = "package";

	/**
	 * Context key for class.
	 */
	private static final String IMPL = "class";
	
	/**
	 * Logging tool
	 */
	private static final Logger logger = LoggerFactory.getLogger(DataElementFactoryWriter.class);
	
	/**
	 * Fully qualified name of generated interface;
	 */
	private String fullyQualifiedName;
	
	/**
	 * Location of CommandLineApp template for use with Velocity.
	 */
	private static final String IMPL_TEMPLATE = "templates/DataElementFactory.vm";

	/**
	 * Constructor
	 * 
	 * @param data the metadata for the data element currently being processed
	 */
	public DataElementFactoryWriter(DataElementMetadata data) {
		super(IMPL_TEMPLATE);
		this.fullyQualifiedName = data.getFullyQualifiedName() + "Factory";
		context.put(PACKAGE, data.getPackageName());
		context.put(IMPL, data.getName());
	}

	@Override
	public Writer openWriter(Filer filer) throws IOException {
		logger.debug("Apache velocity context for @DataElement factory: " + context);
		return filer.createSourceFile(fullyQualifiedName).openWriter();
	}

}
