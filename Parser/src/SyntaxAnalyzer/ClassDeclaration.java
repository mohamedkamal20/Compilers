package SyntaxAnalyzer;
import java.util.ArrayList;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;


public class ClassDeclaration 
{
	private Identifier identifier1;
	private Identifier identifier2;
	private ArrayList<VarDeclaration> varDeclaration;
	private ArrayList<MethodDeclaration> methodDeclaration;
	
	public ClassDeclaration(Identifier identifier1,Identifier identifier2, ArrayList<VarDeclaration> varDeclaration , ArrayList<MethodDeclaration> methodDeclaration )
	{
		this.identifier1 = identifier1;
		this.identifier2 = identifier2;
		this.varDeclaration = varDeclaration;
		this.methodDeclaration = methodDeclaration;
	}
	
	private String printVarDeclaration()
	{
		String result = new String();
		if(varDeclaration == null)return result;
		for (VarDeclaration varDeclaration : this.varDeclaration)
		{
			result += varDeclaration.getValue();
		}
		return result;
	}
	
	private String printMethodDeclaration()
	{
		String result = new String();
		if(methodDeclaration == null)return result;
		for (MethodDeclaration methodDeclaration : this.methodDeclaration)
		{
			result += methodDeclaration.getValue();
		}
		return result;
	}
	
	public String getValue()
    {
         return "class " + identifier1.getValue() + ((identifier2 == null)? ("") : (" extends " + identifier2.getValue()) ) +"\n{\n"+ printVarDeclaration() 
        		 + printMethodDeclaration() + "\n}\n"; 
    }

}
