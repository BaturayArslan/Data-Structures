package algorithm_examples;

import java.util.*;

/* 
	Route Between Nodes: Given a directed graph, design an algorithm to find out whether there is a
	route between two nodes.  
 */

public class RouteBetweenNodes {

	private Graph myGraph;

	private class Node {
		private String name;
		private int data;
		private LinkedList<Node> adjList;

		public Node(String name, int data) {
			this.name = name;
			this.data = data;
			adjList = new LinkedList<Node>();
		}
	}

	private class Graph {
		private ArrayList<Node> nodeList;

		public Graph() {
			nodeList = new ArrayList<Node>();

			Node node1 = new Node("a", 5);
			Node node2 = new Node("b", 10);
			Node node3 = new Node("c", 2);
			Node node4 = new Node("d", 3);
			Node node5 = new Node("e", 7);
			Node node6 = new Node("f", 12);
			nodeList.add(node1);
			nodeList.add(node2);
			nodeList.add(node3);
			nodeList.add(node4);
			nodeList.add(node5);
			nodeList.add(node6);
			node1.adjList.add(node5);
			node5.adjList.add(node2);
			node2.adjList.add(node3);
			node3.adjList.add(node4);
			node3.adjList.add(node6);
			node4.adjList.add(node1);

		}

	}

	public RouteBetweenNodes() {
		myGraph = new Graph();
	}

	public boolean existRoute(Node source, Node destination) {
		// perform DFS and check one of the node equals to destination route
		if (source == null || destination == null) {
			return false;
		}
		HashSet<String> table = new HashSet<String>();
		return search(source, destination.name, table);
	}

	public boolean search(Node node, String name, HashSet<String> table) {

		for (Node item : node.adjList) {
			if (!table.contains(item.name)) {
				table.add(item.name);
				if (item.name == name) {
					return true;
				}
				if (search(item, name, table)) {
					return true;
				}
			}
		}
		return false;
	}

	public Node getNode(int index) {
		return myGraph.nodeList.get(index);
	}

	public static void main(String[] args) {
		RouteBetweenNodes test = new RouteBetweenNodes();
		Node source = test.getNode(0);
		Node destiation = test.getNode(3);
		System.out.println(test.existRoute(source, destiation));
	}
}
