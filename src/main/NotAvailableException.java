package main;

@SuppressWarnings("serial")
public class NotAvailableException extends Exception {

	public NotAvailableException(String person1, String person2) {
		String NAexception = (person1 + " and " + person2 + " cannot be a couple - Not Available Exception");
		System.out.println(NAexception);
	}

}
