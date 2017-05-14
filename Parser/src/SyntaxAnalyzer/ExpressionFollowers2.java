package SyntaxAnalyzer;
public class ExpressionFollowers2 implements ExpressionFollower {

	Token LEFT_SQUARE_B;
	Expression expression;
	Token RIGHT_SQUARE_B;

	public ExpressionFollowers2(Token lEFT_SQUARE_B, Expression expression, Token rIGHT_SQUARE_B) {
		super();
		LEFT_SQUARE_B = lEFT_SQUARE_B;
		this.expression = expression;
		RIGHT_SQUARE_B = rIGHT_SQUARE_B;
	}

	@Override
	public String getValue() {

		return "[" + expression.getValue() + "]";
	}

}
