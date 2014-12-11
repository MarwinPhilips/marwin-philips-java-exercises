package trees.classes;

import java.util.ArrayList;
import java.util.List;

import trees.exceptions.EmptyTreeException;
import trees.exceptions.InvalidPositionException;
import trees.exceptions.UnemptyTreeException;
import trees.interfaces.BasicCollection;
import trees.interfaces.BinaryTree;
import trees.interfaces.Position;

public class LinkedBinaryTree<E> implements BasicCollection, BinaryTree<E> {

	private BinaryTreeNode<E> root;
	private ArrayList<Position<E>> positions;
	private int size = 0;

	@Override
	public int height() throws EmptyTreeException {
		return heightOf(root);
	}

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

	@Override
	public List<Position<E>> positions() {
		positions = new ArrayList<Position<E>>();
		if (root == null)
			return positions;
		preOrder(root);
		return positions;
	}

	private BinaryTreeNode<E> getRoot() {
		if (root == null)
			throw new EmptyTreeException();
		return root;
	}

	@Override
	public Position<E> root() throws EmptyTreeException {
		return getRoot();
	}

	@Override
	public Position<E> parent(Position<E> p) throws InvalidPositionException {
		BinaryTreeNode<E> BinarytreeNode = isPositionInTree(p);
		if (BinarytreeNode.getParent() != null)
			return BinarytreeNode.getParent();
		throw new InvalidPositionException();
	}

	@Override
	public List<Position<E>> children(Position<E> p)
			throws InvalidPositionException {
		BinaryTreeNode<E> BinaryTreeNode = isPositionInTree(p);
		List<Position<E>> positions = new ArrayList<Position<E>>();
		BinaryTreeNode.getChildren().forEach(
				x -> positions.add((Position<E>) x));
		return positions;
	}

	@Override
	public List<Position<E>> descendants(Position<E> p)
			throws InvalidPositionException {
		BinaryTreeNode<E> BinaryTreeNode = isPositionInTree(p);
		ArrayList<Position<E>> descendants = new ArrayList<Position<E>>();
		getDescendants(BinaryTreeNode, descendants);
		return descendants;
	}

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

	@Override
	public boolean isRoot(Position<E> p) throws InvalidPositionException {
		BinaryTreeNode<E> BinaryTreeNode = isPositionInTree(p);
		return BinaryTreeNode == root();
	}

	@Override
	public boolean isInternal(Position<E> p) throws InvalidPositionException {
		BinaryTreeNode<E> BinaryTreeNode = isPositionInTree(p);
		return BinaryTreeNode.getChildren().size() > 0;
	}

	@Override
	public boolean isExternal(Position<E> p) throws InvalidPositionException {
		BinaryTreeNode<E> BinaryTreeNode = isPositionInTree(p);
		return BinaryTreeNode.getChildren().size() == 0;
	}

	@Override
	public int depth(Position<E> p) throws InvalidPositionException {
		BinaryTreeNode<E> BinaryTreeNode = isPositionInTree(p);
		return depth(BinaryTreeNode);
	}

	private int depth(BinaryTreeNode<E> node) {
		if (isRoot(node))
			return 0;
		return 1 + (depth(node.getParent()));
	}

	@Override
	public Position<E> addRoot(E e) throws UnemptyTreeException {
		if (root != null)
			throw new UnemptyTreeException();
		root = new BinaryTreeNode<E>(e, null);
		size++;
		return root;
	}

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

	@Override
	public E replaceElement(Position<E> p, E e) throws InvalidPositionException {
		BinaryTreeNode<E> BinaryTreeNode = isPositionInTree(p);
		BinaryTreeNode.setElement(e);
		return e;
	}

	@Override
	public void swapElements(Position<E> p, Position<E> q)
			throws InvalidPositionException {
		BinaryTreeNode<E> BinaryTreeNodeP = isPositionInTree(p);
		BinaryTreeNode<E> BinaryTreeNodeQ = isPositionInTree(q);
		E toSwap = BinaryTreeNodeP.element();
		BinaryTreeNodeP.setElement(BinaryTreeNodeQ.element());
		BinaryTreeNodeQ.setElement(toSwap);
	}

	@Override
	public Position<E> leftChild(Position<E> p) throws InvalidPositionException {
		BinaryTreeNode<E> BinaryTreeNode = isPositionInTree(p);
		return BinaryTreeNode.getLeftChild();
	}

	@Override
	public Position<E> rightChild(Position<E> p)
			throws InvalidPositionException {
		BinaryTreeNode<E> BinaryTreeNode = isPositionInTree(p);
		return BinaryTreeNode.getRightChild();
	}

	@Override
	public Position<E> sibling(Position<E> p) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasLeft(Position<E> p) throws InvalidPositionException {
		BinaryTreeNode<E> BinaryTreeNode = isPositionInTree(p);
		return BinaryTreeNode.getChildren().size() == 0;
	}

	@Override
	public boolean hasRight(Position<E> p) throws InvalidPositionException {
		BinaryTreeNode<E> BinaryTreeNode = isPositionInTree(p);
		return BinaryTreeNode.getChildren().size() == 0;
	}

	@Override
	public Position<E> insertLeft(Position<E> p, E e)
			throws InvalidPositionException {
		BinaryTreeNode<E> BinaryTreeNode = isPositionInTree(p);
		BinaryTreeNode<E> childToAdd = new BinaryTreeNode<E>(e, BinaryTreeNode);
		BinaryTreeNode.addLeftChild(childToAdd);
		size++;
		return childToAdd;
	}

	@Override
	public Position<E> insertRight(Position<E> p, E e)
			throws InvalidPositionException {
		BinaryTreeNode<E> BinaryTreeNode = isPositionInTree(p);
		BinaryTreeNode<E> childToAdd = new BinaryTreeNode<E>(e, BinaryTreeNode);
		BinaryTreeNode.addRightChild(childToAdd);
		size++;
		return childToAdd;
	}

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

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

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

	private int max(int first, int second) {
		if (first >= second)
			return first;
		return second;
	}

	private void preOrder(BinaryTreeNode<E> p) {
		visit(p);
		for (BinaryTreeNode<E> q : p.getChildren())
			preOrder(q);
	}

	private void visit(BinaryTreeNode<E> p) {
		positions.add(p);
	}

	private BinaryTreeNode<E> isPositionInTree(Position<E> element) {
		if (!(element instanceof BinaryTreeNode)) {
			throw new InvalidPositionException();
		}
		BinaryTreeNode<E> BinaryTreeNode = (BinaryTreeNode<E>) element;
		isBinaryTreeNodeInTree(BinaryTreeNode);
		return BinaryTreeNode;
	}

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

	private void getDescendants(BinaryTreeNode<E> node,
			ArrayList<Position<E>> descendants) {
		for (BinaryTreeNode<E> child : node.getChildren()) {
			descendants.add(child);
			getDescendants(child, descendants);
		}
	}

}
