package main;

@SuppressWarnings("serial")
public class NotToBeFriends extends Exception {

	public NotToBeFriends(String person1, String person2) {
			String NTBFexception = (person1 + " and " + person2 + " cannot be friends - Not To Be Friends Exception");
			System.out.println(NTBFexception);
	}

}
