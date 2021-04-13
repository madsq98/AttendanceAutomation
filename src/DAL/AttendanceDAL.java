package DAL;

import BE.Account;
import BE.Attendance;
import BE.Lesson;
import DAL.Server.MSSQLHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDAL {
    private Connection conn;
    private SchemaDAL schemaDAL;

    public AttendanceDAL() {
        conn = MSSQLHandler.getConnection();
        schemaDAL = new SchemaDAL();
    }

    public List<Attendance> getAllAttendance(Account a) throws SQLException {
        String query = "SELECT * FROM Attendance WHERE accountId = ?;";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1,a.getId());

        ResultSet rs = ps.executeQuery();
        List<Attendance> returnList = new ArrayList<>();
        while(rs.next()) {
            Lesson l = schemaDAL.getLessonById(rs.getInt("lessonsId"));
            if(l != null)
                returnList.add(new Attendance(a,l));
        }
        return returnList;
    }

    public void saveAttendance(Attendance a) throws SQLException {
        String query = "INSERT INTO Attendance(accountId,lessonsId) VALUES (?,?);";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1,a.getAccount().getId());
        ps.setInt(2,a.getLesson().getId());

        ps.executeUpdate();
    }

    public void removeAttendance(Attendance a) throws SQLException {
        String query = "DELETE FROM Attendance WHERE accountId = ? AND lessonsId = ?;";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1,a.getAccount().getId());
        ps.setInt(2,a.getLesson().getId());

        ps.executeUpdate();
    }
}
