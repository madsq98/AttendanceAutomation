package GUI.Dashboard;

import BE.Account;
import GUI.Dashboard.Interfaces.ISubPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    public Label accountName;
    private Account loggedInUser;
    private Controller currentController;

    @FXML
    private AnchorPane dRoot;

    @FXML
    private BorderPane contentBorderPane;

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
        exit();
    }

    public void setAccount(Account loggedInUser) {
        this.loggedInUser = loggedInUser;
        accountName.setText("Velkommen " + loggedInUser.getFirstName() + " " + loggedInUser.getLastName());

        switch(loggedInUser.getType()) {
            case STUDENT -> setPage("StudentSchema");
            case TEACHER -> setPage("TeacherFrontPage");
        }
    }

    private void setPage(String page) {
        String path = "./" + page + "/View.fxml";
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(path));
            Parent p = loader.load();

            contentBorderPane.setCenter(p);

            ISubPage controller = loader.getController();
            controller.setAccounts(null);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
