package SyntaxAnalyzer;
public class NewFollowers2 implements NewFollowers {

	Token identifier;
	Token LEFT_ROUND_B;
	Token RIGHT_ROUND_B;

	public NewFollowers2(Token identifier, Token lEFT_ROUND_B, Token rIGHT_ROUND_B) {
		super();
		this.identifier = identifier;
		LEFT_ROUND_B = lEFT_ROUND_B;
		RIGHT_ROUND_B = rIGHT_ROUND_B;
	}

	@Override
	public String getValue() {
		return identifier.getValue() + "(" + ")";
	}

}
