from dataclasses import dataclass
from typing import Protocol, Any, List, Deque
from abc import abstractclassmethod
from collections import deque
# Custom Binary Search Tree Implementation


class MyBST(Protocol):
    @abstractclassmethod
    def search(item: Any) -> "Node":
        ...

    @abstractclassmethod
    def push(item: Any) -> None:
        ...

    @abstractclassmethod
    def height() -> int:
        ...

    @abstractclassmethod
    def level_traversal() -> List[Any]:
        ...

    @abstractclassmethod
    def inorder_traversal() -> List[Any]:
        ...

    @abstractclassmethod
    def preorder_traversal() -> List[Any]:
        ...


@dataclass
class Node:
    data: Any
    left_node: "Node" = None
    right_node: "Node" = None


class BST(MyBST):
    def __init__(self):
        self.__root = None

    def push(self, item: Any) -> None:
        self.__root = self.__push(item, self.__root)

    def __push(self, item: Any, root: Node) -> Node:
        if (not root):
            return Node(item)
        if (item < root.data):
            root.left_node = self.__push(item, root.left_node)
        else:
            root.right_node = self.__push(item, root.right_node)

        return root

    def height(self) -> int:
        return self.__height(self.__root)

    def __height(self, root: Node) -> int:
        if (not root):
            return -1

        left_height = self.__height(root.left_node)
        right_height = self.__height(root.right_node)
        return max(left_height, right_height) + 1

    def search(self, item: Any) -> Node:
        return self.__search(item, self.__root)

    def __search(self, item: Any, root: Node):
        if (not root):
            return None

        if (root.data == item):
            return root

        if (item < root.data):
            return self.__search(root.left_node)
        else:
            return self.__search(root.left_node)

    def level_traversal(self) -> List[Any]:
        if (not self.__root):
            return []

        que = deque()
        que.append(self.__root)
        list = []
        return self._process_que(list, que)

    def _process_que(self, list: List[Any], que: Deque[Node]) -> List[Any]:
        while (len(que) > 0):
            item = que.popleft()
            if (item.left_node):
                que.append(item.left_node)
            if (item.right_node):
                que.append(item.right_node)
            list.append(item.data)
        return list

    def inorder_traversal(self) -> List[Any]:
        result = []
        self.__inorder_traversal(self.__root, result)
        return result

    def __inorder_traversal(self, root: Node, list: List[Any]):
        if (not root):
            return

        self.__inorder_traversal(root.left_node, list)
        list.append(root.data)
        self.__inorder_traversal(root.right_node, list)

    def preorder_traversal(self) -> List[Any]:
        result = []
        self.__preorder_traversal(self.__root, result)
        return result

    def __preorder_traversal(self, root: Node, list: List[Any]):
        if (not root):
            return

        list.append(root.data)
        self.__preorder_traversal(root.left_node, list)
        self.__preorder_traversal(root.right_node, list)


def main():
    test = BST()
    test.push(5)
    test.push(1)
    test.push(10)
    test.push(9)
    test.push(4)
    print(test.height())
    print(test.inorder_traversal())
    print(test.preorder_traversal())
    print(test.level_traversal())


main()
