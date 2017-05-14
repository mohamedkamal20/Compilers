package lexicalAnalyzer;

public class Token 
{
	private String name;
	private String value;
	private int start;
	
	public Token()
	{
		name = new String();
		value = new String();
		start = 0;
	}
	public Token(String name_ , String value_ , int start_)
	{
		name = name_;
		value = value_;
		start = start_;
	}
	public int getStart()
	{
		return start;
	}
	public void setStart(int start) 
	{
		this.start = start;
	}
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public String getValue() 
	{
		return value;
	}
	public void setValue(String value) 
	{
		this.value = value;
	}
	
	

}
