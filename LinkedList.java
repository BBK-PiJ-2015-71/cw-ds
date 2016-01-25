/**
 * A list is a collection of objects that are sorted and can be
 * accessed by index. The first element in the list is at index 0.
 *
 * A list can store objects of any kind, and they can be of different
 * types: Integers, Doubles, String, or even other lists. However,
 * this list cannot store null objects.
 * 
 * There is no limit to the number of elements in the list (provided
 * that there is free memory in the Java Virtual Machine).
 * 
 * Not all operations on a list will always be successful. For
 * example, a programmer may try to remove an element from an empty
 * list, or from a position where there is nothing. Since we hace not
 * covered exceptions yet, we need another mechanism to report
 * errors. In order to do that, methods of this list will return a
 * {@see ReturnObject} that will contain either an object or an error
 * value of the right kind (as defined in {@see ErrorMessage}). 
 * 
 * @author PiJ
 */
public class LinkedList implements List
{	
	private int index;
	private int size_list;
	private Element startElement;
		
	public LinkedList()
	{
		index=0;
		size_list=0;	
		startElement= new Element("Start Object:Text string 1");
	}

	/**
	 * Returns true if the list is empty, false otherwise. 
	 * 
	 * @return true if the list is empty, false otherwise. 
	 */
	public boolean isEmpty()
	{	
		if(size_list==0)
		{	return true;
		}
		else
		{	return false;
		}
	}

	/**
	 * Returns the number of items currently in the list.
	 * 
	 * @return the number of items currently in the list
	 */
	public int size()
	{	return size_list;
	}

	/**
	 * Returns the element at the given position. 
	 * 
	 * If the index is negative or greater or equal than the size of
	 * the list, then an appropriate error must be returned.
	 * 
	 * @param index the position in the list of the item to be retrieved
	 * @return the element or an appropriate error message, 
	 *         encapsulated in a ReturnObject
	 */
	public ReturnObject get(int index)
	{
		ErrorMessage error1=errortype(index);
		ReturnObject returnobj;
	
		if(error1.equals(ErrorMessage.NO_ERROR))
		{	
			Element elem=startElement;
			for(int j=0;j<index+1;j++)
			{
				elem=elem.getNext();
			}
			returnobj= new ReturnObjectImpl(elem.returnObject());			
		}
		else
		{	
			returnobj= new ReturnObjectImpl(error1);			
		}
		
		return returnobj;
	}

	/**
	 * Returns the elements at the given position and removes it
	 * from the list. The indeces of elements after the removed
	 * element must be updated accordignly.
	 * 
	 * If the index is negative or greater or equal than the size of
	 * the list, then an appropriate error must be returned.
	 * 
	 * @param index the position in the list of the item to be retrieved
	 * @return the element or an appropriate error message, 
	 *         encapsulated in a ReturnObject
	 */
	public ReturnObject remove(int index)
	{
		ErrorMessage error1=errortype(index);
		ReturnObject returnobj;
	
		if(error1.equals(ErrorMessage.NO_ERROR) && size_list>0)
		{	

			Element elem=startElement;
			//Element elem2=startElement;

			for(int j=0;j<index+1;j++)
			{	elem=elem.getNext();
			}

			elem.setNext(elem.getNext().getNext());

			returnobj=new ReturnObjectImpl(elem.getNext().returnObject());
			size_list--;
			
		}
		else
		{	returnobj=new ReturnObjectImpl(error1);
		}
		
		return returnobj;
	}

	/**
	 * Adds an element to the list, inserting it at the given
	 * position. The indeces of elements at and after that position
	 * must be updated accordignly.
	 * 
	 * If the index is negative or greater or equal than the size of
	 * the list, then an appropriate error must be returned.
	 * 
	 * If a null object is provided to insert in the list, the
	 * request must be ignored and an appropriate error must be
	 * returned.
	 * 
	 * @param index the position at which the item should be inserted in
	 *              the list
	 * @param item the value to insert into the list
	 * @return an ReturnObject, empty if the operation is successful
	 *         or containing an appropriate error message otherwise
	 */
	public ReturnObject add(int index, Object item)
	{
		ErrorMessage error1;
		ReturnObject returnobj;

		if(item.equals(null))
		{	error1=ErrorMessage.INVALID_ARGUMENT;
		}
		else if(index<0 || index>=size_list)
		{	error1=ErrorMessage.INDEX_OUT_OF_BOUNDS;
		}
		else
		{	error1=ErrorMessage.NO_ERROR;
		}
	
		if(error1.equals(ErrorMessage.NO_ERROR))
		{	
			Element elem=startElement;

			for(int j=0;j<index+1;j++)
			{	elem=elem.getNext();
			}
			Element newElement=new Element(item);
			newElement.setNext(elem.getNext());
			elem.setNext(newElement);

			size_list++;
			returnobj = new ReturnObjectImpl(item);
		}
		else
		{	returnobj = new ReturnObjectImpl(error1);
		}
		
		return returnobj;
	}

	/**
	 * Adds an element at the end of the list.
	 * 
	 * If a null object is provided to insert in the list, the
	 * request must be ignored and an appropriate error must be
	 * returned.
	 * 
	 * @param item the value to insert into the list
	 * @return an ReturnObject, empty if the operation is successful
	 *         or containing an appropriate error message otherwise
	 */
	public ReturnObject add(Object item)
	{	
		ReturnObject returnobj;

		if(item.equals(null))
		{	
			returnobj= new ReturnObjectImpl(ErrorMessage.INVALID_ARGUMENT);
		}	
		else
		{	
			
			returnobj= new ReturnObjectImpl(item);
			
			Element elem=startElement;
			for(int j=0;j<size_list;j++)
			{	elem=elem.getNext();
			}
			Element newElement=new Element(item);
			elem.setNext(newElement);			
			size_list++;
		}

		return returnobj;
	}

	public ErrorMessage errortype(int index)
	{
		ErrorMessage error1;

		if(size_list==0)
		{	error1=ErrorMessage.EMPTY_STRUCTURE;
		}
		else if(index<0 || index>=size_list)
		{	error1=ErrorMessage.INDEX_OUT_OF_BOUNDS;
		}
		else
		{	error1=ErrorMessage.NO_ERROR;
		}
		
		return error1;
	}
		
/**
	public static void main(String[] args)
	{
		List list1=new ArrayList();
		
		System.out.println("list empty?: "+list1.isEmpty());
		System.out.println("list size?: "+list1.size());

		ReturnObject returnobj=list1.get(0);
		System.out.println("error ?: "+returnobj.getError());

		list1.add("Object 1: String: happy me");
		System.out.println("list empty?: "+list1.isEmpty());
		System.out.println("list size?: "+list1.size());
		ReturnObject returnobj2=list1.get(0);
		System.out.println("error ?: "+returnobj2.getError()+", value: "+returnobj2.getReturnValue());

		list1.add("Object 2: String: ddkdkd");
		System.out.println("list empty?: "+list1.isEmpty());
		System.out.println("list size?: "+list1.size());

		ReturnObject returnobj3=list1.get(1);
		System.out.println("error ?: "+returnobj3.getError()+", value: "+returnobj3.getReturnValue());
		

		ReturnObject returnobj5=list1.remove(1);
		System.out.println("removed: "+returnobj5.getReturnValue());

		ReturnObject returnobj6=list1.get(0);
		System.out.println("error after removing ?: "+returnobj6.getError()+", value after remove: "+returnobj6.getReturnValue());
		
		ReturnObject returnobj9=list1.get(1);
		System.out.println("error1 ?: "+returnobj9.getError()+", value: "+returnobj9.getReturnValue());


		ReturnObject returnobj7=list1.add(0,"inserting item: string: jdjdj");
		System.out.println("error ?: "+returnobj7.getError()+", value: added "+returnobj7.getReturnValue());

		ReturnObject returnobj8=list1.get(0);
		System.out.println("error0 ?: "+returnobj8.getError()+", value: "+returnobj8.getReturnValue());

		ReturnObject returnobj9=list1.get(1);
		System.out.println("error1 ?: "+returnobj9.getError()+", value: "+returnobj9.getReturnValue());

		ReturnObject returnobj10=list1.get(2);
		System.out.println("error2 ?: "+returnobj10.getError()+", value: "+returnobj10.getReturnValue());

	}

*/

}