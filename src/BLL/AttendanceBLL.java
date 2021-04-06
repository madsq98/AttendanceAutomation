package BLL;

import BE.Account;
import BE.Attendance;
import BE.Lesson;
import DAL.AttendanceDAL;

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

    public void setAttended(Account a, Lesson l) {
        allAttendance.add(new Attendance(a,l));
    }
}
