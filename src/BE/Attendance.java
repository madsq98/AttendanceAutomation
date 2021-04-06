package BE;

public class Attendance {
    private Account account;
    private Lesson lesson;

    public Attendance(Account account, Lesson lesson) {
        this.account = account;
        this.lesson = lesson;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }
}
