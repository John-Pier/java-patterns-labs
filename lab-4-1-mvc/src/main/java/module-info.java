module com.johnpier.lab41mvc {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.johnpier.lab41mvc to javafx.fxml;
    exports com.johnpier.lab41mvc;
}