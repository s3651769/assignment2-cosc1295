package main;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.sql.*;

import org.hsqldb.Server;

public class DatabaseAccess {
	private Path file_name_people = Paths.get("people.txt").toAbsolutePath(); 	// network path for 
																				// storage of prior users 
	private Path file_name_relations = Paths.get("relations.txt").toAbsolutePath(); 			// network path for
																				// storage of relationships
	private ArrayList<Person> userdatabase = new ArrayList<Person>();			// create storage array list of users
	private ArrayList<Relationship> relationdatabase = new ArrayList<Relationship>();
	private Scanner sc = new Scanner(System.in);
	private File file1 = new File(file_name_people.toString()); 				//finding file linked to path
	private File file2 = new File(file_name_relations.toString());
	private Server hsqlServer = null;
	private Connection connection = null;
	
	
	public void readinpeople() {
		try {
			   sc = new Scanner(file1); 				
			   sc.useDelimiter(",");
		   } 
		catch (FileNotFoundException e) {
			   System.out.println("Cannot find people file");
			   e.printStackTrace();
		   }
		 try {
			 while(sc.hasNext()) {
				 String name = sc.next();
				 String photo = sc.next();
				 String status = sc.next();
				 String gender = sc.next();
				 int age = sc.nextInt();
				 String state = sc.next();
	
				 	if(age<3) {
				 		userdatabase.add(new Baby(name,photo,status,age, gender, state));
				 		}
				 	if(age>2 & age<17) {
				 		userdatabase.add(new Child(name,photo,status,age, gender, state));
				 		}
				 	if(age>16) {
				 		userdatabase.add(new Adult(name,photo,status,age, gender, state));
				 		}
			 		}
		 		} 
		 catch (Exception e) {
		 		System.err.println("Could not read in people file");
		 		}
		}
	


	public void connectdatabase() {
		hsqlServer = new Server();
		hsqlServer.setLogWriter(null);
		hsqlServer.setSilent(true);
		hsqlServer.setDatabaseName(0, "MiniNetDB");
		hsqlServer.setDatabasePath(0, "file:MiniNetDB");
		hsqlServer.start();// making a connection
	}
	
		public void readinpeopledatabase() {
			ResultSet rs = null;
		
		try {
		Class.forName("org.hsqldb.jdbcDriver");
		connection = DriverManager.getConnection("jdbc:hsqldb:MiniNetDB", "sa", "");
		rs = connection.prepareStatement("select name, photo, status, gender, age, state from people;").executeQuery();
		while (!rs.isLast()) {
		rs.next();
		String name=rs.getString(1);
		String photo=rs.getString(2);
		String status=rs.getString(3);
		String gender=rs.getString(4);
		int age=rs.getInt(5);
		String state=rs.getString(6);
		if(age<3) {
	 		userdatabase.add(new Baby(name,photo,status,age, gender, state));
	 		}
	 	if(age>2 & age<17) {
	 		userdatabase.add(new Child(name,photo,status,age, gender, state));
	 		}
	 	if(age>16) {
	 		userdatabase.add(new Adult(name,photo,status,age, gender, state));
	 		}
		}
		connection.commit();
		} 
	catch (SQLException e2) {
		e2.printStackTrace();
		} 
	catch (ClassNotFoundException e2) {
		e2.printStackTrace();
		}
		}

	public ArrayList<Person> accessuserdatabase() {
		return userdatabase;
	}


public void readinrelations() {
	try {
		   sc = new Scanner(file2); 				
		   sc.useDelimiter(",");
	   } 
	catch (FileNotFoundException e) {
		   System.out.println("Cannot find relationship file");
		   e.printStackTrace();
	   }
	 try {
		 while(sc.hasNext()) {
			 String name1 = sc.next();
			 String name2 = sc.next();
			 String relationship = sc.next();
			 Person person1 = null;
			 Person person2 = null;
			 for (int i=0; i<userdatabase.size(); i++) {
				 if(userdatabase.get(i).getname().equals(name1)) {
					 person1 = userdatabase.get(i);
				 }
				if(userdatabase.get(i).getname().equals(name2)) {
					person2 = userdatabase.get(i);
					 }	
			 }
			relationdatabase.add(new Relationship(person1,person2,relationship));
		 	}
		 
	 	} 
	 catch (Exception e) {
	 		System.err.println("Could not read in relationship file");
	 		}
	}


	public ArrayList<Relationship> accessrelationsdatabase() {
		return relationdatabase;
	}
	
	public void inserttodatabase(Person person1) {
		Statement stmnt = null;
		try {
	
		Class.forName("org.hsqldb.jdbcDriver");
		connection = DriverManager.getConnection("jdbc:hsqldb:MiniNetDB", "sa", "");
		stmnt = connection.createStatement();
		stmnt.executeUpdate("Insert into people (name,photo,status,gender,age,state)"+ 
				"values ('"+ person1.getname()+"','"+person1.getphotopath()+"','"+person1.getstatus()+"','"+
				person1.getgender()+"',"+person1.getage()+",'"+person1.getstate()+"');");
		connection.commit();
				
		} catch (Exception e) {
			System.out.println("Could not add to database");
			e.printStackTrace();
		}
	}
	
	public void stopconnection() {
		try {
			hsqlServer.stop();
		} catch (Exception e) {
		}
	}
}