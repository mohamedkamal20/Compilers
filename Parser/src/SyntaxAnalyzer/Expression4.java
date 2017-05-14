package SyntaxAnalyzer;
public class Expression4 implements Expression {

	Token identefier;
	ExpressionDash expressionDash;

	public Expression4(Token identefier, ExpressionDash expressionDash) {
		super();
		this.identefier = identefier;
		this.expressionDash = expressionDash;
	}

	@Override
	public String getValue() {
		return identefier.getValue() + expressionDash.getValue();
	}

}
