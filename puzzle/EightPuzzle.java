/**
 * 
 */
package a.star.puzzle;

import java.util.LinkedList;

import a.star.algorithm.ProblemNode;

/**
 * @author sellu
 *
 */
public class EightPuzzle extends ProblemNode<EightPuzzle> {

	private int[][] tiles;

	/**
	 * An {@code int Array} storing the position of the empty cell.
	 * 
	 * @value empty[0] => stores the row index
	 * @value empty[1] => stores the column index
	 */
	private int[] empty;

	/**
	 * Constructor for goal state, which only specify the tiles of the current
	 * state, which is the goal state
	 * 
	 * @param goal
	 *            is the goal state
	 */
	public EightPuzzle(int[][] tiles) {
		this(tiles, null);
	}

	/**
	 * Constructor for initial state, specifying goal state
	 * 
	 * @param tiles
	 *            is the current state tiles
	 * @param goal
	 *            is the goal state
	 * @param empty
	 *            is the position of the empty cell Constructor for specifying the
	 *            tiles
	 * 
	 */
	public EightPuzzle(int[][] tiles, EightPuzzle goal) {
		this(tiles, goal, null);
	}

	/**
	 * Constructor for initial state, specifying goal state, and the position of the
	 * empty cell
	 * 
	 * @param tiles
	 *            is the current state tiles
	 * @param goal
	 *            is the goal state
	 * @param empty
	 *            is the position of the empty cell Constructor for specifying the
	 *            tiles
	 * 
	 */
	public EightPuzzle(int[][] tiles, EightPuzzle goal, int[] empty) {

		// call super constructor from ProblemNode class, which set goal state
		super(goal);

		this.tiles = tiles;
		this.empty = empty;

		// if the empty position is not known, set it
		if (empty == null)
			this.empty = getIndex(0, tiles);
	}

	@Override
	public int getH() {
		return getMahattan();
	}

	/**
	 * @return the cost of the current state from the goal state using Hamming
	 *         function
	 */
	public int getHamming() {

		int displaced = 0;

		for (int row = 0; row < this.tiles.length; row++)

			for (int column = 0; column < this.tiles[0].length; column++)
				if (this.tiles[row][column] != this.getGoal().tiles[row][column])
					displaced++;

		return displaced;
	}

	/**
	 * @return the cost of the current state from the goal state using Mahattan
	 *         function
	 */
	public int getMahattan() {

		int cost = 0;

		// [0] => row index, [1] => column index
		int[] valueIndex;

		for (int row = 0; row < this.tiles.length; row++)

			for (int column = 0; column < this.tiles[0].length; column++) {

				valueIndex = getIndex(this.tiles[row][column], this.getGoal().tiles);

				cost += Math.abs(valueIndex[0] - row) + Math.abs(valueIndex[1] - column);
			}

		return cost;
	}

	@Override
	public boolean equal(Object obj) {

		EightPuzzle board = (EightPuzzle) obj;

		for (int row = 0; row < this.tiles.length; row++)

			for (int column = 0; column < this.tiles[0].length; column++)

				if (this.tiles[row][column] != board.tiles[row][column])
					return false;

		return true;
	}

	/**
	 * get all possible moves successors from this current board
	 */
	@Override
	public LinkedList<ProblemNode<EightPuzzle>> successors() {

		LinkedList<ProblemNode<EightPuzzle>> neighbours = new LinkedList<ProblemNode<EightPuzzle>>();

		// hold move tiles
		int[][] moveTiles;

		// move down, if possible
		if (empty[0] != 0) {

			moveTiles = cloneTiles();

			moveTiles[empty[0]][empty[1]] = this.tiles[empty[0] - 1][empty[1]];
			moveTiles[empty[0] - 1][empty[1]] = 0;

			int[] em = { empty[0] - 1, empty[1] };

			neighbours.add(new EightPuzzle(moveTiles, this.getGoal(), em));
		}

		// move left, if possible
		if (empty[1] != 2) {

			moveTiles = cloneTiles();

			moveTiles[empty[0]][empty[1]] = this.tiles[empty[0]][empty[1] + 1];
			moveTiles[empty[0]][empty[1] + 1] = 0;

			int[] em = { empty[0], empty[1] + 1 };

			neighbours.add(new EightPuzzle(moveTiles, this.getGoal(), em));
		}

		// move up, if possible
		if (empty[0] != 2) {

			moveTiles = cloneTiles();

			moveTiles[empty[0]][empty[1]] = this.tiles[empty[0] + 1][empty[1]];
			moveTiles[empty[0] + 1][empty[1]] = 0;

			int[] em = { empty[0] + 1, empty[1] };

			neighbours.add(new EightPuzzle(moveTiles, this.getGoal(), em));
		}

		// move right, if possible
		if (empty[1] != 0) {

			moveTiles = cloneTiles();

			moveTiles[empty[0]][empty[1]] = this.tiles[empty[0]][empty[1] - 1];
			moveTiles[empty[0]][empty[1] - 1] = 0;

			int[] em = { empty[0], empty[1] - 1 };

			neighbours.add(new EightPuzzle(moveTiles, this.getGoal(), em));
		}

		return neighbours;
	}

	/**
	 * @return a tiles board after being cloned
	 */
	private int[][] cloneTiles() {

		int[][] clone = new int[this.tiles.length][this.tiles[0].length];

		for (int row = 0; row < this.tiles.length; row++)

			for (int column = 0; column < this.tiles[0].length; column++)
				clone[row][column] = this.tiles[row][column];

		return clone;
	}

	@Override
	public String toString() {

		String board = "";

		for (int[] row : this.tiles) {

			for (int cell : row)
				board += cell + " ";

			board += "\n";
		}

		return board;
	}

	/**
	 * @param value
	 *            the object to find
	 * @param tiles
	 *            the tiles to find the value from
	 * 
	 * @return the row and column index of a value from a given tiles
	 */
	private int[] getIndex(int value, int[][] tiles) {

		for (int row = 0; row < tiles.length; row++)

			for (int column = 0; column < tiles[0].length; column++)

				if (tiles[row][column] == value) {
					int[] empty = { row, column };
					return empty;
				}

		return null;
	}

	@Override
	public boolean isGoal() {
		return equal(this.getGoal());
	}

}
