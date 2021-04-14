package BLL;

import BE.Account;
import BE.Attendance;
import BE.Lesson;
import DAL.AttendanceDAL;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AttendanceBLL {
    private AttendanceDAL dbAccess;
    private List<Attendance> allAttendance;

    public AttendanceBLL(Account a) {
        dbAccess = new AttendanceDAL();
        allAttendance = new ArrayList<>();

        allAttendance.addAll(dbAccess.getAllAttendance(a));
    }

    public boolean hasAttended(Account a, Lesson l) {
        return allAttendance.contains(new Attendance(a,l));
    }

    public void setAttended(Account a, Lesson l) throws SQLException {


        if(hasAttended(a,l)) {
            return;
        }
        Attendance at = new Attendance(a,l);
        allAttendance.add(at);
        dbAccess.saveAttendance(at);


        //Implement connection to DAL layer as well
    }
}
