package org.eclipse.ice.workflows;

public class WorkflowEngine {

	public enum States {
		EXECUTING, FAILED, FINISHED, INITIALIZED, READY, REVIEWING, WAITING,
		WAITING_FOR_INFO
	}

	public enum Events {
		PARAMETERS_RECEIVED, EXCEPTION, EXCEPTION_HANDLED, EXECUTION_COMPLETE
	}

}
