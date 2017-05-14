package SyntaxAnalyzer;

public class MainClass 
{
	private Identifier identifier1;
	private Identifier identifier2;
	private Stm statement;
	
	public MainClass(Identifier identifier1 , Identifier identifier2 , Stm statement )
	{
		this.identifier1 = identifier1;
		this.identifier2 = identifier2;
		this.statement = statement;
	}
	
	public String getValue()
    {
         return "class " + identifier1.getValue() + " {\npublic static void main" 
         + "(" +" String " +"[]" + identifier2.getValue() + ") {\n" + ((statement == null) ? (""):(statement.getValue())) + "}\n}\n";
    }

}
