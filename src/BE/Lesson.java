package BE;

import java.sql.Timestamp;

public class Lesson {
    private String courseName;
    private Timestamp startTime;
    private Timestamp stopTime;

    public Lesson(String courseName, Timestamp startTime, Timestamp stopTime) {
        this.courseName = courseName;
        this.startTime = startTime;
        this.stopTime = stopTime;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getStopTime() {
        return stopTime;
    }

    public void setStopTime(Timestamp stopTime) {
        this.stopTime = stopTime;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "courseName='" + courseName + '\'' +
                ", startTime=" + startTime +
                ", stopTime=" + stopTime +
                '}';
    }
}
