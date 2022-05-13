package com.johnpier.lab41mvc;

import com.johnpier.lab41mvc.models.*;
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

    private AppModel appModel;

    @FXML
    protected void onAddCell() {
        tableView.getItems().add(new Row(0));
    }

    public void init() {
        appModel = new AppModel(this.tableView);
        initChart();
        initTableValues();
    }

    private void initChart() {
        XYChart.Series<Double, Double> series = new XYChart.Series<>();
        series.setName("Cubic fn");
        lineChart.getData().add(series);
    }

    private void initTableValues() {
        xColumn.setEditable(true);

        this.appModel.setInitialRows();

        xColumn.setCellValueFactory(new PropertyValueFactory<>(appModel.getRows().get(0).xProperty().getName()));
        yColumn.setCellValueFactory(new PropertyValueFactory<>(appModel.getRows().get(0).yProperty().getName()));

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
            var row = appModel.getRows().get(rowNumber);

            if (value != null && !Double.isNaN(value)) {
                row.setX(value);
            } else {
                appModel.getRows().remove(row);
            }

            this.updateChart();
        });
        this.updateChart();
    }

    private void updateChart() {
        XYChart.Series<Double, Double> series = lineChart.getData().get(0);
        series.getData().clear();
        series.getData().addAll(appModel.getChartData());
    }
}