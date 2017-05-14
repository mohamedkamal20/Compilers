package SyntaxAnalyzer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Parser {
	public static Queue<Token> tokens;
	public Goal root;

	public Parser() {
		root = null;
		tokens = new LinkedList<Token>();
		readTokensFromFile();
		/*
		  while(!tokens.isEmpty()) {
		  System.out.println( tokens.peek().getName()+" "+tokens.poll().getValue()); 
		  }
		 */
		//System.out.println(tokens.size()); 
		parse();
	}

	public void readTokensFromFile() {
		try {
			Scanner file = new Scanner(new File("output.txt"));
			String line = new String();
			String[] splitter = new String[2];
			Token token = new Token();
			while (file.hasNext()) {
				line = file.nextLine();

				splitter = line.split(":");

				if (splitter.length == 1) {
					token = new Token(splitter[0], "");
				} else {

					// System.out.println(splitter.length);
					token = new Token(splitter[0], splitter[1]);
				}
				// System.out.println(splitter[0] + splitter[1]);
				//if(token.getName().equals("<EOF>"))break;
				tokens.add(token);
				line = "";
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void parse() {
		parseGoal();
	}

	public void parseGoal() {
		Goal goal = null;

		MainClass mainClass = parseMainClass();
		//System.out.println(1);
		//mainClass.getValue();
		ArrayList<ClassDeclaration> classDeclarations = new ArrayList<ClassDeclaration>();
		ClassDeclaration temp = null;
		while (true) {
			temp = parseClassDeclaration();
			if (temp == null) {
				break;
			}
			classDeclarations.add(temp);
		}
		//System.out.println(1);
			goal = new Goal(mainClass, classDeclarations);
			
			System.out.println(goal.getValue());
	}

	public MainClass parseMainClass() {
		MainClass mainClass = null;
		//System.out.println(tokens.size());
		if ( tokens.peek().getName().equals("<CLASS>")) {
			//System.out.println(tokens.peek().getName());
			tokens.poll();
			//System.out.println(111);
			Identifier identifier1 = parseIdentifier();
			//System.out.println(111);
			if (tokens.peek().getName().equals("<LEFT_CURLY_B>")
					&& tokens.peek().getValue().equals("{")) {
				tokens.poll();
				if (tokens.peek().getName().equals("<PUBLIC>")
						&& tokens.peek().getValue().equals("public")) {
					tokens.poll();
					if (tokens.peek().getName().equals("<STATIC>")
							&& tokens.peek().getValue().equals("static")) {
						tokens.poll();
						if (tokens.peek().getName().equals("<VOID>")
								&& tokens.peek().getValue().equals("void")) {
							tokens.poll();
							if (tokens.peek().getName().equals("<MAIN>")
									&& tokens.peek().getValue().equals("main")) {
								tokens.poll();
								
								if (tokens.peek().getName()
										.equals("<LEFT_ROUND_B>")
										&& tokens.peek().getValue().equals("(")) {
									tokens.poll();
									if (tokens.peek().getName()
											.equals("<STRING>")
											&& tokens.peek().getValue()
													.equals("String")) {
										tokens.poll();
										if (tokens.peek().getName()
												.equals("<LEFT_SQUARE_B>")
												&& tokens.peek().getValue()
														.equals("[")) {
											tokens.poll();
											if (tokens.peek().getName()
													.equals("<RIGHT_SQUARE_B>")
													&& tokens.peek().getValue()
															.equals("]")) {
												tokens.poll();
												Identifier identifier2 = parseIdentifier();
												if (tokens
														.peek()
														.getName()
														.equals("<RIGHT_ROUND_B>")
														&& tokens.peek()
																.getValue()
																.equals(")")) {
													tokens.poll();
													if (tokens
															.peek()
															.getName()
															.equals("<LEFT_CURLY_B>")
															&& tokens
																	.peek()
																	.getValue()
																	.equals("{")) {
														tokens.poll();
														Stm statement = parseStatement();
														if (tokens
																.peek()
																.getName()
																.equals("<RIGHT_CURLY_B>")
																&& tokens
																		.peek()
																		.getValue()
																		.equals("}")) 
														{
															tokens.poll();
															//tokens.poll();
															//System.out.println(tokens.peek().getValue());
															if (tokens
																	.peek()
																	.getName()
																	.equals("<RIGHT_CURLY_B>")
																	&& tokens
																			.peek()
																			.getValue()
																			.equals("}")) 
															{
																tokens.poll();
																mainClass = new MainClass(
																		identifier1,
																		identifier2,
																		statement);
																return mainClass;

															} 
															else {
																System.out.println("Syntax error1");
																System.exit(0);
															}

														} 
														else {
															System.out
																	.println("Syntax error2");
															System.exit(0);
														}
													} else {
														System.out
																.println("Syntax error3");
														System.exit(0);
													}

												} else {
													System.out
															.println("Syntax error4");
													System.exit(0);
												}
											} else {
												System.out
														.println("Syntax error5");
												System.exit(0);
											}

										} else {
											System.out.println("Syntax error6");
											System.exit(0);
										}
									} else {
										System.out.println("Syntax error7");
										System.exit(0);
									}

								} else {
									System.out.println("Syntax error8");
									System.exit(0);
								}

							} else {
								System.out.println("Syntax error9");
								System.exit(0);
							}

						} else {
							System.out.println("Syntax error10");
							System.exit(0);
						}

					} else {
						System.out.println("Syntax error11");
						System.exit(0);
					}

				} else {
					System.out.println("Syntax error12");
					System.exit(0);
				}
			} else {
				System.out.println("Syntax error13");
				System.exit(0);
			}
		} else {
			System.out.println("Syntax error14");
			System.exit(0);
		}
		return mainClass;
	}

	public VarDeclaration parseVarDeclaration() {
		VarDeclaration varDeclaration = null;
		Type type = parseType();
		if(type==null)return null;
		Identifier identifier = parseIdentifier();
		//System.out.println(tokens.peek().getValue());
		if (tokens.peek().getName().equals("<SEMICOLON>")
				&& tokens.peek().getValue().equals(";")) {
			tokens.poll();
			varDeclaration = new VarDeclaration(type, identifier);
			return varDeclaration;
		} else {
			System.out.println("Syntax errore");
			System.exit(0);
		}
		return varDeclaration;
	}

	public ClassDeclaration parseClassDeclaration() {
		ClassDeclaration classDeclaration = null;
		if(tokens.isEmpty())
		{
			return null;
		}
		else if (tokens.peek().getName().equals("<CLASS>")
				&& tokens.peek().getValue().equals("class")) 
		{
			tokens.poll();
			Identifier identifier1 = parseIdentifier();
			Identifier identifier2 = null;
			if (tokens.peek().getName().equals("<EXTENDS>")
					&& tokens.peek().getValue().equals("extends")) {
				tokens.poll();
				identifier2 = parseIdentifier();
				if (tokens.peek().getName().equals("<LEFT_CURLY_B>")
						&& tokens.peek().getValue().equals("{")) {
					tokens.poll();
				} else {
					System.out.println("syntax error");
					System.exit(0);
				}
			} else if (tokens.peek().getName().equals("<LEFT_CURLY_B>")
					&& tokens.peek().getValue().equals("{")) {
				tokens.poll();
			} else {
				System.out.println("syntax errora");
				System.exit(0);
			}

			ArrayList<VarDeclaration> varDeclarations = new ArrayList<VarDeclaration>();
			ArrayList<MethodDeclaration> methodDeclarations = new ArrayList<MethodDeclaration>();
			VarDeclaration temp1 = null;
			MethodDeclaration temp2 = null;
			int count = 0 ;
			//System.out.println(1);
			while (true) {
				temp1 = parseVarDeclaration();
				if (temp1 == null) 
				{
					if(count == 0)
					{
						
						varDeclarations = null;
					}
					break;
				}
				count ++;
				varDeclarations.add(temp1);
			}
			count = 0;
			while (true) {
				temp2 = parseMethodDeclaration();
				if (temp2 == null) 
				{
					if(count == 0)
					{
						methodDeclarations= null;
					}
					break;
				}
				count++;
				methodDeclarations.add(temp2);
			}
			//System.out.println(tokens.peek().getValue());
			if (tokens.peek().getName().equals("<RIGHT_CURLY_B>")
					&& tokens.peek().getValue().equals("}")) {
				tokens.poll();
				
				classDeclaration = new ClassDeclaration(identifier1,
						identifier2, varDeclarations, methodDeclarations);
				return classDeclaration;
			} 
			else {
				System.out.println("syntax errorb");
				System.exit(0);
			}

		} else {
			System.out.println("syntax errorc");
			System.exit(0);
		}

		return classDeclaration;
	}

	public MethodDeclaration parseMethodDeclaration() {
		MethodDeclaration methodDeclaration = null;
		String accessModifier = "";
		//System.out.println(tokens.peek().getName());
		if (tokens.peek().getName().equals("<PUBLIC>")
				|| tokens.peek().getName().equals("<PRIVATE>")) 
		{
			//System.out.println(1);
			if (tokens.peek().getName().equals("<PUBLIC>")
					&& tokens.peek().getValue().equals("public")) 
			{
				//System.out.println(1);
				accessModifier = tokens.poll().getValue();
			} else if (tokens.peek().getName().equals("<PRIVATE>")
					&& tokens.peek().getValue().equals("private")) {
				accessModifier = tokens.poll().getValue();
			}
			else {
				System.out.println("syntax errorf");
				System.exit(0);
			}
			Type methodType = parseType();
			//System.out.println(methodType.getValue());
			Identifier methodName = parseIdentifier();
			//System.out.println(methodName.getValue());
			//System.out.println(methodName.getValue());
			if (tokens.peek().getName().equals("<LEFT_ROUND_B>")
					&& tokens.peek().getValue().equals("(")) {
				tokens.poll();
				//System.out.println(1);
				ArrayList<Type> parameterTypes = new ArrayList<Type>();
				ArrayList<Identifier> parameterNames = new ArrayList<Identifier>();
				Type temp1 = null;
				Identifier temp2 = null;
				int count = 0 ;
				while (true) 
				{
					if(count > 0)
					{
						if(tokens.peek().getName().equals("<COMMA>"))
						{
							tokens.poll();
						}
						else
						{
							break;
						}
						
					}
					else
					{
						count++;
					}
					
					temp1 = parseType();
					temp2 = parseIdentifier();
					//System.out.println(temp2.getValue());
					if (temp1 == null)
					{
						
						if (temp2 == null) {
							break;
						} else {
							System.out.println("syntax errorh");
							System.exit(0);
						}
					} 
					else if (temp2 == null)
					{
						if (temp1 == null) 
						{
							break;
						} 
						else 
						{
							System.out.println("syntax errori");
							System.exit(0);
						}
					}
					parameterTypes.add(temp1);
					parameterNames.add(temp2);
				}
				//System.out.println(1);
				if (tokens.peek().getName().equals("<RIGHT_ROUND_B>")
						&& tokens.peek().getValue().equals(")")) 
				{
					tokens.poll();
					//System.out.println(1);
					if (tokens.peek().getName().equals("<LEFT_CURLY_B>")
							&& tokens.peek().getValue().equals("{")) {
						tokens.poll();
						//System.out.println(1);
						ArrayList<VarDeclaration> varDeclarations = new ArrayList<VarDeclaration>();
						ArrayList<Stm> statements = new ArrayList<Stm>();
						VarDeclaration temp3 = null;
						Stm temp4 = null;
						while (true) {
							temp3 = parseVarDeclaration();
							if (temp3 == null) {
								break;
							}
							//System.out.println(2);
							varDeclarations.add(temp3);
						}
						while (true) {
							temp4 = parseStatement();
							if (temp4 == null) 
							{
								break;
							}
							//System.out.println(2);
							//System.out.println(temp4.getValue());
							statements.add(temp4);
						}

						if (tokens.peek().getName().equals("<RETURN>")
								&& tokens.peek().getValue().equals("return")) {
							tokens.poll();
							//System.out.println(1);
							Expression expression = parseExpression();
							
							if (tokens.peek().getName().equals("<SEMICOLON>")
									&& tokens.peek().getValue().equals(";")) {
								tokens.poll();
								
								if (tokens.peek().getName()
										.equals("<RIGHT_CURLY_B>")
										&& tokens.peek().getValue().equals("}")) {
									tokens.poll();
									//System.out.println(1);
									methodDeclaration = new MethodDeclaration(
											accessModifier, methodType,
											methodName, parameterTypes,
											parameterNames, varDeclarations,
											statements, expression);
									
									return methodDeclaration;

								} else {
									System.out.println("syntax errork");
									System.exit(0);
								}
							} else {
								System.out.println("syntax errorl");
								System.exit(0);
							}
						} else {
							System.out.println("syntax errorm");
							System.exit(0);
						}
					} else {
						System.out.println("syntax errorn");
						System.exit(0);
					}
				} else {
					System.out.println("syntax erroro");
					System.exit(0);
				}
			} 
		} 
		return methodDeclaration;
	}

	public Type parseType() {
		Type type = null;
		if (tokens.peek().getName().equals("<BOOLEAN>")
				&& tokens.peek().getValue().equals("boolean")) {
			tokens.poll();
			if (tokens.peek().getName().equals("<LEFT_SQUARE_B>")
					&& tokens.peek().getValue().equals("[")) {
				tokens.poll();
				if (tokens.peek().getName().equals("<RIGHT_SQUARE_B>")
						&& tokens.peek().getValue().equals("]")) {
					tokens.poll();
					type = new Type("boolean", true);
				} else {
					System.out.println("syntax errorr");
					System.exit(0);
				}
			} else {
				type = new Type("boolean", false);
			}
		} else if (tokens.peek().getName().equals("<INT>")
				&& tokens.peek().getValue().equals("int")) {
			tokens.poll();
			if (tokens.peek().getName().equals("<LEFT_SQUARE_B>")
					&& tokens.peek().getValue().equals("[")) {
				tokens.poll();
				if (tokens.peek().getName().equals("<RIGHT_SQUARE_B>")
						&& tokens.peek().getValue().equals("]")) {
					tokens.poll();
					type = new Type("int", true);
				} else {
					System.out.println("syntax errors");
					System.exit(0);
				}
			} else {
				type = new Type("int", false);
			}
		} else if (tokens.peek().getName().equals("<CHAR>")
				&& tokens.peek().getValue().equals("char")) {
			tokens.poll();
			if (tokens.peek().getName().equals("<LEFT_SQUARE_B>")
					&& tokens.peek().getValue().equals("[")) {
				tokens.poll();
				if (tokens.peek().getName().equals("<RIGHT_SQUARE_B>")
						&& tokens.peek().getValue().equals("]")) {
					tokens.poll();
					type = new Type("char", true);
				} else {
					System.out.println("syntax errort");
					System.exit(0);
				}
			} else {
				type = new Type("char", false);
			}
		} else if (tokens.peek().getName().equals("<STRING>")
				&& tokens.peek().getValue().equals("String")) {
			tokens.poll();
			if (tokens.peek().getName().equals("<LEFT_SQUARE_B>")
					&& tokens.peek().getValue().equals("[")) {
				tokens.poll();
				if (tokens.peek().getName().equals("<RIGHT_SQUARE_B>")
						&& tokens.peek().getValue().equals("]")) {
					tokens.poll();
					type = new Type("String", true);
				} else {
					System.out.println("syntax errorv");
					System.exit(0);
				}
			} else {
				type = new Type("String", false);
			}
		} else if (tokens.peek().getName().equals("<FLOAT>")
				&& tokens.peek().getValue().equals("float")) {
			tokens.poll();
			if (tokens.peek().getName().equals("<LEFT_SQUARE_B>")
					&& tokens.peek().getValue().equals("[")) {
				tokens.poll();
				if (tokens.peek().getName().equals("<RIGHT_SQUARE_B>")
						&& tokens.peek().getValue().equals("]")) {
					tokens.poll();
					type = new Type("float", true);
				} else {
					System.out.println("syntax errorw");
					System.exit(0);
				}
			} else {
				type = new Type("float", false);
			}
		} 
		else
			return type;
		return type;
	}

	public Stm1 parseStm1() // Statement::= "{" ( Statement )* "}"
	{
		Stm1 stm1 = null;
		if (tokens.peek().getName().equals("<LEFT_CURLY_B>")
				&& tokens.peek().getValue().equals("{")) {
			tokens.poll();
			ArrayList<Stm> st = new ArrayList<>();
			Stm st1 = null;
			tokens.poll();
			while (true) {
				st1 = parseStatement();
				if (st1 == null) {
					break;
				}
				st.add(st1);
			}
			if (tokens.peek().getName().equals("<RIGHT_CURLY_B>")
					&& tokens.peek().getValue().equals("}")) {
				tokens.poll();
				stm1 = new Stm1(st);
				return stm1;
			} else {
				System.out.print("Syntax Error");
				System.exit(0);
			}
		} else {
			System.out.print("Syntax Error");
			System.exit(0);
		}
		return stm1;
	}

	public Stm2 parseStm2() // Statement::= "if" "(" Expression ")" Statement
							// Else
	{
		Stm2 stm2 = null;
		if (tokens.peek().getName().equals("<IF>")
				&& tokens.peek().getValue().equals("if")) {
			tokens.poll();
			if (tokens.peek().getName().equals("<LEFT_ROUND_B>")
					&& tokens.peek().getValue().equals("(")) {
				tokens.poll();
				Expression ex = parseExpression();
				if (tokens.peek().getName().equals("<RIGHT_ROUND_B>")
						&& tokens.peek().getValue().equals(")")) {
					tokens.poll();
					Stm st = parseStatement();
					Else els = parseElse();
					stm2 = new Stm2(ex, st, els);
					return stm2;
				} else {
					System.out.print("Syntax Error");
					System.exit(0);
				}
			} else {
				System.out.print("Syntax Error");
				System.exit(0);
			}
		} else {
			System.out.print("Syntax Error");
			System.exit(0);
		}
		return stm2;
	}

	public Stm3 parseStm3() // Statement::= "while" "(" Expression ")" Statement
	{
		Stm3 stm3 = null;
		if (tokens.peek().getName().equals("<WHILE>")
				&& tokens.peek().getValue().equals("while")) {
			tokens.poll();
			if (tokens.peek().getName().equals("<LEFT_ROUND_B>")
					&& tokens.peek().getValue().equals("(")) {
				tokens.poll();

				Expression ex = parseExpression();
				if (tokens.peek().getName().equals("<RIGHT_ROUND_B>")
						&& tokens.peek().getValue().equals(")")) {
					tokens.poll();
					Stm st = parseStatement();
					stm3 = new Stm3(ex, st);
					return stm3;
				} else {
					System.out.print("Syntax Error");
					System.exit(0);
				}

			} else {
				System.out.print("Syntax Error");
				System.exit(0);
			}
		} else {
			System.out.print("Syntax Error");
			System.exit(0);
		}
		return stm3;
	}

	public Stm4 parseStm4() // Statement::= "System.out.println" "(" Expression
							// ")" ";"
	{
		//System.out.println("22222222222222");
		Stm4 stm4 = null;
		if (tokens.peek().getName().equals("<SYSTEM.OUT.PRINTLN>")
				&& tokens.peek().getValue().equals("System.out.println"))
		{
			tokens.poll();
			if (tokens.peek().getName().equals("<LEFT_ROUND_B>")
					&& tokens.peek().getValue().equals("(")) 
			{
				tokens.poll();
				//System.out.println(tokens.peek().getValue());
				Expression ex = parseExpression();
				if (tokens.peek().getName().equals("<RIGHT_ROUND_B>")
						&& tokens.peek().getValue().equals(")")) 
				{
					tokens.poll();
					if (tokens.peek().getName().equals("<SEMICOLON>")
							&& tokens.peek().getValue().equals(";")) 
					{
						//System.out.println("111111111111111111");
						tokens.poll();
						stm4 = new Stm4(ex);
						return stm4;
					} else {
						System.out.print("Syntax Error");
						System.exit(0);
					}

				} else {
					System.out.print("Syntax Error");
					System.exit(0);
				}
			} else {
				System.out.print("Syntax Error");
				System.exit(0);

			}

		} else {
			System.out.print("Syntax Error");
			System.exit(0);

		}
		return stm4;
	}

	public Stm5 parseStm5() // Statement::= Identifier Identifier_Statement
	{
		Stm5 stm5 = null;
		Identifier id = parseIdentifier();

		IdentifierFollowers identifierFollowes = parseIdentifierFollowers();
		stm5 = new Stm5(id, identifierFollowes);

		return stm5;
	}

	public Else parseElse() // Else::= "else" Statement
	{
		Else else1 = null;
		if (tokens.peek().getName().equals("<ELSE>")
				&& tokens.peek().getValue().equals("else")) {
			tokens.poll();
			Stm st = parseStatement();
			else1 = new Else(st);
			return else1;
		} else {
			return null;
		}

	}

	public Identifier1 parseIdentifier1() // Iderntifier-Statements::= "="
											// Expression ";"
	{
		Identifier1 identifier1 = null;
		if (tokens.peek().getName().equals("<EQUAL>")
				&& tokens.peek().getValue().equals("=")) {
			tokens.poll();
			Expression ex = parseExpression();
			if (tokens.peek().getName().equals("<SEMICOLON>")
					&& tokens.peek().getValue().equals(";")) {
				tokens.poll();
				identifier1 = new Identifier1(ex);
				return identifier1;
			} else {
				System.out.print("Syntax Error");
				System.exit(0);

			}
		} else {
			System.out.print("Syntax Error");
			System.exit(0);

		}
		return identifier1;

	}

	public Identifier2 parseIdentifier2() // Iderntifier-Statements::= "["
											// Expression "]" "=" Expression ";"
	{
		Identifier2 identifier2 = null;
		if (tokens.peek().getName().equals("<LEFT_SQUARE_B>")
				&& tokens.peek().getValue().equals("[")) {
			tokens.poll();
			Expression ex = parseExpression();
			if (tokens.peek().getName().equals("<RIGHT_SQUARE_B>")
					&& tokens.peek().getValue().equals("]")) {
				tokens.poll();
				if (tokens.peek().getName().equals("<EQUAL>")
						&& tokens.peek().getValue().equals("=")) {
					tokens.poll();
					Expression ex1 = parseExpression();
					if (tokens.peek().getName().equals("<SEMICOLON>")
							&& tokens.peek().getValue().equals(";")) {
						tokens.poll();
						identifier2 = new Identifier2(ex, ex1);
						return identifier2;
					} else {
						System.out.print("Syntax Error");
						System.exit(0);

					}
				} else {
					System.out.print("Syntax Error");
					System.exit(0);

				}
			} else {
				System.out.print("Syntax Error");
				System.exit(0);

			}

		} else {
			System.out.print("Syntax Error");
			System.exit(0);

		}
		return identifier2;

	}

	// ///////////////////////////
	// ABD_ALMAGEED///////////////////////////////////////////////
	public Expression parseExpression() {

		Token t = tokens.peek();
		//System.out.println(t.getValue());
		if (t.getName().equals("<INTEGRAL_LITERAL>")) {
			t = tokens.poll();
			//System.out.println(t.getValue());
			ExpressionDash exDash = getExD();
			return new Expression1(t, exDash);
		} else if (t.getName().equals("<TRUE>")) {
			t = tokens.poll();
			ExpressionDash exDash = getExD();
			return new Expression2(t, exDash);
		}

		else if (t.getName().equals("<FALSE>")) {
			t = tokens.poll();
			ExpressionDash exDash = getExD();
			return new Expression3(t, exDash);
		} else if (t.getName().equals("<ID>")) {
			t = tokens.poll();
			ExpressionDash exDash = getExD();
			return new Expression4(t, exDash);
		} else if (t.getName().equals("<THIS>")) {
			t = tokens.poll();
			ExpressionDash exDash = getExD();
			return new Expression5(t, exDash);
		} else if (t.getName().equals("<NEW>")) {
			t = tokens.poll();
			//System.out.println(tokens.peek().getValue());
			NewFollowers newFo = getNewFollowers();

			if (newFo == null) 
			{
				System.out.println("error in Expretion no new followers");
				return null;
			}

			ExpressionDash exDash = getExD();
			return new Expression6(t, newFo, exDash);
		} else if (t.getName().equals("<NOT>")) {
			t = tokens.poll();
			Expression Exp = parseExpression();
			if (Exp == null) {
				System.out.println("error in Expretion no expretion after not");
				return null;
			}
			ExpressionDash exDash = getExD();
			return new Expression7(t, Exp, exDash);
		} else if (t.getName().equals("<LEFT_ROUND_B>")) {
			t = tokens.poll();
			Expression Exp = parseExpression();
			if (Exp == null) {
				System.out
						.println("error in expretion no expretion after NOT !");
				return null;
			}
			Token t2 = tokens.peek();
			if (t2.getName().equals("<RIGHT_ROUND_B>")) {
				t2 = tokens.poll();

				ExpressionDash exDash = getExD();
				return new Expression8(t, Exp, t2, exDash);

			} else {
				System.out.println("error no RIGHT_ROUND_B");
				return null;
			}

		}

		else {
			System.out.println("error in EXPRETION");
			return null;
		}
	}

	// _______________________________________________________

	public ExpressionDash getExD() {
		//System.out.println(tokens.peek().getValue());
		ExpressionFollower expFollwers = getExpFollwers();
		if (expFollwers == null) {
			return new ExpressionDash2();
		}
		// make sure
		ExpressionDash expDash = getExD();
		

		return new ExpressionDash1(expFollwers, expDash);

	}

	// _______________________________________________________
	public NewFollowers getNewFollowers() {
		Token t1 = tokens.peek();
		Token t2, t3;
		if (t1.getName().equals("<INT>")) 
		{
			t1 = tokens.poll();
			t2 = tokens.peek();
			if (t2.getName().equals("<LEFT_SQUARE_B>")) {
				t2 = tokens.poll();
				Expression exp = parseExpression();
				if (exp == null) {
					System.out.println("error in newfolwer no expretion");
					return null;
				}
				t3 = tokens.peek();
				if (t3.getName().equals("<RIGHT_SQUARE_B>")) {
					return new NewFollowers1(t1, t2, exp, t3);
				}

			}
		} 
		else if (t1.getName().equals("<ID>")) 
		{
			
			t1 = tokens.poll();
			t2 = tokens.peek();
			//System.out.println(t2.getValue());
			if (t2.getName().equals("<LEFT_ROUND_B>")) {
				t2 = tokens.poll();
				t3 = tokens.peek();
				
				if (t3.getName().equals("<RIGHT_ROUND_B>")) {
					t3 = tokens.poll();
					//System.out.println(t3.getValue());
					return new NewFollowers2(t1, t2, t3);
				}
			}

		} 
		else 
		{
			System.out.println("error in NewFollowers");
			return null;
		}
		//System.out.println("endddd");
		return null;

	}

	// ________________________________________________________
	public ExpressionFollower getExpFollwers() {
		//System.out.println(tokens.peek().getName());
		Token t = tokens.peek();
		if (t.getName().equals("<PLUS>") || t.getName().equals("<MINUS>")
				|| t.getName().equals("<MULTIPLY>")
				|| t.getName().equals("<LESSTHAN>") || t.getName().equals("<AND>")) {
			t = tokens.poll();
			Expression exp = parseExpression();
			if (exp == null) {
				System.out
						.println("error in expritionfollwer no exprition operator");
				return null;
			}
			return new ExpressionFollowers1(t, exp);

		} else if (t.getName().equals("<LEFT_SQUARE_B>")) {
			t = tokens.poll();
			Token t2;
			Expression exp = parseExpression();
			if (exp == null) {
				System.out
						.println("error in expritionfollwer no expression after [");
				return null;
			}
			t2 = tokens.peek();
			if (t2.getName().equals("<RIGHT_SQUARE_B>")) {
				t2 = tokens.poll();
				return new ExpressionFollowers2(t, exp, t2);
			}
		} else if (t.getName().equals("<DOT>")) 
		{
			//System.out.println(1);
			t = tokens.poll();
			DotFollwers dotFo = getDotFollowers();
			if (dotFo == null) {
				System.out
						.println("error in expritionfollwer no dot followers");
				return null;
			}
			return new ExpressionFollowers3(t, dotFo);

		}
		return null;
	}

	// ________________________________________________________

	public DotFollwers getDotFollowers() {
		Token t = tokens.peek();
		Token t2, t3;
		if (t.getName().equals("<LENGTH>")) 
		{
			t = tokens.poll();
			return new DotFollwers1(t);
		} else if (t.getName().equals("<ID>")) 
		{
			//System.out.println(1);
			ArrayList<Expression> expList = new ArrayList<Expression>();
			t = tokens.poll();
			t2 = tokens.peek();
		  //  System.out.println(t2.getValue());
			if (t2.getName().equals("<LEFT_ROUND_B>"))
			{
				t2 = tokens.poll();
				//System.out.println(tokens.peek().getValue()); //10
				Expression exp = parseExpression();
				if (exp != null) 
				{
					expList.add(exp);
					while (tokens.peek().getName().equals("<COMMA>")) {
						tokens.poll();
						Expression expr = parseExpression();
						if (exp == null) {
							System.out
									.println("error in getdotfollower no more expression");
							return null;
						}
						expList.add(expr);
					}
				}
				

				t3 = tokens.peek();

				if (t3.getName().equals("<RIGHT_ROUND_B>")) {
					t3 = tokens.poll();
					return new DotFollwers2(t, t2, expList, t3);
				}
			}
		}

		return null;

	}

	// ________________________________________________________
	public Identifier parseIdentifier() {
		Token t = tokens.peek();
		if (t.getName().equals("<ID>")) {
			t = tokens.poll();
			return new Identifier(t.getValue());
		}
		return null;
	}

	// _________________________________________________________
	// ///////////////////////////ABD_ALMAGEED///////////////////////////////////////////////

	public Stm parseStatement() {
		//System.out.println("11111111111111111");
		Stm st = null;
		if (tokens.peek().getName().equals("<LEFT_CURLY_B>")) {
			st = parseStm1();
			return st;
		} else if (tokens.peek().getName().equals("<IF>")) {
			st = parseStm2();
			return st;
		} else if (tokens.peek().getName().equals("<WHILE>")) {
			st = parseStm3();
			return st;
		} else if (tokens.peek().getName().equals("<SYSTEM.OUT.PRINTLN>")) {
			st = parseStm4();
			return st;
		} else if (tokens.peek().getName().equals("<ID>")) {
			st = parseStm5();
			return st;
		}
		//System.out.println("hna");
		return st;
	}

	public IdentifierFollowers parseIdentifierFollowers() {
		IdentifierFollowers identifierFollower = null;
		if (tokens.peek().getName().equals("<EQUAL>")
				&& tokens.peek().getValue().equals("=")) {
			identifierFollower = parseIdentifier1();
			return identifierFollower;
		} else if (tokens.peek().getName().equals("<LEFT_SQUARE_B>")
				&& tokens.peek().getValue().equals("[")) {
			identifierFollower = parseIdentifier2();
			return identifierFollower;
		}
		return identifierFollower;
	}

	public static void main(String[] args) 
	{
		Parser p = new Parser();
	

	}

}
