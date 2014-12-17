package trees.classes;

import java.util.ArrayList;
import java.util.List;

import trees.interfaces.Position;

/**
 * Represents a TreeNode which can have n children, the right and the left child.
 * Every TreeNode has a parent or is the root of the tree.
 * 
 * @author Marwin Philips
 * @author Mete Turna
 * @version 1.0
 * @since 11.12.2014
 *
 * @param <E>
 */
public class TreeNode<E> implements Position<E> {
	private E element;
	private TreeNode<E> parent;
	private ArrayList<TreeNode<E>> children = new ArrayList<TreeNode<E>>();
	
	public TreeNode(E element, TreeNode<E> parent){
		this.element=element;
		this.parent=parent;
	}
	
	@Override
	public E element() {
		return element;
	}
	public void setElement(E element){
		this.element=element;
	}
	public TreeNode<E> getParent() {
		return parent;
	}
	public void AddChild(TreeNode<E> child){
		children.add(child);
	}
	public void setParent(TreeNode<E> parent) {
		this.parent = parent;
	}
	public List<TreeNode<E>> getChildren() {
		return children;
	}
}
