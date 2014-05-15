package ch.bfh.philm2.lists;

class SingleNode<E> {
	E element;
	SingleNode<E> nextElement;
	public SingleNode(E element, SingleNode<E> nextElement){
		this.element=element;
		this.nextElement=nextElement;
	}
}
