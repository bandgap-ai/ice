package org.eclipse.ice.workflows;

// This is an old experiment I was doing. It has some value, so keeping for now and will delete later.
// Currently completely disabled for the build.

/*import java.io.File;
import java.io.IOException;
import java.util.EnumSet;

import org.eclipse.ice.commands.ConnectionAuthorizationHandlerFactory;
import org.eclipse.ice.commands.ConnectionConfiguration;
import org.eclipse.ice.commands.FileHandlerFactory;
import org.eclipse.ice.commands.IFileHandler;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.config.StateMachineBuilder.Builder;*/

public class TaskTest {

/*	static String name = "ICEIII/ice/org.eclipse.ice.workflows/src/main/resources/test1.txt";
	static String newName = "ICEIII/ice/org.eclipse.ice.workflows/src/main/resources/test2.txt";

	static public StateMachine<WorkflowEngine.States, WorkflowEngine.Events> buildMachine()
			throws Exception {
		Builder<WorkflowEngine.States, WorkflowEngine.Events> builder = new StateMachineBuilder.Builder<>();

		builder.configureStates().withStates()
				.initial(WorkflowEngine.States.INITIALIZED)
				.states(EnumSet.allOf(WorkflowEngine.States.class));

		builder.configureTransitions().withExternal()
				.source(WorkflowEngine.States.INITIALIZED)
				.target(WorkflowEngine.States.EXECUTING)
				.event(WorkflowEngine.Events.PARAMETERS_RECEIVED).and()
				.withExternal().source(WorkflowEngine.States.EXECUTING)
				.target(WorkflowEngine.States.FINISHED)
				.action(new Action<WorkflowEngine.States, WorkflowEngine.Events>() {

					@Override
					public void execute(
							StateContext<WorkflowEngine.States, WorkflowEngine.Events> context) {
						try {
							moveFile(name, newName);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}).event(WorkflowEngine.Events.EXECUTION_COMPLETE);

		return builder.build();
	}

	public static void moveFile(final String filePath, final String newFilePath)
			throws IOException {

		String home = System.getProperty("user.home");
		String separator = String.valueOf(File.separatorChar);
		String homePath = home + separator;

		FileHandlerFactory factory = new FileHandlerFactory();
		ConnectionAuthorizationHandlerFactory authFactory = new ConnectionAuthorizationHandlerFactory();
		ConnectionConfiguration connCfg = new ConnectionConfiguration();
		connCfg.setAuthorization(authFactory.getConnectionAuthorizationHandler("local"));
		IFileHandler fileHandler = factory.getFileHandler(connCfg);
		fileHandler.move(homePath + filePath, homePath + newFilePath);
		return;
	}

	public static void main(String[] args) throws Exception {

		StateMachine<WorkflowEngine.States, WorkflowEngine.Events> taskProcessor = buildMachine();
		taskProcessor.start();
		taskProcessor.sendEvent(WorkflowEngine.Events.PARAMETERS_RECEIVED);
		taskProcessor.sendEvent(WorkflowEngine.Events.EXECUTION_COMPLETE);
		System.out.println(taskProcessor.getState());

		return;
	}

*/
}
