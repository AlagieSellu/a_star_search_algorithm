package a.star.hanoi;

import java.util.LinkedList;

import a.star.algorithm.AStar;
import a.star.algorithm.ProblemNode;

public class Test {

	public static void main(String[] args) {

		int[][] problem = { { 6, 5, 4, 3, 2, 1 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 } };
		int[][] goal = { { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 6, 5, 4, 3, 2, 1 } };

		TowerOfHanoi hanoi = new TowerOfHanoi(problem, new TowerOfHanoi(goal));

		final long startTime1 = System.currentTimeMillis();

		AStar<TowerOfHanoi> astar = new AStar<TowerOfHanoi>(hanoi);

		final long duration1 = System.currentTimeMillis() - startTime1;

		System.out.println("H-Value\t: " + hanoi.getH());
		System.out.println("Moves\t: " + astar.getMoves());
		System.out.println("Opened\t: " + astar.getOpenedCount());
		System.out.println("Closed\t: " + astar.getClosedCount());
		System.out.println("Total\t: " + astar.getExpandedCount());
		System.out.println("Time\t: " + duration1 + " msecs");

		LinkedList<ProblemNode<TowerOfHanoi>> solutionPath = astar.getSolutionPath();

		for (ProblemNode<TowerOfHanoi> node : solutionPath)
			System.out.println(node.toString());
	}

}
