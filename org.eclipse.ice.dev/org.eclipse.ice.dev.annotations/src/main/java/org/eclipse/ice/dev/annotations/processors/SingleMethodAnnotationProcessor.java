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
package org.eclipse.ice.dev.annotations.processors;

import java.io.IOException;
import java.io.Writer;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This annotation processor is an abstract base class for method-type annotations.
 * 
 * Subclasses must add their own annotations for supported annotations,
 * source version, auto service, if needed. For example:
 *
 * <pre>
 * @SupportedAnnotationTypes({ "org.eclipse.ice.dev.annotations.CommandLineApp" })
 * @SupportedSourceVersion(SourceVersion.RELEASE_17)
 * @AutoService(Processor.class)
 * </pre>
 * 
 * Subclasses should setErrorMsg(), and setSuccessMsg() in their constructors
 * to set the logging text. Subclasses must implement getWriter().
 * 
 * @author Jay Jay Billings
 */
public abstract class SingleMethodAnnotationProcessor extends AbstractProcessor {
	
	/**
	 * The file writer that will be used to write the generated file.
	 */
	private GeneratedFileWriter writer;
	
	/**
	 * The message that should be logged if annotation processing fails.
	 */
	private String errTxt;
	

	/**
	 * The message that should be logged if annotation processing succeeds.
	 */
	private String msgTxt;

	/**
	 * This method sets the file writer for the generated file.
	 * @param subWriter Write required by the subclass during processing.
	 */
	protected void setWriter(GeneratedFileWriter subWriter) {
		this.writer = subWriter;
	}

	/**
	 * This method sets the error message text.
	 * @param err Error message text
	 */
	protected void setErrorMsg(String err) {
		this.errTxt = err;
	}
	
	/**
	 * This method sets the message text for success.
	 * @param msg Success message text
	 */
	protected void setSuccessMsg(String msg) {
		this.msgTxt = msg;
	}
	
	/**
	 * This is a protected factory method for subclasses to construct and
	 * return the GeneratedFileWriter that will write the file during
	 * annotation processing.
	 * @param element the current element being processed
	 * @param elements Element utilities from the processing environment
	 * @return the fully constructed writer
	 */
	abstract protected GeneratedFileWriter getWriter(Element element, Elements elements) throws IOException;
	
	/**
	 * Logging tool
	 */
	private static final Logger logger = LoggerFactory.getLogger(SingleMethodAnnotationProcessor.class);

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
					GeneratedFileWriter appWriter = getWriter(element, processingEnv.getElementUtils());
					Writer filer = appWriter.openWriter(processingEnv.getFiler());
					appWriter.write(filer);
					filer.close();
					logger.info(msgTxt);
				} catch (IOException e) {
					logger.error(errTxt, e);
				}
			}
		}

		return retVal;
	}

}
