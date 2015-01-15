package trees.interfaces;

import trees.exceptions.InvalidPositionException;

/**
 * A Binary Tree is Built with TreePositions, of which each can have 2 Children,
 * the left Child and the right Child.
 * You can only modify Positions which are in the Tree.
 * @param <E> the generic class type which can be stored in the Tree.
 */

public interface BinaryTree<E> extends Tree<E> {

	public Position<E> leftChild(Position<E> p) throws InvalidPositionException;

	public Position<E> rightChild(Position<E> p) throws InvalidPositionException;

	public Position<E> sibling(Position<E> p) throws InvalidPositionException;

	public boolean hasLeft(Position<E> p) throws InvalidPositionException;

	public boolean hasRight(Position<E> p) throws InvalidPositionException;

	public Position<E> insertLeft(Position<E> p, E e) throws InvalidPositionException;
	
	public Position<E> insertRight(Position<E> p, E e) throws InvalidPositionException;

	public void insertChildren(Position<E> p, E e1, E e2) throws InvalidPositionException;

}
