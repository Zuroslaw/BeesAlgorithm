package main.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.DAO;

/**
 * Created by mateu on 03.01.2017.
 */
public class InputDataSettingsController {

    @FXML
    public TextField textFileName;

    private Stage stage;

    @FXML
    public void okPressed(ActionEvent actionEvent) {
        DAO.setFileName(textFileName.getText());
        stage.close();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
