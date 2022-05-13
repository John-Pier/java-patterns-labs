package com.johnpier.lab41mvc;

import com.johnpier.lab41mvc.models.CubicFunction;
import javafx.beans.property.*;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.util.converter.*;

public class MvcController {
    @FXML
    private TableView<Row> tableView;
    @FXML
    private TableColumn<Row, Double> xColumn;
    @FXML
    private TableColumn<Row, Double> yColumn;
    @FXML
    private LineChart<Double, Double> lineChart;

    private final ObservableList<Row> rows = FXCollections.observableArrayList(
            new Row(0),
            new Row(-5),
            new Row(-5.1),
            new Row(-3),
            new Row(1.9),
            new Row(1.5),
            new Row(1)
    );

    @FXML
    protected void onAddCell() {
        tableView.getItems().add(new Row(0));
    }

    public void initTableValues() {
        tableView.setItems(rows);
        xColumn.setEditable(true);

        xColumn.setCellValueFactory(new PropertyValueFactory<>(rows.get(0).getXProperty().getName()));
        yColumn.setCellValueFactory(new PropertyValueFactory<>(rows.get(0).getYProperty().getName()));

        var convertor = new DoubleStringConverter() {
            @Override
            public Double fromString(String value) {
                try {
                    return super.fromString(value);
                } catch (Exception ignored) {
                }
                return null;
            }
        };

        xColumn.setCellFactory(TextFieldTableCell.forTableColumn(convertor));
        yColumn.setCellFactory(TextFieldTableCell.forTableColumn(convertor));

        xColumn.setOnEditCommit((TableColumn.CellEditEvent<Row, Double> editEvent) -> {
            TablePosition<Row, Double> position = editEvent.getTablePosition();

            var value = editEvent.getNewValue();
            var rowNumber = position.getRow();
            var row = tableView.getItems().get(rowNumber);

            if (value != null && !Double.isNaN(value)) {
                row.setX(value);
            } else {
                tableView.getItems().remove(row);
            }

            this.updateChart();
        });
        this.updateChart();
    }

    public void initChart() {
        XYChart.Series<Double, Double> series = new XYChart.Series<>();
        series.setName("Cubic fn");
        lineChart.getData().add(series);
    }

    private void updateChart() {
        XYChart.Series<Double, Double> series = lineChart.getData().get(0);
        series.getData().clear();
        for (Row row : tableView.getItems()) {
            series.getData().add(new XYChart.Data<>(row.getX(), row.getY()));
        }
    }

    public static class Row {
        private DoubleProperty xProperty;
        private DoubleProperty yProperty;

        public Row(double x) {
            setX(x);
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