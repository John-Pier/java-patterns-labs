module com.johnpier.lab23facade {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.johnpier.lab23facade to javafx.fxml;
    exports com.johnpier.lab23facade;
    exports com.johnpier.lab23facade.facade;
    opens com.johnpier.lab23facade.facade to javafx.fxml;
}