package trees.classes;

import java.util.List;

import trees.exceptions.EmptyTreeException;
import trees.exceptions.InvalidPositionException;
import trees.exceptions.UnemptyTreeException;
import trees.interfaces.BasicCollection;
import trees.interfaces.BinaryTree;
import trees.interfaces.Position;


public class LinkedBinaryTree<E> implements BasicCollection, BinaryTree {

	@Override
	public int height() throws EmptyTreeException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List elements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List positions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position root() throws EmptyTreeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position parent(Position p) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List children(Position p) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List descendants(Position p) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List ancestors(Position p) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRoot(Position p) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isInternal(Position p) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isExternal(Position p) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int depth(Position p) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Position addRoot(Object e) throws UnemptyTreeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position insertChild(Position p, Object e)
			throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object replaceElement(Position p, Object e)
			throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void swapElements(Position p, Position q)
			throws InvalidPositionException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Position leftChild(Position p) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position rightChild(Position p) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position sibling(Position p) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasLeft(Position p) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasRight(Position p) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Position insertLeft(Position p, Object e)
			throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position insertRight(Position p, Object e)
			throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertChildren(Position p, Object e1, Object e2)
			throws InvalidPositionException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

}
