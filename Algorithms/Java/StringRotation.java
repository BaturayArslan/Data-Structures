package algorithm_examples;

/*
	String Rotation:Assumeyou have a method isSubstringwhich checks if oneword is a substring
	of another. Given two strings, sl and s2, write code to check if s2 is a rotation of sl using only one
	call to isSubstring (e.g., "waterbottle" is a rotation of"erbottlewat"). 
*/

public class StringRotation {

	public boolean isSubString(String first, String second) {
		if (first.length() != second.length()) {
			return false;
		}

		char[] firstInput = first.toCharArray();
		char[] secondInput = second.toCharArray();

		for (int i = 0; i < secondInput.length; i++) {
			if (firstInput[0] == secondInput[i]) {
				if (isMatch(firstInput, secondInput, i)) {
					return true;
				}
			}
		}

		return false;
	}

	private boolean isMatch(char[] firstInput, char[] secondInput, int index) {
		int firstCounter = 0;
		int secondCounter = index;
		for (int i = 0; i < firstInput.length; i++) {
			if (secondCounter == secondInput.length)
				secondCounter = 0;
			if (firstInput[i] != secondInput[secondCounter]) {
				return false;
			}
			secondCounter += 1;
		}

		return true;
	}

	public static void main(String[] args) {

	}

}
