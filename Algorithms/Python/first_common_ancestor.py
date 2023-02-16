from dataclasses import dataclass
from abc import abstractclassmethod
from typing import Protocol, Any, List, Tuple, Dict
from custom_bst import BST, Node


"""
	Design an algorithm and write code to find the first common ancestor
	of two nodes in a binary tree. Avoid storing additional nodes in a data structure. NOTE: This is not
	necessarily a binary search tree. 
"""


class Solution1:
    # Solution with no link to parent

    @staticmethod
    def common_ancestor(root: Node, q: Node, p: Node) -> Node:
        if (not root or q == root or p == root):
            return root

        q_left = Solution1.in_tree(root.left_node)
        p_left = Solution1.in_tree(root.right_node)
        if (q_left != p_left):
            # Nodes is in diffrent side
            return root
        else:
            # nodes is in same side
            if (q_left):
                return Solution1.common_ancestor(root.left_node, q, p)
            else:
                return Solution1.common_ancestor(root.right_node, q, p)

    @staticmethod
    def in_tree(root: Node, q: Node) -> bool:
        if (not root):
            return False

        if (root == q):
            return True

        return Solution1.in_tree(root.left_node, q) or Solution1.in_tree(root.right_node, q)


class Solution2:
    # Solution with a link to parent

    @staticmethod
    def common_ancestor(root: Node, q: Node, p: Node) -> Node:
        if (not root or not q or not q):
            return None

        ancestors: Dict[Node, Node] = {}
        Solution2.push_ancestor(q, ancestors)
        return Solution2.push_ancestor(p, ancestors)

    @staticmethod
    def push_ancestor(q: Node, list: Dict[Node, Node]):
        if (not q):
            return None

        tmp: Node = q
        while (tmp.parent):
            tmp = tmp.parent
            if (tmp in list):
                return tmp
            list[tmp] = tmp

        return None


def main():
    ...


if (__name__ == "__main__"):
    main()
