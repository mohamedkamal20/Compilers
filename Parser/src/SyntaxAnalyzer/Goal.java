package SyntaxAnalyzer;
import java.util.ArrayList;


public class Goal 
{
	private MainClass mainClass;
	private ArrayList<ClassDeclaration> classDeclaration;
	
	public Goal(MainClass mainClass , ArrayList<ClassDeclaration> classDeclaration)
	{
		this.mainClass = mainClass;
		this.classDeclaration = classDeclaration;
	}
	
	public boolean parseRule()
	{
		return true;
	}
	
	private String printClassDeclarations()
	{
		String result = new String();
		if(classDeclaration == null)return result;
		for (ClassDeclaration classDeclaration : this.classDeclaration)
		{
			result += classDeclaration.getValue();
		}
		return result;
	}
	
	public String getValue()
    {
		//System.out.println("ali");
         return mainClass.getValue() + "\n" + printClassDeclarations()  ;
    }

}
