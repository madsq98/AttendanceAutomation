package GUI.Login;

import BE.Account;
import DAL.Server.AccountDAL;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;

public class Controller {

    public JFXTextField loginUsername;
    public JFXPasswordField loginPassword;
    @FXML
    private AnchorPane root;

    public void exit(){
        System.exit(0);
    }

    public void minimize(MouseEvent mouseEvent) {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setIconified(true);
    }

    public void maximize(MouseEvent mouseEvent) {

    }

    public void login(MouseEvent mouseEvent) throws IOException {
        String username = loginUsername.getText();
        String password = loginPassword.getText();

        AccountDAL dal = new AccountDAL();

        try {
            Account test = dal.getAccountLogin(username, password);

            System.out.println(test);
        } catch(SQLException e) {
            e.printStackTrace();
        }

        System.out.println(username + password);

        Stage root1 = (Stage) root.getScene().getWindow();

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../Dashboard/View.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();

        GUI.Dashboard.Controller controller = fxmlLoader.getController();

        root1.close();
    }
}
