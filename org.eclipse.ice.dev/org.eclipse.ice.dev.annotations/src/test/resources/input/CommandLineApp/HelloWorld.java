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
package com.example.hello;

import org.eclipse.ice.dev.annotations.CommandLineApp;

/**
 * This is a simple hello world class with a command line app annotation that
 * will generate an executable Java application that prints hello world. It is
 * used for unit testing the @CommandLineApp annotation.
 * 
 * @author Jay Jay Billings
 */
public class HelloWorld {
	@CommandLineApp
	public void run() {
		System.out.println("Hello World!");
	}
}