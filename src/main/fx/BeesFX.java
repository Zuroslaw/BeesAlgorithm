package main.fx;/**
 * Created by mateu on 20.12.2016.
 */

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
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
    }

    private void initMainView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(BeesFX.class.getResource("BeesFX.fxml"));

        this.rootLayout.setCenter((AnchorPane) loader.load());

        //BeesFXController controller = loader.getController();
        //controller.setMainApp(this);
    }
}
