package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import DbConnection.Worker;
import Model.Hall;
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

public class newOponentController implements Initializable{
	
	private Stage stage;
	private Scene scene;
	
	@FXML
	private ChoiceBox<String> hall;
	
	@FXML
	private TextField name;
	
	@FXML
	private Label label;
	
	private String pickedHall;
	
	public void getHala(ActionEvent event) {
		pickedHall = hall.getValue();
	}
	
	public void add(ActionEvent event) throws IOException {
		
		if(pickedHall == null || name.getText() == null)
			label.setText("All fields are required!");
		else {
			
			int hallId = Hall.getHallIdFromName(pickedHall);
			
			String oponentName = name.getText();
			
			Worker.addNewOponent(oponentName, hallId, true);
			
			Parent root = FXMLLoader.load(getClass().getResource("oponentsView.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		List<String> hallNames = Worker.getHallNames();
		hall.getItems().addAll(hallNames);
		hall.setOnAction(this::getHala);
	}
}
