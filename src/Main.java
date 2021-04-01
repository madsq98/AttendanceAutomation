import DAL.Server.MSSQLHandler;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {
    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage primaryStage) {
        try {
            MSSQLHandler.connect("10.176.111.31", "CSe20A_31", "CSe20A_31", "fravær");
        } catch(Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

        try {
            Parent root = FXMLLoader.load(getClass().getResource("./GUI/Login/View.fxml"));

            primaryStage.setTitle("Attendance Automation");
            primaryStage.setScene(new Scene(root, 800, 600));
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.show();

            root.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    xOffset = event.getSceneX();
                    yOffset = event.getSceneY();
                }
            });

            root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    primaryStage.setX(event.getScreenX() - xOffset);
                    primaryStage.setY(event.getScreenY() - yOffset);
                    primaryStage.setOpacity(0.8f);
                }
            });

            root.setOnMouseDragExited((event) -> {
                primaryStage.setOpacity(1.0f);
            });

            root.setOnMouseReleased((event) -> {
                primaryStage.setOpacity(1.0f);
            });
        } catch(IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
