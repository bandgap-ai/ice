/******************************************************************************
 * Copyright (c) 2024- Amazon.com, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Initial API and implementation and/or initial documentation - 
 *   Jay Jay Billings
 *****************************************************************************/
package org.eclipse.ice.dev.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This is an annotation to create a basic application that is executable on
 * the command line. This annotation must be used on public methods.
 * 
 * This annotation will not update the build system to automatically package
 * the new application.
 * 
 * Unlike other ICE annotations, CommandLineApp will only produce an
 * implementation, not and interface and an implementation. An interface is
 * not needed since main() is implemented as a function in its own class.
 * 
 * @author Jay Jay Billings
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface CommandLineApp {

}
