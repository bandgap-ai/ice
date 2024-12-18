package org.eclipse.ice.workflows;

import java.io.Serializable;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DependencyBuilder implements Serializable {
	
	private static final long serialVersionUID = 1351857302926874542L;

	public void test() {
		System.out.println("DependencyBuilder: Hello!");
	}

}
