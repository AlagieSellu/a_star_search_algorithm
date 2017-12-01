package a.star.puzzle;

import java.util.Arrays;
import java.util.LinkedList;

import a.star.algorithm.AStar;
import a.star.algorithm.ProblemNode;

public class Test {

	public static void main(String[] args) {

		PuzzleGenerator generator = new PuzzleGenerator(8);

		int[][] problem = generator.generateTiles();
		int[][] goal = generator.getGoal();

		EightPuzzle puzzle = new EightPuzzle(problem, new EightPuzzle(goal));

		final long startTime1 = System.currentTimeMillis();

		AStar<EightPuzzle> astar = new AStar<EightPuzzle>(puzzle);

		final long duration1 = System.currentTimeMillis() - startTime1;

		System.out.print("Problem\t: ");
		for (int[] row : problem)
			System.out.print(Arrays.toString(row));
		System.out.println();

		System.out.print("Gaol\t: ");
		for (int[] row : goal)
			System.out.print(Arrays.toString(row));
		System.out.println();

		System.out.println("H-Value\t: " + puzzle.getH());
		System.out.println("Moves\t: " + astar.getMoves());
		System.out.println("Opened\t: " + astar.getOpenedCount());
		System.out.println("Closed\t: " + astar.getClosedCount());
		System.out.println("Total\t: " + astar.getExpandedCount());
		System.out.println("Time\t: " + duration1 + " msecs");

		LinkedList<ProblemNode<EightPuzzle>> solutionPath = astar.getSolutionPath();

		for (ProblemNode<EightPuzzle> node : solutionPath)
			System.out.println(node.toString());
	}

}
