package SyntaxAnalyzer;

public class Type 
{

	private String type;
	private boolean parentheses;
	
	public Type(String type , boolean parentheses)
	{
		if(type == "int" | type == "String" | type == "float" | type == "boolean" | type == "char") this.type = type;
		else System.exit(0);
		this.parentheses = parentheses;
	}
	
	public String getValue()
	{
		return type + ((parentheses)?(" []"):(""));
	}
}
