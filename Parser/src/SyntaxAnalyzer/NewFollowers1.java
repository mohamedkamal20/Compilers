package SyntaxAnalyzer;
public class NewFollowers1 implements NewFollowers {

	Token INT;
	Token LEFT_SQUARE_B;
	Expression expression;
	Token RIGHT_SQUARE_B;

	public NewFollowers1(Token iNT, Token lEFT_SQUARE_B, Expression expression, Token rIGHT_SQUARE_B) {
		super();
		INT = iNT;
		LEFT_SQUARE_B = lEFT_SQUARE_B;
		this.expression = expression;
		RIGHT_SQUARE_B = rIGHT_SQUARE_B;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return "int [" + expression.getValue() + "]";
	}

}
