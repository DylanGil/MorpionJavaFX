package com.example.morpionjavafx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;

public class TicTacToeController {
    private char currentPlayer = 'X';
    private final Cell[][] board = new Cell[3][3];

    @FXML
    private GridPane pane;

    @FXML
    private Button resetButton;

    @FXML
    private Label statusLabel;

    public void initialize() {
        pane.setStyle("-fx-background-color: white");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = new Cell();
                pane.add(board[i][j], i, j);
            }
        }

        statusLabel.setText("X's turn to play");
        resetButton.setOnAction(e -> resetGame());
    }

    @FXML
    private void resetGame() {
        currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
        pane.setStyle("-fx-background-color: white");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j].setPlayer(' ');
                board[i][j].getChildren().clear();
                board[i][j].setStyle("-fx-border-color: black");
            }
        }

        // Reset the status label
        statusLabel.setText(currentPlayer + "'s turn to play");
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
                    pane.setStyle("-fx-background-color: red");
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
                highlightWinningCells(board[i][0], board[i][1], board[i][2]);
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (board[0][i].getPlayer() == player && board[1][i].getPlayer() == player
                    && board[2][i].getPlayer() == player) {
                highlightWinningCells(board[0][i], board[1][i], board[2][i]);
                return true;
            }
        }

        if (board[0][0].getPlayer() == player && board[1][1].getPlayer() == player
                && board[2][2].getPlayer() == player) {
            highlightWinningCells(board[0][0], board[1][1], board[2][2]);
            return true;
        }

        if (board[0][2].getPlayer() == player && board[1][1].getPlayer() == player
                && board[2][0].getPlayer() == player) {
            highlightWinningCells(board[0][2], board[1][1], board[2][0]);
            return true;
        }

        return false;
    }

    private void highlightWinningCells(Cell... cells) {
        for (Cell cell : cells) {
            cell.setStyle("-fx-background-color: #00ff19");
        }
    }
}
