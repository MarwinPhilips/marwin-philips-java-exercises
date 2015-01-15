package trees.interfaces;
/**
 * 
 * A collection is any type of an abstract datatype which stores more than one element.
 *
 */
public interface BasicCollection {
	/**
	 * The size of the collection. Every Element in the Collection increases the size by one
	 * @return
	 */
	public int size(); 
	
	/**
	 * if there are no elements in the Collection it is empty, otherwhise not. 
	 */
	public boolean isEmpty(); 

}
