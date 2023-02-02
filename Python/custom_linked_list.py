from typing import Protocol, Any, Union, List
from abc import abstractclassmethod
from dataclasses import dataclass


class MyLinkedList(Protocol):
    @abstractclassmethod
    def push(item: Any) -> None:
        ...

    @abstractclassmethod
    def insert(index: int, item: Any) -> None:
        ...

    @abstractclassmethod
    def pop() -> Any:
        ...

    @abstractclassmethod
    def delete(index: int) -> None:
        ...

    @abstractclassmethod
    def get(index: int) -> Any:
        ...

    @abstractclassmethod
    def set(index: int, item: Any) -> None:
        ...

    @abstractclassmethod
    def is_empty() -> bool:
        ...


@dataclass
class Node:
    data: Any
    next: "Node" = None


class CustomLinkedList(MyLinkedList):
    def __init__(self):
        self.__length = 0
        self.__head: Union[Node, None] = None

    def __len__(self):
        return self.__length

    def push(self, item: Any) -> None:
        # if list is empty than set self.__head to new node
        if (not self.__head):
            newNode = Node(item)
            self.__head = newNode
            self.__length += 1
            return

        # Go to last Node
        tmp = self.__head
        while (tmp.next):
            tmp = tmp.next

        newNode = Node(item)
        tmp.next = newNode
        self.__length += 1

    def is_empty(self) -> bool:
        if (self.__head == None):
            return True
        return False

    def __get_node(self, index: int) -> Node:
        if (index >= self.__length and index < 0):
            # Raise An Error
            ...

        counter = 0
        tmp = self.__head
        while (counter < index):
            tmp = tmp.next
            counter += 1

        return tmp

    def pop(self) -> Any:
        # Delete last node and return that node data
        if (self.is_empty()):
            return None

        if (self.__length == 1):
            data = self.__head.data
            self.__head = None
            self.__length -= 1
            return data

        prev_node = self.__get_node(self.__length - 2)
        current_node = prev_node.next
        prev_node.next = None
        self.__length -= 1

        return current_node.data

    def get(self, index: int) -> Any:
        if (self.is_empty):
            # Raise An Error
            ...
        return self.__get_node(index).data

    def set(self, index: int, item: Any) -> None:
        node = self.__get_node(index)
        node.data = item

    def insert(self, index: int, item: Any) -> None:
        # In order to insert, length of linked list has to be at least 1
        if (self.is_empty()):
            # Raise Error
            ...

        if (index == 0):
            newNode = Node(item, self.__head)
            self.__head = newNode
            self.__length += 1
            return

        prevNode = self.__get_node(index - 1)
        newNode = Node(item, prevNode.next)
        prevNode.next = newNode
        self.__length += 1

    def delete(self, index: int):
        if (self.is_empty()):
            # Raise Error
            ...

        if (index == 0):
            self.__head = self.__head.next
            self.__length -= 1

        prevNode = self.__get_node(index - 1)
        currentNode = prevNode.next
        prevNode.next = currentNode.next
        self.__length -= 1

    def get_all(self) -> List[Any]:
        itemList = []
        tmp = self.__head
        while (tmp):
            itemList.append(tmp.data)
            tmp = tmp.next
        return itemList


def main():
    test = CustomLinkedList()
    test.push("hello")
    test.push("world")
    arr = test.get_all()
    print(arr)

    test.insert(0, "insertion")
    arr = test.get_all()
    print(arr)

    test.delete(1)
    arr = test.get_all()
    print(arr)

    print(len(test))

    element = test.pop()
    arr = test.get_all()
    print(f"poped Element: {element}, LinkedList: {arr}, length: {len(test)}")


if (__name__ == "__main__"):
    main()
