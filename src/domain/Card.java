package domain;

public class Card {
	private String type;
	private String suit;
	private int value;
	private int order;
	
	public Card(String type, String suit, int value, int order) {
		this.type = type;
		this.suit = suit;
		this.value = value;
		this.order = order;
	}
	
	public Card() {
		
	}
	
	public String getCardName() {
		return type + suit;
	}
	
	public String getCardSuit() {
		return suit;
	}
	
	public int getCardValue() {
		return value;
	}
	
	public int getOrder() {
		return order;
	}
}
