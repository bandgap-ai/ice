package org.eclipse.ice.workflows;


public class WorkflowsModule {

	public DependencyBuilder provideDependencyBuilder() {
		return new DependencyBuilder();
	}
	
}
