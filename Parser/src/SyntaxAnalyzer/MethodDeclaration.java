package SyntaxAnalyzer;

import java.util.ArrayList;

public class MethodDeclaration
{
	
	private String accessModifier;
	private Type methodType;
	private Identifier methodName;
	private ArrayList<Type> parameterTypes;
	private ArrayList<Identifier> parameterNames;
	private ArrayList<VarDeclaration> varDeclarations;
	private ArrayList<Stm> statements;
	private Expression expression;
	
	public MethodDeclaration(String accessModifier , Type methodType , Identifier methodName , ArrayList<Type> parameterTypes 
			,ArrayList<Identifier> parameterNames  , ArrayList<VarDeclaration>varDeclarations , ArrayList<Stm> statements , Expression expression)
	{
		//System.out.println(methodName.getValue());
		this.accessModifier = accessModifier;
		this.methodName = methodName;
		this.methodType = methodType;
		this.parameterNames = parameterNames;
		this.parameterTypes = parameterTypes;
		this.varDeclarations = varDeclarations;
		this.statements = statements;
		this.expression = expression;
	}
	
	private String printParameters()
	{
		int count = 0;
		String result = new String();
		if(parameterNames == null || parameterTypes == null)return result;
		for (Type parameterType : this.parameterTypes)
		{
			if(count == 0)
			{
				result += parameterType.getValue();
				result += " ";
				result += parameterNames.get(count).getValue();
				count++;
			}
			else
			{
				result += ",";
				result += parameterType.getValue();
				result += " ";
				result += parameterNames.get(count).getValue();
				count++;
			}
		}
		return result;
	}
	
	private String printStatements()
	{
		String result = new String();
		if(statements == null)return result;
		for (Stm statement: this.statements)
		{
			result += statement.getValue();
		}
		return result;
	}
	
	private String printVarDeclaration()
	{
		String result = new String();
		if(varDeclarations == null)return result;
		for (VarDeclaration varDeclaration : this.varDeclarations)
		{
			result += varDeclaration.getValue();
		}
		return result;
	}
	
	public String getValue()
    {
		//System.out.println(methodName);
         return ((accessModifier.equals("private")||(accessModifier.equals("public"))?(accessModifier):(""))) 
        		 + methodType.getValue() + " " + methodName.getValue() + "( " + printParameters() + ")" + "\n{\n" + printVarDeclaration() 
        		 + printStatements()+"\nreturn " + expression.getValue()+";\n"+"}" ;
    }

}
