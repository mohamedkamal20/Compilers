package SyntaxAnalyzer;

public class VarDeclaration 
{
	private Type type;
	private Identifier identifier;
	
	public VarDeclaration(Type type , Identifier identifier)
	{
		this.type = type;
		this.identifier = identifier;
	}
	
	public String getValue()
    {
         return type.getValue() + " " + identifier.getValue() +" ;";
    }

}
