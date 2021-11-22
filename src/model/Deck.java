package model;

import java.util.ArrayList;
import java.util.Collections;
import model.Card.Suit;

public class Deck {
    
    private ArrayList<Card> deck;
    
    public Deck() {
        deck = new ArrayList<>();
    }
    
    public ArrayList<Card> getDeck() {
        return deck;
    }
    
    public int getDeckSize() {
        return deck.size();
    }
    
    public void addCard(Card c) {
        deck.add(c);
    }
    
    public void removeCard(Card c) {
        deck.remove(c);
    }
    
    public void shuffle() {
        Collections.shuffle(deck);
    }
    
    public void printCards() {
        ArrayList<Card> deck = new ArrayList<>(this.deck);
        Collections.sort(deck);
        for (Suit s : Suit.values()) {
            System.out.print(s + "'S : - ");
            for (Card c : deck) {
                if (c.getSuit() == s) {
                    System.out.print(c.getRank() + " - ");
                }
            }
            System.out.println("");
        }
        System.out.println("Total: " + deck.size());
    }
}
