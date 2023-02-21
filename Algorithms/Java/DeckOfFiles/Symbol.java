package DeckOfFiles;

public enum Symbol {
	CLUB(1), SPADES(2), HEARTS(3), DIOMAND(4);

	private final int code;

	Symbol(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
