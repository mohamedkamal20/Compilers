package SyntaxAnalyzer;
public class Expression1 implements Expression {

	Token INTEGER_LITERAL;
	ExpressionDash expressionDash;

	public Expression1(Token iNTEGER_LITERAL, ExpressionDash expressionDash) {
		super();
		INTEGER_LITERAL = iNTEGER_LITERAL;
		this.expressionDash = expressionDash;
	}

	@Override
	public String getValue() {

		return INTEGER_LITERAL.getValue() + expressionDash.getValue(); // wrong
	}

}
