package org.eclipse.ice.workflows;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = WorkflowsModule.class)
public interface WorkflowsComponent {
	
	public FileTransferWorkflow buildFileTransferWorkflow();

}
