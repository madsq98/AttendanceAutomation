package DAL;

import BE.Account;
import BE.Attendance;
import DAL.Server.MSSQLHandler;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDAL {
    private Connection conn;

    public AttendanceDAL() {
        conn = MSSQLHandler.getConnection();
    }

    public List<Attendance> getAllAttendance(Account a) {
        return new ArrayList<>();
    }
}
