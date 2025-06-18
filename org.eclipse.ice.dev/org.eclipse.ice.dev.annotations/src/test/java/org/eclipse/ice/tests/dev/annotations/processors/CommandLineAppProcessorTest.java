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
package org.eclipse.ice.tests.dev.annotations.processors;

import static com.google.testing.compile.CompilationSubject.*;
import static com.google.testing.compile.Compiler.javac;
import java.io.File;
import org.eclipse.ice.dev.annotations.processors.CommandLineAppProcessor;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.google.testing.compile.Compilation;

/**
 * This is a unit test for the CommandLineAppProcessor annotation processor.
 * 
 * @author Jay Jay Billings
 */
class CommandLineAppProcessorTest {

	/**
	 * This is a simple test to insure that basic compilation works
	 * for @CommandLineApp.
	 */
	@Test
	void testBasicCompilation() {
		// Set the path as a JavaFileObjectResource to be used by the JDK 
		// compiler.
		JavaFileObjectResource testClassObject = new JavaFileObjectResource() {
			@Override
			public String getPath() {
				return "input" + File.separator + "CommandLineApp" + File.separator + "HelloWorld.java";
			}
		};
		// Compile the class.
		Compilation compilation = javac().withProcessors(new CommandLineAppProcessor()).compile(testClassObject.get());
		// Assert that compilation succeeded.
		assertThat(compilation).succeeded();
	}

}
