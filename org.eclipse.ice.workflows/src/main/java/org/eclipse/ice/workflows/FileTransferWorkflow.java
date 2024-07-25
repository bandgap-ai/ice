package org.eclipse.ice.workflows;

import java.util.ArrayList;
import javax.inject.Inject;

import org.eclipse.ice.dev.annotations.CommandLineApp;
import org.eclipse.ice.tasks.Task;
import org.eclipse.ice.tasks.TaskException;
import org.eclipse.ice.tasks.TaskStateData;
import org.eclipse.ice.tasks.TaskStateDataImplementation;

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
	
	private DependencyBuilder depBuilder;
	
	@Inject
	public FileTransferWorkflow(DependencyBuilder depBuilder) {
		this.depBuilder = depBuilder;
	}
//	}
	
	public record Dependency(Task a, Task b) {};
	
//	@CommandLineApp
	public void runApp() {
		System.out.println("Running FileTransferWorkflow as ICE App");
	}
	
	public void setDependencies() throws TaskException {
		
		
		/* 'Airplane' diagram workflow 
		 * 
		 *                  / - E - \
		 * A - \           /- - F - -\
		 *      > C - D - < - - G - - > - J
		 * B - /           \- - H - -/
		 *                  \ - I - /
		 */
		
		// Two ways to do it. 1. Full declaration, total verbosity. 
		// Declare tasks.
		TaskStateData data = TaskStateDataImplementation.builder().build();
		Task a = new Task(data);
		Task b = new Task(data);
		Task c = new Task(data);
		Task d = new Task(data);
		Task e = new Task(data);
		Task f = new Task(data);
		Task g = new Task(data);
		Task h = new Task(data);
		Task i = new Task(data);
		Task j = new Task(data);
		// Declare dependencies
		ArrayList<Dependency> deps = new ArrayList<Dependency>();
		deps.add(new Dependency(a,c));
		deps.add(new Dependency(b,c));
		deps.add(new Dependency(c,d));
		deps.add(new Dependency(d,e));
		deps.add(new Dependency(d,f));
		deps.add(new Dependency(d,g));
		deps.add(new Dependency(d,h));
		deps.add(new Dependency(d,i));
		deps.add(new Dependency(e,j));
		deps.add(new Dependency(f,j));
		deps.add(new Dependency(g,j));
		deps.add(new Dependency(h,j));
		deps.add(new Dependency(i,j));
		
		depBuilder.test();
		
		// Succinct functional method - Tasks and deps declared simultaneously 
		// using names, etc.
	//	DependencyBuilder.connectAll(Set.of("a","b"),"c").connect("c","d").connectAll("d",Set.of("e","f","g","h","i")).connectAll(Set.of("e","f","g","h","i"),"j");
		
		// Inheritance version
		//connectAll(Set.of("a","b"),"c").connect("c","d").connectAll("d",Set.of("e","f","g","h","i")).connectAll(Set.of("e","f","g","h","i"),"j");
		
		// Can it be done without a static builder or inheritance?
		// - Inheritance is fine and will eliminate the static builder.
		// - Static builder is fine and can eliminate the need for inheritance.
		//   > The static builder can be eliminated by using an injected, locally scoped builder.
		
		
	}
}