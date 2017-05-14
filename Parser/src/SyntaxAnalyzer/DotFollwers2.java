package SyntaxAnalyzer;

import java.util.ArrayList;

public class DotFollwers2 implements DotFollwers {

	Token identifier;
	Token LEFT_ROUND_B;
    ArrayList<Expression> expList;
	Token RIGHT_ROUND_B;
	String printedexlist="";

	
	
	public DotFollwers2(Token identifier, Token lEFT_ROUND_B, ArrayList<Expression> expList, Token rIGHT_ROUND_B) {
		super();
		this.identifier = identifier;
		LEFT_ROUND_B = lEFT_ROUND_B;
		this.expList = expList;
		RIGHT_ROUND_B = rIGHT_ROUND_B;
	}
	
	
	
	@Override
	public String getValue() {
	for(int i=0;i<expList.size();i++){
			if(i > 0)printedexlist+=",";
			printedexlist+=expList.get(i).getValue()+" ";
		}
		return identifier.getValue()+"("+printedexlist+")";
	
				
	}

}
