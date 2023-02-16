package algorithm_examples;

import java.util.*;

/*
	Zero Matrix: Write an algorithm such that if an element in an MxN matrix is 0, its entire row and
	column are set to 0
 */

public class ZeroMatrix {
	private int[][] matrix;
	private int row;
	private int col;

	public ZeroMatrix(int row, int col) {
		this.row = row;
		this.col = col;
		this.matrix = new int[row][col];

		Random rand = new Random();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				matrix[i][j] = rand.nextInt(11);
			}
		}

		print();

	}

	public ArrayList<Position> zero_positions() {
		ArrayList<Position> result = new ArrayList<Position>();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (matrix[i][j] == 0) {
					result.add(new Position(i, j));
				}
			}
		}
		return result;

	}

	public void transform() {
		ArrayList<Position> zeroPositionList = zero_positions();

		for (Position position : zeroPositionList) {
			for (int i = 0; i < row; i++) {
				matrix[i][position.col] = 0;
			}

			for (int i = 0; i < col; i++) {
				matrix[position.row][i] = 0;
			}
		}

	}

	public void print() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.printf("%d ", matrix[i][j]);

			}
			System.out.println("");
		}

	}

	private class Position {
		public int row;
		public int col;

		public Position(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	public static void main(String[] args) {
		ZeroMatrix test = new ZeroMatrix(6, 10);
		System.out.println("");
		test.transform();
		test.print();

	}
}
