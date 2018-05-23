package main;

public class Relationship {
private Person person1;
private Person person2;
private String relationship;

	public Relationship(Person person1, Person person2, String relationship) {
		this.person1=person1;
		this.person2=person2;
		this.relationship=relationship;
	}

	public Person getperson1() {
		return person1;
	}
	
	public Person getperson2() {
		return person2;
	}
	
	public String getrelationshiptype() {
		return relationship;
	}
	
}
