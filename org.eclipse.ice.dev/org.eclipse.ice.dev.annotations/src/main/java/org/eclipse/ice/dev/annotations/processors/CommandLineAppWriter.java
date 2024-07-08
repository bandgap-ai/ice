/*******************************************************************************
 * Copyright (c) 2024- Amazon.com LLC.
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
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.util.Elements;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is a simple utility for writing CommandLineApps to file.
 * 
 * @author Jay Jay Billings
 */
public class CommandLineAppWriter extends VelocitySourceWriter implements GeneratedFileWriter {

	/**
	 * Logging tool
	 */
	private static final Logger logger = LoggerFactory.getLogger(CommandLineAppWriter.class);
	
	/**
	 * Location of CommandLineApp template for use with Velocity.
	 */
	private static final String IMPL_TEMPLATE = "templates/CommandLineApp.vm";

	/**
	 * Constructor
	 * 
	 * @param element the Java element annotated with @CommandLineApp.
	 */
	public CommandLineAppWriter(Element element, Elements elementUtils) throws IOException {
		// Build with the CommandLineApp template
		super(IMPL_TEMPLATE);
		// Read the code properties from the element
		readElementProperties(element, elementUtils);
	}

	/**
	 * Private utility methods for reading properties from the element safely.
	 * 
	 * @param element
	 */
	private void readElementProperties(Element element, Elements elementUtils) throws IOException {
		// The expected annotated element is a public method.
		if (element != null && element.getKind() == ElementKind.METHOD
				&& element.getModifiers().contains(Modifier.PUBLIC)) {
			// Store the target method name
			context.put("method", element.getSimpleName());
			// Get the name of the class that owns the method
			Element classElement = element.getEnclosingElement();
			if (classElement.getKind() == ElementKind.CLASS) {
				context.put("class", classElement.getSimpleName());
			} else {
				throw new IOException("Normal, non-nested classes are required for @CommandLineApp.");
			}
			// Get the name of the package that encloses the method.
			PackageElement packageElement = elementUtils.getPackageOf(element);
			context.put("package", packageElement.getQualifiedName());
		}
	}

	@Override
	public Writer openWriter(Filer filer) throws IOException {
		logger.debug("Apache velocity context for @CommandLineApp: " + context);
		return filer.createSourceFile(context.get("package") + "." + context.get("class") + "App").openWriter();
	}

}
