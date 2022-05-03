module com.johnpier.lab36state {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.johnpier.lab36state to javafx.fxml;
    exports com.johnpier.lab36state;
}