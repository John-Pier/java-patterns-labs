module com.johnpier.lab35observer {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.johnpier.lab35observer to javafx.fxml;
    exports com.johnpier.lab35observer;
}