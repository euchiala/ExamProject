package tn.iit.model;

public class Teacher {
	private String first_name;
	private String last_name;
	private String email;
	private String institution;
	private int phone;
	private int id;

	public Teacher(int id ,String first_name, String last_name, String email, String institution, int phone) {
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.institution = institution;
		this.phone = phone;
	}
	public Teacher(String first_name, String last_name, String email, String institution, int phone) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.institution = institution;
		this.phone = phone;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getInstitution() {
		return institution;
	}
	public void setInstitution(String institution) {
		this.institution = institution;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
