package BLL;

import BE.Account;
import BE.Attendance;
import BE.Course;
import BE.Lesson;
import DAL.AttendanceDAL;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class AttendanceBLL {
    private AttendanceDAL dbAccess;
    private List<Attendance> allAttendance;

    public AttendanceBLL(Account a) throws SQLException {
        dbAccess = new AttendanceDAL();
        allAttendance = new ArrayList<>();

        allAttendance.addAll(dbAccess.getAllAttendance(a));
    }

    public boolean hasAttended(Account a, Lesson l) {
        return allAttendance.contains(new Attendance(a,l));
    }

    public void setAttended(Account a, Lesson l) throws SQLException {
        if(hasAttended(a,l)) return;

        Attendance at = new Attendance(a,l);

        allAttendance.add(at);

        dbAccess.saveAttendance(at);
    }

    public List<Attendance> getAttendanceInterval(LocalDate from, LocalDate to, Course course) {
        List<Attendance> returnList = new ArrayList<>();
        for(Attendance a : allAttendance) {
            Lesson lesson = a.getLesson();
            LocalDate start = lesson.getStartTime().toLocalDateTime().toLocalDate();
            if( (start.isAfter(from) || start.isEqual(from)) && (start.isBefore(to) || start.isEqual(to)) ) {
                if(course != null) {
                    if(course.getName().equals(lesson.getCourseName())) {
                        returnList.add(a);
                    }
                } else
                    returnList.add(a);
            }
        }

        return returnList;
    }

    public double getCourseAttendance(Account a, Course c, List<Lesson> lessons, List<Attendance> attendances) {
        double totalLessons = 0;
        double totalAttended = 0;

        for(Lesson l : lessons) {
            if(c.getId() != -1) {
                if (l.getCourseName().equals(c.getName())) {
                    totalLessons++;
                    if (hasAttended(a, l))
                        totalAttended++;
                }
            } else {
                totalLessons++;
                if(hasAttended(a,l))
                    totalAttended++;
            }
        }

        return (totalAttended / totalLessons) * 100;
    }

    public double[] getDaysAbsence(Account a, Course c, List<Lesson> lessons, List<Attendance> attendances) {
        double[] totalLessons = {0,0,0,0,0};
        double[] totalAttended = {0,0,0,0,0};

        for(Lesson l : lessons) {
            int weekIndex = l.getStartTime().toLocalDateTime().toLocalDate().getDayOfWeek().getValue() - 1;
            if(c.getId() != -1) {
                if(l.getCourseName().equals(c.getName())) {
                    totalLessons[weekIndex]++;
                    if(hasAttended(a,l))
                        totalAttended[weekIndex]++;
                }
            } else {
                totalLessons[weekIndex]++;
                if(hasAttended(a,l))
                    totalAttended[weekIndex]++;
            }
        }

        double[] absence = new double[5];
        for(int i = 0; i < totalLessons.length; i++) {
            double att = (totalAttended[i] / totalLessons[i]) * 100;
            att = Double.isNaN(att) ? 0 : att;
            absence[i] = 100 - att;
        }

        return absence;
    }
}