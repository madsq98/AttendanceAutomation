package GUI.Dashboard.StudentMenu;

import GUI.Dashboard.Interfaces.ISideMenu;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

import java.io.IOException;

public class Controller implements ISideMenu {
    public Label accountName;
    GUI.Dashboard.Controller mainController;

    @Override
    public void setController(GUI.Dashboard.Controller c) {
        mainController = c;
    }

    @Override
    public void setName(String name) {
        accountName.setText("Velkommen " + name);
    }

    public void logout(ActionEvent actionEvent) throws IOException {
        mainController.logout(actionEvent);
    }

    public void studentStatistics(ActionEvent actionEvent) {
        mainController.setPage("StudentStatistics");
    }

    public void studentFrontpage(ActionEvent actionEvent) {
        mainController.setPage("StudentSchema");
    }

    public void studentSettings(ActionEvent actionEvent) {
        mainController.setPage("UserSettings");
    }

    public void studentRegistrations() { mainController.setPage("StudentRegistrations"); }
}
