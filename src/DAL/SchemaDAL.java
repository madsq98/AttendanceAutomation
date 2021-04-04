package DAL;

import BE.Account;
import BE.Lesson;
import DAL.Server.MSSQLHandler;
import javafx.collections.FXCollections;

import java.sql.*;
import java.util.ArrayList;
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

        List<Lesson> lessons = new ArrayList<>();
        while(rs.next()) {
            int courseId = rs.getInt("id");
            String courseName = rs.getString("courseName");

            String query2 = "SELECT * FROM Lessons WHERE courseId = ?";
            PreparedStatement ps2 = conn.prepareStatement(query2);
            ps2.setInt(1,courseId);

            ResultSet rs2 = ps2.executeQuery();

            while(rs2.next()) {
                Timestamp start = rs2.getTimestamp("start");
                Timestamp stop = rs2.getTimestamp("stop");

                Lesson l = new Lesson(courseName,start,stop);

                lessons.add(l);
            }
        }

        return lessons;
    }
}
