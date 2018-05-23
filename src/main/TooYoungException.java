package main;

@SuppressWarnings("serial")
public class TooYoungException extends Exception {

	public TooYoungException(String person1, String person2) {
		String TYexception = (person1 + " and " + person2 + " cannot be friends - Too Young Exception");
		System.out.println(TYexception);
}

}
