package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import DbConnection.Worker;
import Model.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class updatePlayerController implements Initializable{

	private Stage stage;
	private Scene scene;
	
	@FXML
	private TextField name, surname, height, weight, jerseyNumber;
	
	@FXML
	private Label label;
	
	@FXML
	private RadioButton active, inactive;
	
	public static Player pickedPlayer;
	private boolean status;
	private int tmp = 0;
	
	//Checking if there is no empty fields. If so, the player is being updated by calling the Worker-class method
	@SuppressWarnings("null")
	public void update(ActionEvent event) throws IOException {
		 
		 try {
			 String newName = pickedPlayer.getName(), newSurname = pickedPlayer.getSurname();
			 short newHeight = pickedPlayer.getHeight();
			 double newWeight = pickedPlayer.getWeight();
			 int newJerseyNumber = pickedPlayer.getJerseyNumber();
			 boolean act = pickedPlayer.isActive();
			 
			if(name.getText().compareTo("") != 0) {
				newName = name.getText();
			}
			
			if(surname.getText().compareTo("") != 0) {
				newSurname = surname.getText();
			}
			
			if(height.getText().compareTo("") != 0) {
				newHeight = Short.parseShort(height.getText());
			}
			
			if(weight.getText().compareTo("") != 0) {
				newWeight = Double.parseDouble(weight.getText());
			}
			
			if(jerseyNumber.getText().compareTo("") != 0) {
				newJerseyNumber = Integer.parseInt(jerseyNumber.getText());
			}

			if(tmp > 0)
				act = status;
			
			Worker.updatePerson(pickedPlayer.getPersonId(), newName, newSurname, newHeight, newWeight, newJerseyNumber, act);
				
			Parent root = FXMLLoader.load(getClass().getResource("playersView.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		 }
		 catch(Exception e) {
			 label.setText("Invalid input!");
		 }
	}
	
	public void getActive(ActionEvent event) {
		
		if(active.isSelected())
			status = true;
		else if(inactive.isSelected())
			status = false;
		tmp++;
	}

	//Initializing the gui by displaying current player informations which then can be either changed or left like they were before
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		name.setText(pickedPlayer.getName());
		surname.setText(pickedPlayer.getSurname()); 
		height.setText(String.valueOf(pickedPlayer.getHeight())); 
		weight.setText(String.valueOf(pickedPlayer.getWeight()));
		jerseyNumber.setText(String.valueOf(pickedPlayer.getJerseyNumber()));
		
	}
}
