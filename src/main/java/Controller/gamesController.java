package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import DbConnection.Worker;
import Model.Season;
import Model.Competition;
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

public class gamesController implements Initializable{
	
	private Stage stage;
	private Scene scene;
	
	@FXML
	private ListView<String> gamesList;
	
	@FXML
	private ChoiceBox<String> seasonList;
	
	@FXML
	private ChoiceBox<String> competitionList;
	
	@FXML
	private Label filterLabel;
	private int counter = 0;
	private String pickedSeason;
	private String pickedCompetition;
	private List<String> games = new ArrayList<>();
	private List<Game> gamesObject = new ArrayList<>();
	private List<Game> filteredGames = new ArrayList<>();
	
	//Getting all played games from the database and displaying them in formated way on Games gui, populating the
	//choice boxes with season marks and competition names
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		counter = 0;
		gamesObject = Worker.getAllGames();
		playerRegistrationController.games = gamesObject;
		
		List<String> seasonNames = Worker.getSeasonNames();
		List<String> competitionNames = Worker.getCompetitionNames();
		
		seasonList.getItems().addAll(seasonNames);
		seasonList.setOnAction(this::getSeason);
		competitionList.getItems().addAll(competitionNames);
		competitionList.setOnAction(this::getCompetition);
		
		fillList(gamesObject);
	}
	
	//Method which displays and allows user to register home players for the picked game. Since the list of games
	//can be either all or filtered, based on counter and string respresentation of the game, game object is extracted
	//and passed to the playerRegistrationController class
	public void registrationView(ActionEvent event) throws IOException {
		
		if(gamesList.getSelectionModel().getSelectedItem() != null) {
			playerRegistrationController.pickedGame = gamesList.getSelectionModel().getSelectedItem();
			
			if(counter == 0)
				for(int i = 0; i < games.size(); i++) {
					if(games.get(i).compareTo(gamesList.getSelectionModel().getSelectedItem()) == 0) {
						playerRegistrationController.game = gamesObject.get(i);
						break;
					}
				}
			else
				for(int i = 0; i < games.size(); i++) {
					if(games.get(i).compareTo(gamesList.getSelectionModel().getSelectedItem()) == 0) {
						playerRegistrationController.game = filteredGames.get(i);
						break;
					}
				}

			Parent root = FXMLLoader.load(getClass().getResource("playerRegistrationView.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	}
	
	public void getSeason(ActionEvent event) {
		
		pickedSeason = seasonList.getValue();
	}
	
	public void getCompetition(ActionEvent event) {
		
		pickedCompetition = competitionList.getValue();
	}
	
	//Getting the list of games based on the selected season and competition, and incrementing the counter to indicate
	//other methods that games are filtered
	public void showFilteredGames(ActionEvent event) {
		
		if(pickedSeason == null || pickedCompetition == null)
			filterLabel.setText("Both fields are required!");
		else {
			counter++;
			
			Integer seasonId = Season.getSeasonIdFromName(pickedSeason);
			Integer competitionId = Competition.getCompetitionIdFromName(pickedCompetition);
			
			filteredGames = Worker.getFilteredGames(gamesObject, seasonId, competitionId);
			
			fillList(filteredGames);
		}
	}
	
	//Method used for displaying games, in formatted way, as strings
 	private void fillList(List<Game> gamesObject) {
		
		gamesList.getItems().clear();
		
		games.clear();
		
		for(int i = 0; i < gamesObject.size(); i++) {
			Game u = gamesObject.get(i);
			String game = "";
			
			String season = Season.getSeasonNameFromId(u.getSeason_and_competition().getSeasonId());
			
			String competition = Competition.getCompetitionNameFromId(u.getSeason_and_competition().getCompetitionId());
			
			game += season + ", " + competition;
			
			if(u.isHome())
				game += " | My BC : " + u.getOponent().getName();
			else
				game += "| " + u.getOponent().getName() + " : My BC";
			
			game += " | " + u.getHall().getName();
			
			game += " | " + u.getGameDate();
			
			if(u.isHome())
				game += " | " + u.getClubPoints() + " : " + u.getOponentPoints();
			else
				game += " | " + u.getOponentPoints() + " : " + u.getClubPoints();
			
			game += " | " + u.getScore();
			games.add(game);
		}
		
		gamesList.getItems().addAll(games);
	}
	
	public void addNewGame(ActionEvent event) throws IOException {
		
		Parent root = FXMLLoader.load(getClass().getResource("newGameView.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	//Getting the game object based on the counter and the string representation of the game, removing the game from the
	//list, updating the gui, and soft-deleting the game from the database using Worker class
	public void deleteGame(ActionEvent event) {
		
		Game gameToDelete = null;
		if(gamesList.getSelectionModel().getSelectedItem() != null) {
			
			if(counter == 0)
				for(int i = 0; i < games.size(); i++) {
					if(games.get(i).compareTo(gamesList.getSelectionModel().getSelectedItem()) == 0) {
						gameToDelete = gamesObject.get(i);
						break;
					}
				}
			else
				for(int i = 0; i < games.size(); i++) {
					if(games.get(i).compareTo(gamesList.getSelectionModel().getSelectedItem()) == 0) {
						gameToDelete = filteredGames.get(i);
						break;
					}
				}
			
			Worker.deleteGame(gameToDelete.getGameId());
			
			gamesObject.remove(gameToDelete);
			
			fillList(gamesObject);
		}
	}
	
	//Getting the game object based on the counter and the string representation of the game, opening new window
	//for changing the game parameters
	public void updateGame(ActionEvent event) throws IOException {
		
		if(gamesList.getSelectionModel().getSelectedItem() != null) {
			
			if(counter == 0)
				for(int i = 0; i < games.size(); i++) {
					if(games.get(i).compareTo(gamesList.getSelectionModel().getSelectedItem()) == 0) {
						updateGameController.pickedGame = gamesObject.get(i);
						break;
					}
				}
			else
				for(int i = 0; i < games.size(); i++) {
					if(games.get(i).compareTo(gamesList.getSelectionModel().getSelectedItem()) == 0) {
						updateGameController.pickedGame = filteredGames.get(i);
						break;
					}
				}
			
			Parent root = FXMLLoader.load(getClass().getResource("updateGameView.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
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
