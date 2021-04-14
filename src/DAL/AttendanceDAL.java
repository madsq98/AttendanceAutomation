package DAL;

import BE.Account;
import BE.Attendance;
import DAL.Server.MSSQLHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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


    public void saveAttendance(Attendance a) throws SQLException {

        String query = "INSERT INTO Attendance(accountId,lessonsId) VALUES (?,?);";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1,a.getAccount().getId());
        ps.setInt(2,a.getLesson().getId());

        ps.executeUpdate();
    }

}
