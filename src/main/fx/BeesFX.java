package main.fx;/**
 * Created by mateu on 20.12.2016.
 */

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class BeesFX extends Application {

    private BorderPane rootLayout;
    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        initRootLayout();
        initMainView();
    }

    private void initRootLayout() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(BeesFX.class.getResource("RootLayout.fxml"));
        this.rootLayout = (BorderPane) loader.load();

        primaryStage.setScene(new Scene(rootLayout));
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                /*
                    W razie gdyby bylo potrzebne zamkniecie czegos potem
                 */
            }
        });
        primaryStage.show();
        primaryStage.setMinHeight(primaryStage.getHeight());
        primaryStage.setMinWidth(primaryStage.getWidth());

        RootLayoutController c = loader.getController();
        c.setMainApp(this);
    }

    private void initMainView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(BeesFX.class.getResource("BeesFX.fxml"));

        this.rootLayout.setCenter((AnchorPane) loader.load());
    }

    public void showInputDataSettings() {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(BeesFX.class.getResource("InputDataSettings.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();

            Stage inputDataSettingsDialog = new Stage();
            inputDataSettingsDialog.setTitle("Ustawienia danych wejściowych");
            inputDataSettingsDialog.initModality(Modality.WINDOW_MODAL);
            inputDataSettingsDialog.initOwner(primaryStage);
            Scene inputDataSettingsDialogScene = new Scene(pane);
            inputDataSettingsDialog.setScene(inputDataSettingsDialogScene);

            InputDataSettingsController c = loader.getController();
            c.setStage(inputDataSettingsDialog);
            inputDataSettingsDialog.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showLineChartSettings() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(BeesFX.class.getResource("LineChartSettings.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();

            Stage LineChartSettingsDialog = new Stage();
            LineChartSettingsDialog.setTitle("Ustawienia wykresu");
            LineChartSettingsDialog.initModality(Modality.WINDOW_MODAL);
            LineChartSettingsDialog.initOwner(primaryStage);
            Scene LineChartSettingsDialogScene = new Scene(pane);
            LineChartSettingsDialog.setScene(LineChartSettingsDialogScene);

            LineChartSettingsController c = loader.getController();
            c.setStage(LineChartSettingsDialog);
            LineChartSettingsDialog.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
