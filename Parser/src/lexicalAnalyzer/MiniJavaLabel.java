package lexicalAnalyzer;

public class MiniJavaLabel 
{
	private  String name;
	private String regularExpression;
	
	public MiniJavaLabel()
	{
		name = new String ();
		regularExpression =  new String ();
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getRegularExpression() 
	{
		return regularExpression;
	}

	public void setRegularExpression(String regularExpression)
	{
		this.regularExpression = regularExpression;
	}

}
