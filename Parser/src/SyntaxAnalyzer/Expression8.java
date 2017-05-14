package SyntaxAnalyzer;
public class Expression8 implements Expression {

	Token LEFT_ROUND_B;
	Expression expression;
	Token RIGHT_ROUND_B;
	ExpressionDash expressionDash;

	public Expression8(Token lEFT_ROUND_B, Expression expression, Token rIGHT_ROUND_B, ExpressionDash expressionDash) {
		super();
		LEFT_ROUND_B = lEFT_ROUND_B;
		this.expression = expression;
		RIGHT_ROUND_B = rIGHT_ROUND_B;
		this.expressionDash = expressionDash;
	}

	@Override
	public String getValue() {

		return "(" + expression.getValue() + ")" + expressionDash.getValue();
	}

}
