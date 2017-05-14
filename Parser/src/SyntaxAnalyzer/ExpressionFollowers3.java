package SyntaxAnalyzer;
public class ExpressionFollowers3 implements ExpressionFollower {

	Token DOT;
	DotFollwers dotfollwers;

	public ExpressionFollowers3(Token dOT, DotFollwers dotfollwers) {
		super();
		DOT = dOT;
		this.dotfollwers = dotfollwers;
	}

	@Override
	public String getValue() {

		return "." + dotfollwers.getValue();
	}

}
