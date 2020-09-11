
//	Date: 		March 6, 2020
//	Description:	
//				Implementing the in-shuffle algorithm
//

import java.util.ArrayDeque;
import java.util.Deque;

public class PerfectShuffle {
	
	public static void main(String[] args) {
		
		System.out.println("Card Shuffle by S. De Leon\n");
		
		int[] original = null;
	
		if (args.length == 1) {	
			
			int numberOfCards = Integer.parseInt(args[0]); //number of cards suggested
			
			System.out.println(numberOfCards + " Cards");

			original = new int[numberOfCards];
			
			for(int i = 0; i < original.length; i++){
				original[i] = i+1;
			}
			
		} else {
			
			System.out.println(52 + " Cards");
			original = new int[52];

		}
		
		int[] cards = new int[original.length];

		//prints out the original cards 
		System.out.println("\nOriginal:");		
		for(int i = 0; i < original.length; i++){			
			original[i] = i+1;

			cards[i] = original[i];

			System.out.println(cardName(original[i]));
		}
		
		//card shuffling
		Deque<Integer> shuffledCards = new ArrayDeque<>();
			
		int round = 0;

		do {

			System.out.println("\nRound " + ++round + ":");

			shuffledCards = inShuffle(cards);

			for(int i = 0; i < cards.length; i++) {

				cards[i] = shuffledCards.removeFirst();
				System.out.println(cardName(cards[i]));
			}

		} while(!isEqual(original, cards));

		System.out.println("\nOriginal order in " + round + " in-shuffle."); 

	}
	
	
	//Takes a deck of cards and performs an in-shuffle on the deck.
	public static Deque<Integer> inShuffle(int[] deck) {
		
		Deque<Integer> shuffledCards = new ArrayDeque<>();
		
		int numberOfCards = deck.length;
		int halfCards = numberOfCards/2;
				
		for (int i = 0; i < halfCards; i++) {

			shuffledCards.addLast(deck[i+halfCards]);
			shuffledCards.addLast(deck[i]);
		}
		
		return shuffledCards; 		
	}
	
	
	//To get the name of the card from a card number.
	public static String cardName(int value) {
		
		final String[] suits = {"Spade", "Diamond", "Heart", "Club"};
		final String[] cards = {"Jack", "Queen", "King"};
		
		--value;
		String sValue;
		int iValue = value % 13;
		
		switch(iValue) {
			case 0: 
				sValue = "Ace";
				break;
			case 10: case 11: case 12: 
				sValue = cards[iValue - 10];
				break;
			default:
				sValue = Integer.toString(iValue+1);
				break;
		}
		
		return sValue + " of " + suits[value/13];
	}


	//Checks whether the two decks (arrays) are equal.
	public static boolean isEqual(int[] original, int[] cards){

		boolean isEqual = true;

		int i = 0;

		while(i < original.length) {

			if(original[i] == cards[i])
				i++;
			else {
				isEqual = false;
				break;
			}
		}

		return isEqual;
	}

}
