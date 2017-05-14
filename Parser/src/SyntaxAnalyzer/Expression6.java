package SyntaxAnalyzer;
public class Expression6 implements Expression {

	Token NEW;
	NewFollowers newFollowers;
	ExpressionDash expressionDash;

	public Expression6(Token nEW, NewFollowers newFollowers, ExpressionDash expressionDash) {
		super();
		NEW = nEW;
		this.newFollowers = newFollowers;
		this.expressionDash = expressionDash;
	}

	@Override
	public String getValue() {

		return "new" + newFollowers.getValue() + expressionDash.getValue();
	}

}
