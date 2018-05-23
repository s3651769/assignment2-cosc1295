package main;

@SuppressWarnings("serial")
public class NotToBeCoupledException extends Exception {

	public NotToBeCoupledException(String person1, String person2) {
		String NtbCexception = (person1 + " and " + person2 + " cannot be a couple - Not To Be Coupled Exception");
		System.out.println(NtbCexception);
	}


}
