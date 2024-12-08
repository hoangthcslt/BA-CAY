package com.example.ba_cay_project_2;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class BaCayGameApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Tạo ảnh nền
        Image backgroundImage = new Image(getClass().getResource("/anh_nen/nen1.jpg").toExternalForm());
        BackgroundImage background = new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, new BackgroundSize(500, 500, true, true, true, true));
        Pane root = new Pane();
        root.setBackground(new Background(background));

        // Nhạc nền
        Media music = new Media(getClass().getResource("/anh_nen/nhac_nen.mp3").toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(music);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Lặp lại vô hạn
        mediaPlayer.play();

        // Tạo tiêu đề
        Text title = new Text("_CARDS GAME_");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        title.setFill(Color.WHITE);
        title.setStroke(Color.BLACK);
        title.setStrokeWidth(2);

        Text subtitle = new Text("NHÓM 1");
        subtitle.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        subtitle.setFill(Color.WHITE);
        subtitle.setStroke(Color.BLACK);
        subtitle.setStrokeWidth(2);

        VBox titleBox = new VBox(10, title, subtitle);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setLayoutX(212); // Tùy chỉnh vị trí tiêu đề
        titleBox.setLayoutY(50);

        // Tạo các nút
        Button button1 = new Button("Chơi Ba Cây");

        button1.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        button1.setStyle("-fx-background-color: #008CBA; -fx-text-fill: white;");


        VBox buttonBox = new VBox(20, button1);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setLayoutX(320); // Tùy chỉnh vị trí nút
        buttonBox.setLayoutY(250);

        // Xử lý sự kiện nút
        button1.setOnAction(e -> showGameModeSelection(primaryStage)); // Chuyển sang Bước 2


        // Thêm các phần tử vào giao diện
        root.getChildren().addAll(titleBox, buttonBox);

        // Hiển thị cửa sổ chính
        Scene scene = new Scene(root, 800, 600); // Kích thước cửa sổ
        primaryStage.setTitle("Game Bài 3 Cây");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Chuyển sang giao diện chọn chế độ chơi
    private void showGameModeSelection(Stage primaryStage) {
        // Tạo root layout cho Bước 2
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);

        // Tạo các nút chế độ chơi
        Button play1 = new Button("CHƠI 1 NGƯỜI");
        Button play2 = new Button("CHƠI 2 NGƯỜI");
        Button play3 = new Button("CHƠI 3 NGƯỜI");
        Button play4 = new Button("CHƠI 4 NGƯỜI");

        play1.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        play2.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        play3.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        play4.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        play1.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        play2.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        play3.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        play4.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");

        // Thêm sự kiện cho từng nút
        play1.setOnAction(e -> showPlayerInputDialog(primaryStage, 1));
        play2.setOnAction(e -> showPlayerInputDialog(primaryStage, 2));
        play3.setOnAction(e -> showPlayerInputDialog(primaryStage, 3));
        play4.setOnAction(e -> showPlayerInputDialog(primaryStage, 4));

        root.getChildren().addAll(play1, play2, play3, play4);

        // Tạo scene mới và hiển thị
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
    }

    // Hiển thị hộp thoại nhập tên người chơi
    private void showPlayerInputDialog(Stage primaryStage, int numPlayers) {
        // GridPane để tổ chức input
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);

        List<TextField> playerNameInputs = new ArrayList<>();

        // Tạo các ô nhập cho từng người chơi
        for (int i = 0; i < numPlayers; i++) {
            Label label = new Label("Tên người chơi " + (i + 1) + ":");
            label.setFont(Font.font("Arial", FontWeight.BOLD, 16));
            TextField textField = new TextField();
            playerNameInputs.add(textField);

            grid.add(label, 0, i);
            grid.add(textField, 1, i);
        }

        // Nút "BẮT ĐẦU"
        Button startButton = new Button("BẮT ĐẦU");
        startButton.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        startButton.setStyle("-fx-background-color: #FF5722; -fx-text-fill: white;");
        grid.add(startButton, 0, numPlayers, 2, 1);
        GridPane.setHalignment(startButton, HPos.CENTER);

        // Xử lý sự kiện bấm nút "BẮT ĐẦU"
        startButton.setOnAction(e -> {
            // Lưu tên người chơi
            List<String> playerNames = new ArrayList<>();
            for (TextField textField : playerNameInputs) {
                String name = textField.getText().trim();
                if (name.isEmpty()) {
                    // Hiển thị cảnh báo nếu không nhập tên
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Cảnh báo");
                    alert.setHeaderText("Thiếu tên người chơi!");
                    alert.setContentText("Vui lòng nhập tên cho tất cả người chơi.");
                    alert.showAndWait();
                    return;
                }
                playerNames.add(name);
            }

            // Chuyển sang giao diện Bước 3
            startGame(primaryStage, playerNames);
        });

        // Tạo scene và hiển thị
        Scene scene = new Scene(grid, 600, 400);
        primaryStage.setScene(scene);
    }
    //Tạo hiệu ứng chia bài
    private void dealCards(Stage primaryStage, List<String> playerNames, Pane gamePane) {
        Ba_Cay_Logic gameLogic = new Ba_Cay_Logic();

        // Tạo người chơi trong logic game
        for (String name : playerNames) {
            gameLogic.addPlayer(new Player(name));
        }

        // Tạo bộ bài
        Deck deck = new Deck();
        deck.shuffle();

        // Số người chơi
        int numPlayers = playerNames.size();

        // Danh sách vị trí tương ứng với từng người chơi
        List<Double[]> playerPositions = new ArrayList<>();
        playerPositions.add(new Double[]{100.0, 100.0});  // Vị trí người chơi 1
        playerPositions.add(new Double[]{600.0, 100.0});  // Vị trí người chơi 2
        if (numPlayers > 2) playerPositions.add(new Double[]{100.0, 400.0}); // Người chơi 3
        if (numPlayers > 3) playerPositions.add(new Double[]{600.0, 400.0}); // Người chơi 4

        // Animation timeline để thực hiện hiệu ứng chia bài
        Timeline timeline = new Timeline();

        int totalCards = numPlayers * 3; // Tổng số lá bài cần chia

        for (int i = 0; i < totalCards; i++) {
            int playerIndex = i % numPlayers; // Chọn người chơi theo vòng tròn
            Double[] targetPosition = playerPositions.get(playerIndex);

            // Tạo lá bài
            Cards card = deck.dealOneCard();
            String cardImagePath = "/anh_bai/" + card.getRank().toLowerCase() + "_" + card.getSuit().toLowerCase() + ".png";
            ImageView cardView = new ImageView(new Image(getClass().getResourceAsStream(cardImagePath)));
            cardView.setFitWidth(100);
            cardView.setFitHeight(150);

            // Đặt vị trí lá bài ở giữa màn hình
            cardView.setLayoutX(350);
            cardView.setLayoutY(280);

            gamePane.getChildren().add(cardView);

            // Animation di chuyển lá bài đến vị trí người chơi
            KeyValue kvX = new KeyValue(cardView.layoutXProperty(), targetPosition[0]);
            KeyValue kvY = new KeyValue(cardView.layoutYProperty(), targetPosition[1]);
            KeyFrame kf = new KeyFrame(Duration.seconds(0.5 + i * 0.2), kvX, kvY);
            timeline.getKeyFrames().add(kf);

            // Sau khi di chuyển, thêm lá bài vào tay người chơi
            int currentPlayer = playerIndex;
            timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.5 + i * 0.2), e -> {
                gameLogic.getPlayers().get(currentPlayer).addCard(card);
            }));
        }

        // Xử lý sau khi chia bài xong
        timeline.setOnFinished(e -> {
            // Hiển thị nút "OK, PLAY!"
            Button playButton = new Button("OK, PLAY!");
            playButton.setFont(Font.font("Arial", FontWeight.BOLD, 24));
            playButton.setStyle("-fx-background-color: #FFC107; -fx-text-fill: black;");
            playButton.setLayoutX(350);
            playButton.setLayoutY(280);

            // Xử lý sự kiện khi nhấn nút "OK, PLAY!"
            playButton.setOnAction(event -> showResults(primaryStage, gameLogic));
            gamePane.getChildren().add(playButton);
        });

        // Bắt đầu hiệu ứng
        timeline.play();
    }


    private void showResults(Stage primaryStage, Ba_Cay_Logic gameLogic) {
        Pane resultPane = new Pane();
        resultPane.setPrefSize(800, 600);

        // Đặt ảnh nền
        ImageView background = new ImageView(new Image(getClass().getResourceAsStream("/anh_nen/nen4.jpg")));
        background.setFitWidth(800);
        background.setFitHeight(600);
        resultPane.getChildren().add(background);

        // Tính điểm cho tất cả người chơi
        List<Player> players = gameLogic.getPlayers();
        for (Player player : players) {
            player.calculatePoints();
        }

        // Hiển thị thông tin người chơi
        VBox resultBox = new VBox(20);
        resultBox.setLayoutX(240);
        resultBox.setLayoutY(50);
        resultBox.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8); -fx-padding: 20px; -fx-border-radius: 10; -fx-background-radius: 10;");

        Label resultTitle = new Label("KẾT QUẢ TRÒ CHƠI");
        resultTitle.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        resultTitle.setStyle("-fx-text-fill: black;");
        resultBox.getChildren().add(resultTitle);

        // Tìm điểm cao nhất
        int maxPoints = players.stream().mapToInt(Player::getTotalPoints).max().orElse(0);

        // Hiển thị thông tin từng người chơi
        for (Player player : players) {
            Label playerInfo = new Label(
                    player.getName() + " | Bài: " + player.getHand() + " | Điểm: " + player.getTotalPoints()
            );
            playerInfo.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
            playerInfo.setStyle("-fx-text-fill: black;");
            resultBox.getChildren().add(playerInfo);
        }

        // Hiển thị người chiến thắng
        List<Player> winners = players.stream()
                .filter(p -> p.getTotalPoints() == maxPoints)
                .toList();

        String winnerText = "Người chiến thắng: ";
        if (winners.size() == 1) {
            winnerText += winners.get(0).getName();
        } else {
            winnerText += winners.stream().map(Player::getName).reduce((a, b) -> a + ", " + b).orElse("");
        }

        Label winnerLabel = new Label(winnerText);
        winnerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        winnerLabel.setStyle("-fx-text-fill: green;");
        resultBox.getChildren().add(winnerLabel);

        // Thêm nút thoát hoặc chơi lại
        Button exitButton = new Button("Kết Thúc");
        exitButton.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        exitButton.setStyle("-fx-background-color: #FF5733; -fx-text-fill: white;");
        exitButton.setOnAction(e -> primaryStage.close());

        Button replayButton = new Button("Chơi Lại");
        replayButton.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        replayButton.setStyle("-fx-background-color: #FFC107; -fx-text-fill: black;");
        replayButton.setOnAction(e -> startGame(primaryStage, players.stream().map(Player::getName).toList()));

        HBox buttonsBox = new HBox(20, replayButton, exitButton);
        buttonsBox.setAlignment(Pos.CENTER);
        buttonsBox.setLayoutX(250);
        buttonsBox.setLayoutY(500);

        resultPane.getChildren().addAll(resultBox, buttonsBox);

        // Hiển thị giao diện kết quả
        Scene resultScene = new Scene(resultPane);
        primaryStage.setScene(resultScene);
        primaryStage.show();
    }




    // Hàm chuyển sang giao diện bước 3 (sẽ triển khai ở bước tiếp theo)
    private void startGame(Stage primaryStage, List<String> playerNames) {
        // Bổ sung "AI_NOOD" nếu chỉ có 1 người chơi
        if (playerNames.size() == 1) {
            playerNames.add("AI_NOOD");
        }

        // Tạo root layout cho giao diện chơi game
        Pane root = new Pane();

        // Cài đặt ảnh nền
        Image backgroundImage = new Image(getClass().getResourceAsStream("/anh_nen/nen3.png"));
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(800, 600, false, false, false, false)
        );
        root.setBackground(new Background(background));

        // Hiển thị tên người chơi ở các góc màn hình
        int numPlayers = playerNames.size();
        List<Label> playerLabels = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            Label playerLabel = new Label(playerNames.get(i));
            playerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
            playerLabel.setTextFill(Color.WHITE);

            // Vị trí tương ứng cho từng người chơi
            if (i == 0) {
                playerLabel.setLayoutX(50);
                playerLabel.setLayoutY(50);
            } else if (i == 1) {
                playerLabel.setLayoutX(650);
                playerLabel.setLayoutY(50);
            } else if (i == 2) {
                playerLabel.setLayoutX(50);
                playerLabel.setLayoutY(500);
            } else if (i == 3) {
                playerLabel.setLayoutX(650);
                playerLabel.setLayoutY(500);
            }

            playerLabels.add(playerLabel);
            root.getChildren().add(playerLabel);
        }

        // Nút "CHIA BÀI"
        Button chiaBaiButton = new Button("CHIA BÀI");
        chiaBaiButton.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        chiaBaiButton.setStyle("-fx-background-color: #FFC107; -fx-text-fill: black;");
        chiaBaiButton.setLayoutX(350);
        chiaBaiButton.setLayoutY(280);

       // Xử lý sự kiện nhấn nút "CHIA BÀI"
         chiaBaiButton.setOnAction(e -> dealCards(primaryStage, playerNames, root));
         root.getChildren().add(chiaBaiButton);


        // Tạo scene và hiển thị
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
