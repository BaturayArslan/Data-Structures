package algorithm_examples;

import java.lang.*;

/*
	Given a real number between O and 1 (e.g., 0.72) that is passed in as a double, print
	the binary representation. If the number cannot be represented accurately in binary with at most 32
	characters, print "ERROR:'
*/
public class BinaryToString {

	public static String binaryToString(double value) {
		StringBuilder result = new StringBuilder();
		result.append("0,");
		try {
			calculateBinary(result, value);
			return result.toString();
		} catch (Exception e) {
			// TODO: handle exception
			return "ERROR";
		}
	}

	private static void calculateBinary(StringBuilder builder, double value) throws Exception {
		if (value == (double) 0) {
			return;
		} else if (builder.length() > 32) {
			throw new Exception("Error");
		}

		value *= 2;

		// append values decimal part
		builder.append(Integer.toString((int) value));

		// recurse with values fractional(floating) part
		calculateBinary(builder, value - (int) value);
		;
	}

	public static void main(String[] args) {
		String result = BinaryToString.binaryToString((double) 22 / 7);
		System.out.println(result);
	}
}
