module com.example{
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.ikonli.core; // For Ikonli core
    requires org.kordamp.ikonli.javafx; // For JavaFX Ikonli



    opens com.example to javafx.fxml;
    exports com.example;
}
