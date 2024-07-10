package org.eclipse.ice.workflow;

import org.apache.commons.lang3.RandomStringUtils;
import org.eclipse.ice.dev.annotations.CommandLineApp;

//@Workflow(name = "FileTransferWorkflow")
//@App(name = "FileTransferApp")
/**
 * This workflow has three tasks: 1. Collect the list of files to transfer from
 * the user 2. Move the files 3. Report the results back to the user
 */
/*public class FTW2 {

	@Task(name = "File Transfer Web UI")
	@Action(HandleUISubmission.class)
	WebUIRunner fileUI;

	@JavaAction
	public void transferFiles() {

	}

	@WorkflowDependencies
	Set<Dependencies> setDependencies() {

		// One way
		Dependencies chain = DependencyBuilder.start(taskA).precedes(taskB).
				precedes(taskC);
		// Yet another
		Dependencies chain3 = DependencyBuilder.start(taskG).follows(taskH).
				follows(taskI);
		
		// Actually, want to reserve these for iterable?
		// Another
		Dependencies chain2 = DependencyBuilder.start(taskD).next(taskE).next(taskF);
				
		// Finally
		Dependencies chain4 = DependencyBuilder.start(taskJ).last(taskK).last(taskL);

	}

	public static void main(String[] args) {
		// Templated, basic app. Can generate Spring Boot App with a more specific
		// annotation, so look for more Spring annotations, etc.
		// Note "FileTransferWorkflowFactory" has to be injected.
		Workflow workflow = FileTransferWorkflowFactory.builder().build();
		// Templated, nothing custom. Same for all @App instances.
		try {
			workflow.start();
			while (workflow.isReady() || workflow.isRunning()) {
				// Pass on
				continue;
			}
		} catch (WorkflowException e) {
			System.out.println(e);
		}
		return;
	}
}*/

public class FileTransferWorkflow {
	
//	@Action
//	UIRunner fileUI;

	
//	@Action
//	FileTransferTool fileTransfer;
	
//	@Override
//	FileTransferWorkflow {
//		addTask(fileUI,new UIData());
//		addTask(fileTransfer, new FileTransferData());
//	}
	
	@CommandLineApp
	public void runApp() {
		System.out.println("Running FileTransferWorkflow as ICE App");
	}
}