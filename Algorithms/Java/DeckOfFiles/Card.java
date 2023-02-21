package DeckOfFiles;

/*
	Deck of Cards: Design the data structures for a generic deck of cards. Explain how you would
	subclass the data structures to implement blackjack. 
*/

public class Card {
	protected int value;
	protected boolean Available;
	private Symbol symbol;

	public Card(int value, Symbol symbol) {
		this.value = value;
		this.symbol = symbol;
		this.Available = true;
	}

	public int getValue() {
		return value;
	}

	public boolean isAvailable() {
		return Available;
	}

	public Symbol getSymbol() {
		return symbol;
	}

	public void setAvailable(boolean available) {
		this.Available = available;
	}
}
