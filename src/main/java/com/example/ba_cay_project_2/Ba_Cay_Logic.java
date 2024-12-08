package com.example.ba_cay_project_2;

import java.util.ArrayList;
import java.util.List;

public class Ba_Cay_Logic {
        private final Deck deck;
        private final List<Player> players;

        public Ba_Cay_Logic() {
            this.deck = new Deck();
            this.players = new ArrayList<>();
        }
        public List<Player> getPlayers() {
            return players;
        }

        // Thêm người chơi vào danh sách
        public void addPlayer(Player player) {
            players.add(player);
        }

        // Bắt đầu game
        public void startGame() {
            // Xào bài
            deck.shuffle();

            // Chia bài cho tất cả người chơi (3 lá mỗi người)
            List<List<Cards>> hands = deck.dealCards(players.size());
            for (int i = 0; i < players.size(); i++) {
                players.get(i).resetHand();  // Reset tay bài của người chơi trước khi chia
                for (Cards card : hands.get(i)) {
                    players.get(i).addCard(card); // Chia từng lá bài cho người chơi
                }
            }

            // Tính điểm cho tất cả người chơi
            calculatePointsForAll();
        }

        // Tính điểm cho tất cả người chơi
        private void calculatePointsForAll() {
            for (Player player : players) {
                player.calculatePoints();
            }
        }

        // Xác định người thắng (xử lý cả trường hợp HÒA)
        public List<Player> determineWinners() {
            List<Player> winners = new ArrayList<>();
            int maxPoints = Integer.MIN_VALUE;

            for (Player player : players) {
                int currentPoints = player.getTotalPoints();
                if (currentPoints > maxPoints) {
                    winners.clear();
                    winners.add(player);
                    maxPoints = currentPoints;
                } else if (currentPoints == maxPoints) {
                    winners.add(player);
                }
            }
            return winners;
        }


        // In kết quả game (nếu cần, có thể chuyển vào lớp khác)
        public void printGameResults() {
            System.out.println("Kết quả game:");
            for (Player player : players) {
                System.out.printf("%s: %d điểm%n", player.getName(), player.getTotalPoints());
            }

            List<Player> winners = determineWinners();
            if (winners.size() > 1) {
                System.out.println("Game HÒA giữa các người chơi:");
                for (Player winner : winners) {
                    System.out.println("- " + winner.getName());
                }
            } else {
                System.out.println("Winner: " + winners.get(0).getName());
            }
        }

}



