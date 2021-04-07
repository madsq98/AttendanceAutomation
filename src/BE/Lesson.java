package BE;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Lesson {
    private int id;
    private String courseName;
    private Timestamp startTime;
    private Timestamp stopTime;

    public Lesson() {
        this.id = -1;
        this.courseName = "EMPTY";
        this.startTime = null;
        this.stopTime = null;
    }

    public Lesson(int id, String courseName, Timestamp startTime, Timestamp stopTime) {
        this.id = id;
        this.courseName = courseName;
        this.startTime = startTime;
        this.stopTime = stopTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", startTime=" + startTime +
                ", stopTime=" + stopTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if(o == this) return true;

        if(!(o instanceof Lesson)) return false;

        Lesson l = (Lesson) o;

        return id == l.getId();
    }
}
