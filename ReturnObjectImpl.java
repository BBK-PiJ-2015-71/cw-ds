/**
 * A wrapper containing either an object (the result of an operation
 * on a data structure) or an error value.
 */
public class ReturnObjectImpl implements ReturnObject
{
 	private Object obj1=null;
	private ErrorMessage error1;	

	public ReturnObjectImpl(Object obj)
	{
		this.obj1=obj;
		this.error1=ErrorMessage.NO_ERROR;
	}

	public ReturnObjectImpl(ErrorMessage error2)
	{
		this.error1=error2;
	}

	/**
	* Returns whether there has been an error
	*/
	public boolean hasError()
	{
		if(error1==ErrorMessage.NO_ERROR)
		{
			return false;
		}
		else
		{	return true;
		}
	}

	/**
	 * Returns the error message. 
	 * 
	 * This method must return NO_ERROR if and only if
	 * {@hasError} returns false.
	 * 
	 * @return the error message
	 */
	public ErrorMessage getError()
	{
		return error1;
	}

	/**
	 * Returns the object wrapped in this ReturnObject, i.e. the
	 * result of the operation if it was successful, or null if
	 * there has been an error.
	 * 
	 * Note that the output of this method must be null if {@see
	 * hasError} returns true, but the opposite is not true: if
	 * {@see hasError} returns false, this method may or may not
	 * return null.
	 * 
	 * @return the return value from the method or null if there has been an error
	 */
	public Object getReturnValue()
	{
		if(this.hasError())
		{
			return null;
		}
		else
		{
			return obj1;
		}
	}

/**Test code
	public static void main(String[] str)
	{	String str2="test string";
		ErrorMessage error2    =ErrorMessage.EMPTY_STRUCTURE;
		ReturnObjectImpl testObj1= new ReturnObjectImpl(str2);
		System.out.println(testObj1.hasError());
		System.out.println(testObj1.getError());
		System.out.println(testObj1.getReturnValue());
	}
*/
}

