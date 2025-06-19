/*******************************************************************************
 * Copyright (c) 2025- The Band Gap Corporation. All rights reserved. This 
 * program and the accompanying materials are made available under the terms of 
 * the Eclipse Public License v1.0 which accompanies this distribution, and is 
 * available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Jay Jay Billings - Initial Implementation
 *******************************************************************************/
package org.eclipse.ice.workflows;

import java.util.ArrayList;
import org.eclipse.ice.tasks.Task;
import org.eclipse.ice.tasks.TaskException;
import org.eclipse.ice.tasks.TaskStateData;
import org.eclipse.ice.tasks.TaskStateDataImplementation;
import org.eclipse.ice.tasks.basics.MoveTask;
import org.eclipse.ice.dev.annotations.CommandLineApp;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

//@Workflow(name = "FileTransferWorkflow")
//@App(name = "FileTransferApp")
/**
 * This workflow has three tasks: 1. Collect the list of files to transfer from
 * the user 2. Move the files 3. Report the results back to the user
 */
/*
 * public class FTW2 {
 * 
 * @Task(name = "File Transfer Web UI")
 * 
 * @Action(HandleUISubmission.class) WebUIRunner fileUI;
 * 
 * @JavaAction public void transferFiles() {
 * 
 * }
 * 
 * @WorkflowDependencies Set<Dependencies> setDependencies() {
 * 
 * // One way Dependencies chain =
 * DependencyBuilder.start(taskA).precedes(taskB). precedes(taskC); // Yet
 * another Dependencies chain3 = DependencyBuilder.start(taskG).follows(taskH).
 * follows(taskI);
 * 
 * // Actually, want to reserve these for iterable? // Another Dependencies
 * chain2 = DependencyBuilder.start(taskD).next(taskE).next(taskF);
 * 
 * // Finally Dependencies chain4 =
 * DependencyBuilder.start(taskJ).last(taskK).last(taskL);
 * 
 * }
 * 
 * public static void main(String[] args) { // Templated, basic app. Can
 * generate Spring Boot App with a more specific // annotation, so look for more
 * Spring annotations, etc. // Note "FileTransferWorkflowFactory" has to be
 * injected. Workflow workflow = FileTransferWorkflowFactory.builder().build();
 * // Templated, nothing custom. Same for all @App instances. try {
 * workflow.start(); while (workflow.isReady() || workflow.isRunning()) { //
 * Pass on continue; } } catch (WorkflowException e) { System.out.println(e); }
 * return; } }
 */

@ApplicationScoped
public class FileTransferWorkflow {

	public record Dependency(Task a, Task b) {
	};

	
//	@Action
//	UIRunner fileUI;

//	@Action
//	FileTransferTool fileTransfer;

//	@Override
//	FileTransferWorkflow {
//		addTask(fileUI,new UIData());
//		addTask(fileTransfer, new FileTransferData());

	@Inject
	private MoveTask moveTask;
	
	@Inject
	private DependencyBuilder depBuilder;

	// How does this behave with inheritance?

	public FileTransferWorkflow() {
		//this.depBuilder = depBuilder;
	}
//	}

	@CommandLineApp
	public void runApp() {
		System.out.println("Running FileTransferWorkflow as ICE App");
		try {
			setDependencies();
			System.out.println("Task name = " + moveTask.getTaskStateData().getName());
		} catch (TaskException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//@Inject
	// How the fuck do I do this? I used Dynamic services and a factory in 2.x.
	// Need to do @Inject @Any and catch an Instance<Iterable>
	// - Glad I left this note! ~JJB 20250509.
	//private FileListTask<FileListData> fileListTask;
	
	public void setDependencies() throws TaskException {

		/* Start small:
		 * GetFileList -> WriteFileList
		 */
		
		/*
		 * 'Airplane' workflow diagram 
		 * 
		 *                  / - E - \ 
		 * A - \           /- - F - -\ 
		 *      > C - D - < - - G - - > - J 
		 * B - /           \- - H - -/ 
		 *                  \ - I - /
		 */

		// Just using one task state data object for testing. A real application would use multiple.
		TaskStateData stateData = TaskStateDataImplementation.builder().build();
		// Two ways to do it. 1. Full declaration, total verbosity.
		// Declare tasks.
		// Usually this would be Task<T> for some action data type (domain data), but that can be ignored for now.
		Task a = new Task(stateData);
		Task b = new Task(stateData);
		Task c = new Task(stateData);
		Task d = new Task(stateData);
		Task e = new Task(stateData);
		Task f = new Task(stateData);
		Task g = new Task(stateData);
		Task h = new Task(stateData);
		Task i = new Task(stateData);
		Task j = new Task(stateData);
		// Declare dependencies
		ArrayList<Dependency> deps = new ArrayList<Dependency>();
		deps.add(new Dependency(a, c));
		deps.add(new Dependency(b, c));
		deps.add(new Dependency(c, d));
		deps.add(new Dependency(d, e));
		deps.add(new Dependency(d, f));
		deps.add(new Dependency(d, g));
		deps.add(new Dependency(d, h));
		deps.add(new Dependency(d, i));
		deps.add(new Dependency(e, j));
		deps.add(new Dependency(f, j));
		deps.add(new Dependency(g, j));
		deps.add(new Dependency(h, j));
		deps.add(new Dependency(i, j));

		depBuilder.test();

		// Succinct functional method - Tasks and deps declared simultaneously
		// using names, etc.
		// tailSet = Set.of("a","b")
		// wingSet = Set.of("e","f","g","h","i")
		// DependencyBuilder.connectAll(tailSet,"c").connect("c","d").connectAll("d",wingSet).connectAll(wingSet,"j");

		// Inheritance version
		// connectAll(tailSet,"c").connect("c","d").connectAll("d",wingSet).connectAll(wingSet,"j");

	}
}