package com.example.ba_cay_project_2;

public class Cards {
    private String rank; // Giá trị (A, 2, 3,..., K)
    private String suit; // Chất (Hearts, Diamonds, Clubs, Spades)

    public Cards(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    public int getPointValue() {
        // Tính điểm cho game 3 Cây (A=1, 2-10 giữ nguyên, J/Q/K=10)
        return switch (rank) {

            case "Ace", "Jack", "Queen", "King" -> 10;
            default -> {
                try {
                    yield Integer.parseInt(rank);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid card rank: " + rank);
                }
            }
        };
    }

    @Override
    public String toString() {
        String suitSymbol = switch (suit) {
            case "Hearts" -> "♥";
            case "Diamonds" -> "♦";
            case "Clubs" -> "♣";
            case "Spades" -> "♠";
            default -> "?";
        };
        return rank + suitSymbol;
    }
}


