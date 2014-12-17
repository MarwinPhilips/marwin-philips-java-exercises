package trees.classes;

import java.util.ArrayList;
import java.util.List;
import trees.exceptions.EmptyTreeException;
import trees.exceptions.InvalidPositionException;
import trees.exceptions.UnemptyTreeException;
import trees.interfaces.BasicCollection;
import trees.interfaces.Position;
import trees.interfaces.Tree;

/**
* Represents a Linked tree which has exactly one root TreeNode which can have 0..n children.
* Each of this children can have 0..n children and so on.
*
* @author  Marwin Philips
* @author  Mete Turna
* @version 1.0
* @since   11.12.2014
*/

public class LinkedTree<E> implements BasicCollection, Tree<E> {
	
	/*
	 * Parameter
	 */
	private TreeNode<E> root;
	private ArrayList<Position<E>> positions;
	private int size = 0;

	/*
	 * Returns the root node (if it exists!) or throw an EmptyTreeException
	 */
	private TreeNode<E> getRoot() throws EmptyTreeException {
		if (root == null)
			throw new EmptyTreeException();
		return root;
	}

	/*
	 * Return the height of the tree. Throws exception when tree is Empty.
	 * @see trees.interfaces.Tree#height()
	 */
	@Override
	public int height() throws EmptyTreeException {
		return heightOf(root);
	}

	/*
	 *  Returns a list of all elements
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
	 * Returns the position of the root node or throws an EmptyTreeException when the tree is empty.
	 * @see trees.interfaces.Tree#root()
	 */
	@Override
	public Position<E> root() throws EmptyTreeException {
		return getRoot();
	}

	/*
	 * Return the parent of node p or throws an InvalidPositionException if p is not in the tree.
	 * @see trees.interfaces.Tree#parent(trees.interfaces.Position)
	 */
	@Override
	public Position<E> parent(Position<E> p) throws InvalidPositionException {
		TreeNode<E> treeNode = isPositionInTree(p);
		if (treeNode.getParent() != null)
			return treeNode.getParent();
		throw new InvalidPositionException();
	}

	/*
	 * Returns a list of all children of p or throws an InvalidPositionException
	 * if p is not in the tree.
	 * @see trees.interfaces.Tree#children(trees.interfaces.Position)
	 */
	@Override
	public List<Position<E>> children(Position<E> p)
			throws InvalidPositionException {
		TreeNode<E> treeNode = isPositionInTree(p);
		List<Position<E>> positions = new ArrayList<Position<E>>();
		treeNode.getChildren().forEach(x -> positions.add((Position<E>) x));
		return positions;
	}

	/*
	 * Returns a list of all descendants of p or throws an InvalidPositionException
	 * if p is not in the tree.
	 * @see trees.interfaces.Tree#descendants(trees.interfaces.Position)
	 */
	@Override
	public List<Position<E>> descendants(Position<E> p)
			throws InvalidPositionException {
		TreeNode<E> treeNode = isPositionInTree(p);
		ArrayList<Position<E>> descendants = new ArrayList<Position<E>>();
		getDescendants(treeNode, descendants);
		return descendants;
	}

	/*
	 * Returns a list of all ancestors of p or throws an InvalidPositionException
	 * if p is not in the tree.
	 * @see trees.interfaces.Tree#ancestors(trees.interfaces.Position)
	 */
	@Override
	public List<Position<E>> ancestors(Position<E> p) {
		TreeNode<E> treeNode = isPositionInTree(p);
		ArrayList<Position<E>> ancestors = new ArrayList<Position<E>>();
		while (treeNode.getParent() != null) {
			ancestors.add(treeNode.getParent());
			treeNode = treeNode.getParent();
		}
		return ancestors;
	}

	/*
	 * Indicates whether p is a root or or throws an InvalidPositionException
	 * if p is not in the tree.
	 * @see trees.interfaces.Tree#isRoot(trees.interfaces.Position)
	 */
	@Override
	public boolean isRoot(Position<E> p) throws InvalidPositionException {
		TreeNode<E> treeNode = isPositionInTree(p);
		return treeNode == root();
	}

	/*
	 * Indicates whether p is an internal node or throws an InvalidPositionException
	 * if p is not in the tree.
	 * @see trees.interfaces.Tree#isInternal(trees.interfaces.Position)
	 */
	@Override
	public boolean isInternal(Position<E> p) throws InvalidPositionException {
		TreeNode<E> treeNode = isPositionInTree(p);
		return treeNode.getChildren().size() > 0;
	}

	/*
	 * Indicates whether p is an external node or throws an InvalidPositionException
	 * if p is not in the tree.
	 * @see trees.interfaces.Tree#isExternal(trees.interfaces.Position)
	 */
	@Override
	public boolean isExternal(Position<E> p) throws InvalidPositionException {
		TreeNode<E> treeNode = isPositionInTree(p);
		return treeNode.getChildren().size() == 0;
	}

	/*
	 * Returns a list of all descendants of p or throws an InvalidPositionException
	 * if p is not in the tree.
	 * @see trees.interfaces.Tree#depth(trees.interfaces.Position)
	 */
	@Override
	public int depth(Position<E> p) throws InvalidPositionException {
		TreeNode<E> treeNode = isPositionInTree(p);
		return depth(treeNode);
	}

	/*
	 * 	Returns over recursion the depth of node p
	 */
	private int depth(TreeNode<E> node) {
		if (isRoot(node))
			return 0;
		return 1 + (depth(node.getParent()));
	}

	/*
	 * Adds a root which stores e to an empty tree. 
	 * Throws UnemptyTreeException If there is already a root
	 * @see trees.interfaces.Tree#addRoot(java.lang.Object)
	 */
	@Override
	public Position<E> addRoot(E e) throws UnemptyTreeException {
		if (root != null)
			throw new UnemptyTreeException();
		root = new TreeNode<E>(e, null);
		size++;
		return root;
	}

	/*
	 * Insert a new child p which stores e or throws an InvalidPositionException if 
	 * p is not in the tree.
	 * @see trees.interfaces.Tree#insertChild(trees.interfaces.Position, java.lang.Object)
	 */
	@Override
	public Position<E> insertChild(Position<E> p, E e)
			throws InvalidPositionException {
		TreeNode<E> treeNode = isPositionInTree(p);
		TreeNode<E> childToAdd = new TreeNode<E>(e, treeNode);
		treeNode.AddChild(childToAdd);
		size++;
		return childToAdd;
	}

	/*
	 * stores e at node p or throws an InvalidPositionException if 
	 * p is not in the tree.
	 * @see trees.interfaces.Tree#replaceElement(trees.interfaces.Position, java.lang.Object)
	 */
	@Override
	public E replaceElement(Position<E> p, E e) throws InvalidPositionException {
		TreeNode<E> treeNode = isPositionInTree(p);
		treeNode.setElement(e);
		return e;
	}

	/*
	 * Swaps the element stored at p and q or throws an InvalidPositionException if 
	 * p or q is not in the tree.
	 * @see trees.interfaces.Tree#swapElements(trees.interfaces.Position, trees.interfaces.Position)
	 */
	@Override
	public void swapElements(Position<E> p, Position<E> q)
			throws InvalidPositionException {
		TreeNode<E> treeNodeP = isPositionInTree(p);
		TreeNode<E> treeNodeQ = isPositionInTree(q);
		E toSwap = treeNodeP.element();
		treeNodeP.setElement(treeNodeQ.element());
		treeNodeQ.setElement(toSwap);
	}

	/*
	 * Returns the number of nodes
	 * @see trees.interfaces.BasicCollection#size()
	 */
	@Override
	public int size() {
		return size;
	}

	/*
	 * Indicates whether the tree is empty
	 * @see trees.interfaces.BasicCollection#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Returns the tree as a String, separated with spaces an ().
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
	/**
	 * Builds recursively the String of the whole tree, separated with spaces and ().
	 * 
	 */
	private String makeChildrenString(TreeNode<E> node) {
		String childrenString = node.element().toString();
		// If there is only one child, add " (child.toString())"
		if (node.getChildren().size() ==1 ) {
			childrenString += " ("+node.getChildren().get(0).element().toString()+")";
		}
		// if there is more than one child add " ( child1 child2 child3)"
		else if (node.getChildren().size()>1){
			childrenString += " (";
			int numberOfChildren = node.getChildren().size();
			int currentChild = 1;
			// if the current node's children have more childs, we have to add them to the
			// string.
			for (TreeNode<E> child : node.getChildren()) {
				
				childrenString +=makeChildrenString(child);
				
				if(numberOfChildren>currentChild){
					childrenString+=" ";
				}else{
					childrenString+=")";
				}
				currentChild++;
			}
			
		}
		return childrenString;
	}

	/*
	 * return the height of the subtree (p).
	 */
	private int heightOf(TreeNode<E> node) {
		if (isExternal(node))
			return 0;
		else {
			int h = 0;
			for (TreeNode<E> q : node.getChildren()) {
				h = max(h, heightOf(q));
			}
			h++;
			return h;
		}
	}

	/*
	 * returns the larger one of first and second.
	 */
	private int max(int first, int second) {
		if (first >= second)
			return first;
		return second;
	}

	/*
	 * A node is visited before its descendants
	 */
	private void preOrder(TreeNode<E> p) {
		visit(p);
		for (TreeNode<E> q : p.getChildren())
			preOrder(q);
	}

	/*
	 * Visiting a node p. Used to build positions with all elements in tree.
	 */
	private void visit(TreeNode<E> p) {
		positions.add(p);
	}

	/*
	 * Return the Position of the element in the TreeNode or give an exception
	 */
	private TreeNode<E> isPositionInTree(Position<E> element) {
		if (!(element instanceof TreeNode)) {
			throw new InvalidPositionException();
		}
		TreeNode<E> treeNode = (TreeNode<E>) element;
		isTreeNodeInTree(treeNode);
		return treeNode;
	}

	/*
	 * If the treeNode or one of its parent is the same as root it return,
	 * Otherwise throws InvalidPositionException
	 */
	private void isTreeNodeInTree(TreeNode<E> treeNode) {
		if (treeNode == getRoot())
			return;
		while (treeNode.getParent() != null) {
			treeNode = treeNode.getParent();
			if (treeNode == root)
				return;
		}
		throw new InvalidPositionException();
	}

	/*
	 * Get the descendants of the child.
	 */
	private void getDescendants(TreeNode<E> node,
			ArrayList<Position<E>> descendants) {
		for (TreeNode<E> child : node.getChildren()) {
			descendants.add(child);
			getDescendants(child, descendants);
		}
	}

}
