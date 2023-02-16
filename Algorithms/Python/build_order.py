from dataclasses import dataclass
from abc import abstractclassmethod
from typing import Protocol, Any, List, Tuple
from custom_bst import BST, Node


"""
	You are given a list of projects and a list of dependencies (which is a list of pairs of
	projects, where the second project is dependent on the first project). All of a project's dependencies
	must be built before the project is. Find a build order that will allow the projects to be built. If there
	is no valid build order, return an error.
	EXAMPLE
	Input:
	projects: a, b, c, d, e, f
	dependencies: (a, d), (f, b), (b, d), (f, a), (d, c)
	Output: f, e, a, b, d, c 
"""


@dataclass
class Node:
    name: str
    data: Any
    adjacency_list: List["Node"]


class Graph:
    def __init__(self, vertices: List[str], edges: List[Tuple[str, str]]):
        self.node_list: dict[str, Node] = {}

        for name in vertices:
            self.node_list[name] = Node(name, None, [])

        for dependency_name, dependent_name in edges:
            self.node_list[dependent_name].adjacency_list.append(
                self.node_list[dependency_name])

    def build_order(self) -> List[str]:
        ordered_nodes: dict[str, str] = {}
        order_list: List[str] = []

        for name in self.node_list:
            self._make_order(order_list, ordered_nodes, name)

        return order_list

    def _make_order(self, order_list: List[str], ordered_nodes: dict[str, str], name: str):
        if (name in ordered_nodes):
            return
        for node in self.node_list[name].adjacency_list:

            self._make_order(order_list, ordered_nodes, node.name)

        ordered_nodes[name] = name
        order_list.append(name)


def main():
    list = ["a", "b", "c", "d", "e", "f"]
    dependencies = [("a", "d"), ("f", "b"), ("b", "d"), ("f", "a"), ("d", "c")]
    graph = Graph(list, dependencies)
    result = graph.build_order()
    print(result)


if (__name__ == "__main__"):
    main()
