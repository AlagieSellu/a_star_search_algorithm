package a.star.algorithm;

import java.util.LinkedList;

/**
 * Class {@code ProblemNode<E>} is an abstract class which any one trying to use
 * the {@code AStar} algorithm to find the shortest path to a goal node, should
 * extend in his or her {@code Problem} class
 */
public abstract class ProblemNode<E> {

	/**
	 * Stores the goal state of each problem, so that each problem node will know
	 * the goal state
	 */
	private final E goal;

	/**
	 * A constructor the create a {@code ProblemNode} which knows the goal state of
	 * the sub class
	 * 
	 * @param goal
	 *            the goal state, in type of the sub class
	 */
	public ProblemNode(E goal) {
		this.goal = goal;
	}

	/**
	 * @return the heuristic that estimates the cost of the cheapest path from the
	 *         initial point to the goal
	 */
	public abstract int getH();

	/**
	 * Check if this is equal to a specific {@code ProblemNode<E>}
	 * 
	 * @param node
	 *            to compare with the current {@code ProblemNode<E>}
	 */
	public abstract boolean equal(Object node);

	/**
	 * Get all the {@code ProblemNode<E>} reachable from the current
	 * {@code ProblemNode<E>}
	 */
	public abstract LinkedList<ProblemNode<E>> successors();

	/**
	 * Returns a string representation of the object. In general, the
	 * {@code toString} method returns a string that "textually represents" this
	 * object.
	 * 
	 * @return a string representation of the object.
	 */
	public abstract String toString();

	/**
	 * Returns if this is the goal node or not.
	 * 
	 * @return {@code true} if this current node is the goal node
	 */
	public abstract boolean isGoal();

	/**
	 * @return the goal state
	 */
	public E getGoal() {
		return goal;
	}

}
