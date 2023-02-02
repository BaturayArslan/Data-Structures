from typing import Protocol, Any
from abc import abstractclassmethod
from dataclasses import dataclass, field
from queue import Queue

""" Custom Stack implementation with linked lists"""


@dataclass()
class Node:
    data: Any
    next: "Node" = None


class MyStack(Protocol):
    @abstractclassmethod
    def push(item: int) -> Any:
        ...

    @abstractclassmethod
    def pop() -> Any:
        ...

    @abstractclassmethod
    def is_empty() -> bool:
        ...


class CustomStack(MyStack):
    def __init__(self):
        self.__length = 0
        self.__head = None

    def is_empty(self) -> bool:
        if (self.__head == None):
            return True
        return False

    def push(self, item: Any) -> None:
        if (self.is_empty()):
            self.__head = Node(item)
            self.__length += 1
            return

        newNode = Node(item, self.__head)
        self.__head = newNode
        self.__length += 1

    def pop(self) -> Any:
        if (self.is_empty()):
            # Raise Error
            raise Exception("Stack is Empty.")

        tmp = self.__head.data
        self.__head = self.__head.next
        self.__length -= 1
        return tmp

    def __len__(self):
        return self.__length


def main():
    test = CustomStack()
    test.push("hello")
    test.push(5)
    test.push("merhaba")
    print(f"length:{len(test)}")
    print(f"{test.pop()} {test.pop()} {test.pop()}")
    # test.pop() # Gives An Error


if (__name__ == "__main__"):
    main()
