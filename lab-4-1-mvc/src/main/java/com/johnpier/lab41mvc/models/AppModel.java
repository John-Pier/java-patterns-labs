package com.johnpier.lab41mvc.models;

import javafx.collections.*;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableView;

import java.util.*;

public class AppModel {

    private final TableView<Row> tableView;

    public AppModel(TableView<Row> tableView) {
        this.tableView = tableView;
    }

    private final ObservableList<Row> rows = FXCollections.observableArrayList(
            new Row(0),
            new Row(-5),
            new Row(-5.1),
            new Row(-3),
            new Row(1.9),
            new Row(1.5),
            new Row(1)
    );

    public void setInitialRows() {
        tableView.setItems(rows);
    }

    public ObservableList<Row> getRows() {
        return tableView.getItems();
    }

    public List<XYChart.Data<Double, Double>> getChartData() {
        var list = new ArrayList<XYChart.Data<Double, Double>>();
        for (Row row : tableView.getItems()) {
            list.add(new XYChart.Data<>(row.getX(), row.getY()));
        }
        return list;
    }
}
