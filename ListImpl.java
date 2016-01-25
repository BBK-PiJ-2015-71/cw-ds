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
public class ListImpl implements List
{	
	private Object[] object_list;
	private int index;
	private int size_list;
		
	public ListImpl(int size)
	{
		object_list=new Object[size];
		index=0;
		size_list=0;	
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
		Object object2;

	
		if(error1.equals(ErrorMessage.NO_ERROR))
		{	object2=object_list[index-1];
		}
		else
		{	object2=null;
		}

		ReturnObject returnobj = new ReturnObjectImpl(error1,object2);
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
		Object object3;	
	
		if(error1.equals(ErrorMessage.NO_ERROR) && size_list>0)
		{	object3=object_list[index];
			for(int i=index;i<size_list-1;i++)
			{	object_list[i]=object_list[i+1];
			}
			size_list--;
		}
		else
		{	object3=null;
		}
		ReturnObject returnobj = new ReturnObjectImpl(error1,object3);
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
		Object object3=null;	

		if(item.equals(null))
		{	error1=ErrorMessage.INVALID_ARGUMENT;
		}
		else
		{	error1=errortype(index);
		}
	
		if(error1.equals(ErrorMessage.NO_ERROR))
		{	for(int i=size_list;i>=index+1;i--)
			{	
				object_list[i]=object_list[i-1];
			}
			object_list[index]=item;
			object3=item;
			size_list++;
		}

		ReturnObject returnobj = new ReturnObjectImpl(error1,object3);
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
		ErrorMessage error1;
		Object object3=null;	

		if(item.equals(null))
		{	error1=ErrorMessage.INVALID_ARGUMENT;
		}	
		else
		{	error1=ErrorMessage.NO_ERROR;
		}

		if(error1.equals(ErrorMessage.NO_ERROR))
		{	
			object_list[size_list]=item;
			size_list++;
			object3=item;
		}

		ReturnObject returnobj = new ReturnObjectImpl(error1,object3);
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

	public static void main(String[] args)
	{
		List list1=new ListImpl(3000);
		list1.add("Uni");
		list1.get(0);
	}

}