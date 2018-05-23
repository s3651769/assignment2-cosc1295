package main;

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
	
	public void setname(String entername) { 				// setter for name
		name = entername;
	}
	
	public String getname() { 				// getter for name
		return name;
	}
	
	public void setphotopath() { 			// setter for photo
		photo = sc.nextLine();
	}
	
	public String getphotopath() { 			// getter for photo
		return photo;
	}
	
	public void setstatus() { 				// setter for status
		status=sc.nextLine();
	}
	
	public String getstatus() { // getter for status
		return status;
	}
	
	public void setage() throws NoSuchAge { // setter for age
		try {
			age=sc.nextInt(); //using try/catch in case something other than an integer is entered
			if ((age <= 0) || (age >= 150)) {
	    	   throw new NoSuchAge(age);
			}
		}
		catch (NumberFormatException nfe) {
		}
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
		
	public void setstate() {
		boolean unknownstate = false;
		do {
		System.out.println("Type in State from the following options:");
		for (int i=0; i<StateList.state.length; i++) {
		System.out.print(StateList.state[i]+ " ");
		}
		System.out.println();
			state=sc.next();
			System.out.println(StateList.samestate(state));
			for (int i=0; i<StateList.state.length; i++) {
				if(state.equals(StateList.state[i])) {
					unknownstate = true;
				}
			}
			if (!unknownstate) {
				System.out.println(state + " is unknown, try again");
			}
		} while (!unknownstate);
	}
	
	public String getstate() { //getter for state
		return state;
	}
	
}
