package model;

public class Card implements Comparable<Card> {

    public enum Suit {
        DIAMOND, CLUB, HEART, SPADE, JOKER
    }

    public enum Rank {
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE, JOKER
    }

    private Rank rank;
    private Suit suit;

    public Card(Suit s, Rank r) {
        suit = s;
        rank = r;
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public int compareTo(Card o) {
        return getRank().compareTo(o.getRank());
    }

    @Override
    public String toString() {
        if (rank == Rank.JOKER) {
            return rank.toString();
        }
        else {
            return String.valueOf(rank) + String.valueOf(suit);
        }
    }
}
