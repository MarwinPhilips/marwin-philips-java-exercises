package ch.bfh.philm2.lists;

public class Stack<E> {
	private SingleNode<E> lastElement;
	private int size;
	public Stack(){
		
	}
	/**
	 * Puts the Element on the top of the stack
	 * @param element
	 */
	public void push(E element){
		if(lastElement == null){
			lastElement = new SingleNode<E>(element,null);
		}else{
			SingleNode<E> newElement = new SingleNode<E>(element,lastElement);
			lastElement=newElement;
		}
		size++;
	}
	/**
	 * Gets the top element of the stack. Returns null if the stack is empty.
	 * .
	 * @return
	 */
	public E get(){
		if (lastElement != null) {
			SingleNode<E> returner = lastElement;
			if (returner.nextElement != null) {
				lastElement = returner.nextElement;
			}
			size--;
			return returner.element;
		}
		return null;
	}
	public int getSize(){
		return size;
	}
}
