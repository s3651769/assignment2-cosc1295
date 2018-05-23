package main;

import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.*;
import javafx.geometry.Insets;

public class UI extends Application {
	private String userselection = null;
	private String userselection1 = null;
	private String userselection2 = null;
	private String userselection3 = null;
	private String userselection4 = null;
	private String userselection5 = null;
	private String userselection6 = null;
	

	@Override
	public void start(Stage primaryStage) {
		Driver driver = new Driver();
		driver.access();
		
		BorderPane borderpane = new BorderPane();
		HBox hbox = new HBox();
		VBox vbox = createVbox(driver);
		GridPane root = createGridPane();
	
	
		borderpane.setTop(hbox);
		borderpane.setLeft(vbox);
		borderpane.setCenter(root);
	
		Menu menulist = new Menu("Please Select an Option from the Menu");

		MenuItem selectprofile = createSelectUserMenuItem(root, driver);
		MenuItem showrelationship = createShowRelationshipMenuItem(root, driver);
		MenuItem addrelationship = createAddRelationshipMenuItem(root, driver);
		MenuItem adduser = createAddUserMenuItem(root, driver);
		MenuItem deleteuser = createDeleteUserMenuItem(root, driver);
		MenuItem exit = createExitMenuItem(root,driver);
	

		menulist.getItems().addAll(selectprofile,showrelationship,addrelationship,adduser,deleteuser,exit);
	
		MenuBar menubar = new MenuBar();
		menubar.autosize();
		menubar.getMenus().addAll(menulist);
		hbox.getChildren().add(menubar);
	
		Scene scene = new Scene(borderpane, 500, 500);
		
		primaryStage.setTitle("MiniNet");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public GridPane createGridPane() {
		GridPane gridpane = new GridPane();
		Text title = new Text("Welcome to MiniNet");
		title.setFont(Font.font("Century Gothic",FontWeight.BOLD,20));
		gridpane.add(title, 0, 0, 8, 1);
		gridpane.setPadding(new Insets(15,15,15,15));
		gridpane.setVgap(10);
		gridpane.setHgap(10);
		return gridpane;
	}
	
	public GridPane resetGridPane(GridPane gridpane) {
		gridpane.getChildren().clear();
		Text title = new Text("Welcome to MiniNet");
		title.setFont(Font.font("Century Gothic",FontWeight.BOLD,20));
		gridpane.add(title, 0, 0, 8, 1);
		return gridpane;
	}
	
	public Label createLabel(String text) {
		Label label = new Label(text);
		label.setFont(Font.font("Century Gothic"));
		label.setWrapText(true);
		return label;
	}
	
	public Button createButton(String text) {
		Button button = new Button(text);
		button.setFont(Font.font("Century Gothic"));
		button.setWrapText(true);
		return button;
	}
	
	public Text createText(String text) {
		Text newtext = new Text(text);
		newtext.setFont(Font.font("Century Gothic"));
		return newtext;
	}
	
	public VBox createVbox(Driver driver) {
		VBox vbox = new VBox();
		vbox.autosize();
		vbox.setPadding(new Insets(40,15,15,15));
		Label list = createLabel("Show List of Users:");
		vbox.getChildren().add(list);
		for(int i=0; i < driver.printnames().size(); i++) {
			vbox.getChildren().add(createText(driver.printnames().get(i)));
		}
		return vbox;
	}
	
	public MenuItem createSelectUserMenuItem(GridPane gridpane, Driver driver) {
		MenuItem selectprofile = new MenuItem("Select User and Show Profile");
		selectprofile.setOnAction(e -> {	
			gridpane.getChildren().clear();
			Label selectionlabel = createLabel("Enter name of user you would like to select here:");
			TextField name = new TextField();
			Button submitname = new Button("Submit Name");
			Button clearname = new Button("Clear Name");
			gridpane.add(selectionlabel, 0, 3);
			gridpane.add(name,0,4,8,1);
			gridpane.add(submitname, 0,5,8,1);
			gridpane.add(clearname,1,5);
	        
	        submitname.setOnAction(s -> {
	        	if (name.getText()!=null) {
	        		System.out.println("You typed in " + name.getText());
	        		userselection = name.getText();
	        		Person person1 = driver.returnProfile(userselection);
	        		gridpane.getChildren().clear();
	        		try {
	        		Text profilename = new Text(person1.getname());
	        		Text profilestatus = new Text(person1.getstatus());
	        		String path = person1.getphotopath();
	        		Integer age = person1.getage();
	        		Text profileage = new Text(age.toString());
	        		Text profilegender = new Text(person1.getgender());
	        		Text profilestate = new Text(person1.getstate());
	        		if (path!=null) {
	        		Image Pic = new Image("main/"+path);
	                ImageView ivPic = new ImageView(Pic);
	                gridpane.add(ivPic,0,3);}
	        		gridpane.add(profilename, 0, 1);
	        		gridpane.add(profilestatus, 0, 2);
	        		gridpane.add(profileage,0,4);
	        		gridpane.add(profilegender, 0, 5);
	        		gridpane.add(profilestate, 0, 6);
	        		}
	        		catch (Exception noperson) {
	        			Text exceptionalert = new Text("Error finding person");
	        			gridpane.add(exceptionalert, 0, 1);
	        		}
	        	}
	        });
	        clearname.setOnAction(c -> {
	        	if (name.getText()!=null) {
	        		name.clear();
	        	}
	        });
	        
		});
		return selectprofile;
	}
	
	public MenuItem createShowRelationshipMenuItem(GridPane gridpane, Driver driver) {
		MenuItem showrelationship = new MenuItem("Select User and Show Existing Relationships");
		showrelationship.setOnAction(e -> {	
			gridpane.getChildren().clear();
			Label selectionlabel = createLabel("Enter name of user you would like to select here:");
			TextField name = new TextField();
			Button submitname = createButton("Submit Name");
			Button clearname = createButton("Clear Name");
			gridpane.add(selectionlabel, 0, 3);
			gridpane.add(name,0,4,8,1);
			gridpane.add(submitname, 0,5,8,1);
			gridpane.add(clearname,1,5);
        	
			submitname.setOnAction(s -> {
				if (name.getText()!=null) {
					System.out.println(name.getText());
					userselection = name.getText();
					ArrayList<Relationship> relations = driver.returnRelationships(userselection);
					gridpane.getChildren().clear();
					try {
						for(int i=0; i<relations.size(); i++) {
							Text line = new Text((relations.get(i).getperson1().getname()+" and "+ 
									relations.get(i).getperson2().getname()+ ": "+relations.get(i).getrelationshiptype()));
							gridpane.add(line, 0, i);
						}
					}
					catch (Exception noperson) {
						Text exceptionalert = new Text("Error finding relationship");
						gridpane.add(exceptionalert, 0, 1);
					}
				}
			});
        clearname.setOnAction(c -> {
        	if (name.getText()!=null) {
        		name.clear();
        		}
        	});
        
		});
		return showrelationship;
	}

	public MenuItem createAddRelationshipMenuItem(GridPane gridpane, Driver driver) {
		MenuItem addrelationship = new MenuItem("Select User and Add New Relationship");
		addrelationship.setOnAction(e -> {
			gridpane.getChildren().clear();
			Label selectionlabel1 = createLabel("Enter name of user you would like to select here:");
			TextField name1 = new TextField();
			Label selectionlabel2 = createLabel("Enter another user, you would like to add a relationship with:");
			TextField name2 = new TextField();
			Label selectionlabel3 = createLabel("Select relationship type from the drop down");
			Button submitnames = createButton("Submit Names");
			Button clearnames = createButton("Clear Names");
			ComboBox<String> relationshiptype = new ComboBox<String>();
			relationshiptype.getItems().addAll(
	            "friends",
	            "parents",
	            "couple",
	            "classmates",
	            "colleagues"  
	        );
	        
			gridpane.add(selectionlabel1, 0, 3);
			gridpane.add(name1,0,4,8,1);
			gridpane.add(selectionlabel2, 0, 5);
			gridpane.add(name2,0,6,8,1);
			gridpane.add(selectionlabel3, 0, 7);
			gridpane.add(relationshiptype, 0, 8,8,1);
			gridpane.add(submitnames, 0,9,8,1);
			gridpane.add(clearnames,1,9);
        
			submitnames.setOnAction(s -> {
				if (name1.getText()!=null & name2.getText()!=null & relationshiptype.getValue()!=null) {
					userselection1 = name1.getText();
					userselection2 = name2.getText();
					userselection3 = relationshiptype.getValue();
        				if(userselection3=="friends") {
        					try {
        						driver.addRelationshipsFriends(userselection1, userselection2);
        					} 
        					catch (NotToBeFriends e1) {
        					}
        					catch (TooYoungException e2) {
        					}
        					gridpane.getChildren().clear();
        				} else if(userselection3=="parents") {
        					try {
        						driver.addRelationshipsParent(userselection1, userselection2);
        					}
        					catch (NoParentException e3) {
        					}
        				} else if(userselection3=="couple") {
        					try {
        						driver.addRelationshipsCouple(userselection1, userselection2);
        					}
        					catch(NotToBeCoupledException e4) {
        					}
        					catch(NotAvailableException e5) {
        					}
        				} else if(userselection3=="colleagues") {
        					try {
        						driver.addRelationshipsColleagues(userselection1, userselection2);
        					}
        					catch(NotToBeColleaguesException e6) {
        					}
        				}
        				else if(userselection3=="classmates") {
        					try {
        						driver.addRelationshipsClassmates(userselection1, userselection2);
        					}
        					catch(NotToBeClassmatesException e7) {
        					}
        				}
					}
				else {
					System.out.println("Please complete the options for adding a new relationship");
				}
        	});

        
			clearnames.setOnAction(c -> {
				if (name1.getText()!=null || name2.getText()!=null) {
					name1.clear();
					name2.clear();
				}
			});
		});
		return addrelationship;
	}
	
	public MenuItem createAddUserMenuItem(GridPane gridpane, Driver driver) {
		MenuItem adduser = new MenuItem("Add an New User");
		
		adduser.setOnAction(e -> {
			gridpane.getChildren().clear();
			Label selectionlabel1 = createLabel("Enter name of new user:");
			TextField name = new TextField();
			Label selectionlabel2 = createLabel("Enter status for new user (if applicable):");
			TextField status = new TextField();
			Label selectionlabel3 = createLabel("Enter age for new user:");
			TextField age = new TextField("20");
			Label selectionlabel4 = createLabel("Enter imagepath for new user (if applicable):");
			TextField imagepath = new TextField();
			Label selectionlabel5 = createLabel("Select gender for new user:");
			ComboBox<String> gender = new ComboBox<String>();
			gender.getItems().addAll(
		            "female",
		            "male"
		        );
			Label selectionlabel6 = createLabel("Select state for new user:");
			ComboBox<String> state = new ComboBox<String>();
			state.getItems().addAll(
		            "VIC",
		            "NSW",
		            "ACT",
		            "WA",
		            "QLD",
		            "TAS",
		            "SA",
		            "NT"
		      
		        );
			Button submitnames = createButton("Submit Information");
			Button clearnames = createButton("Clear Information");
			
			gridpane.add(selectionlabel1, 0, 3);
			gridpane.add(name,0,4);
			gridpane.add(selectionlabel2, 0, 5);
			gridpane.add(status,0,6);
			gridpane.add(selectionlabel3, 0, 7);
			gridpane.add(age, 0,8);
			gridpane.add(selectionlabel4,0,9);
	        gridpane.add(imagepath,0,10);
	        gridpane.add(selectionlabel5,0,11);
	        gridpane.add(gender, 0, 12);
	        gridpane.add(selectionlabel6, 0, 13);
	        gridpane.add(state, 0, 14);
	        gridpane.add(submitnames, 0, 15);
	        gridpane.add(clearnames, 1, 15);
	        
	        submitnames.setOnAction(s -> {
	        	int userselectionage=20;
	        	if (name.getText()!=null & age.getText()!=null & gender.getValue()!=null & state.getValue()!=null) {
	        		userselection1 = name.getText();
	        		userselection2 = status.getText();
	        		userselection3 = age.getText();
	        		try {
	        			userselectionage = Integer.parseInt(userselection3);
	        		} catch (NumberFormatException nfe) {
	        			Text exceptionalert = new Text("Age must be a number");
	        			exceptionalert.setFill(Color.RED);
	        			gridpane.add(exceptionalert, 0, 16);
	        		}
	    			if ((userselectionage <= 0) || (userselectionage >= 150)) {
	    		    	  try {
							throw new NoSuchAge(userselectionage);
						} catch (NoSuchAge e1) {
						}
	    				}
	        		userselection4 = imagepath.getText();
	        		userselection5 = gender.getValue();
	        		userselection6 = state.getValue();
	        		driver.addNewPerson(userselection1, userselection2, userselectionage, userselection4, userselection5, userselection6);
	        	}
	        	else {
	        	System.out.println("Please complete the options for adding a new user");	
	        	}
	        });
		});
		return adduser;
	}
	
	public MenuItem createDeleteUserMenuItem(GridPane gridpane, Driver driver) {
		MenuItem deleteuser = new MenuItem("Delete User and their Relationships");
		deleteuser.setOnAction(e -> {	
			gridpane.getChildren().clear();
			Label selectionlabel = createLabel("Enter name of user you would like to delete here:");
			TextField name = new TextField();
			Button submitname = createButton("Submit Name");
			Button clearname = createButton("Clear Name");
			gridpane.add(selectionlabel, 0, 3);
			gridpane.add(name,0,4,8,1);
			gridpane.add(submitname, 0,5,8,1);
			gridpane.add(clearname,1,5);
	        
	        submitname.setOnAction(s -> {
	        	if (name.getText()!=null) {
	        		System.out.println("You typed in " + name.getText());
	        		userselection = name.getText();
	        		try {
	        			String completion = driver.deletePerson(userselection);
	        			gridpane.getChildren().clear();
	        			Text successfuldelete = createText(completion);
	        			gridpane.add(successfuldelete, 0, 1);
	        		} catch (NullPointerException NPE) {
	        			Text NPEtext = new Text("Could not find person");
	        			NPEtext.setFill(Color.RED);
	        			System.out.println(NPEtext);
	        			gridpane.getChildren().clear();
	        			gridpane.add(NPEtext, 0, 1);
	        		} catch (NoParentException NPE2) {
	        		}
	    		}
	        });
	        
	        clearname.setOnAction(c -> {
	        	if (name.getText()!=null) {
	        		name.clear();
	        	}
	        });
	        
		});
		return deleteuser;
	}
	
	public MenuItem createExitMenuItem(GridPane gridpane, Driver driver) {
		MenuItem exit = new MenuItem("Exit MiniNet");
		exit.setOnAction(e -> {
			gridpane.getChildren().clear();
			driver.closing();
			System.exit(0);
		});
		return exit;
	}
}
