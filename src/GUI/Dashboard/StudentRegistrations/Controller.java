package GUI.Dashboard.StudentRegistrations;

import BE.Account;
import BE.Attendance;
import BE.Course;
import BE.Lesson;
import BLL.AccountBLL;
import BLL.AttendanceBLL;
import BLL.SchemaBLL;
import GUI.Dashboard.Interfaces.ISubPage;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class Controller implements ISubPage {
    private Account currentAccount;
    private AccountBLL accountBLL;
    private SchemaBLL schemaBLL;
    private AttendanceBLL attendanceBLL;

    private LocalDate fromDate;
    private LocalDate toDate;
    private Course selectedCourse;

    private ObservableList<Course> observableCourses;
    private ObservableList<Lesson> allPeriodLessons;
    private ObservableList<Lesson> periodLessons;
    private ObservableList<Attendance> periodAttendance;

    @FXML
    private ComboBox<Course> courseSelector;
    @FXML
    private DatePicker dateFromSelector;
    @FXML
    private DatePicker dateToSelector;
    @FXML
    private TableView<Lesson> tblViewLessons;
    @FXML
    private TableColumn<Lesson,String> clmnDateTime;
    @FXML
    private TableColumn<Lesson,String> clmnStartTime;
    @FXML
    private TableColumn<Lesson,String> clmnStopTime;
    @FXML
    private TableColumn<Lesson,String> clmnCourse;
    @FXML
    private TableColumn<Lesson,String> clmnAttendance;

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

        try {
            observableCourses.add(new Course(-1,"Alle fag"));
            observableCourses.addAll(schemaBLL.getUserCourses());
            courseSelector.setItems(observableCourses);
            courseSelector.getSelectionModel().selectFirst();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        dateFromSelector.setValue(LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()));
        dateToSelector.setValue(LocalDate.now());
    }

    @Override
    public void setAttendanceBLL(AttendanceBLL attendanceBLL) { this.attendanceBLL = attendanceBLL; }

    @Override
    public void load() {

    }

    public void initialize() {
        periodLessons = FXCollections.observableArrayList();

        allPeriodLessons = FXCollections.observableArrayList();

        periodAttendance = FXCollections.observableArrayList();

        observableCourses = FXCollections.observableArrayList();

        dateFromSelector.valueProperty().addListener((observable, oldValue, newValue) -> {
            fromDate = newValue;
            update();
        });
        dateToSelector.valueProperty().addListener((observable, oldValue, newValue) -> {
            toDate = newValue;
            update();
        });
        courseSelector.valueProperty().addListener((observable, oldValue, newValue) -> {
            selectedCourse = newValue;
            update();
        });

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat dateFormat = new SimpleDateFormat("d/M-y");

        clmnDateTime.setCellValueFactory(c -> {
            return new SimpleStringProperty(dateFormat.format(c.getValue().getStartTime()));
        });

        clmnStartTime.setCellValueFactory(c -> {
            return new SimpleStringProperty(timeFormat.format(c.getValue().getStartTime()));
        });

        clmnStopTime.setCellValueFactory(c -> {
            return new SimpleStringProperty(timeFormat.format(c.getValue().getStopTime()));
        });

        clmnCourse.setCellValueFactory(c -> {
            return new SimpleStringProperty(c.getValue().getCourseName());
        });

        clmnAttendance.setCellValueFactory(c -> {
            return new SimpleStringProperty( (attendanceBLL.hasAttended(currentAccount,c.getValue())) ? "Ja" : "Nej" );
        });
    }

    private void update() {
        if(fromDate != null && toDate != null) {
            periodLessons.setAll(schemaBLL.getLessonsInterval(fromDate, toDate, selectedCourse));
            allPeriodLessons.setAll(schemaBLL.getLessonsInterval(fromDate, toDate, null));
            periodAttendance.setAll(attendanceBLL.getAttendanceInterval(fromDate, toDate, selectedCourse));

            tblViewLessons.setItems(periodLessons);
        }
    }
}