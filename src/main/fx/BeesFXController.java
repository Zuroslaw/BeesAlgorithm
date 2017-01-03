package main.fx;

import algorithm.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Item;
import model.Parcel;
import util.DAO;
import util.Timer;

import java.util.*;

/**
 * Created by mateu on 02.01.2017.
 */
public class BeesFXController {

    @FXML
    public ChoiceBox choiceAlgorithm;
    @FXML
    public ChoiceBox choiceHeuristics;
    @FXML
    public TextField textBeesDistribution;
    @FXML
    public TextField textSourcesToDump;
    @FXML
    public TextField textSourcesToDumpCandidates;
    @FXML
    public TextField textIterations;
    @FXML
    public LineChart algorithmChart;
    @FXML
    public Label labelResult;
    @FXML
    public Label labelTime;
    @FXML
    public Label labelAverage;
    @FXML
    public TextField textNumOfRuns;

    static boolean showMean = true;
    static boolean showBest = true;

    private String[] algorithmNames = {"Podstawowy", "Losowe porzucanie"};
    private String[] heuristicNames = {"Podstawowa", "Losowa zamiana"};

    @FXML
    public void runAlgorithm(ActionEvent actionEvent) throws Exception{
        algorithmChart.getData().clear();
        BeesAlgorithmBase algo = null;
        ParcelHeuristics ph = null;

        /*
         * DAO potem powinno czytac z dowolnego pliku, na razie zahardkodowane
         */
        DAO dao = DAO.getInstance();
        dao.readParcel();
        ArrayList<Item> fullItemList = dao.readItems();

        int maxIter = Integer.parseInt(textIterations.getText());

        String[] beesDistributionStrings = textBeesDistribution.getText().replaceAll("\\s", "").split(",");
        int[] beesDistribution = new int[beesDistributionStrings.length];
        for (int i = 0; i < beesDistribution.length; i++) {
            beesDistribution[i] = Integer.parseInt(beesDistributionStrings[i]);
        }
        int sourcesToDump = Integer.parseInt(textSourcesToDump.getText());
        int sourcesToDumpCandidates = Integer.parseInt(textSourcesToDumpCandidates.getText());

        switch ((String) choiceHeuristics.getSelectionModel().getSelectedItem()) {
            case "Podstawowa":
                ph = new ParcelHeuristicsNew();
                break;
            case "Losowa zamiana":
                ph = new ParcelHeuristicsRandomReplacement();
        }
        switch ((String) choiceAlgorithm.getSelectionModel().getSelectedItem()) {
            case "Podstawowy":
                algo = new BeesAlgorithmImpl(new Parcel(), fullItemList, maxIter, beesDistribution, sourcesToDump, ph);
                break;
            case "Losowe porzucanie":
                algo = new BeesAlgorithmRandomDump(new Parcel(), fullItemList, maxIter, beesDistribution, sourcesToDumpCandidates, sourcesToDump, ph);
        }
        int numOfRuns = Integer.parseInt(textNumOfRuns.getText());
        ArrayList<Double> resultList = new ArrayList<>(numOfRuns);
        ArrayList<Double> timeList = new ArrayList<>(numOfRuns);

        for (int i = 0; i < numOfRuns; i++) {
            Timer.tic();
            Parcel p = algo.run();
            timeList.add(Timer.toc());
            resultList.add(p.getCurrentQuality());
            populateChart(algo, i + 1);
        }
        /*
         * To ponizej jest straszne i pewnie cos jest nie tak ale w miare dziala
         */
        double minValue = showMean ? Collections.min(algo.meanOfRun) : Collections.min(algo.bestOfRun);
        double maxValue = showBest ? Collections.max(resultList) : Collections.max(algo.meanOfRun);
        algorithmChart.getYAxis().setAutoRanging(false);
        ((NumberAxis) algorithmChart.getYAxis()).setLowerBound(minValue);
        ((NumberAxis) algorithmChart.getYAxis()).setUpperBound(maxValue);
        labelResult.setText(Double.toString(Collections.max(resultList)));
        labelAverage.setText(Double.toString(resultList.stream().mapToDouble(Double::doubleValue).average().getAsDouble()));
        labelTime.setText(Double.toString(timeList.stream().mapToDouble(Double::doubleValue).average().getAsDouble()) + " s");
    }

    @FXML
    public void algorithmChanged() {
        if (((String) choiceAlgorithm.getSelectionModel().getSelectedItem()).equals("Losowe porzucanie"))
            textSourcesToDumpCandidates.setDisable(false);
        else
            textSourcesToDumpCandidates.setDisable(true);
    }

    private void populateChart(BeesAlgorithmBase algo, int num) {

        ArrayList<Double> bestOfRun = algo.bestOfRun;
        ArrayList<Double> meanOfRun = algo.meanOfRun;

        if (showMean) {
            XYChart.Series meanSeries = new XYChart.Series();
            meanSeries.setName("Srednia" + Integer.toString(num));
            Double currentMean = 0d;
            for (int i = 0; i < meanOfRun.size(); i++) {
                if (!meanOfRun.get(i).equals(currentMean) || i == meanOfRun.size() - 1) {
                    meanSeries.getData().add(new XYChart.Data(i+1, meanOfRun.get(i)));
                    currentMean = bestOfRun.get(i);
                }
            }
            algorithmChart.getData().add(meanSeries);
        }

        if (showBest) {
            XYChart.Series bestSeries = new XYChart.Series();
            bestSeries.setName("Najlepsza" + Integer.toString(num));
            Double currentBest = 0d;
            for (int i = 0; i < bestOfRun.size(); i++) {
                if (!bestOfRun.get(i).equals(currentBest) || i == bestOfRun.size() - 1) {
                    bestSeries.getData().add(new XYChart.Data(i+1, bestOfRun.get(i)));
                    currentBest = bestOfRun.get(i);
                }
            }
            algorithmChart.getData().add(bestSeries);
        }

    }

    @FXML
    public void initialize() throws Exception {

        textSourcesToDumpCandidates.setDisable(true);
        choiceAlgorithm.setItems(FXCollections.observableArrayList(algorithmNames));
        choiceAlgorithm.getSelectionModel().selectFirst();
        choiceHeuristics.setItems(FXCollections.observableArrayList(heuristicNames));
        choiceHeuristics.getSelectionModel().selectFirst();
        textBeesDistribution.setText("70, 50, 30, 20, 15, 10, 8, 7, 6, 5, 4, 3, 2");
        textSourcesToDump.setText("5");
        textSourcesToDumpCandidates.setText("0");
        textIterations.setText("500");
        textNumOfRuns.setText("1");

        choiceAlgorithm.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> algorithmChanged());

        algorithmChart.getXAxis().setLabel("Iteracja");
        ((NumberAxis)algorithmChart.getXAxis()).setTickUnit(50);
        algorithmChart.getYAxis().setLabel("Wartosc");
        algorithmChart.setAxisSortingPolicy(LineChart.SortingPolicy.X_AXIS);
        algorithmChart.setCreateSymbols(false);
    }

}
