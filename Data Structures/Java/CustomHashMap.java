package com.mycompany.datastructures;

import java.util.*;
import java.lang.Iterable;
import java.security.KeyException;
import javax.xml.stream.events.Characters;

interface Countable {
	public int length();
}

interface MyHashMap {
	public Set keys();

	public Collection getValues();

	public void put(Object key, Object value);

	public void delete(Object key);

	public Object get(Object key);

	public boolean exists(Object key);

	public int size();

	public int length();

}

class CustomBucket<K, V> {
	private K key;
	private V value;
	private int hashCode;

	public CustomBucket(K key, V value, int hashcode) {
		this.key = key;
		this.value = value;
		this.hashCode = hashcode;
	}

	public K getKey() {
		return key;
	}

	public V getValue() {
		return value;
	}

	public int getHash() {
		return hashCode;
	}
}

public class CustomHashMap<K extends String, V> {
	// TODO::Write set function for updating element
	// TODO::Check "put" method.What happends when we provide a key that key already
	// in HashMap

	private int size = 8;
	private int length = 0;
	private CustomBucket<K, V>[] arr;

	public CustomHashMap() {
		arr = new CustomBucket[size];
	}

	private int hash(K key) {
		int sum = 0;
		for (int i = 0; i < key.length(); i++) {
			sum += (int) key.charAt(i);
		}
		return sum % size;
	}

	private int fullnessRat() {
		return (length / size) * 100;
	}

	private void doubleSize() {
		CustomBucket<K, V>[] buckets = getBuckets();
		size *= 2;
		CustomBucket<K, V>[] newArr = new CustomBucket[size];
		int hashVal;
		int finalPlace;
		for (int i = 0; i < length; i++) {
			hashVal = hash(buckets[i].getKey());
			finalPlace = collisionHandler(hashVal, newArr);
			newArr[finalPlace] = new CustomBucket<K, V>(buckets[i].getKey(), buckets[i].getValue(), hashVal);
		}
		arr = newArr;
	}

	public void put(K key, V value) {
		if (fullnessRat() >= 70)
			doubleSize();
		int hashVal = hash(key);
		int finalPlace = collisionHandler(hashVal, arr);
		arr[finalPlace] = new CustomBucket<K, V>(key, value, hashVal);
		length++;
	}

	public void delete(K key) throws KeyException {
		int index = findKeyIndex(key);
		arr[index] = null;
	}

	public V get(K key) throws KeyException {
		int index = findKeyIndex(key);
		return arr[index].getValue();
	}

	private int findKeyIndex(K key) throws KeyException {
		int hashVal = hash(key);
		return searchKeyIndex(key, hashVal);

	}

	private int searchKeyIndex(K key, int index) throws KeyException {
		if (index >= size)
			index = 0;

		if (arr[index] == null) {
			// Throw and Key ERRROR
			throw new KeyException();
		}

		if (arr[index].getKey() == key) {
			return index;
		} else {
			return searchKeyIndex(key, index + 1);
		}

	}

	public V[] getValues() {
		V[] tmp = (V[]) new Object[length];
		int index = 0;
		for (int i = 0; i < size; i++) {
			if (arr[i] == null) {
				continue;
			}
			tmp[index] = arr[i].getValue();
			index++;
		}
		return tmp;
	}

	public K[] getKeys() {
		K[] tmp = (K[]) new Object[length];
		int index = 0;
		for (int i = 0; i < size; i++) {
			if (arr[i] == null) {
				continue;
			}
			tmp[index] = arr[i].getKey();
			index++;
		}
		return tmp;
	}

	private CustomBucket<K, V>[] getBuckets() {
		CustomBucket<K, V>[] tmp = new CustomBucket[length];
		int index = 0;
		for (int i = 0; i < size; i++) {
			if (arr[i] == null) {
				continue;
			}
			tmp[index] = arr[i];
			index++;
		}
		return tmp;

	}

	private int collisionHandler(int hashVal, CustomBucket<K, V>[] arr) {
		if (hashVal >= size)
			hashVal = 0;
		if (arr[hashVal] == null) {
			return hashVal;
		} else {
			return collisionHandler(hashVal + 1, arr);
		}
	}

	public static void main(String[] args) throws KeyException {
		CustomHashMap<String, String> test = new CustomHashMap<String, String>();
		test.put("ab", "merhaba");
		test.put("b", "hello");
		test.put("ba", "test");
		System.out.println(test.get("ba"));

	}
}
