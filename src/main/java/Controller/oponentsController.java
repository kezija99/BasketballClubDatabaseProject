package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import DbConnection.Worker;
import Model.Oponent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class oponentsController implements Initializable{
	
	private Stage stage;
	private Scene scene;
	
	@FXML
	private ListView<String> oponentsList;
	
	private List<Oponent> oponentsObject = new ArrayList<>();
	private List<String> oponents = new ArrayList<>();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		oponentsObject = Worker.getAllOponents();
		
		fillList(oponentsObject);
	}
	
	//Updating the list of oponents in the formatted string representation
	private void fillList(List<Oponent> oponentsObject) {
		
		oponentsList.getItems().clear();
		
		oponents.clear();
		
		for(int i = 0; i < oponentsObject.size(); i++) {
			Oponent u = oponentsObject.get(i);
			String oponent = "";
			
			oponent = u.getName() + " | " + u.getHall().getName();
			oponents.add(oponent);
		}
		
		oponentsList.getItems().addAll(oponents);
	}
	
	public void addOponent(ActionEvent event) throws IOException {
		
		Parent root = FXMLLoader.load(getClass().getResource("newOponentView.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void updateOponent(ActionEvent event) throws IOException {
		
		if(oponentsList.getSelectionModel().getSelectedItem() != null) {
			
			for(int i = 0; i < oponents.size(); i++) {
				if(oponents.get(i).compareTo(oponentsList.getSelectionModel().getSelectedItem()) == 0) {
					updateOponentController.pickedOponent = oponentsObject.get(i);
					break;
				}
			}
			
			Parent root = FXMLLoader.load(getClass().getResource("updateOponentView.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	}
	
	//Getting the picked oponent object based on the selected item from the list and doing soft-delete by calling Worker-class method
	public void deleteOponent(ActionEvent event) {
		
		if(oponentsList.getSelectionModel().getSelectedItem() != null) {
			
			Oponent pickedOponent = null;
			
			for(int i = 0; i < oponents.size(); i++) {
				if(oponents.get(i).compareTo(oponentsList.getSelectionModel().getSelectedItem()) == 0) {
					pickedOponent = oponentsObject.get(i);
					break;
				}
			}
			
			Worker.deleteOponent(pickedOponent.getOponentId());
			
			oponentsObject.remove(pickedOponent);
			
			fillList(oponentsObject);
		}
	}
	
	public void toMenu(ActionEvent event) throws IOException {
		
		Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
