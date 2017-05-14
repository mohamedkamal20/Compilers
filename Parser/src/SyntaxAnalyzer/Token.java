package SyntaxAnalyzer;

public class Token 
{
	private String name;
	private String value;
	
	public Token()
	{
		name = new String();
		value = new String();
	}
	
	public Token(String name_ , String value_ )
	{
		name = name_;
		value = value_;
	}

	public String getName() 
	{
		if(name == null) return "";
		else
		{
			return name;
		}
		
	}
	/*
	public void setName(String name) 
	{
		this.name = name;
	}
	*/
	public String getValue() 
	{
		return value;
	}
	public void setValue(String value) 
	{
		this.value = value;
	}
	
}

