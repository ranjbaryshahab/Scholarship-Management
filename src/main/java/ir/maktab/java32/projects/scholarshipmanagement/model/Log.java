package ir.maktab.java32.projects.scholarshipmanagement.model;

import ir.maktab.java32.projects.scholarshipmanagement.core.annotations.Entity;
import ir.maktab.java32.projects.scholarshipmanagement.core.annotations.Id;

@Entity
public class Log {
    @Id
    private Integer id;
    private User user;
    private String date;
    private String action;
    private Scholarship scholarship;

    public Log(Integer id, User user, String date, String action, Scholarship scholarship) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.action = action;
        this.scholarship = scholarship;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Scholarship getScholarship() {
        return scholarship;
    }

    public void setScholarship(Scholarship scholarship) {
        this.scholarship = scholarship;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", username=" + user.getUsername() + '\'' +
                ", scholarship_id=" + scholarship.getId() + '\'' +
                ", action='" + action + '\'' +
                ", date='" + date +
                '}';
    }
}
