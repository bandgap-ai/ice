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
package org.eclipse.ice.tasks.annotations;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;

import org.eclipse.ice.dev.annotations.processors.GeneratedFileWriter;
import org.eclipse.ice.dev.annotations.processors.VelocitySourceWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This writer is used for creating Action Factories from the Velocity template.
 * 
 * @author Jay Jay Billings
 */
public class ActionFactoryWriter extends VelocitySourceWriter implements GeneratedFileWriter {

	/**
	 * Context key for package.
	 */
	private static final String PACKAGE = "package";

	/**
	 * Context key for Action implementation.
	 */
	private static final String IMPL = "class";

	/**
	 * Logging tool
	 */
	private static final Logger logger = LoggerFactory.getLogger(ActionFactoryWriter.class);

	/**
	 * Location of CommandLineApp template for use with Velocity.
	 */
	private static final String IMPL_TEMPLATE = "templates/ActionFactory.vm";

	/**
	 * Constructor
	 * 
	 * @param element      the Java element annotated with @Action.
	 * @param elementUtils the element utils
	 */
	public ActionFactoryWriter(Element element, Elements elementUtils) throws IOException {
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
		
		// Only proceed if the expected annotated element is a public method.
		if (element != null && element.getKind() == ElementKind.METHOD
				&& element.getModifiers().contains(Modifier.PUBLIC)) {
			ExecutableElement method = (ExecutableElement) element;
			// Store the target method name
			context.put("method", method.getSimpleName().toString());
			// Grab the input parameter and its type
			List<? extends VariableElement> parameters = method.getParameters();
			if (parameters.size() == 1) {
				VariableElement param = parameters.get(0);
				context.put("dataTypePackage", elementUtils.getPackageOf(param));
				TypeMirror paramType = param.asType();
				context.put("dataType", paramType.toString());
				context.put("paramName", param.getSimpleName().toString());
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
		} else {
			String msg = "Can't generate Action Factory. Check @Action declaration";
			throw new IOException(msg);
		}
	}

	@Override
	public Writer openWriter(Filer filer) throws IOException {
		logger.debug("Apache velocity context for @Action factory: " + context);
		String fileName = context.get("package") 
				+ "." + context.get("class") + "ActionFactory";
		return filer.createSourceFile(fileName).openWriter();
	}

}
