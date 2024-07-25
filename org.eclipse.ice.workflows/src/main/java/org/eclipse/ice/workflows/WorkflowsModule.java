package org.eclipse.ice.workflows;

import dagger.Module;
import dagger.Provides;

@Module
public class WorkflowsModule {

	@Provides
	public DependencyBuilder provideDependencyBuilder() {
		return new DependencyBuilder();
	}
	
}
