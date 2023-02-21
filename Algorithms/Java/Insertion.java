package algorithm_examples;

/*
	You are given two 32-bit numbers, N and M, and two bit positions, i and j.
	Write a method to insert M into N such that M starts at bit j and ends at bit i.
	You can assume that the bits j through i have enough space to fit all of M. That is,
	if M = 10011, you can assume that there are at least 5 bits between j and i. You would not,
	for example, have j = 3 and i = 2, because M could not fully fit between bit 3 and bit 2.
	EXAMPLE
	Input: N 10000000000, M
	Output: N = 10001001100 
*/

public class Insertion {
	private static class FirstSolution {
		public static int insertion(int N, int M, int i, int j) {
			N = clearBits(N, i, j);
			int mask = createMask(M, i, j);
			return N | mask;
		}

		public static int clearBits(int value, int i, int j) {
			for (int k = i; k <= j; k++) {
				value = clear(value, k);
			}
			return value;
		}

		public static int clear(int value, int index) {
			return value &= (~(1 << index));
		}

		public static int createMask(int value, int i, int j) {
			int mask = 0;
			for (int k = i; k <= j; k++) {
				if (getBit(value, k - i)) {
					mask = setBit(mask, k);
				}
			}

			return mask;
		}

		public static boolean getBit(int value, int index) {
			return (value & (1 << index)) != 0;
		}

		public static int setBit(int value, int index) {
			return value | (1 << index);
		}
	}

	public static void main(String[] args) {
		int N = 17;
		int M = 1;
		int i = 3;
		int j = 3;
		N = FirstSolution.insertion(N, M, i, j);
		System.out.println(N);
	}

}
