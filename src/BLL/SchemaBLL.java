package BLL;

import BE.Account;
import BE.Lesson;
import BE.Schema;
import DAL.SchemaDAL;
import UTIL.ArrayTools;
import javafx.collections.FXCollections;

import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SchemaBLL {
    private SchemaDAL dbAccess;

    private List<Lesson> allLessons;

    private Account account;

    private AttendanceBLL attendanceBLL;

    public SchemaBLL(Account a) throws SQLException {
        account = a;
        attendanceBLL = new AttendanceBLL(a);
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
                    String sign = (attendanceBLL.hasAttended(account,l)) ? "✓" : "✗";
                    l.setCourseName(l.getCourseName()+sign);
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

    public List<Schema> getWeekSchemaFormatted() {
        List<List<Lesson>> weekSchema = getWeekSchema();

        int indexOfLargest = ArrayTools.getIndexOfLargest(weekSchema);

        List<Schema> returnList = new ArrayList<>();
        for(int i = 0; i < weekSchema.get(indexOfLargest).size(); i++) {
            Schema s = new Schema();
            Lesson monday = (i < weekSchema.get(0).size() ) ? weekSchema.get(0).get(i) : new Lesson();
            s.setMonday(monday);
            Lesson tuesday = (i < weekSchema.get(1).size() ) ? weekSchema.get(1).get(i) : new Lesson();
            s.setTuesday(tuesday);
            Lesson wednesday = (i < weekSchema.get(2).size() ) ? weekSchema.get(2).get(i) : new Lesson();
            s.setWednesday(wednesday);
            Lesson thursday = (i < weekSchema.get(3).size() ) ? weekSchema.get(3).get(i) : new Lesson();
            s.setThursday(thursday);
            Lesson friday = (i < weekSchema.get(4).size() ) ? weekSchema.get(4).get(i) : new Lesson();
            s.setFriday(friday);
            returnList.add(s);
        }

        return returnList;
    }

    public Lesson getCurrentLesson() {
        LocalDateTime now = LocalDateTime.now();
        Lesson currentLesson = null;

        for(Lesson l : allLessons) {
            if(now.isAfter(l.getStartTime().toLocalDateTime()) && now.isBefore(l.getStopTime().toLocalDateTime()))
                currentLesson = l;
        }

        return currentLesson;
    }
}
