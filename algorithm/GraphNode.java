package a.star.algorithm;

public class GraphNode<E> implements Comparable<GraphNode<E>> {

	/**
	 * The pointer to the parent of the current {@code GraphNode<E>}. Null if this
	 * is the root {@code GraphNode<E>}.
	 */
	private GraphNode<E> parent;

	/**
	 * The element which the {@code GraphNode<E>} is to hold, in a Graph data
	 * structures.
	 */
	private ProblemNode<E> element;

	/**
	 * A constructor to create an instant of this class, for root node
	 * 
	 * @param element
	 *            the element to be stored in this node
	 */
	public GraphNode(ProblemNode<E> element) {
		this(element, null);
	}

	/**
	 * A constructor to create an instant of this class
	 * 
	 * @param parent
	 *            the parent node of this node
	 * @param element
	 *            the element to be stored in this node
	 */
	public GraphNode(ProblemNode<E> element, GraphNode<E> parent) {
		this.setParent(parent);
		this.setElement(element);
	}

	/**
	 * @return the element
	 */
	public ProblemNode<E> getElement() {
		return element;
	}

	/**
	 * @param element
	 *            the element to set
	 */
	private void setElement(ProblemNode<E> element) {
		this.element = element;
	}

	/**
	 * @return the parent
	 */
	public GraphNode<E> getParent() {
		return parent;
	}

	/**
	 * @param parent
	 *            the parent to set
	 */
	public void setParent(GraphNode<E> parent) {
		this.parent = parent;
	}

	/**
	 * @return the depth by counting the the depth from the initial node, which have
	 *         no parent
	 */
	public int getDepth() {

		// if there is no parent, then this is the root node. It's depth is 0
		if (parent == null)
			return 0;

		// else add 1 to the depth of it's parent
		return parent.getDepth() + 1;
	}

	/**
	 * The {@link #compareTo(GraphNode)} method to be implemented when using the
	 * {@code Comparable<GraphNode<E>>} interface, to help the open
	 * {@code PriorityQueue<GraphNode<E>>} to properly arrange the
	 * {@code GraphNode<E>} in order of the {@value F Function = G Function () + H
	 * Function}
	 */
	@Override
	public int compareTo(GraphNode<E> other) {

		// if this node is less costly than the other node, then shift down
		if (this.getElement().getH() + this.getDepth() < other.getElement().getH() + this.getDepth())
			return -1;

		// else if this node is more costly than the other node, then shift up
		else if (this.getElement().getH() + this.getDepth() > other.getElement().getH() + this.getDepth())
			return 1;

		// else right position found
		return 0;
	}

}
