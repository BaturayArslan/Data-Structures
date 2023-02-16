package algorithm_examples;

import java.util.EmptyStackException;

/* 
	An animal shelter, which holds only dogs and cats, operates on a strictly"first in, first
	out" basis. People must adopt either the "oldest" (based on arrival time) of all animals at the shelter,
	or they can select whether they would prefer a dog or a cat (and will receive the oldest animal of
	that type). They cannot select which specific animal they would like. Create the data structures to
	maintain this system and implement operations such as enqueue, dequeueAny, dequeueDog,
	and dequeueCat. You may use the built-in Linked list data structure. 
 */

public class AnimalShelter {

	private class Animal {
		private String name;

		public Animal(String name) {
			this.name = name;
		}
	}

	private class Cat extends Animal {
		public Cat(String name) {
			super(name);
		}
	}

	private class Dog extends Animal {
		public Dog(String name) {
			super(name);
		}
	}

	private class Node {
		private Animal data;
		private Node nextNode;
		private int timestamp;

		public Node(Animal data, int timestamp) {
			this.data = data;
			this.timestamp = timestamp;
		}
	}

	private Node dogHead;
	private Node dogTail;
	private Node catHead;
	private Node catTail;
	private int counter;

	public AnimalShelter() {
		Cat cat1 = new Cat("gece");
		Cat cat2 = new Cat("bal");
		Cat cat3 = new Cat("tekir");
		Dog dog1 = new Dog("bozkurt");
		Dog dog2 = new Dog("karabaş");
		Dog dog3 = new Dog("kar");
	}

	public void enqueue(Animal item) {
		if (item instanceof Dog) {
			enqueueDog(item);
		} else {
			enqueueCat(item);
		}
	}

	public void enqueueDog(Animal item) {
		if (dogHead == null && dogTail == null) {
			Node newNode = new Node(item, counter);
			dogHead = newNode;
			dogTail = newNode;
			counter += 1;
			return;
		}
		Node newNode = new Node(item, counter);
		dogTail.nextNode = newNode;
		dogTail = newNode;
		counter += 1;
	}

	public void enqueueCat(Animal item) {
		if (catHead == null && catTail == null) {
			Node newNode = new Node(item, counter);
			catHead = newNode;
			catTail = newNode;
			counter += 1;
			return;
		}
		Node newNode = new Node(item, counter);
		catTail.nextNode = newNode;
		catTail = newNode;
		counter += 1;
	}

	public static void main(String[] args) {
		AnimalShelter animalShelter = new AnimalShelter();

	}

	public Animal dequeueAny() {
		Node catNode = peekCat();
		Node dogNode = peekDog();
		if (catNode == null) {
			return dequeueDog();
		} else if (dogNode == null) {
			return dequeueCat();
		} else {
			if (dogNode.timestamp < catNode.timestamp) {
				return dequeueDog();
			} else {
				return dequeueCat();
			}
		}

	}

	public Animal dequeueCat() {
		if (catHead == null) {
			return null;
		} else if (catHead == catTail) {
			Animal result = catHead.data;
			catHead = null;
			catTail = null;
			return result;
		}

		Animal result = catHead.data;
		catHead = catHead.nextNode;
		return result;

	}

	public Animal dequeueDog() {
		if (dogHead == null) {
			return null;
		} else if (dogHead == dogTail) {
			Animal result = dogHead.data;
			dogHead = null;
			dogTail = null;
			return result;
		}

		Animal result = dogHead.data;
		dogHead = dogHead.nextNode;
		return result;

	}

	public Node peekDog() {
		if (dogHead == null) {
			return null;
		}

		Node result = dogHead;
		return result;
	}

	public Node peekCat() {
		if (catHead == null) {
			return null;
		}

		Node result = catHead;
		return result;
	}
}
