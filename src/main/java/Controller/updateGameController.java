package Controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import DbConnection.Worker;
import Model.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class updateGameController implements Initializable{
	
	private Stage stage;
	private Scene scene;
	
	@FXML
	private DatePicker newDatePicker;
	
	@FXML 
	private TextField clubPoints, oponentPoints;

	@FXML
	private Label label;
	
	public static Game pickedGame;
	private LocalDate newGameDate;
	
	
	public void getDate(ActionEvent event) {
		
		LocalDate myDate = newDatePicker.getValue();
		newGameDate = myDate;
	}
	
	//Reading and parsing the input parameters and passing them to the method updateGame of class Worker
	public void update(ActionEvent event) throws IOException {
		
		 try {
			 int newCp = 0;
			 int newOp = 0;
			 LocalDate gameDate = pickedGame.getGameDate();
 
			 newCp = Integer.parseInt(clubPoints.getText());
			
			 newOp = Integer.parseInt(oponentPoints.getText());
			
			 
			 if(newGameDate != null) 
				gameDate = newGameDate;
			
			Worker.updateGame(pickedGame.getGameId(), gameDate, newCp, newOp);
			
			Parent root = FXMLLoader.load(getClass().getResource("gamesView.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		 }
		 catch(Exception e) {
			 e.printStackTrace();
			 label.setText("Points must be positive round numbers!");
		 }
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		clubPoints.setText(String.valueOf(pickedGame.getClubPoints())); 
		oponentPoints.setText(String.valueOf(pickedGame.getOponentPoints()));
		
	}
}
