package Controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import DbConnection.Worker;
import Model.Hall;
import Model.Oponent;
import Model.Season;
import Model.Season_and_Competition;
import Model.Competition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

public class newGameController implements Initializable{
	
	private int radio = 0;
	
	private Stage stage;
	private Scene scene;
	
	@FXML
	private DatePicker myDatePicker;
	
	@FXML
	private RadioButton home, away;
	
	@FXML
	private ChoiceBox<String> hall;
	
	@FXML
	private ChoiceBox<String> oponent;
	
	@FXML
	private ChoiceBox<String> s_i_t;
	
	@FXML
	private Label myLabel;
	
	private LocalDate gameDate;
	private boolean homeGame;
	private String pickedHall;
	private String pickedOponent;
	private String pickedS_i_t;
	
	public void getDate(ActionEvent event) {
		
		LocalDate myDate = myDatePicker.getValue();
		gameDate = myDate;
	}
	
	public void getRadioInfo(ActionEvent event) {
		radio++;
		homeGame = home.isSelected();
	}
	
	public void getHall(ActionEvent event) {
		pickedHall = hall.getValue();
	}
	
	public void getOponent(ActionEvent event) {
		pickedOponent = oponent.getValue();
	}
	
	public void getSiT(ActionEvent event) {
		pickedS_i_t = s_i_t.getValue();
	}
	
	//Checking if all fields are entered. If so, the game parameters are passed to a Worker class which will save new game in database
	public void addGame(ActionEvent event) throws IOException {
		
		if(gameDate == null || radio == 0 || pickedHall == null || pickedOponent == null || pickedS_i_t == null)
			myLabel.setText("All fields are required!");
		else {
			int hallId = Hall.getHallIdFromName(pickedHall);
			
			int oponentId = Oponent.getOponentIdFromName(pickedOponent);
			
			String s = pickedS_i_t.split(", ")[0];
			String t = pickedS_i_t.split(", ")[1];
			
			int seasonId = Season.getSeasonIdFromName(s);
			int competitionId = Competition.getCompetitionIdFromName(t);
			
			Worker.addNewGame(gameDate, 0, 0, homeGame, oponentId, seasonId, competitionId, hallId);
			
			Parent root = FXMLLoader.load(getClass().getResource("gamesView.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	}

	//Initializing the choice boxes with season and competition string representation after extracting all season marks and competiton
	//names using Worker class.
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		List<String> hallNames = Worker.getHallNames();
		hall.getItems().addAll(hallNames);
		hall.setOnAction(this::getHall);
		
		List<String> oponentNames = Worker.getOponentNames();
		oponent.getItems().addAll(oponentNames);
		oponent.setOnAction(this::getOponent);
		
		List<Season_and_Competition> SeasonsAndCompetitions = Worker.getSeasonsAndCompetitions();
		
		List<String> SeasonsAndCompetitionsNames = new ArrayList<>();
		for(Season_and_Competition s : SeasonsAndCompetitions) {
			String seasonName = Season.getSeasonNameFromId(s.getSeasonId());
			String competitionName = Competition.getCompetitionNameFromId(s.getCompetitionId());
			
			SeasonsAndCompetitionsNames.add(seasonName + ", " + competitionName);
		}
		
		s_i_t.getItems().addAll(SeasonsAndCompetitionsNames);
		s_i_t.setOnAction(this::getSiT);
	}
}
