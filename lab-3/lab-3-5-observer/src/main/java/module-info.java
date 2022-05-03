module com.johnpier.lab35observer {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.johnpier.lab35observer to javafx.fxml;
    exports com.johnpier.lab35observer;
}