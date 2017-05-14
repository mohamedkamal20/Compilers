package SyntaxAnalyzer;
public class Expression5 implements Expression {

	Token THIS;
	ExpressionDash expressionDash;

	public Expression5(Token tHIS, ExpressionDash expressionDash) {
		super();
		THIS = tHIS;
		this.expressionDash = expressionDash;
	}

	@Override
	public String getValue() {

		return "this" + expressionDash.getValue();
	}

}
