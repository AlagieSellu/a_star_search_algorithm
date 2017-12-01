package a.star.puzzle;

import java.util.LinkedList;
import java.util.Random;

public class PuzzleGenerator {

	private int row, column;
	private int[][] goal;
	private Random random;

	public PuzzleGenerator(int dimension) {
		this(dimension, true);
	}

	public PuzzleGenerator(int dimension, boolean def) {

		this.row = (int) Math.sqrt(dimension + 1);
		this.column = row;

		this.random = new Random();

		if (def) {
			goal = new int[row][column];
			int i = 1;
			for (int row = 0; row < this.row; row++)
				for (int column = 0; column < this.column; column++)
					goal[row][column] = i++;
			goal[row - 1][column - 1] = 0;
		} else
			this.setGoal(generateTiles());

	}

	public int[][] generateTiles() {

		LinkedList<Integer> cells = new LinkedList<Integer>();

		for (int i = 0; i < row * column; i++)
			cells.add(i);

		int[][] tiles = new int[row][column];

		for (int row = 0; row < this.row; row++)
			for (int column = 0; column < this.column; column++)
				tiles[row][column] = cells.remove(random.nextInt(cells.size()));

		return tiles;
	}

	/**
	 * @return the goal
	 */
	public int[][] getGoal() {
		return goal;
	}

	/**
	 * @param goal
	 *            the goal to set
	 */
	public void setGoal(int[][] goal) {
		this.goal = goal;
	}

}
