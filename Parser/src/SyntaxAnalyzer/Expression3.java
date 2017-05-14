package SyntaxAnalyzer;
public class Expression3 implements Expression {

	Token FALSE;
	ExpressionDash expressiondash;

	public Expression3(Token fLSE, ExpressionDash expressiondash) {
		super();
		FALSE = fLSE;
		this.expressiondash = expressiondash;
	}

	@Override
	public String getValue() {

		return "false" + expressiondash.getValue();

	}
}
