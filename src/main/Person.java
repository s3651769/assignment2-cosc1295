package main;
/**
 * 
 * @author HannahBorash
 *
 */
import java.util.*;

public abstract class Person { 				// creating superclass person with all field for profile
	private String name;
	private String photo;
	private String status;
	private int age; 						// guidelines say age is integer
	private String gender;
	private String state;
	private Scanner sc = new Scanner(System.in); 
	
	public Person(String name, String photo, String status, int age, String gender, String state) {
		this.name=name;
		this.photo=photo;
		this.status=status;
		this.age=age;
		this.gender=gender;
		this.state=state;
	}
	
	
	public String getname() { 				// getter for name
		return name;
	}
	
	public String getphotopath() { 			// getter for photo
		return photo;
	}
	
	
	public String getstatus() { // getter for status
		return status;
	}
	
	public int getage() { // getter for age
		return age;
	}
	
	public void setgender() { 				// setter for status
		gender=sc.next();
		
	}
	
	public String getgender() { // getter for status
		return gender;
	}
		
	
	public String getstate() { //getter for state
		return state;
	}
	
}
