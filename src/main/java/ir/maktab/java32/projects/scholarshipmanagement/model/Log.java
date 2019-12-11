package ir.maktab.java32.projects.scholarshipmanagement.model;

import ir.maktab.java32.projects.scholarshipmanagement.core.annotations.Entity;
import ir.maktab.java32.projects.scholarshipmanagement.core.annotations.Id;

import java.util.Date;

@Entity
public class Log {
    @Id
    private Long id;
    private User user;
    private String date;
    private String activity;

    public Log(Long id, User user, String date, String activity) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.activity = activity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", username=" + user.getUsername() +
                ", date='" + date + '\'' +
                ", activity='" + activity + '\'' +
                '}';
    }
}
