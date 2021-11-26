package domain;

import java.util.*;

public class Simulation {
	private Vector<Card> deck = new Vector<Card>(52);
	private Vector<Card> playerHand = new Vector<Card>(13);
	private Vector<Card> partnerHand = new Vector<Card>(13);
	Random random = new Random();
	Scanner input = new Scanner(System.in);
	private int playerHandValue;
	
	public void run(){
		createDeck();
		dealPlayerHand();
		
		System.out.println("Here is your hand:");
		
		for(int i = 0; i < playerHand.size(); i++) {
			System.out.print(playerHand.elementAt(i).getCardName() + " ");
		}
		
		playerHandValue = calculateHandValue(playerHand);
		
		System.out.print("\nThis hand is worth " + playerHandValue + " points.\n" + "Running simulation.....\n" + "\nThe estimated probability based on 1000 simulated hands:");
		
		simulation();
		
		System.out.print("\n\nAnother hand [Y/N]?");
		
		userInput();
	}
	
	
	
	private void createDeck() {
		
		for(int i = 0; i < 13; i++) {
			if(i < 9) {
				Card card = new Card(Integer.toString(i+2), "S", 0, i+1);
				deck.add(card);
			}
			else if(i == 9) {
				Card card = new Card("J", "S", 1, 10);
				deck.add(card);
			}
			else if( i == 10) {
				Card card = new Card("Q", "S", 2, 11);
				deck.add(card);
			}
			else if( i == 11) {
				Card card = new Card("K", "S", 3, 12);
				deck.add(card);
			}
			else if( i == 12) {
				Card card = new Card("A", "S", 4, 13);
				deck.add(card);
			}
		}
		
		for(int i = 0; i < 13; i++) {
			if(i < 9) {
				Card card = new Card(Integer.toString(i+2), "H", 0, i+14);
				deck.add(card);
			}
			else if(i == 9) {
				Card card = new Card("J", "H", 1, 23);
				deck.add(card);
			}
			else if( i == 10) {
				Card card = new Card("Q", "H", 2, 24);
				deck.add(card);
			}
			else if( i == 11) {
				Card card = new Card("K", "H", 3, 25);
				deck.add(card);
			}
			else if( i == 12) {
				Card card = new Card("A", "H", 4, 26);
				deck.add(card);
			}
		}
		
		for(int i = 0; i < 13; i++) {
			if(i < 9) {
				Card card = new Card(Integer.toString(i+2), "D", 0, i+27);
				deck.add(card);
			}
			else if(i == 9) {
				Card card = new Card("J", "D", 1, 36);
				deck.add(card);
			}
			else if( i == 10) {
				Card card = new Card("Q", "D", 2, 37);
				deck.add(card);
			}
			else if( i == 11) {
				Card card = new Card("K", "D", 3, 38);
				deck.add(card);
			}
			else if( i == 12) {
				Card card = new Card("A", "D", 4, 39);
				deck.add(card);
			}
		}
		
		for(int i = 0; i < 13; i++) {
			if(i < 9) {
				Card card = new Card(Integer.toString(i+2), "C", 0, i+40);
				deck.add(card);
			}
			else if(i == 9) {
				Card card = new Card("J", "C", 1, 49);
				deck.add(card);
			}
			else if( i == 10) {
				Card card = new Card("Q", "C", 2, 50);
				deck.add(card);
			}
			else if( i == 11) {
				Card card = new Card("K", "C", 3, 51);
				deck.add(card);
			}
			else if( i == 12) {
				Card card = new Card("A", "C", 4, 52);
				deck.add(card);
			}
		}
	}
	
	
	
	private void dealPlayerHand() {
		Card[] cards = new Card[13];
		Card tempCard = new Card();
		for(int i = 0; i < 13; i++) {
			int x = random.nextInt(deck.size());
			playerHand.add(deck.elementAt(x));
			deck.remove(x);
		}
		
		for(int i = 0; i < playerHand.size(); i++) {
			cards[i] = playerHand.elementAt(i);
		}
		
		playerHand.removeAllElements();
		
		for(int i = 0; i < 13; i++) {
			for(int j = 0; j < 13; j++) {
				if(cards[j].getOrder() > cards[i].getOrder()) {
					tempCard = cards[i];
					cards[i] = cards[j];
					cards[j] = tempCard;
				}
			}
		}
		
		for(int i = 0; i < 13; i++) {
			playerHand.add(cards[i]);
		}
	}
	
	private int calculateHandValue(Vector<Card> hand) {
		int handValue = 0;
		Iterator<Card> itr = hand.iterator();
		while(itr.hasNext()) {
			handValue += itr.next().getCardValue();
		}
		
		handValue += calculateDistributionPoints(hand);
		
		return handValue;
	}
	
	private int calculateDistributionPoints(Vector<Card> hand) {
		int distributionPoints = 0;
		int sCounter = 0; 
		int hCounter = 0; 
		int dCounter = 0;
		int cCounter = 0;
		Card card;

		Iterator<Card> itr = hand.iterator();
		while(itr.hasNext()) {
			card = itr.next();
			
			if(card.getCardSuit() == "S") {
				sCounter++;
			}
			else if(card.getCardSuit() == "H") {
				hCounter++;
			}
			else if(card.getCardSuit() == "D") {
				dCounter++;
			}
			else if(card.getCardSuit() == "C") {
				cCounter++;
			}
		}
		
		if(sCounter == 2) {
			distributionPoints += 1;
		}
		if(hCounter == 2) {
			distributionPoints += 1;
		}
		if(dCounter == 2) {
			distributionPoints += 1;
		}
		if(cCounter == 2) {
			distributionPoints += 1;
		}
		if(sCounter == 1) {
			distributionPoints += 2;
		}
		if(hCounter == 1) {
			distributionPoints += 2;
		}
		if(dCounter == 1) {
			distributionPoints += 2;
		}
		if(cCounter == 1) {
			distributionPoints += 2;
		}
		if(sCounter == 0) {
			distributionPoints += 5;
		}
		if(hCounter == 0) {
			distributionPoints += 5;
		}
		if(dCounter == 0) {
			distributionPoints += 5;
		}
		if(cCounter == 0) {
			distributionPoints += 5;
		}
		
		return distributionPoints;
	}
	
	private int calculateTotalPoints(int partnerHandValue) {
		return playerHandValue + partnerHandValue;
	}
	
	private void simulation() {
		double passCount = 0;
		double	partScoreCount = 0;
		double gameCount = 0;
		double smallSlamCount = 0;
		double grandSlamCount = 0;
		int totalValue;
	
		for(int i = 0; i < 1000; i++) {
			
			for(int j = 0; j < 13; j++) {
				int x = random.nextInt(deck.size());
				partnerHand.add(deck.elementAt(x));
			}
			
			totalValue = calculateTotalPoints(calculateHandValue(partnerHand));
			
			if(totalValue < 20) {
				passCount++;
			}
			else if((totalValue >= 20) && (totalValue <= 25)) {
				partScoreCount++;
			}
			else if((totalValue > 25) && (totalValue <= 31)) {
				gameCount++;
			}
			else if((totalValue > 31) && (totalValue <= 35)) {
				smallSlamCount++;
			}
			else if(totalValue >= 36) {
				grandSlamCount++;
			}
			
			partnerHand.removeAllElements();
		}
		
		System.out.print("\nPass: " + passCount / 10 + "%" + "\nPart score: " + partScoreCount / 10 + "%" + "\nGame: " 
				+ gameCount / 10 + "%" + "\nSmall slam: " + smallSlamCount / 10 + "%" + "\nGrand slam: " + grandSlamCount / 10 + "%");
	}

	private void userInput() {
			char in = Character.toUpperCase(input.next().charAt(0));
		
			if(in == 'Y'){
				deck.removeAllElements();
				playerHand.removeAllElements();
				run();
			}else if(in == 'N'){
				input.close();
				System.exit(0);
			}else {
				System.out.println("Invalid input. \nAnother hand [Y/N]?");
				userInput();
			}
		
	}
}
