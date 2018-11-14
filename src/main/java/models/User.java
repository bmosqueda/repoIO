package models;

public class User 
{
	private int user_id;
	private String account_number;
	private String email;
	private int role;
	private int school_id;
	
	public User(String account_number, String email, int role, int school_id) {
		super();
		this.account_number = account_number;
		this.email = email;
		this.role = role;
		this.school_id = school_id;
	}
	
	public User(int user_id, String account_number, String email, int role, int school_id) {
		super();
		this.account_number = account_number;
		this.email = email;
		this.role = role;
		this.school_id = school_id;
		this.user_id = user_id;
	}
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getAccount_number() {
		return account_number;
	}
	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public int getSchool_id() {
		return school_id;
	}
	public void setSchool_id(int school_id) {
		this.school_id = school_id;
	}
}