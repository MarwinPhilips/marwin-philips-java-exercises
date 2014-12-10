package trees.classes;

import java.util.ArrayList;
import java.util.List;

import trees.interfaces.Position;

public class BinaryTreeNode<E> implements Position<E> {
	private E element;
	private BinaryTreeNode<E> parent;
	private BinaryTreeNode<E> leftChild;
	private BinaryTreeNode<E> rightChild;

	public BinaryTreeNode(E element, BinaryTreeNode<E> parent) {
		this.element = element;
		this.parent = parent;
	}

	@Override
	public E element() {
		return element;
	}

	public void setElement(E element) {
		this.element = element;
	}

	public BinaryTreeNode<E> getParent() {
		return parent;
	}

	public void setParent(BinaryTreeNode<E> parent) {
		this.parent = parent;
	}

	public void addLeftChild(BinaryTreeNode<E> leftChild) {
		this.leftChild = leftChild;
	}

	public void addRightChild(BinaryTreeNode<E> rightChild) {
		this.rightChild = rightChild;
	}

	public BinaryTreeNode<E> getLeftChild() {
		return leftChild;
	}

	public BinaryTreeNode<E> getRightChild() {
		return rightChild;
	}

	public List<BinaryTreeNode<E>> getChildren() {
		ArrayList<BinaryTreeNode<E>> ret = new ArrayList<BinaryTreeNode<E>>();
		if (getLeftChild() != null)
			ret.add(getLeftChild());
		if (getRightChild() != null)
			ret.add(getRightChild());
		return ret;
	}
}