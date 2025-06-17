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
package org.eclipse.ice.tasks.annotations;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.util.Elements;

import org.eclipse.ice.dev.annotations.processors.GeneratedFileWriter;
import org.eclipse.ice.dev.annotations.processors.VelocitySourceWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is a simple utility for writing Actions to file.
 * 
 * @author Jay Jay Billings
 */
public class ActionWriter extends VelocitySourceWriter implements GeneratedFileWriter {

	/**
	 * Logging tool
	 */
	private static final Logger logger = LoggerFactory.getLogger(ActionWriter.class);
	
	/**
	 * Location of Action template for use with Velocity.
	 */
	private static final String IMPL_TEMPLATE = "templates/Action.vm";

	/**
	 * Constructor
	 * 
	 * @param element the Java element annotated with @Action.
	 */
	public ActionWriter(Element element, Elements elementUtils) throws IOException {
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
			
			// Need: Class, Method, Package, Type
			
			System.out.println("Read elements! --> " + element);
			
			// Store the target method name
			context.put("method", element.getSimpleName());
			
			System.out.println(element.asType());
			
			// Get the argument type of the method and store it as the "type".
			
			// Otherwise, if there is no argument or too many arguments,
			// complain and abort.
			List<? extends Element> paramTypes = element.getEnclosedElements();
		//	System.out.println("Param size = " + paramTypes.size());
			//System.out.println(paramTypes);
			if (paramTypes.size() == 1) {
		//		Element param = paramTypes.get(0);
			//	context.put("type", param.getSimpleName());
				//System.out.println(param);
			} else {
				throw new IOException("The method must take one argument for @Action.");
			}
			
			// Get the name of the class that owns the method
			Element classElement = element.getEnclosingElement();
			if (classElement.getKind() == ElementKind.CLASS) {
				context.put("class", classElement.getSimpleName());
			} else {
				throw new IOException("Normal, non-nested classes are required for @Action.");
			}
			// Get the name of the package that encloses the method.
			PackageElement packageElement = elementUtils.getPackageOf(element);
			context.put("package", packageElement.getQualifiedName());
		}
	}

	@Override
	public Writer openWriter(Filer filer) throws IOException {
		logger.debug("Apache velocity context for @Action: " + context);
		return filer.createSourceFile(context.get("package") + "." + context.get("class") + "Action").openWriter();
	}

}
