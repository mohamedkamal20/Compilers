package SyntaxAnalyzer;
public class DotFollwers1 implements DotFollwers {

	Token LENGTH;

	public DotFollwers1(Token lENGTH) {
		super();
		LENGTH = lENGTH;
	}

	@Override
	public String getValue() {
		return "length";
	}

}
