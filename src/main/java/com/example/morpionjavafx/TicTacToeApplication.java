package com.example.morpionjavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TicTacToeApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("tictactoe-view.fxml"));
        Scene scene = new Scene(loader.load(), 540, 600);
        stage.setTitle("Tic Tac Toe by Pierre Marquet & Dylan Gil Amaro");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
