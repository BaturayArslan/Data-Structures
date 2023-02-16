from typing import Any, Optional
from dataclasses import dataclass
import random


@dataclass
class Node:
	data: Any
	nextNode: Optional["Node"] = None


class LinkedList:
	def __init__(self):
		self.__head: Node = None
		self.__length: int = 0

	def push(self, item: Any) -> None:
		if (not self.__head):
			newNode = Node(item)
			self.__head = newNode
			self.__length += 1
			return

		tmp = self.__head
		while (tmp.nextNode):
			tmp = tmp.nextNode

		newNode = Node(item)
		tmp.nextNode = newNode
		self.__length += 1

	def make_random(self, size: int) -> None:
		"""Full fill linked list with random values"""
		for i in range(size):
			self.push(random.randrange(0, 10))

	def partition(self, value: int) -> None:
		"""
				Solution for Cracking the coding interview -> LinkedLists -> Partition problem (page: 105)
				Partition: Write code to partition a linked list around a value x, such that all nodes less than x come
				before all nodes greater than or equal to x. If x is contained within the list, the values of x only need
				to be after the elements less than x (see below). The partition element x can appear anywhere in the
				"right partition"; it does not need to appear between the left and right partitions.
				EXAMPLE
				Input:
				Output:
				3 -> 5 -> 8 -> 5 -> 10 -> 2 -> 1 [partition= 5]
				3 -> 1 -> 2 -> 10 -> 5 -> 5 -> 8
		"""
		if (not self.__head):
			return

		tmp = self.__head
		rightBeg = None
		rightEnd = None
		leftBeg = None
		leftEnd = None

		while (tmp):
			if (tmp.data >= value):
				if (rightEnd):
					rightEnd.nextNode = tmp
					rightEnd = tmp
				else:
					rightBeg = tmp
					rightEnd = tmp
			else:
				if (leftEnd):
					leftEnd.nextNode = tmp
					leftEnd = tmp
				else:
					leftBeg = tmp
					leftEnd = tmp

			tmp = tmp.nextNode

		rightEnd.nextNode = None
		leftEnd.nextNode = rightBeg

		self.__head = leftBeg

	def reverse(self):
		if (not self.__head):
			return

		tmp = self.__head
		prevNode = None
		nextNode = tmp.nextNode

		while (nextNode):
			tmp.nextNode = prevNode
			prevNode = tmp
			tmp = nextNode
			nextNode = nextNode.nextNode

		tmp.nextNode = prevNode
		self.__head = tmp
		self.print()

	@staticmethod
	def sum_list(first_list: "LinkedList", second_list: "LinkedList") -> "LinkedList":
		""" 
				Solution for cracking the coding algorithm -> LinkedLists -> Sum List problem (Page: 106)
				Sum Lists: You have two numbers represented by a linked list, where each node contains a single
				digit. The digits are stored in reverse order, such that the 1 's digit is at the head of the list. Write a
				function that adds the two numbers and returns the sum as a linked list.
				EXAMPLE
				Input: (7-> 1 -> 6) + (5 -> 9 -> 2).That is,617 + 295.
				Output: 2 -> 1 -> 9. That is, 912.
				FOLLOW UP
				Suppose the digits are stored in forward order. Repeat the above problem.
				EXAMPLE
				lnput:(6 -> 1 -> 7) + (2 -> 9 -> 5).That is,617 + 295.
				Output: 9 -> 1 -> 2. That is, 912. 
		"""
		if (not first_list.__head and not second_list.__head):
			return

		# first_list.reverse()
		# second_list.reverse()

		first_tmp = first_list.__head
		second_tmp = second_list.__head
		coefficient = 0
		sum = 0
		result = LinkedList()

		while (first_tmp or second_tmp):
			if (not first_tmp):
				sum, coefficient = LinkedList.add_number(
					0, second_tmp.data, coefficient)
				result.push(sum)
				second_tmp = second_tmp.nextNode
			elif (not second_tmp):
				sum, coefficient = LinkedList.add_number(
					first_tmp.data, 0, coefficient)
				result.push(sum)
				first_tmp = first_tmp.nextNode
			else:
				sum, coefficient = LinkedList.add_number(
					first_tmp.data, second_tmp.data, coefficient)
				result.push(sum)
				first_tmp = first_tmp.nextNode
				second_tmp = second_tmp.nextNode

		if (coefficient):
			result.push(coefficient)

		# result.reverse()
		return result

	@staticmethod
	def add_number(first: int, second: int, coefficient: int):
		sum = first + second
		sum += coefficient
		coefficient = 0
		if (sum >= 10):
			coefficient = 1
			sum -= 10
		return (sum, coefficient)

	def get_length(self) -> int:
		if (not self.__head):
			return 0

		tmp = self.__head
		counter = 0
		while (tmp):
			counter += 1
			tmp = tmp.nextNode

		return counter

	def intersection(self, list: "LinkedList") -> Node:
		"""
				Solution for cracking the coding algorithm -> LinkedLists -> Intersection problem (Page: 106)
				Intersection: Given two (singly) linked lists, determine if the two lists intersect. Return the intersecting node. Note that the intersection is defined based on reference, not value. That is, if the kth
				node of the first linked list is the exact same node (by reference) as the jth node of the second
				linked list, then they are intersecting. 
		"""
		if (not self.__head or not list.__head):
			return

		first_length = self.get_length()
		second_length = list.get_length()

		first_tmp = self.__head
		second_tmp = list.__head

		if (first_length > second_length):
			counter = first_length - second_length
			while (counter > 0):
				first_tmp = first_tmp.nextNode
				counter -= 1
		elif (second_length > first_length):
			counter = second_length - first_length
			while (counter > 0):
				second_tmp = second_tmp.nextNode
				counter -= 1

		return self.is_intersect(first_tmp, second_tmp)

	def is_intersect(self, first: Node, second: Node) -> Node:
		if (not first or not second):
			return None

		if (second == first):
			return second

		return self.is_intersect(first.nextNode, second.nextNode)

	def print(self):
		if (not self.__head):
			return

		tmp = self.__head
		while (tmp):
			print("%d " % tmp.data, end="")
			tmp = tmp.nextNode
		print("")


def main():
	# linkedlist = LinkedList()
	# linkedlist.make_random(10)
	# linkedlist.print()
	# linkedlist.partition(5)
	# linkedlist.print()

	first = LinkedList()
	first.make_random(2)
	first.print()

	second = LinkedList()
	second.make_random(3)
	second.print()

	result = LinkedList.sum_list(first, second)
	result.print()


# if (__name__ == "__main__"):
main()
