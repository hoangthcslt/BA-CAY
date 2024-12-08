package com.example.ba_cay_project_2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
        private final List<Cards> cards; // Bộ bài 52 lá

        public Deck() {
            cards = new ArrayList<>();
            String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
            String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};

            // Tạo bộ bài 52 lá
            for (String suit : suits) {
                for (String rank : ranks) {
                    cards.add(new Cards(rank, suit));
                }
            }
        }

        // Trộn bài
        public void shuffle() {
            Collections.shuffle(cards);
        }

        // Chia bài cho một số lượng người chơi (mỗi người 3 lá)
        public List<List<Cards>> dealCards(int numPlayers) {
            if (numPlayers < 1 || numPlayers > 4) {
                throw new IllegalArgumentException("Số lượng người chơi phải từ 1 đến 4!");
            }
            if (cards.size() < numPlayers * 3) {
                throw new IllegalStateException("Không đủ bài để chia cho tất cả người chơi!");
            }

            List<List<Cards>> hands = new ArrayList<>();
            int cardIndex = 0;
            for (int i = 0; i < numPlayers; i++) {
                hands.add(new ArrayList<>(cards.subList(cardIndex, cardIndex + 3)));
                cardIndex += 3;
            }
            cards.subList(0, cardIndex).clear(); // Xóa các lá đã chia
            return hands;
        }


    public Cards dealOneCard() {
            if (!cards.isEmpty()) {
                return cards.remove(0); // Rút lá đầu tiên từ bộ bài
            }
            throw new IllegalStateException("Bộ bài đã hết!");
        }



        // Kiểm tra số lượng bài còn lại
        public int remainingCards() {
            return cards.size();
        }

        @Override
        public String toString() {
            return cards.toString();
        }
    }


