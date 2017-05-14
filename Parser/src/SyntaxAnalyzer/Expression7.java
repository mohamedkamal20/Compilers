package SyntaxAnalyzer;
public class Expression7 implements Expression {

	Token NOT;
	Expression expression;
	ExpressionDash expressionDash;

	public Expression7(Token nOT, Expression expression, ExpressionDash expressionDash) {
		super();
		NOT = nOT;
		this.expression = expression;
		this.expressionDash = expressionDash;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return "!" + expression.getValue() + expressionDash.getValue();
	}

}
