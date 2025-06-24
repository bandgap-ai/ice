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

import java.io.IOException;
import javax.annotation.processing.Processor;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.util.Elements;

import org.eclipse.ice.dev.annotations.processors.GeneratedFileWriter;
import org.eclipse.ice.dev.annotations.processors.SingleMethodAnnotationProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.auto.service.AutoService;

/**
 * This annotation processor builds implementations of IActionFactory using a 
 * Velocity template. See {@link org.eclipse.ice.tasks.annotations.Action}.
 * 
 * @author Jay Jay Billings
 */
@SupportedAnnotationTypes({ "org.eclipse.ice.tasks.annotations.Action" })
@SupportedSourceVersion(SourceVersion.RELEASE_17)
@AutoService(Processor.class)
public class ActionFactoryProcessor extends SingleMethodAnnotationProcessor {

	/**
	 * Logging tool
	 */
	private static final Logger logger = LoggerFactory.getLogger(ActionFactoryProcessor.class);

	/**
	 * Default constructor that configures the logging text.
	 */
	public ActionFactoryProcessor() {
		setErrorMsg("Cannot write ActionFactory.");
		setSuccessMsg("ActionFactory successfully generated.");
	}

	@Override
	protected GeneratedFileWriter getWriter(Element element, Elements elements) throws IOException {
		return new ActionFactoryWriter(element, elements);
	}

}
