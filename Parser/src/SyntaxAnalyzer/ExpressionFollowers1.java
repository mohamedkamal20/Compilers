package SyntaxAnalyzer;
public class ExpressionFollowers1 implements ExpressionFollower {

	Token operator;
	Expression expression;

	public ExpressionFollowers1(Token operator, Expression expression) {
		super();
		this.operator = operator;
		this.expression = expression;
	}

	@Override
	public String getValue() {

		return operator.getValue() + expression.getValue();

	}

}
