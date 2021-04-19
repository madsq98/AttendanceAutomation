package GUI.Dashboard.TeacherCreateAccount;

import BE.Account;
import BLL.AccountBLL;
import BLL.AttendanceBLL;
import BLL.SchemaBLL;
import GUI.Dashboard.Interfaces.ISubPage;

import java.util.List;

public class Controller implements ISubPage {
    private Account currentAccount;
    private AccountBLL accountBLL;
    private SchemaBLL schemaBLL;
    private AttendanceBLL attendanceBLL;
    private GUI.Dashboard.Controller mainController;

    @Override
    public void setCurrentAccount(Account a) {
        currentAccount = a;
    }

    @Override
    public void setAccountBLL(AccountBLL accountBLL) {
        this.accountBLL = accountBLL;
    }

    @Override
    public void setSchemaBLL(SchemaBLL schemaBLL) {
        this.schemaBLL = schemaBLL;
    }

    @Override
    public void setAttendanceBLL(AttendanceBLL attendanceBLL) { this.attendanceBLL = attendanceBLL; }

    @Override
    public void setMainController(GUI.Dashboard.Controller controller) {
        this.mainController = controller;
    }

    @Override
    public void load() {

    }
}