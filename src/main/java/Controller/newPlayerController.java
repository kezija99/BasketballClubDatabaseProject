package Controller;

import java.time.LocalDate;

import DbConnection.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class newPlayerController {

	private Stage stage;
	private Scene scene;
	
	@FXML
	private TextField name, surname, nacionality, height, weight, jerseyNumber;
	
	@FXML
	private DatePicker datePicker;
	
	@FXML
	private Label label;
	
	private LocalDate date;
	
	
	public void getDate(ActionEvent event) {
		
		LocalDate myDate = datePicker.getValue();
		date = myDate;
	}
	
	//Checking if all fields are entered. If so, passing the parameters to a Worker class which will save the new player to database
	public void add(ActionEvent event) {
		
		if(date == null || name.getText() == null || surname.getText() == null || nacionality.getText() == null || height.getText() == null
				|| weight.getText() == null || jerseyNumber.getText() == null)
			label.setText("All fields are required!");
		else {
			
			String nameTmp = name.getText();
			String surnameTmp = surname.getText();
			String nacionalityTmp = nacionality.getText();
			short heightTmp = 0;
			double weightTmp = 0;
			int jerseyNumberTmp = 0;
			try {
				heightTmp = Short.parseShort(height.getText());
				weightTmp = Double.parseDouble(weight.getText());
				jerseyNumberTmp = Integer.parseInt(jerseyNumber.getText());
				
				Worker.addNewPlayer(nameTmp, surnameTmp, date, nacionalityTmp, heightTmp, weightTmp, jerseyNumberTmp);
				
				Parent root = FXMLLoader.load(getClass().getResource("playersView.fxml"));
				stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			}
			catch(Exception e) {
				e.printStackTrace();
				label.setText("Height, weight and jersey number has to be numbers!");
			}
			
		}
	}
	
}
