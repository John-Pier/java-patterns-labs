package com.johnpier.lab41mvc;

import com.johnpier.lab41mvc.models.CubicFunction;
import javafx.beans.property.*;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.util.converter.*;

import java.text.Format;

public class MvcController {
    @FXML
    private TableView<Row> tableView;
    @FXML
    private TableColumn<Row, Double> xColumn;
    @FXML
    private TableColumn<Row, Double> yColumn;

    private final ObservableList<Row> rows = FXCollections.observableArrayList(
            new Row(0, 3),
            new Row(1, 4),
            new Row(2, 5)
    );

    @FXML
    protected void onAddCell() {
        tableView.getItems().add(new Row(0, 0));
    }

    public void initTableValues() {
        tableView.setItems(rows);
        xColumn.setEditable(true);

        xColumn.setCellValueFactory(new PropertyValueFactory<>(rows.get(0).getXProperty().getName()));
        yColumn.setCellValueFactory(new PropertyValueFactory<>(rows.get(0).getYProperty().getName()));

        xColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        xColumn.setOnEditCommit((TableColumn.CellEditEvent<Row, Double> editEvent) -> {
            TablePosition<Row, Double> position = editEvent.getTablePosition();

            var value = editEvent.getNewValue();
            var rowNumber = position.getRow();
            var row = tableView.getItems().get(rowNumber);

            if (value != null && !Double.isNaN(value)) {

                row.setX(value);

                if (rowNumber >= rows.size()) {
                    rows.add(row);
                }

                tableView.setItems(rows);
            } else {
                rows.remove(row);
            }
        });
    }

    public static class Row {
        private DoubleProperty xProperty;
        private DoubleProperty yProperty;

        public Row(double x, double y) {
            setX(x);
            setY(y);
        }

        public DoubleProperty getXProperty() {
            if (xProperty == null) {
                xProperty = new SimpleDoubleProperty(this, "x");
            }
            return xProperty;
        }

        public DoubleProperty getYProperty() {
            if (yProperty == null) {
                yProperty = new SimpleDoubleProperty(this, "y");
            }
            return yProperty;
        }

        public double getX() {
            return getXProperty().doubleValue();
        }

        public void setX(double x) {
            getXProperty().setValue(x);
            calculateY();
        }

        public double getY() {
            return getYProperty().doubleValue();
        }

        public void setY(double y) {
            getYProperty().setValue(y);
        }

        private void calculateY() {
            setY(CubicFunction.getInstance().calculateY(getX()));
        }
    }
}