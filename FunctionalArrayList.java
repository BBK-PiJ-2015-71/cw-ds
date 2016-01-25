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
public class FunctionalArrayList implements FunctionalList
{	
	private Object[] object_list;
	private int index;
	private int size_list;
		
	public FunctionalArrayList()
	{
		object_list=new Object[10000];
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
		ReturnObject returnobj;
	
		if(error1.equals(ErrorMessage.NO_ERROR))
		{	
			returnobj= new ReturnObjectImpl(object_list[index]);			
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
			returnobj=new ReturnObjectImpl(object_list[index]);
			for(int i=index;i<size_list-1;i++)
			{	object_list[i]=object_list[i+1];
			}
			object_list[index]=null;
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
		{	for(int i=size_list;i>=index+1;i--)
			{	
				object_list[i]=object_list[i-1];
			}
			object_list[index]=item;
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
			object_list[size_list]=item;
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
     * Returns the element at the beginning of the list. 
     * 
     * If the list is empty, an appropriate error is returned. 
     *
     * @return a copy of the element at the beginning of the list or 
     *         an error if the list is empty.
     */
    public ReturnObject head()
	{

		ErrorMessage error1=errortype(0);
		ReturnObject returnobj;
	
		if(error1.equals(ErrorMessage.NO_ERROR))
		{	
			returnobj= new ReturnObjectImpl(object_list[0]);			
		}
		else
		{	
			returnobj= new ReturnObjectImpl(error1);			
		}
		
		return returnobj;

	}

    /**
     * Returns a list with the elements in this list except the
     * head. The elements must be in the same order. The original list
     * must not change or be affected by changes in the new list. 
     * 
     * If the list is empty, another empty list is returned. 
     */
    public FunctionalList rest()
	{		
		ErrorMessage error1=errortype(0);
		FunctionalList list1=new FunctionalArrayList();
	
		if(error1.equals(ErrorMessage.NO_ERROR))
		{	
			//list1.setList(this.getList());
			list1.remove(0);	
			return list1;		
		}
		else
		{	
			return list1;			
		}

	}



		
/**
	public static void main(String[] args)
	{
		List list1=new ArrayList();
		ReturnObject returnobj4=list1.remove(0);
		System.out.println("error rm?: "+returnobj4.getError());

		System.out.println("list empty?: "+list1.isEmpty());
		System.out.println("list size?: "+list1.size());

		ReturnObject returnobj=list1.get(0);
		System.out.println("error ?: "+returnobj.getError());

		list1.add("Object 1: String: happy me");
		System.out.println("list empty?: "+list1.isEmpty());
		System.out.println("list size?: "+list1.size());
		ReturnObject returnobj2=list1.get(0);
		System.out.println("error ?: "+returnobj2.getError()+", value: "+returnobj2.getReturnValue());

		ReturnObject returnobj5=list1.remove(0);
		System.out.println("removed: "+returnobj5.getReturnValue());

		ReturnObject returnobj6=list1.get(0);
		System.out.println("error after removing ?: "+returnobj6.getError()+", value after remove: "+returnobj6.getReturnValue());


		list1.add("Object 2: String: ddkdkd");
		System.out.println("list empty?: "+list1.isEmpty());
		System.out.println("list size?: "+list1.size());

		ReturnObject returnobj3=list1.get(0);
		System.out.println("error ?: "+returnobj3.getError()+", value: "+returnobj3.getReturnValue());

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