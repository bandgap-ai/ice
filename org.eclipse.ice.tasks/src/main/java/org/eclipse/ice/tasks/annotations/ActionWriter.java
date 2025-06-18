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
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;

import org.eclipse.ice.dev.annotations.processors.GeneratedFileWriter;
import org.eclipse.ice.dev.annotations.processors.VelocitySourceWriter;
import org.eclipse.ice.tasks.IActionType;
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
				TypeMirror paramType = param.asType();
				context.put("type", paramType.toString());
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
			// Get the IActionType
			ExecutableElement methodElement = (ExecutableElement) element;
			Action actionAnnotation = methodElement.getAnnotation(Action.class);
			String actionTypeString = actionAnnotation.actionType();
			
			// Use the utility class for action type validation.
			ActionTypeValidator validator = new ActionTypeValidator();
			if (!validator.isValidActionType(actionTypeString, elementUtils)) {
				logger.warn("Invalid @Action value: 'IActionType." + actionTypeString +
									"' does not resolve to a valid enum constant.");
				logger.warn("Defaulting to IActionType.FUNCTION.JAVA instead.");
				actionTypeString = "IActionType.FUNCTION.JAVA";
			}
			context.put("actionTypeClass", IActionType.class.getCanonicalName());
			context.put("actionTypeValue", actionTypeString);
			
		} else {
			throw new IOException("@Action can only be used on public methods.");
		}
	}

	@Override
	public Writer openWriter(Filer filer) throws IOException {
		logger.debug("Apache velocity context for @Action: " + context);
		return filer.createSourceFile(context.get("package") + "." + context.get("class") + "Action").openWriter();
	}

}
