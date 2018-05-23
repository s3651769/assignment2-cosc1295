package main;
 
	
public class StateList {
	public static final String state[] =  {"VIC","QLD","NSW", "WA","SA","NT","TAS","ACT"}; 
	public static final String vic[] = {"V", "VIC", "VICTORIA"};
	public static final String nsw[] = {"NEW", "NSW", "NEW SOUTH WALES"};
	

	public static String samestate(String entry) {
		for (int i=0; i<vic.length; i++) {
			if(entry.toUpperCase()==vic[i]) {
				entry="VIC";
			}
		}
		for (int i=0; i<nsw.length; i++) {
			if(entry.toUpperCase()==nsw[i]) {
				entry="NSW";
			}
		}
		return entry;
	}
}