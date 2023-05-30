module com.example.morpionjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.morpionjavafx to javafx.fxml;
    exports com.example.morpionjavafx;
}