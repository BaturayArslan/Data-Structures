package algorithm_examples;

/*
	You have an integer and you can flip exactly one bit from a 0 to a 1. Write code to
	find the length of the longest sequence of ls you could create.
	EXAMPLE
	Input: 1775
	Output: 8  
*/

public class FlipBit {
	public static int longestSequence(int value) {
		int max = 0;
		for (int i = 0; i < 32; i++) {
			int tmp = value | (1 << i);
			int sequence = getSequence(tmp);
			max = sequence > max ? sequence : max;
		}

		return max;
	}

	public static int getSequence(int value) {
		int counter = 0;
		int max = 0;

		for (int i = 0; i < 32; i++) {
			if ((value & (1 << i)) != 0) {
				counter += 1;
			} else {
				max = counter > max ? counter : max;
				counter = 0;
			}
		}
		return max;
	}

	public static void main(String[] args) {
		System.out.println(longestSequence(11));
	}
}
