module com.example.question0_3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires com.google.gson;

    opens com.example.question0_3.model to com.google.gson, javafx.base;
    opens com.example.question0_3.Enum to com.google.gson;
    opens com.example.question0_3 to javafx.fxml;
    opens com.example.question0_3.view to javafx.fxml;

    exports com.example.question0_3;
}