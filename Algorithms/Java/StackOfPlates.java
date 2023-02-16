package algorithm_examples;

import java.util.*;

/*
	Imagine a (literal) stack of plates. If the stack gets too high, it might topple.
	Therefore, in real life, we would likely start a new stack when the previous stack exceeds some
	threshold. Implement a data structure SetOfStacks that mimics this. SetO-fStacks should be
	composed of several stacks and should create a new stack once the previous one exceeds capacity.
	SetOfStacks. push() and SetOfStacks. pop() should behave identically to a single stack
	(that is, pop () should return the same values as it would if there were just a single stack).  
*/

public class StackOfPlates<T extends Number> {

	private class Stack {
		private Integer[] list;
		private int index = -1;

		public Stack(int size) {
			this.list = new Integer[size];
		}
	}

	private ArrayList<Stack> stacks;
	private int point;
	private int trashHold;

	public StackOfPlates(int trashHold) {
		stacks = new ArrayList<Stack>();
		stacks.add(new Stack(trashHold));
		this.point = 0;
		this.trashHold = trashHold;
	}

	public void push(Integer item) {
		if (stacks.get(point).index + 1 >= trashHold) {
			// go to new stack
			stacks.add(new Stack(trashHold));
			point += 1;

		}

		Stack stack = stacks.get(point);
		stack.list[stack.index + 1] = item;
		stack.index += 1;
	}

	public Integer pop() {
		if (point == 0 && stacks.get(point).index == -1) {
			throw new EmptyStackException();
		} else if (stacks.get(point).index == -1) {
			point -= 1;
		}
		Stack stack = stacks.get(point);
		Integer tmp = stack.list[stack.index];
		stack.index -= 1;
		return tmp;
	}

	public static void main(String[] args) {
		StackOfPlates<Integer> test = new StackOfPlates<Integer>(2);
		test.push(5);
		test.push(2);
		test.push(3);
		System.out.println(test.pop());
		System.out.println(test.pop());
		System.out.println(test.pop());
		System.out.println(test.pop());

	}
}
