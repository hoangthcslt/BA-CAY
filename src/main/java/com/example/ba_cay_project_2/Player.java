package com.example.ba_cay_project_2;

import java.util.ArrayList;
import java.util.List;

public class Player {
        private String name; // Tên người chơi
        private List<Cards> hand; // Các lá bài trên tay
        private int totalPoints; // Tổng điểm

        public Player(String name) {
            this.name = name;
            this.hand = new ArrayList<>();
            this.totalPoints = 0;
        }

        // Lấy tên người chơi
        public String getName() {
            return name;
        }

        // Lấy tổng điểm của người chơi
        public int getTotalPoints() {
            return totalPoints;
        }

        // Thêm 1 lá bài vào tay
        public void addCard(Cards card) {
            hand.add(card);
        }
        // Thêm nhiều lá bài vào tay
        public void receiveCards(List<Cards> newCards) {
            hand.addAll(newCards);
        }


        // Tính điểm cho người chơi
        public void calculatePoints() {
            totalPoints = 0;
            for (Cards card : hand) {
                totalPoints += card.getPointValue();
            }
            totalPoints %= 10; // Chỉ lấy phần dư chia cho 10
        }

        // Xóa tất cả bài trên tay và đặt lại tổng điểm
        public void resetHand() {
            hand.clear();
            totalPoints = 0;
        }

        // Trả về thông tin người chơi (dùng để in kết quả)
        @Override
        public String toString() {
            StringBuilder handString = new StringBuilder();
            for (Cards card : hand) {
                handString.append(card.toString()).append(", ");
            }
            // Xóa dấu phẩy cuối cùng
            if (!handString.isEmpty()) handString.setLength(handString.length() - 2);

            return String.format("%s's hand: [%s] | Points: %d", name, handString, totalPoints);
        }

    // Lấy danh sách các lá bài hiện tại (nếu cần truy cập bên ngoài)
        public List<Cards> getHand() {
            return new ArrayList<>(hand); // Trả về bản sao để tránh chỉnh sửa trực tiếp
        }
    }


