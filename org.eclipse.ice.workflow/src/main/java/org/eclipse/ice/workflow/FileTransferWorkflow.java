package org.eclipse.ice.workflow;

import org.apache.commons.lang3.RandomStringUtils;

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
}

public class FileTransferWorkflow extends LinearWorkflow implements ICEApp {
	
	@Action
	UIRunner fileUI;

	
	@Action
	FileTransferTool fileTransfer;
	
	@Override
	FileTransferWorkflow {
		addTask(fileUI,new UIData());
		addTask(fileTransfer, new FileTransferData());
	}
	
	runApp() {
		start();
	}
}
*/

/*
 * (C) Copyright 2003-2023, by Barak Naveh and Contributors.
 *
 * JGraphT : a free Java graph-theory library
 *
 * See the CONTRIBUTORS.md file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0, or the
 * GNU Lesser General Public License v2.1 or later
 * which is available at
 * http://www.gnu.org/licenses/old-licenses/lgpl-2.1-standalone.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR LGPL-2.1-or-later
 */

import org.jgrapht.*;
import org.jgrapht.alg.cycle.CycleDetector;
import org.jgrapht.graph.*;
import org.jgrapht.graph.builder.GraphBuilder;
import org.jgrapht.graph.builder.GraphTypeBuilder;
import org.jgrapht.nio.*;
import org.jgrapht.nio.dot.*;
import org.jgrapht.traverse.*;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.util.*;

/**
 * A simple introduction to using JGraphT.
 *
 * @author Barak Naveh
 */
public final class FileTransferWorkflow {
	private FileTransferWorkflow() {
	} // ensure non-instantiability.

	/**
	 * The starting point for the demo.
	 *
	 * @param args ignored.
	 *
	 * @throws URISyntaxException if invalid URI is constructed.
	 * @throws ExportException    if graph cannot be exported.
	 */
	public static void main(String[] args) throws URISyntaxException, ExportException {
		Graph<String, DefaultEdge> stringGraph = createStringGraph();

		// note undirected edges are printed as: {<v1>,<v2>}
		System.out.println("-- toString output");
		System.out.println(stringGraph.toString());
		System.out.println();

		// create a graph based on URI objects
		Graph<URI, DefaultEdge> hrefGraph = createHrefGraph();

		// find the vertex corresponding to www.jgrapht.org
		URI start = hrefGraph.vertexSet().stream().filter(uri -> uri.getHost().equals("www.jgrapht.org")).findAny()
				.get();

		// perform a graph traversal starting from that vertex
		System.out.println("-- traverseHrefGraph output");
		traverseHrefGraph(hrefGraph, start);
		System.out.println();

		System.out.println("-- renderHrefGraph output");
		renderHrefGraph(hrefGraph);
		System.out.println();

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
	
	/**
	 * Creates a toy directed graph based on URI objects that represents link
	 * structure.
	 *
	 * @return a graph based on URI objects.
	 */
	private static Graph<URI, DefaultEdge> createHrefGraph() throws URISyntaxException {

		Graph<URI, DefaultEdge> g = new DefaultDirectedGraph<>(DefaultEdge.class);

		URI google = new URI("http://www.google.com");
		URI wikipedia = new URI("http://www.wikipedia.org");
		URI jgrapht = new URI("http://www.jgrapht.org");

		// add the vertices
		g.addVertex(google);
		g.addVertex(wikipedia);
		g.addVertex(jgrapht);

		// add edges to create linking structure
		g.addEdge(jgrapht, wikipedia);
		g.addEdge(google, jgrapht);
		g.addEdge(google, wikipedia);
		g.addEdge(wikipedia, google);

		return g;
	}

	/**
	 * Traverse a graph in depth-first order and print the vertices.
	 *
	 * @param hrefGraph a graph based on URI objects
	 *
	 * @param start     the vertex where the traversal should start
	 */
	private static void traverseHrefGraph(Graph<URI, DefaultEdge> hrefGraph, URI start) {
		Iterator<URI> iterator = new DepthFirstIterator<>(hrefGraph, start);
		while (iterator.hasNext()) {
			URI uri = iterator.next();
			System.out.println(uri);
		}
	}

	/**
	 * Render a graph in DOT format.
	 *
	 * @param hrefGraph a graph based on URI objects
	 */
	private static void renderHrefGraph(Graph<URI, DefaultEdge> hrefGraph) throws ExportException {

		DOTExporter<URI, DefaultEdge> exporter = new DOTExporter<>(v -> v.getHost().replace('.', '_'));
		exporter.setVertexAttributeProvider((v) -> {
			Map<String, Attribute> map = new LinkedHashMap<>();
			map.put("label", DefaultAttribute.createAttribute(v.toString()));
			return map;
		});
		Writer writer = new StringWriter();
		exporter.exportGraph(hrefGraph, writer);
		System.out.println(writer.toString());
	}

	/**
	 * Create a toy graph based on String objects.
	 *
	 * @return a graph based on String objects.
	 */
	private static Graph<String, DefaultEdge> createStringGraph() {
		Graph<String, DefaultEdge> g = new SimpleGraph<>(DefaultEdge.class);

		String v1 = "v1";
		String v2 = "v2";
		String v3 = "v3";
		String v4 = "v4";

		// add the vertices
		g.addVertex(v1);
		g.addVertex(v2);
		g.addVertex(v3);
		g.addVertex(v4);

		// add edges to create a circuit
		g.addEdge(v1, v2);
		g.addEdge(v2, v3);
		g.addEdge(v3, v4);
		g.addEdge(v4, v1);

		return g;
	}
}
