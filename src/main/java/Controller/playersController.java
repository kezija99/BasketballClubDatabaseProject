package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class playersController implements Initializable{
	
	private Stage stage;
	private Scene scene;
	
	@FXML
	private ListView<String> playersList;
	
	private List<Player> playersObject = new ArrayList<>();
	private List<String> players = new ArrayList<>();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		playersObject = Worker.getAllPlayers();
		
		fillList(playersObject);
	}
	
	private void fillList(List<Player> playersObject) {
		
		playersList.getItems().clear();
		
		players.clear();
		
		for(int i = 0; i < playersObject.size(); i++) {
			Player ig = playersObject.get(i);
			String player = "";
			
			player = ig.getName() + "|" + ig.getSurname() + "|" + ig.getNacionality() + "|" + ig.getHeight() + "|" + ig.getWeight() + "|" + ig.getJerseyNumber()
			+ "|" + ig.getDateOfBirth();
			
			if(ig.isActive())
				player += " Active";
			else
				player += " Inactive";
			
			players.add(player);
		}
		
		playersList.getItems().addAll(players);
	}
	
	public void addPlayer(ActionEvent event) throws IOException {
		
		Parent root = FXMLLoader.load(getClass().getResource("newPlayerView.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void updatePlayer(ActionEvent event) throws IOException {
		
		if(playersList.getSelectionModel().getSelectedItem() != null) {
			
			for(int i = 0; i < players.size(); i++) {
				if(players.get(i).compareTo(playersList.getSelectionModel().getSelectedItem()) == 0) {
					updatePlayerController.pickedPlayer = playersObject.get(i);
					break;
				}
			}
			
			Parent root = FXMLLoader.load(getClass().getResource("updatePlayerView.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	}
	
	//Getting the player object based on the selected string representation and soft-deleting it from the database using class Worker
	public void deletePlayer(ActionEvent event) {
		
		if(playersList.getSelectionModel().getSelectedItem() != null) {
			
			Player playerToDelete = null;
			
			for(int i = 0; i < players.size(); i++) {
				if(players.get(i).compareTo(playersList.getSelectionModel().getSelectedItem()) == 0) {
					playerToDelete = playersObject.get(i);
					break;
				}
			}
			
			Worker.deletePlayer(playerToDelete.getPersonId());
			
			playersObject.remove(playerToDelete);
			
			fillList(playersObject);
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
