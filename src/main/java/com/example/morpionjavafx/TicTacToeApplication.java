package com.example.morpionjavafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.IOException;

public class TicTacToeApplication extends Application {
    private char currentPlayer = 'X';
    private final Cell[][] board = new Cell[3][3];
    private final Label statusLabel = new Label("X's turn to play");

    @Override
    public void start(Stage stage) throws IOException {
        GridPane pane = new GridPane();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = new Cell();
                pane.add(board[i][j], i, j);
            }
        }

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(pane);
        borderPane.setBottom(statusLabel);

        Scene scene = new Scene(borderPane, 450, 450);
        stage.setTitle("Tic Tac Toe by Pierre Marquet & Dylan Gil Amaro");
        stage.setScene(scene);
        stage.show();
    }

    public Boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].getPlayer() == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public Boolean hasWon(char player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0].getPlayer() == player && board[i][1].getPlayer() == player
                    && board[i][2].getPlayer() == player) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (board[0][i].getPlayer() == player && board[1][i].getPlayer() == player
                    && board[2][i].getPlayer() == player) {
                return true;
            }
        }

        if (board[0][0].getPlayer() == player && board[1][1].getPlayer() == player
                && board[2][2].getPlayer() == player) {
            return true;
        }

        if (board[0][2].getPlayer() == player && board[1][1].getPlayer() == player
                && board[2][0].getPlayer() == player) {
            return true;
        }

        return false;
    }

    public class Cell extends Pane {
        private char player = ' ';

        private Cell() {
            setStyle("-fx-border-color: black");
            this.setPrefSize(400, 400);
            this.setOnMouseClicked(e -> handleMouseClick());
        }

        private void handleMouseClick() {
            if (player == ' ' && currentPlayer != ' ') {
                setPlayer(currentPlayer);

                if (hasWon(currentPlayer)) {
                    statusLabel.setText(currentPlayer + " won! The game is over");
                    currentPlayer = ' ';
                } else if (isBoardFull()) {
                    statusLabel.setText("Draw! The game is over");
                    currentPlayer = ' ';
                } else {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                    statusLabel.setText(currentPlayer + "'s turn");
                }
            }
        }

        public char getPlayer() {
            return player;
        }

        public void setPlayer(char player) {
            this.player = player;

            if (player == 'X') {
                Line line1 = new Line(10, 10, this.getWidth() - 10, this.getHeight() - 10);
                line1.endXProperty().bind(this.widthProperty().subtract(10));
                line1.endYProperty().bind(this.heightProperty().subtract(10));
                line1.setStrokeWidth(10);


                Line line2 = new Line(10, this.getHeight() - 10, this.getWidth() - 10, 10);
                line2.startYProperty().bind(this.heightProperty().subtract(10));
                line2.endXProperty().bind(this.widthProperty().subtract(10));
                line2.setStrokeWidth(10);

                getChildren().addAll(line1, line2);

            } else if (player == 'O') {

                Ellipse ellipse = new Ellipse(this.getWidth() / 2, this.getHeight() / 2, this.getWidth() / 2 - 10,
                        this.getHeight() / 2 - 10);
                ellipse.centerXProperty().bind(this.widthProperty().divide(2));
                ellipse.centerYProperty().bind(this.heightProperty().divide(2));
                ellipse.radiusXProperty().bind(this.widthProperty().divide(2).subtract(10));
                ellipse.radiusYProperty().bind(this.heightProperty().divide(2).subtract(10));
                ellipse.setStrokeWidth(10);
                ellipse.setStroke(Color.BLACK);
                ellipse.setFill(Color.TRANSPARENT);


                getChildren().add(ellipse);
            }
        }
    }
    
    public static void main(String[] args) {
        launch();
    }
}