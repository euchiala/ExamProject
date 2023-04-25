package tn.iit.model;

import java.util.Date;

public class Authorization {
    private int id;
    private Teacher teacher;
    private Date authorization_date;
    private int authorized_hours;
    private int teacher_id;

    public Authorization(int id, Teacher teacher, Date authorization_date, int authorized_hours) {
        this.id = id;
        this.teacher = teacher;
        this.authorization_date = authorization_date;
        this.authorized_hours = authorized_hours;
    }

    public Authorization(Teacher teacher, Date authorization_date, int authorized_hours) {
        this.teacher = teacher;
        this.authorization_date = authorization_date;
        this.authorized_hours = authorized_hours;
    }
    
    public Authorization(int teacher_id, Date authorization_date, int authorized_hours) {
        this.setTeacher_id(teacher_id);
        this.authorization_date = authorization_date;
        this.authorized_hours = authorized_hours;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Date getAuthorization_date() {
        return authorization_date;
    }

    public void setAuthorization_date(Date authorization_date) {
        this.authorization_date = authorization_date;
    }

    public int getAuthorized_hours() {
        return authorized_hours;
    }

    public void setAuthorized_hours(int authorized_hours) {
        this.authorized_hours = authorized_hours;
    }

	public int getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(int teacher_id) {
		this.teacher_id = teacher_id;
	}
}
