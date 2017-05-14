package lexicalAnalyzer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import SyntaxAnalyzer.Parser;

public class Analyzer 
{
	private ArrayList<MiniJavaLabel> miniJavaLabels;
	private String content;
	
	public Analyzer()
	{
		miniJavaLabels = new ArrayList<MiniJavaLabel>();
		content = new String();
		loadMiniJavaLabels();
		readInputFromFile();
		
	}
	public void loadMiniJavaLabels()
	{
		MiniJavaLabel label =  new MiniJavaLabel();
		label.setName("COMMENT1");
		label.setRegularExpression("(\\/\\/.*)");
		miniJavaLabels.add(label);
		label =  new MiniJavaLabel();
		label.setName("COMMENT2");
		label.setRegularExpression("(\\/\\*)(.*|(\\n)*)*(\\*\\/)");
		miniJavaLabels.add(label);
		label =  new MiniJavaLabel();
		label.setName("COMMENT_L");
		label.setRegularExpression("\\/\\*");
		miniJavaLabels.add(label);
		label =  new MiniJavaLabel();
		label.setName("COMMENT_R");
		label.setRegularExpression("(\\*\\/)");
		miniJavaLabels.add(label);
		label =  new MiniJavaLabel();
		label.setName("STRING_LITERAL");
		label.setRegularExpression("\".*\"");
		miniJavaLabels.add(label);
		label =  new MiniJavaLabel();
		label.setName("A_CHAR");
		label.setRegularExpression("\\'(.)?\\'");
		miniJavaLabels.add(label);
		label =  new MiniJavaLabel();
		label.setName("SYSTEM.OUT.PRINTLN");
		label.setRegularExpression("\\bSystem\\.out\\.println\\b");
		miniJavaLabels.add(label);
		label =  new MiniJavaLabel();
		label.setName("FLOAT_LITERAL");
		label.setRegularExpression("\\b[0-9][0-9]*\\.[0-9][0-9]*\\b");
		miniJavaLabels.add(label);
		/*
		label =  new MiniJavaLabel();
		label.setName("EOL");
		label.setRegularExpression("(\n)");
		miniJavaLabels.add(label);
		*/
		label =  new MiniJavaLabel();
		label.setName("PLUS");
		label.setRegularExpression("[+]");
		miniJavaLabels.add(label);
		label =  new MiniJavaLabel();
		label.setName("LEFT_CURLY_B");
		label.setRegularExpression("[\\{]");
		miniJavaLabels.add(label);
		label =  new MiniJavaLabel();
		label.setName("RIGHT_CURLY_B");
		label.setRegularExpression("[\\}]");
		miniJavaLabels.add(label);
		label =  new MiniJavaLabel();
		label.setName("LEFT_SQUARE_B");
		label.setRegularExpression("(\\[)");
		miniJavaLabels.add(label);
		label =  new MiniJavaLabel();
		label.setName("RIGHT_SQUARE_B");
		label.setRegularExpression("(\\])");
		miniJavaLabels.add(label);
		label =  new MiniJavaLabel();
		label.setName("LEFT_ROUND_B");
		label.setRegularExpression("(\\()");
		miniJavaLabels.add(label);
		label =  new MiniJavaLabel();
		label.setName("RIGHT_ROUND_B");
		label.setRegularExpression("(\\))");
		miniJavaLabels.add(label);
		label =  new MiniJavaLabel();
		label.setName("COMMA");
		label.setRegularExpression("[\\,]");
		miniJavaLabels.add(label);
		label =  new MiniJavaLabel();
		label.setName("SEMICOLON");
		label.setRegularExpression("(\\;)");
		miniJavaLabels.add(label);
		label =  new MiniJavaLabel();
		label.setName("DOT");
		label.setRegularExpression("[\\.]");
		miniJavaLabels.add(label);
		label =  new MiniJavaLabel();
		label.setName("NOT");
		label.setRegularExpression("[\\!]");
		miniJavaLabels.add(label);
		label =  new MiniJavaLabel();
		label.setName("EQUAL");
		label.setRegularExpression("[\\=]");
		miniJavaLabels.add(label);
		label =  new MiniJavaLabel();
		label.setName("AND");
		label.setRegularExpression("[\\&&]");
		miniJavaLabels.add(label);
		label =  new MiniJavaLabel();
		label.setName("MINUS");
		label.setRegularExpression("[\\-]");
		miniJavaLabels.add(label);
		label =  new MiniJavaLabel();
		label.setName("MULTIPLY");
		label.setRegularExpression("[\\*]");
		miniJavaLabels.add(label);
		label =  new MiniJavaLabel();
		label.setName("LESSTHAN");
		label.setRegularExpression("[\\<]");
		miniJavaLabels.add(label);
		label =  new MiniJavaLabel();
		label.setName("GREATERTHAN");
		label.setRegularExpression("\\>]");
		miniJavaLabels.add(label);
		label =  new MiniJavaLabel();
		label.setName("IF");
		label.setRegularExpression("(\\bif\\b)");
		miniJavaLabels.add(label);
		label =  new MiniJavaLabel();
		label.setName("INT");
		label.setRegularExpression("(\\bint\\b)");
		miniJavaLabels.add(label);
		label =  new MiniJavaLabel();
		label.setName("ELSE");
		label.setRegularExpression("(\\belse\\b)");
		miniJavaLabels.add(label);
		label =  new MiniJavaLabel();
		label.setName("MAIN");
		label.setRegularExpression("(\\bmain\\b)");
		miniJavaLabels.add(label);
		label =  new MiniJavaLabel();
		label.setName("THIS");
		label.setRegularExpression("(\\bthis\\b)");
		miniJavaLabels.add(label);
		label =  new MiniJavaLabel();
		label.setName("TRUE");
		label.setRegularExpression("(\\btrue\\b)");
		miniJavaLabels.add(label);
		label =  new MiniJavaLabel();
		label.setName("VOID");
		label.setRegularExpression("(\\bvoid\\b)");
		miniJavaLabels.add(label);
		label =  new MiniJavaLabel();
		label.setName("CLASS");
		label.setRegularExpression("\\bclass\\b");
		miniJavaLabels.add(label);
		label =  new MiniJavaLabel();
		label.setName("FALSE");
		label.setRegularExpression("\\bfalse\\b");
		miniJavaLabels.add(label);
		label =  new MiniJavaLabel();
		label.setName("WHILE");
		label.setRegularExpression("\\bwhile\\b");
		miniJavaLabels.add(label);
		label =  new MiniJavaLabel();
		label.setName("LENGTH");
		label.setRegularExpression("\\blength\\b");
		miniJavaLabels.add(label);
		label =  new MiniJavaLabel();
		label.setName("PUBLIC");
		label.setRegularExpression("(\\bpublic\\b)");
		miniJavaLabels.add(label);
		label =  new MiniJavaLabel();
		label.setName("RETURN");
		label.setRegularExpression("(\\breturn\\b)");
		miniJavaLabels.add(label);
		label =  new MiniJavaLabel();
		label.setName("STATIC");
		label.setRegularExpression("\\bstatic\\b");
		miniJavaLabels.add(label);
		label =  new MiniJavaLabel();
		label.setName("NEW");
		label.setRegularExpression("\\bnew\\b");
		miniJavaLabels.add(label);
		label =  new MiniJavaLabel();
		label.setName("STRING");
		label.setRegularExpression("\\bstring\\b");
		miniJavaLabels.add(label);
		label =  new MiniJavaLabel();
		label.setName("FLOAT");
		label.setRegularExpression("\\bfloat\\b");
		miniJavaLabels.add(label);
		label =  new MiniJavaLabel();
		label.setName("CHARACTER");
		label.setRegularExpression("\\bchar\\b");
		miniJavaLabels.add(label);
		label =  new MiniJavaLabel();
		label.setName("BOOLEAN");
		label.setRegularExpression("\\bboolean\\b");
		miniJavaLabels.add(label);
		label =  new MiniJavaLabel();
		label.setName("EXTENDS");
		label.setRegularExpression("\\bextends\\b");
		miniJavaLabels.add(label);
		label =  new MiniJavaLabel();
		label.setName("INTEGRAL_LITERAL");
		label.setRegularExpression("\\b[0-9][0-9]*\\b");
		miniJavaLabels.add(label);
		label =  new MiniJavaLabel();
		label.setName("SQUOTE");
		label.setRegularExpression("[\']");
		miniJavaLabels.add(label);
		label =  new MiniJavaLabel();
		label.setName("DQUOTE");
		label.setRegularExpression("[\"]");
		miniJavaLabels.add(label);
		label =  new MiniJavaLabel();
		label.setName("ID");
		label.setRegularExpression("[a-zA-Z_$][a-zA-Z0-9_$]*");
		miniJavaLabels.add(label);
		label =  new MiniJavaLabel();
	}
	public ArrayList<Token> checkSyntax()
	{
		ArrayList<Token> inputTokens = new ArrayList<Token>();
		Token token =  new Token();
		Pattern p = null ;
		Matcher m = null;
		StringBuilder builder = new StringBuilder(content);
		for(int i = 0 ; i < miniJavaLabels.size() ; ++i)
		{
			p =  Pattern.compile(miniJavaLabels.get(i).getRegularExpression());
			m = p.matcher(content);
			while(m.find())
			{
				/*
				if(miniJavaLabels.get(i).getName().equals("ID"))
				{
					Pattern specialNames = Pattern.compile(specialNamesRE);
					Matcher check = specialNames.matcher(m.group());
					if(check.find())continue;
				}
				*/
				for(int j = m.start() ; j < m.end() ; ++j)
				{
					builder.replace(j, j+1, " ");
				}
				content = builder.toString();
				builder = new StringBuilder(content);
				token.setName(miniJavaLabels.get(i).getName());
				token.setValue(m.group());
				token.setStart(m.start());
				inputTokens.add(token);
				token =  new Token();
			}
		}
		content = content.replaceAll("\\p{Z}", "").replace("\t", "");
		content = content.replace("\n", "").replace("\r", "");
		//System.out.println(content+"\n-----------------");
		//System.out.println(content.length());
		if(content.length() != 0)
		{
			System.out.println("Syntax Error:( "+"unidentified token "+content+" )");
			System.exit(0);
		}
		return inputTokens;
	}
	public ArrayList<Token> sortTokens(ArrayList<Token> result)
	{
		Token temp1 = new Token();
		Token temp2 = new Token();
		for(int i = 0 ; i < result.size() ; ++i)
		{
			for(int j = i+1 ; j <= (result.size()-1); j++)
			{
				if(result.get(i).getStart() > result.get(j).getStart())
				{
					temp1 = new Token(result.get(j).getName() ,result.get(j).getValue(),result.get(j).getStart());
					temp2 = new Token(result.get(i).getName() ,result.get(i).getValue(),result.get(i).getStart());
					result.remove(i);
					result.add(i,temp1);
					result.remove(j);
					result.add(j,temp2);
					temp1 = new Token();
					temp2 = new Token();
				}
			}
		}
		// int  class  name
		//0     1      2
		return result;
	}
	public void printTokens()
	{
		ArrayList<Token> allTokens = checkSyntax();
		ArrayList<Token>finalResult = new ArrayList<Token>();
		finalResult	 = sortTokens(allTokens);
		for(int i = 0 ; i < finalResult.size() ; ++i)
		{
			System.out.println("<"+finalResult.get(i).getName()+">"+" : "+finalResult.get(i).getValue());
			//System.out.println(finalResult.get(i).getStart());
		}
		writeOutputToFile(finalResult);
	}
	public void readInputFromFile()
	{
			try 
			{
				content = new Scanner(new File("input.txt")).useDelimiter("\\Z").next();
				System.out.println(content+"\n-------------------------");
			} 
			catch (FileNotFoundException e)
			{				
				e.printStackTrace();
			}
	}
	public void writeOutputToFile(ArrayList<Token> result)
	{
		try 
		{
			Formatter file = new Formatter("output.txt");
			for(int i = 0 ; i < result.size() ; ++i)
			{
				file.format("<%s>:%s%n",result.get(i).getName() ,result.get(i).getValue());
			}
			file.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	public static void main(String[] args) 
	{
		Analyzer a = new Analyzer();
		a.printTokens();		
	}

}
