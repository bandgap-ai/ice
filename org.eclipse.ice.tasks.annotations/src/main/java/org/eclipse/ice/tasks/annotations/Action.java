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
package org.eclipse.ice.tasks.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.eclipse.ice.tasks.IActionType;

/**
 * 
 * This is an annotation to create an implementation of IAction. This 
 * annotation must be used on public methods, not classes.
 * 
 * To conform with the IAction interface, the annotated method must return
 * true if executes successfully and false otherwise. If can also throw any
 * exceptions that catches and the generated implementation will handle them.
 * 
 * @author Jay Jay Billings
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface Action {
	
	String actionType() default "IActionType.FUNCTION.JAVA";

}
