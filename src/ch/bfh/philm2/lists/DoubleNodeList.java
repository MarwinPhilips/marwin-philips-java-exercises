package ch.bfh.philm2.lists;



public class DoubleNodeList<E> {	
	private DoubleNode<E> firstNode;
	private DoubleNode<E> lastNode;
	private int size;
	public DoubleNodeList(){
	}
	/**
	 * Adds the Element at the end of the list.
	 * @param element
	 */
	public void add(E element){
		if(lastNode==null){
			lastNode=new DoubleNode<E>(null,element,null);
			firstNode=lastNode;
			size++;
		}else{
			DoubleNode<E> newNode= new DoubleNode<E>(lastNode,element,null);
			lastNode=newNode;
			size++;
		}
	}
	public void addElementAt(E element, int position){
		// Can't insert an element further away than the last
		if(checkPosition(position)){
			return;
		}
		// get the Node at the position
		if(firstNode==null){
			add(element);
		}else{
			DoubleNode<E> next = getDoubleNodeAt(position);
			DoubleNode<E> newNode = new DoubleNode<E>(next,element,next.nextNode);
			if(newNode.nextNode==null){
				lastNode=newNode;
			}else{
				newNode.nextNode.lastNode=newNode;
			}
			size++;
		}
	}
	
	public void removeElement(E element){
		
	}
	public boolean removeElementAt(int position){
		if(checkPosition(position)){
			return false;
		}
		DoubleNode<E> toRemove = getDoubleNodeAt(position);
		if(toRemove.nextNode==null){
			// Is the only Element in the List
			if(toRemove.lastNode==null){
				firstNode=null;
				lastNode=null;				
			}
			// Is the last Element in the List
			else
			{
				toRemove.lastNode.nextNode=null;
				lastNode = toRemove.lastNode;
			}
			
		}
		// is the First Element in the List.
		else if (toRemove.lastNode==null){
			toRemove.nextNode.lastNode=null;
			firstNode=toRemove.nextNode;
		}
		// Element is somewhere in the list.
		else
		{
			toRemove.lastNode.nextNode=toRemove.nextNode;
			toRemove.nextNode.lastNode=toRemove.lastNode;
		}
		size--;
		return true;
	}
	
	/**
	 * Returns the Element at the position in the DoubleNodeList. 
	 * 
	 * @param position
	 * @return Returns null if position is smaller than 0 or Larger than the size of the DoubleNodeList.
	 */
	public E getElement(int position){
		// Position is larger than the current size.
		if(checkPosition(position)){
			return null;
		}
		return getDoubleNodeAt(position).element;
	}
	/**
	 * The Position is Invalid if the position is larger than the size or smaller than 0.
	 * @param position
	 * @return True: Possible Position, false: Impossible position.
	 */
	private boolean checkPosition(int position) {
		return position>size||position < 0;
	}
	/**
	 * Gets the DoubleNode<E> at the position in the List.
	 * @param position
	 * @return
	 */
	private DoubleNode<E> getDoubleNodeAt(int position){
		DoubleNode<E> next = firstNode;
		for(int i = 0; i < position;i++){
			next = next.nextNode;
		}
		return next;
	}
	
}
