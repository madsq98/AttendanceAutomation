package GUI.Dashboard.StudentSchema;

import BE.Account;
import BE.Lesson;
import BE.Schema;
import BLL.AccountBLL;
import BLL.SchemaBLL;
import GUI.Dashboard.Interfaces.ISubPage;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.LocalDate;
import java.util.List;

public class Controller implements ISubPage {
    private Account currentAccount;
    private AccountBLL accountBLL;
    private SchemaBLL schemaBLL;

    private Lesson currentLesson;

    @FXML
    private TableView<Schema> studentSchema;
    @FXML
    private TableColumn<Schema,String> schemaMonday;
    @FXML
    private TableColumn<Schema,String> schemaTuesday;
    @FXML
    private TableColumn<Schema,String> schemaWednesday;
    @FXML
    private TableColumn<Schema,String> schemaThursday;
    @FXML
    private TableColumn<Schema,String> schemaFriday;

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

        setItemsInSchema();
    }

    private void setItemsInSchema() {
        currentLesson = schemaBLL.getCurrentLesson();

        List<Schema> weekSchema = schemaBLL.getWeekSchemaFormatted();

        ObservableList<Schema> items = FXCollections.observableArrayList();
        items.addAll(weekSchema);

        studentSchema.setItems(items);

        schemaMonday.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getMonday().toString()));
        schemaTuesday.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTuesday().toString()));
        schemaWednesday.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getWednesday().toString()));
        schemaThursday.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getThursday().toString()));
        schemaFriday.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getFriday().toString()));

        schemaMonday.prefWidthProperty().bind(studentSchema.widthProperty().multiply(0.19));
        schemaTuesday.prefWidthProperty().bind(studentSchema.widthProperty().multiply(0.19));
        schemaWednesday.prefWidthProperty().bind(studentSchema.widthProperty().multiply(0.19));
        schemaThursday.prefWidthProperty().bind(studentSchema.widthProperty().multiply(0.19));
        schemaFriday.prefWidthProperty().bind(studentSchema.widthProperty().multiply(0.19));

        for(Object r : studentSchema.getItems()) {
            int i = 1;
            for (Object c : studentSchema.getColumns()) {
                TableColumn column = (TableColumn) c;

                if(i == LocalDate.now().getDayOfWeek().getValue())
                    column.setStyle("-fx-background-color:#e6e6fa;");
                i++;
            }
        }
    }

    public void registerAttendance(ActionEvent actionEvent) {
        studentSchema.setItems(null);
    }
}
