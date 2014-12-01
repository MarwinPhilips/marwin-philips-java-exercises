package trees.classes;

import java.util.ArrayList;
import java.util.List;

import trees.exceptions.EmptyTreeException;
import trees.exceptions.InvalidPositionException;
import trees.exceptions.UnemptyTreeException;
import trees.interfaces.BasicCollection;
import trees.interfaces.Position;
import trees.interfaces.Tree;

public class LinkedTree<E> implements BasicCollection, Tree<E> {
	private TreeNode<E> root;
	private ArrayList<Position<E>> positions;
	private int size = 0;

	private TreeNode<E> getRoot() {
		if (root == null)
			throw new EmptyTreeException();
		return root;
	}

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

	@Override
	public Position<E> root() throws EmptyTreeException {
		return getRoot();
	}

	@Override
	public Position<E> parent(Position<E> p) throws InvalidPositionException {
		TreeNode<E> treeNode = isPositionInTree(p);
		if (treeNode.getParent() != null)
			return treeNode.getParent();
		throw new InvalidPositionException();
	}

	@Override
	public List<Position<E>> children(Position<E> p)
			throws InvalidPositionException {
		TreeNode<E> treeNode = isPositionInTree(p);
		List<Position<E>> positions = new ArrayList<Position<E>>();
		treeNode.getChildren().forEach(x -> positions.add((Position<E>) x));
		return positions;
	}

	@Override
	public List<Position<E>> descendants(Position<E> p)
			throws InvalidPositionException {
		TreeNode<E> treeNode = isPositionInTree(p);
		ArrayList<Position<E>> descendants = new ArrayList<Position<E>>();
		getDescendants(treeNode, descendants);
		return descendants;
	}

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

	@Override
	public boolean isRoot(Position<E> p) throws InvalidPositionException {
		TreeNode<E> treeNode = isPositionInTree(p);
		return treeNode == root();
	}

	@Override
	public boolean isInternal(Position<E> p) throws InvalidPositionException {
		TreeNode<E> treeNode = isPositionInTree(p);
		return treeNode.getChildren().size() > 0;
	}

	@Override
	public boolean isExternal(Position<E> p) throws InvalidPositionException {
		TreeNode<E> treeNode = isPositionInTree(p);
		return treeNode.getChildren().size() == 0;
	}

	@Override
	public int depth(Position<E> p) throws InvalidPositionException {
		TreeNode<E> treeNode = isPositionInTree(p);
		return depth(treeNode);
	}

	private int depth(TreeNode<E> node) {
		if (isRoot(node))
			return 0;
		return 1 + (depth(node.getParent()));
	}

	@Override
	public Position<E> addRoot(E e) throws UnemptyTreeException {
		if (root != null)
			throw new UnemptyTreeException();
		root = new TreeNode<E>(e, null);
		size++;
		return root;
	}

	@Override
	public Position<E> insertChild(Position<E> p, E e)
			throws InvalidPositionException {
		TreeNode<E> treeNode = isPositionInTree(p);
		TreeNode<E> childToAdd = new TreeNode<E>(e, treeNode);
		treeNode.AddChild(childToAdd);
		size++;
		return childToAdd;
	}

	@Override
	public E replaceElement(Position<E> p, E e) throws InvalidPositionException {
		TreeNode<E> treeNode = isPositionInTree(p);
		treeNode.setElement(e);
		return e;
	}

	@Override
	public void swapElements(Position<E> p, Position<E> q)
			throws InvalidPositionException {
		TreeNode<E> treeNodeP = isPositionInTree(p);
		TreeNode<E> treeNodeQ = isPositionInTree(q);
		E toSwap = treeNodeP.element();
		treeNodeP.setElement(treeNodeQ.element());
		treeNodeQ.setElement(toSwap);
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

	private String makeChildrenString(TreeNode<E> node) {
		String childrenString = node.element().toString();
		if (node.getChildren().size() ==1 ) {
			childrenString += " ("+node.getChildren().get(0).element().toString()+")";
		}else if (node.getChildren().size()>1){
			childrenString += " (";
			int numberOfChildren = node.getChildren().size();
			int currentChild = 1;
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

	private int max(int first, int second) {
		if (first >= second)
			return first;
		return second;
	}

	private void preOrder(TreeNode<E> p) {
		visit(p);
		for (TreeNode<E> q : p.getChildren())
			preOrder(q);
	}

	private void visit(TreeNode<E> p) {
		positions.add(p);
	}

	private TreeNode<E> isPositionInTree(Position<E> element) {
		if (!(element instanceof TreeNode)) {
			throw new InvalidPositionException();
		}
		TreeNode<E> treeNode = (TreeNode<E>) element;
		isTreeNodeInTree(treeNode);
		return treeNode;
	}

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

	private void getDescendants(TreeNode<E> node,
			ArrayList<Position<E>> descendants) {
		for (TreeNode<E> child : node.getChildren()) {
			descendants.add(child);
			getDescendants(child, descendants);
		}
	}

}
