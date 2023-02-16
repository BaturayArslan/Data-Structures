package algorithm_examples;

/*
	String Compression: Implement a method to perform basic string compression using the counts
	of repeated characters. For example, the string aabcccccaaa would become a2blc5a3. If the
	"compressed" string would not become smaller than the original string, your method should return
	the original string. You can assume the string has only uppercase and lowercase letters (a - z).  
*/

public class StringCompression {
	private char[] input;

	public StringCompression(String input) {
		this.input = input.toCharArray();
	}

	public String compress() {
		StringBuilder result = new StringBuilder();
		char prevChar = input[0];
		int counter = 1;

		for (int i = 1; i < input.length; i++) {
			char character = input[i];
			if (prevChar == character) {
				counter += 1;
			} else {
				result.append(prevChar);
				result.append(counter);
				prevChar = character;
				counter = 1;
			}
		}
		result.append(prevChar);
		result.append(counter);

		if (result.length() == input.length * 2) {
			return new String(input);
		}

		return result.toString();

	}

	public static void main(String[] args) {
		StringCompression test = new StringCompression("aabcccccaaa");
		String result = test.compress();
		System.out.println(result);
	}
}
