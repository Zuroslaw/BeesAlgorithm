package main.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

/**
 * Created by mateu on 03.01.2017.
 */
public class LineChartSettingsController {

    @FXML
    public CheckBox checkboxShowBest;
    @FXML
    public CheckBox checkboxShowMean;

    private Stage stage;

    @FXML
    public void okPressed(ActionEvent actionEvent) {
        BeesFXController.showBest = checkboxShowBest.isSelected();
        BeesFXController.showMean = checkboxShowMean.isSelected();
        stage.close();
    }

    @FXML
    public void initialize() {
        checkboxShowBest.setSelected(BeesFXController.showBest);
        checkboxShowMean.setSelected(BeesFXController.showMean);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
