package algorithm_examples;

import java.util.*;

// TODO:: that code has a bug fix later

/*
	Rotate Matrix: Given an image represented by an NxN matrix, where each pixel in the image is 4
	bytes, write a method to rotate the image by 90 degrees. Can you do this in place?
*/

public class RotateMatrix {
	private int col;
	private int row;
	private int[][] matrix;

	public RotateMatrix(int dimension) {
		this.row = dimension;
		this.col = dimension;
		this.matrix = new int[row][col];

		Random rand = new Random();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				matrix[i][j] = rand.nextInt(11);
			}
		}

		print();

	}

	public void print() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.printf("%d ", matrix[i][j]);

			}
			System.out.println("");
		}

	}

	public void rotate() {
		for (int i = 0; i < row / 2; i++) {
			int lastRow = row - 1 - i;
			int lastCol = row - 1 - i;
			for (int j = i; j < lastCol; j++) {
				int topLeft = matrix[i][j];
				int topRight = matrix[j][lastCol];
				matrix[j][lastCol] = topLeft;
				int bottomRight = matrix[lastRow][lastCol - j];
				matrix[lastRow][lastCol - j] = topRight;
				int bottomLeft = matrix[lastRow - j][i];
				matrix[lastRow - j][i] = bottomRight;
				matrix[i][j] = bottomLeft;

			}
			print();

		}
	}

	public static void main(String[] args) {
		RotateMatrix test = new RotateMatrix(4);
		System.out.println();
		test.rotate();
	}
}
