from dataclasses import dataclass
from abc import abstractclassmethod
from typing import Protocol, List
from custom_bst import BST, Node

"""
	Validate BST: Implement a function to check if a binary tree is a binary search tree. 
"""


def validate_bst(root: Node) -> bool:
    if (root == None):
        return True

    left_data = root.left_node.data if root.left_node else float('-inf')
    right_data = root.right_node.data if root.right_node else float('inf')

    if (root.data > right_data and root.data <= left_data):
        return False

    return validate_bst(root.left_node) and validate_bst(root.right_node)


class Solution1:
    @staticmethod
    def validate_bst(root: Node) -> bool:
        arr = []
        index = 0
        Solution1.inorder_traverser(root, arr)
        return Solution1.check_bst(arr)

    @staticmethod
    def inorder_traverser(root: Node, arr: List[int]) -> None:
        if (not root):
            return

        Solution1.inorder_traverser(root.left_node, arr)
        arr.append(root.data)
        Solution1.inorder_traverser(root.right_node, arr)

    @staticmethod
    def check_bst(arr: List[int]) -> bool:
        for index, element in enumerate(arr[:-1]):
            if (arr[index] > arr[index + 1]):
                return False
        return True


class Solution1:
    @staticmethod
    def validate_bst(root: Node) -> bool:
        last_data = float('-inf')
        return Solution2.check_bst(root, last_data)

    @staticmethod
    def check_bst(root: Node, last_data: int) -> bool:
        if (not root):
            return True

        if (not Solution2.check_bst(root.left_node, last_data)):
            return False

        if (last_data != float('-inf') and last_data > root.data):
            return False

        last_data = root.data

        if (not Solution2.check_bst(root.right_node, last_data)):
            return False

        return True


class Solution2:

    @staticmethod
    def validate_bst(root: Node) -> bool:
        return Solution2(root, None, None)

    @staticmethod
    def check_bst(root: Node, min: int, max: int) -> bool:
        if (not root):
            return True

        if (min and root.data <= min):
            return False
        if (max and root.data > max):
            return False
        if (not Solution2.check_bst(root.left_node, min, root.data)):
            return False
        if (not Solution2.check_bst(root.right_node, root.data, max)):
            return False

        return True


def main():
    bst = BST()
    bst.push(10)
    bst.push(7)
    bst.push(12)
    bst.push(7)
    bst.push(11)
    bst.push(15)
    bst.push(4)
    bst.push(8)
    bst.push(5)
    bst.push(9)
    # print(validate_bst(bst._BST__root))
    print(Solution2.validate_bst(bst._BST__root))


if (__name__ == "__main__"):
    main()
