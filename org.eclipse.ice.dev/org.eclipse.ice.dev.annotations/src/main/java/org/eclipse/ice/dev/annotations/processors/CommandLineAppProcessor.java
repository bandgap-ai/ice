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

import java.io.IOException;
import java.io.Writer;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.auto.service.AutoService;

/**
 * This annotation processor builds simple command line applications based on
 * the @CommandLineApp annotation. It creates a simple Java application using a
 * Velocity template for a basic Java main() program.
 * 
 * This processor has no knowledge of the underlying build system for the
 * application, if there is one, and, as such, it does not attempt to update any
 * such build system to automatically package the new application. Clients must
 * implement that functionality separately.
 * 
 * @author Jay Jay Billings
 */
@SupportedAnnotationTypes({ "org.eclipse.ice.dev.annotations.CommandLineApp" })
@SupportedSourceVersion(SourceVersion.RELEASE_17)
@AutoService(Processor.class)
public class CommandLineAppProcessor extends AbstractProcessor {

	/**
	 * Logging tool
	 */
	private static final Logger logger = LoggerFactory.getLogger(CommandLineAppProcessor.class);

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		boolean retVal = false;

		// Loop over all incoming annotations
		for (TypeElement annotation : annotations) {
			// Get the annotated elements
			Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(annotation);

			// Write each annotated element of CommandLineAppWriter. Note that the
			// error checks are all handled within CommandLineAppWriter.
			for (final Element element : annotatedElements) {
				try {
					GeneratedFileWriter appWriter = new CommandLineAppWriter(element, processingEnv.getElementUtils());
					Writer filer = appWriter.openWriter(processingEnv.getFiler());
					appWriter.write(filer);
					filer.close();
					logger.info("App generated.");
				} catch (IOException e) {
					logger.error("Cannot write CommandLineApp.", e);
				}
			}
		}

		return retVal;
	}

}
