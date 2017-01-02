package main.fx;

import algorithm.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import model.Item;
import model.Parcel;
import util.DAO;
import util.Timer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

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

    private String[] algorithmNames = {"Podstawowy", "Losowe porzucanie"};
    private String[] heuristicNames = {"Podstawowa", "Losowa zamiana"};

    @FXML
    public void runAlgorithm(ActionEvent actionEvent) throws Exception{
        BeesAlgorithmBase algo = null;
        ParcelHeuristics ph = null;

        /*
         * DAO potem powinno czytac z dowolnego pliku, na razie zahardkodowane
         */
        DAO dao = DAO.getInstance();
        dao.setFileName("input3.txt");
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
            case "Losowa zmiana":
                ph = new ParcelHeuristicsRandomReplacement();
        }
        switch ((String) choiceAlgorithm.getSelectionModel().getSelectedItem()) {
            case "Podstawowy":
                algo = new BeesAlgorithmImpl(new Parcel(), fullItemList, maxIter, beesDistribution, sourcesToDump, ph);
                break;
            case "Losowe porzucanie":
                algo = new BeesAlgorithmRandomDump(new Parcel(), fullItemList, maxIter, beesDistribution, sourcesToDumpCandidates, sourcesToDump, ph);
        }
        Timer.tic();
        Parcel p = algo.run();
        Timer.toc();
        System.out.println(p);
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
    }
}
