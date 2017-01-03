package main.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * Created by mateu on 03.01.2017.
 */
public class RootLayoutController {

    private BeesFX mainApp;

    public void setMainApp(BeesFX mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void openInputDataSettings(ActionEvent actionEvent) {
        mainApp.showInputDataSettings();
    }

    @FXML
    public void openLineChartSettings(ActionEvent actionEvent) {
        mainApp.showLineChartSettings();
    }
}
