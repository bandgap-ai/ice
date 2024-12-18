package org.eclipse.ice.tests.workflows;

import static org.junit.Assert.*;

import org.eclipse.ice.tasks.TaskException;
import org.eclipse.ice.workflows.FileTransferWorkflow;
import org.eclipse.ice.workflows.WorkflowsComponent;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class FileTransferWorkflowTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testSetDependencies() {
		
	/*	WorkflowsComponent wComp = DaggerWorkflowsComponent.create();
		
		try {
			FileTransferWorkflow ftw = wComp.buildFileTransferWorkflow();
			ftw.setDependencies();
		} catch (TaskException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}

}
