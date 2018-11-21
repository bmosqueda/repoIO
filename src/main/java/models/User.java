package models;

import org.json.simple.JSONObject;

public class User extends Model 
{
	private int user_id;
	private String account_number;
	private String name;
	private String email;
	private String password;	
	private int role;	//Admin: 1, Validator: 2, Common: 3
	private int school_id;
	private String image;
	
	//Others
	private String school_name;
	public static String fields_without_password = "u.user_id, u.account_number, u.name, u.email, u.role, u.school_id, u.image";
	
	//To create
	public User(String account_number, String name, String email, String  password, int role, int school_id) {
		super();
		
		this.account_number = account_number;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.school_id = school_id;
	}
	
	//Cuando se recupera de la base de datos
	public User(int user_id, String account_number, String name, String email, int role, int school_id, String image, String school_name) {
		super();
		this.user_id = user_id;
		this.account_number = account_number;
		this.name= name;
		this.email = email;
		this.role = role;
		this.school_id = school_id;
		this.image = image;
		this.school_name= school_name;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String toJSON() {
		JSONObject json = new JSONObject();
		
		json.put("user_id", this.user_id);
		json.put("account_number", this.account_number);
		json.put("name", this.name);
		json.put("email", this.email);
		json.put("role", this.role);
		json.put("school_id", this.school_id);
		json.put("image", this.image);
		json.put("school_name", this.school_name);
		
		return json.toString();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSONObject() {
		JSONObject json = new JSONObject();
		
		json.put("user_id", this.user_id);
		json.put("account_number", this.account_number);
		json.put("name", this.name);
		json.put("email", this.email);
		json.put("role", this.role);
		json.put("school_id", this.school_id);
		json.put("image", this.image);
		json.put("school_name", this.school_name);
		
		return json;
	}
	
	@Override
	public String toString( ) {
		return "Account number: " + this.account_number + "\n"+
				"Email: " + this.email+ "\n"+
				"Name: " + this.name+ "\n"+
				"User_id: " + this.user_id+ "\n";
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSchool_name() {
		return school_name;
	}
	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}