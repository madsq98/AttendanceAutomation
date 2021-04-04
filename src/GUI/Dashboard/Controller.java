package GUI.Dashboard;

import BE.Account;
import BE.UserType;
import BLL.AccountBLL;
import BLL.SchemaBLL;
import GUI.Dashboard.Interfaces.ISideMenu;
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
import java.sql.SQLException;

public class Controller {
    private AccountBLL accountBLL;
    private SchemaBLL schemaBLL;

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
        try {
            schemaBLL = new SchemaBLL(loggedInUser);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        String name = loggedInUser.getFirstName() + " " + loggedInUser.getLastName();

        switch(loggedInUser.getType()) {
            case STUDENT:
                setMenu(UserType.STUDENT,name);
                setPage("StudentSchema");
                break;
            case TEACHER:
                setMenu(UserType.TEACHER,name);
                setPage("TeacherFrontPage");
                break;
        }
    }

    public void setPage(String page) {
        String path = "./" + page + "/View.fxml";
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(path));
            Parent p = loader.load();

            contentBorderPane.setCenter(p);

            ISubPage controller = loader.getController();
            controller.setAccountBLL(accountBLL);
            controller.setCurrentAccount(loggedInUser);
            controller.setSchemaBLL(schemaBLL);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void setMenu(UserType type, String name) {
        String path = "./" + ((type == UserType.STUDENT) ? "StudentMenu" : "TeacherMenu") + "/Menu.fxml";
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(path));
            Parent p = loader.load();

            contentBorderPane.setLeft(p);

            ISideMenu controller = loader.getController();
            controller.setName(name);
            controller.setController(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
