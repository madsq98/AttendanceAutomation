package GUI.Dashboard.TeacherMenu;

import GUI.Dashboard.Interfaces.ISideMenu;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

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

    public void logout(ActionEvent actionEvent) {
        mainController.logout(actionEvent);
    }

    public void teacherFrontpage(ActionEvent actionEvent) {
        mainController.setPage("TeacherFrontPage");
    }

    public void teacherStatistics(ActionEvent actionEvent) {
        mainController.setPage("TeacherStatistics");
    }

    public void teacherCreateAccount(ActionEvent actionEvent) {
        mainController.setPage("TeacherCreateAccount");
    }
}
