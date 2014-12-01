package ch.bfh.philm2.lists;

class DoubleNode<T>{
	DoubleNode<T> lastNode;
	DoubleNode<T> nextNode;
	T element;
	public DoubleNode(DoubleNode<T> lastNode,T element, DoubleNode<T> nextNode){
		this.lastNode=lastNode;
		this.nextNode=nextNode;
		this.element=element;
	}
}
