package SyntaxAnalyzer;
public class ExpressionDash1  implements ExpressionDash{

	ExpressionFollower expressionfollower;
	ExpressionDash expressiondash;

	public ExpressionDash1(ExpressionFollower expressionfollower, ExpressionDash expressiondash) 
	{
		this.expressionfollower = expressionfollower;
		this.expressiondash = expressiondash;
	}

	public String getValue() {
		return expressionfollower.getValue();
	}
}
