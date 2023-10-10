package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import DbConnection.Worker;
import Model.Hall;
import Model.Oponent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class updateOponentController implements Initializable{
	
	private Stage stage;
	private Scene scene;
	
	@FXML
	private ChoiceBox<String> hallChoice;
	
	@FXML
	private TextField newName;
	
	@FXML
	private Label label;
	
	private String pickedHall;
	public static Oponent pickedOponent;
	
	public void getHall(ActionEvent event) {
		pickedHall = hallChoice.getValue();
	}
	
	public void update(ActionEvent event) throws IOException {
		
		int hallId = Hall.getHallIdFromName(pickedOponent.getHall().getName());
		String oponentName = pickedOponent.getName();
		
		if(pickedHall != null) 
			hallId = Hall.getHallIdFromName(pickedHall);
		
		if(newName.getText().compareTo("") != 0) 
			oponentName = newName.getText();
		
		Worker.updateOponent(pickedOponent.getOponentId(), oponentName, hallId);
		
		Parent root = FXMLLoader.load(getClass().getResource("oponentsView.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	//Populating the hall-s choice box with hall names using Worker class methods
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		newName.setText(pickedOponent.getName());
		List<String> hallNames = Worker.getHallNames();
		hallChoice.getItems().addAll(hallNames);
		hallChoice.setOnAction(this::getHall);
	}
}
