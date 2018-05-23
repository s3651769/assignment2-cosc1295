package main;

@SuppressWarnings("serial")
public class NotToBeColleaguesException extends Exception {

	public NotToBeColleaguesException(String person1, String person2) {
		String NTBCexception = (person1 + " and " + person2 + " cannot be colleagues - Not To Be Colleagues Exception");
		System.out.println(NTBCexception);
	}



}
