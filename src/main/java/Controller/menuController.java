package Controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class menuController {
	
	private Stage stage;
	private Scene scene;
	
	public void toPlayers(ActionEvent event) throws IOException {
		
		Parent root = FXMLLoader.load(getClass().getResource("playersView.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void toOponents(ActionEvent event) throws IOException {
		
		Parent root = FXMLLoader.load(getClass().getResource("oponentsView.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void toGames(ActionEvent event) throws IOException {
		
		Parent root = FXMLLoader.load(getClass().getResource("gamesView.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
