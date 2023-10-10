package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import DbConnection.Worker;
import Model.Player;
import Model.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class playerRegistrationController implements Initializable{
	
	private Stage stage;
	private Scene scene;
	
	@FXML
	private Label gameLabel;
	
	@FXML
	private ChoiceBox<String> playerChoice;
	
	@FXML
	private ListView<String> playerList;
	
	public static String pickedGame;
	public static Game game;
	public static List<Game> games;
	
	private String selectedPlayer;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		gameLabel.setText(pickedGame);
		refreshLists();
	}
	
	public void back(ActionEvent event) throws IOException {
		
		Parent root = FXMLLoader.load(getClass().getResource("gamesView.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	private void refreshLists() {
		
		playerList.getItems().clear();
		playerChoice.getItems().clear();
		
		List<Integer> registeredPlayers = Worker.getRegisteredPlayersIds(game.getGameId());
		List<String> stringRegisteredPlayers = new ArrayList<>();
		
		for(Integer i : registeredPlayers)
			stringRegisteredPlayers.add(Player.getPlayerNameFromId(i));
		
		playerList.getItems().addAll(stringRegisteredPlayers);
		
		List<Integer> activePlayersIds = Worker.getActivePlayersIds();
		
		for(int i = 0; i < activePlayersIds.size();) {
			if(registeredPlayers.contains(activePlayersIds.get(i))) {
				activePlayersIds.remove(activePlayersIds.get(i));
			}
			else
				i++;
		}
		
		List<String> stringPlayers = new ArrayList<>();
		for(int i : activePlayersIds) 
			stringPlayers.add(Player.getPlayerNameFromId(i));
		
		playerChoice.getItems().addAll(stringPlayers);
		playerChoice.setOnAction(this::getSelectedPlayer);
	}
	
	public void getSelectedPlayer(ActionEvent event) {
		
		selectedPlayer = playerChoice.getValue();
	}
	
	public void registerPlayer(ActionEvent event) {
		
		if(selectedPlayer != null && playerList.getItems().size() < 12) {
			
			String name = selectedPlayer.split(" ")[0];
			String surname = selectedPlayer.split(" ")[1];
			Integer playerId = Player.getIdFromPlayerName(name, surname);
			
			Worker.registerPlayer(game.getGameId(), playerId);
			
			refreshLists();
		}
		
	}
}
