package a.star.algorithm;

public class GaolNode<E> {

	private final E goal;

	public GaolNode(E goal) {
		this.goal = goal;
	}

	/**
	 * @return the goal
	 */
	public E getGoal() {
		return goal;
	}

}
