public class Element
{
	private Object obj;
	private Element nextElement;
	
	public Element(Object object1)
	{
		obj=object1;
		nextElement=null;	
	}


	public Object returnObject()
	{
		return obj;
	}
	public Element getNext()
	{
		return nextElement;
	}
	public void setNext(Element elem2)
	{
		this.nextElement=elem2;
	}
	
}