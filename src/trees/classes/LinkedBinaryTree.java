package trees.classes;

import java.util.ArrayList;
import java.util.List;

import trees.exceptions.EmptyTreeException;
import trees.exceptions.InvalidPositionException;
import trees.exceptions.UnemptyTreeException;
import trees.interfaces.BasicCollection;
import trees.interfaces.BinaryTree;
import trees.interfaces.Position;

/**
* ...
*
* @author  Marwin Philips, Mete Turna
* @version 1.0
* @since   11.12.2014
*/
public class LinkedBinaryTree<E> implements BasicCollection, BinaryTree<E> {

	/*
	 * Parameter
	 */
	private BinaryTreeNode<E> root;
	private ArrayList<Position<E>> positions;
	private int size = 0;

	/*
	 * Maximum depth of the Tree or give an exception
	 * @see trees.interfaces.Tree#height()
	 */
	@Override
	public int height() throws EmptyTreeException {
		return heightOf(root);
	}
	//i'm a text!
	/*
	 * Returns a list of all element
	 * @see trees.interfaces.Tree#elements()
	 */
	@Override
	public List<E> elements() {
		List<Position<E>> positions = positions();
		List<E> elements = new ArrayList<E>();
		if (positions.size() == 0)
			return elements;
		for (Position<E> p : positions)
			elements.add(p.element());
		return elements;
	}

	/*
	 * Returns a list of all positions
	 * @see trees.interfaces.Tree#positions()
	 */
	@Override
	public List<Position<E>> positions() {
		positions = new ArrayList<Position<E>>();
		if (root == null)
			return positions;
		preOrder(root);
		return positions;
	}

	/*
	 * Returns the root node (if it exists!) or give an exception
	 */
	private BinaryTreeNode<E> getRoot() {
		if (root == null)
			throw new EmptyTreeException();
		return root;
	}

	/*
	 * Returns the position of the root node or give an exception
	 * @see trees.interfaces.Tree#root()
	 */
	@Override
	public Position<E> root() throws EmptyTreeException {
		return getRoot();
	}

	/*
	 * Return the parent of node p or give an exception
	 * @see trees.interfaces.Tree#parent(trees.interfaces.Position)
	 */
	@Override
	public Position<E> parent(Position<E> p) throws InvalidPositionException {
		BinaryTreeNode<E> BinarytreeNode = isPositionInTree(p);
		if (BinarytreeNode.getParent() != null)
			return BinarytreeNode.getParent();
		throw new InvalidPositionException();
	}

	/*
	 * Returns a list of all children of p or give an exception
	 * @see trees.interfaces.Tree#children(trees.interfaces.Position)
	 */
	@Override
	public List<Position<E>> children(Position<E> p)
			throws InvalidPositionException {
		BinaryTreeNode<E> BinaryTreeNode = isPositionInTree(p);
		List<Position<E>> positions = new ArrayList<Position<E>>();
		BinaryTreeNode.getChildren().forEach(
				x -> positions.add((Position<E>) x));
		return positions;
	}

	/*
	 * Returns a list of all descendants of p or give an exception
	 * @see trees.interfaces.Tree#descendants(trees.interfaces.Position)
	 */
	@Override
	public List<Position<E>> descendants(Position<E> p)
			throws InvalidPositionException {
		BinaryTreeNode<E> BinaryTreeNode = isPositionInTree(p);
		ArrayList<Position<E>> descendants = new ArrayList<Position<E>>();
		getDescendants(BinaryTreeNode, descendants);
		return descendants;
	}

	/*
	 * Returns a list of all ancestors of p 
	 * @see trees.interfaces.Tree#ancestors(trees.interfaces.Position)
	 */
	@Override
	public List<Position<E>> ancestors(Position<E> p) {
		BinaryTreeNode<E> BinaryTreeNode = isPositionInTree(p);
		ArrayList<Position<E>> ancestors = new ArrayList<Position<E>>();
		while (BinaryTreeNode.getParent() != null) {
			ancestors.add(BinaryTreeNode.getParent());
			BinaryTreeNode = BinaryTreeNode.getParent();
		}
		return ancestors;
	}

	/*
	 * Indicates whether p is a root or give an exception
	 * @see trees.interfaces.Tree#isRoot(trees.interfaces.Position)
	 */
	@Override
	public boolean isRoot(Position<E> p) throws InvalidPositionException {
		BinaryTreeNode<E> BinaryTreeNode = isPositionInTree(p);
		return BinaryTreeNode == root();
	}

	/*
	 * 	Indicates whether p is an internal node or give an exception
	 * @see trees.interfaces.Tree#isInternal(trees.interfaces.Position)
	 */
	@Override
	public boolean isInternal(Position<E> p) throws InvalidPositionException {
		BinaryTreeNode<E> BinaryTreeNode = isPositionInTree(p);
		return BinaryTreeNode.getChildren().size() > 0;
	}

	/*
	 * Indicates whether p is an external node or give an exception
	 * @see trees.interfaces.Tree#isExternal(trees.interfaces.Position)
	 */
	@Override
	public boolean isExternal(Position<E> p) throws InvalidPositionException {
		BinaryTreeNode<E> BinaryTreeNode = isPositionInTree(p);
		return BinaryTreeNode.getChildren().size() == 0;
	}

	/*
	 * Returns a list of all descendants of p or give an exception
	 * @see trees.interfaces.Tree#depth(trees.interfaces.Position)
	 */
	@Override
	public int depth(Position<E> p) throws InvalidPositionException {
		BinaryTreeNode<E> BinaryTreeNode = isPositionInTree(p);
		return depth(BinaryTreeNode);
	}

	/*
	 * 	Returns the depth of node p
	 */
	private int depth(BinaryTreeNode<E> node) {
		if (isRoot(node))
			return 0;
		return 1 + (depth(node.getParent()));
	}

	/*
	 * 	Adds a root which stores e to an empty tree
	 * @see trees.interfaces.Tree#addRoot(java.lang.Object)
	 */
	@Override
	public Position<E> addRoot(E e) throws UnemptyTreeException {
		if (root != null)
			throw new UnemptyTreeException();
		root = new BinaryTreeNode<E>(e, null);
		size++;
		return root;
	}

	/*
	 * Insert a new child p which stores e, when the left node is empty else insert the 
	 * new child to the right node
	 * @see trees.interfaces.Tree#insertChild(trees.interfaces.Position, java.lang.Object)
	 */
	@Override
	public Position<E> insertChild(Position<E> p, E e)
			throws InvalidPositionException {
		BinaryTreeNode<E> BinaryTreeNode = isPositionInTree(p);
		BinaryTreeNode<E> childToAdd = new BinaryTreeNode<E>(e, BinaryTreeNode);
		if (BinaryTreeNode.getLeftChild() == null) {
			BinaryTreeNode.addLeftChild(childToAdd);
		} else
			BinaryTreeNode.addRightChild(childToAdd);
		size++;
		return childToAdd;
	}

	/*
	 * 	stores e at node p
	 * @see trees.interfaces.Tree#replaceElement(trees.interfaces.Position, java.lang.Object)
	 */
	@Override
	public E replaceElement(Position<E> p, E e) throws InvalidPositionException {
		BinaryTreeNode<E> BinaryTreeNode = isPositionInTree(p);
		BinaryTreeNode.setElement(e);
		return e;
	}

	/*
	 * 	Swaps the element stored at p and q
	 * @see trees.interfaces.Tree#swapElements(trees.interfaces.Position, trees.interfaces.Position)
	 */
	@Override
	public void swapElements(Position<E> p, Position<E> q)
			throws InvalidPositionException {
		BinaryTreeNode<E> BinaryTreeNodeP = isPositionInTree(p);
		BinaryTreeNode<E> BinaryTreeNodeQ = isPositionInTree(q);
		E toSwap = BinaryTreeNodeP.element();
		BinaryTreeNodeP.setElement(BinaryTreeNodeQ.element());
		BinaryTreeNodeQ.setElement(toSwap);
	}

	/*
	 * Returns the left child of p (if it exists)
	 * @see trees.interfaces.BinaryTree#leftChild(trees.interfaces.Position)
	 */
	@Override
	public Position<E> leftChild(Position<E> p) throws InvalidPositionException {
		BinaryTreeNode<E> BinaryTreeNode = isPositionInTree(p);
		return BinaryTreeNode.getLeftChild();
	}

	/*
	 * Returns the right child of p (if it exists)
	 * @see trees.interfaces.BinaryTree#rightChild(trees.interfaces.Position)
	 */
	@Override
	public Position<E> rightChild(Position<E> p)
			throws InvalidPositionException {
		BinaryTreeNode<E> BinaryTreeNode = isPositionInTree(p);
		return BinaryTreeNode.getRightChild();
	}

	/*
	 * Returns the sibling of p (if it exists)
	 * @see trees.interfaces.BinaryTree#sibling(trees.interfaces.Position)
	 */
	@Override
	public Position<E> sibling(Position<E> p) throws InvalidPositionException {
		return null;
	}

	/*
	 * Checks whether p has a left child
	 * @see trees.interfaces.BinaryTree#hasLeft(trees.interfaces.Position)
	 */
	@Override
	public boolean hasLeft(Position<E> p) throws InvalidPositionException {
		BinaryTreeNode<E> BinaryTreeNode = isPositionInTree(p);
		return BinaryTreeNode.getChildren().size() == 0;
	}

	/*
	 * Checks whether p has a right child
	 * @see trees.interfaces.BinaryTree#hasRight(trees.interfaces.Position)
	 */
	@Override
	public boolean hasRight(Position<E> p) throws InvalidPositionException {
		BinaryTreeNode<E> BinaryTreeNode = isPositionInTree(p);
		return BinaryTreeNode.getChildren().size() == 0;
	}

	/*
	 * Adds a left child to p (unless it exists)
	 * @see trees.interfaces.BinaryTree#insertLeft(trees.interfaces.Position, java.lang.Object)
	 */
	@Override
	public Position<E> insertLeft(Position<E> p, E e)
			throws InvalidPositionException {
		BinaryTreeNode<E> BinaryTreeNode = isPositionInTree(p);
		BinaryTreeNode<E> childToAdd = new BinaryTreeNode<E>(e, BinaryTreeNode);
		BinaryTreeNode.addLeftChild(childToAdd);
		size++;
		return childToAdd;
	}

	/*
	 * Adds a right child to p (unless it exists)
	 * @see trees.interfaces.BinaryTree#insertRight(trees.interfaces.Position, java.lang.Object)
	 */
	@Override
	public Position<E> insertRight(Position<E> p, E e)
			throws InvalidPositionException {
		BinaryTreeNode<E> BinaryTreeNode = isPositionInTree(p);
		BinaryTreeNode<E> childToAdd = new BinaryTreeNode<E>(e, BinaryTreeNode);
		BinaryTreeNode.addRightChild(childToAdd);
		size++;
		return childToAdd;
	}

	/*
	 * Adds a left and a right child to an external node p
	 * @see trees.interfaces.BinaryTree#insertChildren(trees.interfaces.Position, java.lang.Object, java.lang.Object)
	 */
	@Override
	public void insertChildren(Position<E> p, E e1, E e2)
			throws InvalidPositionException {
		BinaryTreeNode<E> BinaryTreeNode = isPositionInTree(p);
		BinaryTreeNode<E> childrenToAddLeft = new BinaryTreeNode<E>(e1,
				BinaryTreeNode);
		BinaryTreeNode<E> childrenToAddRight = new BinaryTreeNode<E>(e2,
				BinaryTreeNode);
		BinaryTreeNode.addLeftChild(childrenToAddLeft);
		BinaryTreeNode.addRightChild(childrenToAddRight);
		size += 2;
	}

	/*
	 * 	Returns the number of nodes
	 * @see trees.interfaces.BasicCollection#size()
	 */
	@Override
	public int size() {
		return size;
	}

	/*
	 * 	Indicates whether the tree is empty
	 * @see trees.interfaces.BasicCollection#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/*
	 * Returns the Values in the overwritten method toString()
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (root == null)
			return "";
		if (root.getChildren().size() == 0)
			return root.element().toString();
		return makeChildrenString(root);
	}
	
	private String makeChildrenString(BinaryTreeNode<E> node) {
		String childrenString = node.element().toString();
		if (node.getChildren().size() == 1) {
			childrenString += " ("
					+ node.getChildren().get(0).element().toString() + ")";
		} else if (node.getChildren().size() > 1) {
			childrenString += " (";
			int numberOfChildren = node.getChildren().size();
			int currentChild = 1;
			for (BinaryTreeNode<E> child : node.getChildren()) {
				childrenString += makeChildrenString(child);

				if (numberOfChildren > currentChild) {
					childrenString += " ";
				} else {
					childrenString += ")";
				}
				currentChild++;
			}

		}
		return childrenString;
	}

	/*
	 * return the height of the subtree (p).
	 */
	private int heightOf(BinaryTreeNode<E> node) {
		if (isExternal(node))
			return 0;
		else {
			int h = 0;
			for (BinaryTreeNode<E> q : node.getChildren()) {
				h = max(h, heightOf(q));
			}
			h++;
			return h;
		}
	}

	/*
	 * help method
	 */
	private int max(int first, int second) {
		if (first >= second)
			return first;
		return second;
	}

	/*
	 * A node is visited before its descendants
	 */
	private void preOrder(BinaryTreeNode<E> p) {
		visit(p);
		for (BinaryTreeNode<E> q : p.getChildren())
			preOrder(q);
	}
	
	/*
	 * Visiting a node p
	 */
	private void visit(BinaryTreeNode<E> p) {
		positions.add(p);
	}

	/*
	 * Return the Position of the element in the BinaryTreeNode or give an exception
	 */
	private BinaryTreeNode<E> isPositionInTree(Position<E> element) {
		if (!(element instanceof BinaryTreeNode)) {
			throw new InvalidPositionException();
		}
		BinaryTreeNode<E> BinaryTreeNode = (BinaryTreeNode<E>) element;
		isBinaryTreeNodeInTree(BinaryTreeNode);
		return BinaryTreeNode;
	}

	/*
	 * 
	 */
	private void isBinaryTreeNodeInTree(BinaryTreeNode<E> BinaryTreeNode) {
		if (BinaryTreeNode == getRoot())
			return;
		while (BinaryTreeNode.getParent() != null) {
			BinaryTreeNode = BinaryTreeNode.getParent();
			if (BinaryTreeNode == root)
				return;
		}
		throw new InvalidPositionException();
	}

	/*
	 * 
	 */
	private void getDescendants(BinaryTreeNode<E> node,
			ArrayList<Position<E>> descendants) {
		for (BinaryTreeNode<E> child : node.getChildren()) {
			descendants.add(child);
			getDescendants(child, descendants);
		}
	}

}
