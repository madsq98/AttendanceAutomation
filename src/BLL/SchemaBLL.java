package BLL;

import BE.Account;
import BE.Lesson;
import DAL.SchemaDAL;

import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

    public List<List<Lesson>> getWeekSchema() {
        Calendar cal = Calendar.getInstance();

        List<Lesson> monday = new ArrayList<>();
        List<Lesson> tuesday = new ArrayList<>();
        List<Lesson> wednesday = new ArrayList<>();
        List<Lesson> thursday = new ArrayList<>();
        List<Lesson> friday = new ArrayList<>();
        for(int i = Calendar.MONDAY; i <= Calendar.FRIDAY; i++) {
            cal.set(Calendar.DAY_OF_WEEK, i);
            LocalDate thisDate = cal.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            for(Lesson l : allLessons) {
                LocalDate comparison = l.getStartTime().toLocalDateTime().toLocalDate();
                if(thisDate.equals(comparison)) {
                    switch(i) {
                        case Calendar.MONDAY -> monday.add(l);
                        case Calendar.TUESDAY -> tuesday.add(l);
                        case Calendar.WEDNESDAY -> wednesday.add(l);
                        case Calendar.THURSDAY -> thursday.add(l);
                        case Calendar.FRIDAY -> friday.add(l);
                    }
                }
            }
        }

        List<List<Lesson>> returnLessons = new ArrayList<>();
        returnLessons.add(monday);
        returnLessons.add(tuesday);
        returnLessons.add(wednesday);
        returnLessons.add(thursday);
        returnLessons.add(friday);

        return returnLessons;
    }
}
