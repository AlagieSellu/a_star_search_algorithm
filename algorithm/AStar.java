package a.star.algorithm;

import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * This algorithm is to find the shortest path from an initial problem state to
 * a goal state
 * 
 * @param <E>
 *            the type of problem to be solved
 */
public class AStar<E> {

	/**
	 * This final variable of type {@code PorblemNode<E>} hold the initial node
	 * which the A* Algorithm is to solve.
	 */
	private final ProblemNode<E> problem;

	/**
	 * A variable of type {@code PriorityQueue<GraphNode<E>>} which holds all the
	 * nodes to be exploited by the A* Algorithm in an order of {@code GraphNode}
	 * with the smallest {@code F Function = G Function () + H Function} first in
	 * the list.
	 */
	private PriorityQueue<GraphNode<E>> open;

	/**
	 * A variable of type {@code LinkedList<GraphNode<E>>} which hold all the
	 * exploited nodes.
	 */
	private LinkedList<GraphNode<E>> close;

	/**
	 * The {@code GraphNode} holds the solution, which can be used to traced the
	 * shortest path to the initial Node holding the initial problem
	 */
	private GraphNode<E> solution;

	/**
	 * Create a {@code AStar} with a specified initial {@code ProblemNode<E>} which
	 * tell the start point the algorithm is to solve to the goal.
	 * 
	 * @param problem
	 *            the initial problem to solve from
	 */
	public AStar(ProblemNode<E> problem) {

		this.problem = problem;
		this.solution = null;

		// inizilize the two list; the open and the close
		open = new PriorityQueue<GraphNode<E>>();
		close = new LinkedList<GraphNode<E>>();

		// add the initial problem node to the list, in a GraphNode<E> object form
		open.add(new GraphNode<E>(problem));

		GraphNode<E> currentNode;

		// do-while to loop until the solution is found or no solution
		do {

			// start by removing the best node in the open list
			currentNode = open.remove();

			// if node removed in open list is the goal, then set solution and break the
			// do-while loop
			if (currentNode.getElement().isGoal()) {
				this.solution = currentNode;
				break;
			}

			// add the node in the close list after it is confirmed not to be the goal node
			close.add(currentNode);

			// get all the possible nodes reachable from the current node position
			LinkedList<ProblemNode<E>> successors = currentNode.getElement().successors();

			GraphNode<E> generated;

			// for through all the reachable nodes from the current node
			for (ProblemNode<E> successor : successors) {

				// get node in list if the child node already generated in either the open or
				// close list
				generated = generated(successor);

				// if child node not in both list, then add to the open list
				if (generated == null)
					open.add(new GraphNode<E>(successor, currentNode));

				// else if it is in one of the list and seems cheaper than the one already in
				// the list, then make the parent of the current node as the parent of the one
				// already in the list
				else if (generated.getDepth() > currentNode.getDepth())
					generated.setParent(currentNode.getParent());

			}

			// while open is not empty, keep on looping
		} while (!open.isEmpty());
	}

	/**
	 * check if child node is in or not in both open and close list
	 */
	private GraphNode<E> generated(ProblemNode<E> child) {

		// find in the open list first
		for (GraphNode<E> node : open)
			if (node.getElement().equal(child))
				return node;

		// then find in the close list
		for (GraphNode<E> node : close)
			if (node.getElement().equal(child))
				return node;

		// else return null if not in the list
		return null;
	}

	/**
	 * @return the problem
	 */
	public ProblemNode<E> getProblem() {
		return problem;
	}

	/**
	 * @return the number of moves to reach the goal node
	 */
	public int getMoves() {
		return solution.getDepth();
	}

	/**
	 * @return the solutionPath in a {@code LinkedList<ProblemNode<E>>} starting
	 *         from the initial position to the goal position
	 */
	public LinkedList<ProblemNode<E>> getSolutionPath() {

		LinkedList<ProblemNode<E>> solutionPath = new LinkedList<ProblemNode<E>>();

		GraphNode<E> solution = this.solution;

		while (solution != null) {
			solutionPath.addFirst(solution.getElement());
			solution = solution.getParent();
		}

		return solutionPath;
	}

	/**
	 * @return the number of node expanded to find the shortest path
	 */
	public int getExpandedCount() {
		return getOpenedCount() + getClosedCount();
	}

	/**
	 * @return the number of node expanded in the open list in find the shortest
	 *         path
	 */
	public int getOpenedCount() {
		return open.size();
	}

	/**
	 * @return the number of node expanded in the close list in find the shortest
	 *         path
	 */
	public int getClosedCount() {
		return close.size();
	}

}
