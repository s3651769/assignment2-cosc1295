package main;

@SuppressWarnings("serial")
public class NoSuchAge extends Exception {
	
	public NoSuchAge(int age) {
		System.out.println("Age must be between 0-150");
		System.out.println(age + " was entered");
	}
}
