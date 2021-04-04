package DAL;

import BE.Account;
import BE.Lesson;
import DAL.Server.MSSQLHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SchemaDAL {
    private Connection conn;

    public SchemaDAL() {
        conn = MSSQLHandler.getConnection();
    }

    public List<Lesson> getAllLessons(Account a) throws SQLException {
        String query = "SELECT Courses.id, Courses.courseName FROM UserCourses INNER JOIN Courses ON Courses.id = UserCourses.courseId WHERE UserCourses.accountId = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1,a.getId());
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            
        }
    }
}
