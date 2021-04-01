package GUI.Dashboard;

import BE.Account;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Controller {
    public Label accountName;
    private Account loggedInUser;

    @FXML
    private AnchorPane dRoot;

    public void exit(){
        System.exit(0);
    }

    public void minimize(MouseEvent mouseEvent) {
        Stage stage = (Stage) dRoot.getScene().getWindow();
        stage.setIconified(true);
    }

    public void maximize(MouseEvent mouseEvent) {

    }

    public void logout(ActionEvent actionEvent) {
    }

    public void setAccount(Account loggedInUser) {
        this.loggedInUser = loggedInUser;
        accountName.setText("Velkommen " + loggedInUser.getFirstName() + " " + loggedInUser.getLastName());
    }
}
