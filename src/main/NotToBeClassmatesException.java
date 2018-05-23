package main;

@SuppressWarnings("serial")
public class NotToBeClassmatesException extends Exception {

	public NotToBeClassmatesException(String person1, String person2) {
		String NTBCexception = (person1 + " and " + person2 + " cannot be classmates - Not To Be Classmates Exception");
		System.out.println(NTBCexception);
	}

}
