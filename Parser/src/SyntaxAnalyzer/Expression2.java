package SyntaxAnalyzer;
public class Expression2 implements Expression {

	Token TRUE;
	ExpressionDash expressiondash;

	public Expression2(Token tRUE, ExpressionDash expressiondash) {
		super();
		TRUE = tRUE;
		this.expressiondash = expressiondash;
	}

	@Override
	public String getValue() {

		return "true" + expressiondash.getValue();
	}

}
