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
package org.eclipse.ice.dev.annotations.processors;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

import com.google.auto.service.AutoService;

/**
 * This annotation processor builds simple command line applications based on
 * the @CommandLineApp annotation. It creates a simple Java application using
 * a Velocity template for a basic Java main() program.
 * 
 * This processor will not update the build system to automatically package the
 * new application.
 */
@SupportedAnnotationTypes({
	"org.eclipse.ice.dev.annotations.CommandLineApp"
})
@SupportedSourceVersion(SourceVersion.RELEASE_17)
@AutoService(Processor.class)
public class CommandLineAppProcessor extends AbstractProcessor {

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		// TODO Auto-generated method stub
		return false;
	}

}
