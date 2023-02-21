package algorithm_examples;

/*
	Given a positive integer, print the next smallest and the next largest number that
	have the same number of 1 bits in their binary representation.
*/

public class NextNumber {
	public static int[] nextNumber(int value) {
		int nextSmallest = getSmallest(value);
		int nextLarger = getLarger(value);

		int[] result = new int[2];
		result[0] = nextSmallest;
		result[1] = nextLarger;
		return result;
	}

	private static int getLarger(int value) {
		int oneCount = getOneCount(value);
		int result = -1 << 32 - oneCount - 1;
		result &= (~(1 << 31));

		return result;
	}

	private static int getOneCount(int value) {
		int counter = 0;
		for (int i = 0; i < 32; i++) {
			if ((value & (1 << i)) != 0) {
				counter += 1;
			}
		}
		return counter;
	}

	private static int getOneCount(int value, int index) {
		if (index < 0) {
			return -1;
		}

		int counter = 0;
		for (int i = 0; i <= index; i++) {
			if ((value & (1 << i)) != 0) {
				counter += 1;
			}
		}

		return counter;
	}

	private static int getSmallest(int value) {
		for (int i = 1; i < 32; i++) {
			// find first "0" bit that has "1" bit right before, like 0"0"1100
			if ((value & (1 << i - 1)) != 0 && (value & (1 << i)) == 0) {
				// set found "0" bit and clear before "1" bit
				value |= (1 << i);
				value &= (~(1 << i - 1));
				// at this point move every before "1" bit to begining
				int OneCount = getOneCount(value, i - 2);
				if (OneCount >= 0) {
					int clearMask = -1 << i - 1;
					int setMask = (1 << OneCount) - 1;
					value &= clearMask;
					value |= setMask;
				}
				return value;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		int[] result = nextNumber(11);
		System.out.println(result[0]);
		System.out.println(result[1]);
	}
}
