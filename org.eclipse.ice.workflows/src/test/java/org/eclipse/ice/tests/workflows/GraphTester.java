/******************************************************************************
 * Copyright (c) 2024- Amazon.com LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Initial API and implementation and/or initial documentation - 
 *   Jay Jay Billings
 *****************************************************************************/
package org.eclipse.ice.tests.workflows;

import org.jgrapht.*;
import org.jgrapht.alg.cycle.CycleDetector;
import org.jgrapht.graph.*;
import org.jgrapht.graph.builder.GraphBuilder;
import org.jgrapht.nio.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.jgrapht.traverse.*;

import java.net.*;
import java.util.*;

/**
 * Some simple examples with JGraphT used for testing and experimentation.
 *
 * @author Jay Jay Billings
 */
public final class GraphTester {

	/**
	 * Simple main printing some details about various simple graphs.
	 */
	public static void main(String[] args) throws URISyntaxException, ExportException {
		System.out.println("----- DirectedPseudograph: Linear output -----");
		renderLinearGraph();
		System.out.println();

		System.out.println("----- DirectedPseudograph: Reduction output (l=5) -----");
		renderReductionGraph(5);
		System.out.println();
		
		System.out.println("----- DirectedPseudograph: Reduction output (l=50) -----");
		renderReductionGraph(50);
		System.out.println();
		
		System.out.println("----- DirectedPseudograph: Reduction output (l=500) -----");
		renderReductionGraph(500);
		System.out.println();
		
		System.out.println("----- DirectedPseudograph: Triangle Dependency -----");
		renderTriangleGraph();
		System.out.println();

		System.out.println("----- DirectedPseudograph: Basic Cycle -----");
		renderCyclicGraph();
		System.out.println();

		System.out.println("----- DirectedPseudograph: Self Loop -----");
		renderSelfLoopGraph();
		System.out.println();
		
		return;
	}

	private static void iterateDirectedGraph(Graph<String,DefaultEdge> graph) {
		Iterator<String> iterator = new TopologicalOrderIterator<>(graph);
		while (iterator.hasNext()) {
			String vertex = iterator.next();
			System.out.println(vertex);
		}
	}
	
	private static void iterateCyclicGraph(Graph<String,DefaultEdge> graph) {
		Iterator<String> iterator = new BreadthFirstIterator<>(graph);
		while (iterator.hasNext()) {
			String vertex = iterator.next();
			System.out.println(vertex);
		}
	}
	
	
	private static void renderLinearGraph() {
		GraphBuilder<String, DefaultEdge, ? extends DirectedPseudograph<String, DefaultEdge>> builder = DirectedPseudograph
				.createBuilder(DefaultEdge.class);

		Graph linearGraph = builder.addEdge("A", "B").addEdge("B", "C").buildAsUnmodifiable();
		System.out.println(linearGraph.toString());
		iterateDirectedGraph(linearGraph);
	}

	private static void renderReductionGraph(int length) {
		GraphBuilder<String, DefaultEdge, ? extends DirectedPseudograph<String, DefaultEdge>> builder = DirectedPseudograph
				.createBuilder(DefaultEdge.class);
		
		for (int i = 0; i < length; i++) {
			String generatedString = RandomStringUtils.randomAlphanumeric(10);
		    builder = builder.addEdge(generatedString, "A");
		}
		
		Graph reductionGraph = builder.buildAsUnmodifiable();
		System.out.println(reductionGraph.toString());
		iterateDirectedGraph(reductionGraph);
	}

	private static void renderTriangleGraph() {
		GraphBuilder<String, DefaultEdge, ? extends DirectedPseudograph<String, DefaultEdge>> builder = DirectedPseudograph
				.createBuilder(DefaultEdge.class);

		Graph triangleGraph = builder.addEdge("A", "B").addEdge("B", "C").addEdge("A","C").buildAsUnmodifiable();
		System.out.println(triangleGraph.toString());
		iterateDirectedGraph(triangleGraph);
		CycleDetector<String, DefaultEdge> cycleChecker = new CycleDetector<String, DefaultEdge>(triangleGraph);
		System.out.println("Cycle detected: " + cycleChecker.detectCycles());
	}

	private static void renderCyclicGraph() {
		GraphBuilder<String, DefaultEdge, ? extends DirectedPseudograph<String, DefaultEdge>> builder = DirectedPseudograph
				.createBuilder(DefaultEdge.class);

		Graph cyclicGraph = builder.addEdge("A", "B").addEdge("B", "C").addEdge("C","A").buildAsUnmodifiable();
		System.out.println(cyclicGraph.toString());
		// Fails with NotDirectedAcyclicGraphException
		// iterateDirectedGraph(cyclicGraph);
		iterateCyclicGraph(cyclicGraph);
		CycleDetector<String, DefaultEdge> cycleChecker = new CycleDetector<String, DefaultEdge>(cyclicGraph);
		System.out.println("Cycle detected: " + cycleChecker.detectCycles());
	}

	private static void renderSelfLoopGraph() {
		GraphBuilder<String, DefaultEdge, ? extends DirectedPseudograph<String, DefaultEdge>> builder = DirectedPseudograph
				.createBuilder(DefaultEdge.class);

		Graph selfLoopGraph = builder.addEdge("A", "A").buildAsUnmodifiable();
		System.out.println(selfLoopGraph.toString());
		// Fails with NotDirectedAcyclicGraphException
		// iterateDirectedGraph(selfLoopGraph);
		iterateCyclicGraph(selfLoopGraph);
		CycleDetector<String, DefaultEdge> cycleChecker = new CycleDetector<String, DefaultEdge>(selfLoopGraph);
		System.out.println("Cycle detected: " + cycleChecker.detectCycles());
	}
}
