package main;
/**
 * 
 * @author HannahBorash
 *
 */
import java.util.ArrayList;


public class Driver {
	private DatabaseAccess data = new DatabaseAccess(); 							//gives our driver class an instance for database access
	
/**
 * 	method for access to external data sources
 */
	
	public void access() {
		data.readinpeople(); 														// read in the people text file
		data.readinrelations();														// read in the relationship text file									// if there is no people text file,  
		data.connectdatabase();														// connect to SQL database
		if (data.accessuserdatabase().size()==0) {
			data.readinpeopledatabase();											// if no people text file access db
		}
	}

	/**
	 * 	method to print all names of users 
	 */
	
	public ArrayList<String> printnames() {
		ArrayList<String> namestoprint = new ArrayList<String>();
		String name = null;
		
		for(int i=0; i<data.accessuserdatabase().size(); i++) {
			name = data.accessuserdatabase().get(i).getname();
			namestoprint.add(name);
		}
		
		return namestoprint;
	}
	
	/**
	 * 	method to return person to access profile 
	 */
	
	public Person returnProfile(String username) {    								// returns person object with profile details,  
																					// from	string user name entered
		Person person1 = null;														// local variable Person for method
		
		for(int i=0;i<data.accessuserdatabase().size();i++) { 						// for user database
			if (data.accessuserdatabase().get(i).getname().contains(username)){		// if user name is in database or user name is part of a name string,
				person1 = data.accessuserdatabase().get(i);							// return the person if they exist
			}
		}
		return person1;																// return person object
	}
	
	
	
	// method to check if person is adult
	
	public boolean isAdult(Person person1) {										// return boolean true if person1 is of class adult
		boolean adult=false;														// local variable boolean adult is false 
		
		if(person1.getClass()==Adult.class) {										// if person1 is adult class change the boolean
			adult=true;																// to true
		}
		return adult;
	}
	
	
	
	// method to check if person is child
	
	public boolean isChild(Person person1) {										// return boolean true if person1 is of class child
		boolean child=false;														// local variable boolean child is false 
		
		if(person1.getClass()==Child.class) {										// if person1 is child class change the boolean
			child=true;																// to true
		}
		return child;
	}
	
	
	
	// method to check if person is baby
	
	public boolean isBaby(Person person1) {											// return boolean true if person1 is of class baby
		boolean baby=false;															// local variable boolean baby is false 
		
		if(person1.getClass()==Baby.class) {										// if person1 is baby class change the boolean
			baby=true;																// to true
		}
		return baby;
	}
	
	
	
	// method to return array relationships objects which contain the user name entered
	
	public ArrayList<Relationship> returnRelationships(String username) {
		ArrayList<Relationship> relations = new ArrayList<Relationship>();			// local variable generated for array list to return
		
		for(int i=0;i<data.accessuserdatabase().size();i++) {						// for each item in user list
			if (data.accessuserdatabase().get(i).getname().contains(username)){		// if Person object for row contains String user name
				for (int j=0; j<data.accessrelationsdatabase().size(); j++) {		//  for each row of relations database
					if(data.accessuserdatabase().get(i)==data.accessrelationsdatabase().get(j).getperson1()) 		// if Person is equal to person1
						relations.add(data.accessrelationsdatabase().get(j));										// add to relations array
					if(data.accessuserdatabase().get(i)==data.accessrelationsdatabase().get(j).getperson2())		// if Person is equal to person2
						relations.add(data.accessrelationsdatabase().get(j));										// add to relations array
					}
				}
			}
		return relations;																							// return array database 
	}
	
	
	
	// method to check if person is in a couple
	
	public boolean inCouple(Person person1) {										// return boolean true if person1 has a couple relationship
		boolean couple=false;														// recorded in the relationship database
		ArrayList<Relationship> relations = returnRelationships(person1.getname());									// generate relationships for person1
		
		for(int i=0; i<relations.size();i++) {										// for each relationship in the array list, check to see if couple
			if(relations.get(i).getrelationshiptype()=="couple") {					// if relationship type is couple
				couple=true;														// change local variable couple to true
			}
		}
		return couple;																// return couple boolean
	}
	
	
	
	// method to check if person is a parent
	
	public boolean isParent(Person person1) {										// return boolean true if person1 has a parent relationship
		boolean parent=false;														// local variable boolean parent is false
		ArrayList<Relationship> relations = returnRelationships(person1.getname());									// generate relationships for person1
		
		for(int i=0; i<relations.size();i++) {										// for each relationship in the array list, check to see if parent
			if(relations.get(i).getrelationshiptype()=="parent") {					// if relationship type is parent
				parent=true;														// change local variable parent to true
			}
		}
		return parent;																// return parent boolean		
	}
	
	
	
	// method to check if relationship is a duplicate
	
	public boolean addRelationships(String username1, String username2) {     		// return boolean true if relationship is not a duplicate
		String person1 = null;														// local variable for Person object's name for person1
		String person2 = null;														// local variable for Person object's name for person2
		Boolean proceed = true;														// local variable boolean is true			
		
		person1 = returnProfile(username1).getname();								// person1 object name
		person2 = returnProfile(username2).getname();								// person2 object name
		
		if(person1.compareTo(person2)<0) {											// if person1 name is alphabetically before person2 name
			for(int i=0; i<data.accessrelationsdatabase().size();i++) {											// for relationship list
				if(data.accessrelationsdatabase().get(i).getperson1().getname()==person1 & 						// check for existing relationship
						data.accessrelationsdatabase().get(i).getperson2().getname()==person2) {				// between person 1 and person 2
					proceed=false;													// if exists change boolean to false
				}
			}
		}
		else if(person1.compareTo(person2)>0) {										// if person2 name is alphabetically before person1 name
			for(int i=0; i<data.accessrelationsdatabase().size();i++) {											// for relationship list
				if(data.accessrelationsdatabase().get(i).getperson1().getname()==person2 & 						// check for existing relationship
						data.accessrelationsdatabase().get(i).getperson2().getname()==person1) {				// between person 1 and person 2
					proceed=false;													// if exists change boolean to false
				}
			}
		}
		return proceed;																// return boolean proceed
	}
	
	
	
	// method to add relationship type friendship
	
	public String addRelationshipsFriends(String username1, String username2) throws NotToBeFriends, TooYoungException {
		Person person1 = null;														// local variables for object person1
		Person person2 = null;														// local variables for object person2
		String addfriendssuccessful = "Could not add friendship";					// local variables for string message to feed UI
		
		if(addRelationships(username1, username2)==true){							// check for duplicate relationships
			person1 = returnProfile(username1);
			person2 = returnProfile(username2);
			if(isChild(person1)==true ||isChild(person2)==true) {					// check if either Person object is class child
				if(person1.getage()+2>person2.getage() || person1.getage()-2<person2.getage()) 					// if outside of age bracket 
					throw new NotToBeFriends(person1.getname(), person2.getname());								// throw exception not to be friends
				}
			else if(isBaby(person1)==true ||isBaby(person2)==true) {				// check if either Person object is class baby
				throw new TooYoungException(person1.getname(), person2.getname());								// throw exception too young exception
			}
			else {
				if(person1.getname().compareTo(person2.getname())<0) {				// check if person1 is alphabetically first
					data.accessrelationsdatabase().add(new Relationship(person1,person2,"friends")); 			// add friendship between person1 and
				}																	// person2 in alphabetical order 
				else data.accessrelationsdatabase().add(new Relationship(person2,person1,"friends"));
				addfriendssuccessful = (person1.getname() + " and " + person2.getname() + " are now friends"); // change successful friendship message
			System.out.println(addfriendssuccessful);								// to send to UI it so reflects the people and print to console
			}
		}
		return addfriendssuccessful;												// return successful message string
		
	}
	
	
	
	// method to add relationship type parent
	
	public String addRelationshipsParent(String username1, String username2) throws NoParentException {
		Person person1 = null;														// local variables for object person1
		Person person2 = null;														// local variables for object person2
		Person couple = null;														// local variables for object couple as other parent
		String addparentssuccessful = "Could not add parent";						// message to pass to the UI
		
		if(addRelationships(username1, username2)==true){                         	// check for no duplicates 
			person1=returnProfile(username1);										// return person object for entered person name1
			person2=returnProfile(username2);										// return person object for entered person name2
			if(isAdult(person1)==true & isChild(person2)==true || isAdult(person1)==true & isBaby(person2)==true) {
				if(inCouple(person1)==false) {										// if person 1 is an adult and they aren't in a couple throw a no
					throw new NoParentException(person1.getname(),person2.getname());						// parent exception
				}
				else {									
					for(int i=0; i<data.accessrelationsdatabase().size();i++) {		// for each relationship, check person1 and person2 to find
						if(data.accessrelationsdatabase().get(i).getperson1()==person1 & 					// the couple relationship and
								data.accessrelationsdatabase().get(i).getrelationshiptype()=="couple") {	// find the corresponding couple person
								couple = data.accessrelationsdatabase().get(i).getperson2();				// who must also be a parent
						}
						if(data.accessrelationsdatabase().get(i).getperson2()==person1 & 
								data.accessrelationsdatabase().get(i).getrelationshiptype()=="couple") {
								couple = data.accessrelationsdatabase().get(i).getperson1();
						}
					}
					if(couple!=null) {												// if there is a couple object, add the new relationship
						if(person1.getname().compareTo(person2.getname())<0) {		// checking for alphabetical order
							data.accessrelationsdatabase().add(new Relationship(person1,person2,"parents"));
							}
						else {
							data.accessrelationsdatabase().add(new Relationship(person2,person1,"parents"));
							}
						if(couple.getname().compareTo(person2.getname())<0) {
							data.accessrelationsdatabase().add(new Relationship(couple,person2,"parents"));	// add other parent as well
							}
						else {
							data.accessrelationsdatabase().add(new Relationship(person2,couple,"parents"));
							}
						addparentssuccessful = ("Added parents " + person1.getname() + " and " + couple.getname() +
								" to " + person2.getname());						// change success message to feed to UI
						}
					}
				}	
		else if(isAdult(person2)==true & isChild(person1)==true || isAdult(person2)==true & isBaby(person1)==true) { 
			if(inCouple(person2)==false) {																	// if person 2 is an adult and they aren't in
				throw new NoParentException(person1.getname(),person2.getname());							// a couple then throw a no parent exception
				}
			else {
				for(int i=0; i<data.accessrelationsdatabase().size();i++) {
					if(data.accessrelationsdatabase().get(i).getperson1()==person2 & 
						data.accessrelationsdatabase().get(i).getrelationshiptype()=="couple") {
						couple = data.accessrelationsdatabase().get(i).getperson2();
						}	
					if(data.accessrelationsdatabase().get(i).getperson2()==person2 & 
						data.accessrelationsdatabase().get(i).getrelationshiptype()=="couple") {
						couple = data.accessrelationsdatabase().get(i).getperson1();
						}
					}
				if(couple!=null) {
					if(person1.getname().compareTo(person2.getname())<0) {
						data.accessrelationsdatabase().add(new Relationship(person1,person2,"parents"));
						}
					else {
						data.accessrelationsdatabase().add(new Relationship(person2,person1,"parents"));
						}
					if(couple.getname().compareTo(person1.getname())<0) {
						data.accessrelationsdatabase().add(new Relationship(couple,person1,"parents"));
						}
					else {
						data.accessrelationsdatabase().add(new Relationship(person1,couple,"parents"));
						}
				addparentssuccessful = ("Added parents " + person2.getname() + " and " + couple.getname() +
						" to " + person1.getname());								// change success message to feed to UI
					}
				}
			}
		}
		System.out.println(addparentssuccessful);									// print success method to console
		return addparentssuccessful;												// return string to UI 
	}
	
	
	
	//method to add relationship couple between two adults
	
	public String addRelationshipsCouple(String username1, String username2) throws NotToBeCoupledException, NotAvailableException {
		Person person1 =returnProfile(username1);									// local variables for object person1
		Person person2 =returnProfile(username2);									// local variables for object person2
		String addcouplesuccessful = "Could not add couple";						// message to pass to the UI
		
		if(addRelationships(username1,username2)==true) {							// check to say that there isn't already a relationship
			if(isAdult(person1)!=true || isAdult(person2)!=true) {					// check to see if both people are adults
				throw new NotToBeCoupledException(person1.getname(),person2.getname());						// otherwise throw not to be coupled
			}																		// exception
			else if(inCouple(person1)==true ||inCouple(person2)==true) {			// check to see that neither person1 or person 2 objects are already
				throw new NotAvailableException(person1.getname(),person2.getname());						// in a couple, otherwise throw a not
			}																		// available exception
			else {																	// otherwise sorting by alphabetical order of couple
				if(person1.getname().compareTo(person2.getname())<0) {				
					data.accessrelationsdatabase().add(new Relationship(person1,person2,"couple"));
				}
				else data.accessrelationsdatabase().add(new Relationship(person2,person1,"couple"));
				addcouplesuccessful = (person1.getname() + " and " + person2.getname() + " are now a couple"); // change success message to feed to UI
			}																		// to explain success
		}
		System.out.println(addcouplesuccessful);                                    // print success message to console
		return addcouplesuccessful;													// return success message to UI 
	}
	
	

	//method to add relationship colleagues between two adults
	
	public String addRelationshipsColleagues(String username1, String username2) throws NotToBeColleaguesException {
		Person person1 =returnProfile(username1);									// local variables for object person1
		Person person2 =returnProfile(username2);									// local variables for object person2
		String addcolleaguessuccessful = "Could not add colleagues";				// message to pass to the UI	
		
		if(addRelationships(username1,username2)==true) {							// check to say there isn't already a relationship
			if(isAdult(person1)!=true || isAdult(person2)!=true) {					// check that both are adults
				throw new NotToBeColleaguesException(person1.getname(),person2.getname());					// otherwise throw a not to be colleagues
			}																		// exception
			else {																	
				if(person1.getname().compareTo(person2.getname())<0) {				// sorting by alphabetical order
					data.accessrelationsdatabase().add(new Relationship(person1,person2,"colleagues"));		// add relationship between colleagues
				}
				else data.accessrelationsdatabase().add(new Relationship(person2,person1,"colleagues"));
				addcolleaguessuccessful = (person1.getname() + " and " + person2.getname() + " are now colleagues");	// change success message
			}																		// to feed to UI to explain success
		}
		System.out.println(addcolleaguessuccessful);								// print success message to console
		return addcolleaguessuccessful;												// return success message to UI
	}
	
	
	
	//method to add relationship classmate between two adults or adult and child
	
	public String addRelationshipsClassmates(String username1, String username2) throws NotToBeClassmatesException {
		Person person1 =returnProfile(username1);									// local variables for object person1
		Person person2 =returnProfile(username2);									// local variables for object person2
		String addclassmatessuccessful = "Could not add classmates";				// message to pass to the UI
		
		if(addRelationships(username1,username2)==true) {							// check to say there isn't already a relationship
			if(isBaby(person1)==true || isBaby(person2)!=true) {					// check that neither person is a baby
				throw new NotToBeClassmatesException(person1.getname(),person2.getname());				// otherwise throw a not to be classmates
			}																		// exception
			else {
				if(person1.getname().compareTo(person2.getname())<0) {				// sorting by alphabetical order
					data.accessrelationsdatabase().add(new Relationship(person1,person2,"classmates"));	// add relationship between classmates
				}
				else data.accessrelationsdatabase().add(new Relationship(person2,person1,"classmates"));
				addclassmatessuccessful = (person1.getname() + " and " + person2.getname() + " are now classmates");
			}																		// change success message to feed to UI to explain success
		}
		System.out.println(addclassmatessuccessful);								// print success message to console
		return addclassmatessuccessful;												// return success message to UI
	}
	
	
	
	//method to add a new user
	
	public String addNewPerson(String newname, String newstatus, int newage, String newimagepath, String newgender, String newstate) {
		String addnewusersuccessful = " could not add new user";
		String gender = null;
		if(newgender=="female") {
			gender="F";
		}
		else {
			gender="M";
		}
		Person persontodatabase = null;
		if(newage<3) {
	 		data.accessuserdatabase().add(new Baby(newname,newimagepath,newstatus,newage, gender, newstate));
	 		addnewusersuccessful = (newname + "added as baby");
	 		System.out.println(addnewusersuccessful);
	 		}
	 	if(newage>2 & newage<17) {
	 		data.accessuserdatabase().add(new Child(newname,newimagepath,newstatus,newage, gender, newstate));
	 		addnewusersuccessful = (newname + " added as child");
	 		System.out.println(addnewusersuccessful);
	 		}
	 	if(newage>16) {
	 		data.accessuserdatabase().add(new Adult(newname,newimagepath,newstatus,newage, gender, newstate));
	 		addnewusersuccessful = (newname + " added as adult");
	 		System.out.println(addnewusersuccessful);
	 		}
	 	for(int i=0; i<data.accessuserdatabase().size(); i++) {
	 		if(data.accessuserdatabase().get(i).getname()==newname) {
	 			persontodatabase=data.accessuserdatabase().get(i);
	 			data.inserttodatabase(persontodatabase);
	 		}
	 	}
		return addnewusersuccessful;
	}


	public String deletePerson(String person) throws NoParentException{
		String deleterusersuccessful = "Could not delete user";
		Boolean candelete = false;
		Person user = returnProfile(person);
		ArrayList<Relationship> deletablerelationships = returnRelationships(person);
	
	for (int i=0; i<deletablerelationships.size(); i++)
		if(deletablerelationships.get(i).getrelationshiptype()=="parents" & isAdult(user)==true) {
			throw new NoParentException(deletablerelationships.get(i).getperson1().getname(),deletablerelationships.get(i).getperson2().getname());
		}
		else {
			for(int j=0; j<data.accessrelationsdatabase().size(); j++) {
				if(deletablerelationships.get(i)==data.accessrelationsdatabase().get(j)) {
					data.accessrelationsdatabase().remove(j);
				}
			}
			candelete=true;
		}
	if(candelete=true) {
		for (int k=0; k<data.accessuserdatabase().size(); k++) {
			if(user==data.accessuserdatabase().get(k)) {
				data.accessuserdatabase().remove(k);
				}
			}
		deleterusersuccessful=("Deleted " + user.getname() + " from MiniNet");
		}
	return deleterusersuccessful;
	}

	public void closing() {
		data.stopconnection();
	}
}