from typing import Any, List
from dataclasses import dataclass

""""
	Minimal Tree: Given a sorted (increasing order) array with unique integer elements,
	write an algorithm to create a binary search tree with minimal height.
"""


@dataclass
class Node:
    data: Any
    left_node: "Node" = None
    right_node: "Node" = None


def minimal_tree(list: List[int]) -> Node:
    if (len(list) == 1):
        return Node(list[0])

    half_point = int(len(list) / 2)
    data = list[half_point]
    newNode = Node(data)
    newNode.left_node = minimal_tree(list[0:half_point])
    if (half_point + 1 < len(list)):
        newNode.right_node = minimal_tree(list[half_point + 1:])

    return newNode


def main():
    result = minimal_tree([5, 6, 8, 12, 17, 19, 20, 25, 30])
    print(result)


main()
