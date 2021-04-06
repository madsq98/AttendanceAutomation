package BE;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Lesson {
    private String courseName;
    private Timestamp startTime;
    private Timestamp stopTime;

    public Lesson() {
        this.courseName = "EMPTY";
        this.startTime = null;
        this.stopTime = null;
    }

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
        if(courseName == "EMPTY") return "";
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return courseName + "\n" + sdf.format(startTime) + " - " + sdf.format(stopTime);
    }
}
