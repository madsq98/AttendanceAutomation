package BLL;

import BE.Account;
import BE.Lesson;
import DAL.SchemaDAL;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SchemaBLL {
    private SchemaDAL dbAccess;

    private List<Lesson> allLessons;

    public SchemaBLL(Account a) throws SQLException {
        dbAccess = new SchemaDAL();
        allLessons = new ArrayList<>();

        allLessons.addAll(dbAccess.getAllLessons(a));
    }

    public List<Lesson> getAllLessons() {
        return allLessons;
    }
}
