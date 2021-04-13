package DAL;

import BE.*;
import BLL.AccountBLL;
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
    private AccountBLL accountBLL;

    public AttendanceDAL() {
        conn = MSSQLHandler.getConnection();
        schemaDAL = new SchemaDAL();
        accountBLL = new AccountBLL();
    }

    public List<Attendance> getAllAttendance() throws SQLException {
        String query = "SELECT * FROM Attendance";
        PreparedStatement ps = conn.prepareStatement(query);
        //ps.setInt(1,a.getId());

        ResultSet rs = ps.executeQuery();
        List<Attendance> returnList = new ArrayList<>();
        while(rs.next()) {
            Lesson l = schemaDAL.getLessonById(rs.getInt("lessonsId"));
            Account a = accountBLL.getAccountFromId(rs.getInt("accountId"));
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

    public List<Account> getAccountsFromCourse(Course c) throws SQLException {
        AccountBLL accBLL = new AccountBLL();

        String query = "SELECT * FROM UserCourses WHERE courseId = ?;";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1,c.getId());

        ResultSet rs = ps.executeQuery();
        List<Account> returnList = new ArrayList<>();
        while(rs.next()) {
            Account a = accBLL.getAccountFromId(rs.getInt("accountId"));
            if(a != null) {
                if(a.getType() == UserType.STUDENT)
                    returnList.add(a);
            }
        }

        return returnList;
    }
}
