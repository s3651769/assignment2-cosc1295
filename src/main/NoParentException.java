package main;

@SuppressWarnings("serial")
public class NoParentException extends Exception {

	public NoParentException(String person1, String person2) {
		System.out.println("Cannot have child without two parents");
		System.out.println("Exception between " + person1 + " and " + person2);
	}

}
