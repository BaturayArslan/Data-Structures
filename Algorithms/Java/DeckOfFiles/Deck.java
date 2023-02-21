package DeckOfFiles;

import java.util.Random;
import java.util.*;

public class Deck {
	ArrayList<Card> cards;
	int top;

	public Deck() {
		for (int i = 0; i < 4; i++) {
			for (int j = 1; j <= 13; j++) {
				switch (i) {
					case 0:
						cards.add(new Card(j, Symbol.CLUB));
						break;
					case 1:
						cards.add(new Card(j, Symbol.SPADES));
						break;
					case 2:
						cards.add(new Card(j, Symbol.HEARTS));
						break;
					case 3:
						cards.add(new Card(j, Symbol.DIOMAND));
						break;
					default:

				}
			}
		}
		top = 0;
	}

	public void shuffle() {
		Random random = new Random();
		for (int i = top; i < cards.size(); i++) {
			int randomInt = random.nextInt(51 - i + 1) + i; // return integer between 0-51
			Card tmp = cards.get(i);
			cards.set(i, cards.get(randomInt));
			cards.set(randomInt, tmp);
		}
	}

	public Card peek() {
		if (top + 1 >= cards.size() || !cards.get(top + 1).isAvailable()) {
			return null;
		}

		Card card = cards.get(top + 1);
		card.setAvailable(false);
		top += 1;
		return card;
	}

	public List<Card> getAvailables() {
		return cards.subList(top, cards.size());
	}

	public List<Card> getUnAvailables() {
		return cards.subList(0, top);
	}
}
