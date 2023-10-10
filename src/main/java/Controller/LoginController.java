package Controller;

import DbConnection.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
	
	private Stage stage;
	private Scene scene;
	
    @FXML
    PasswordField passwordField;

    @FXML
    TextField usernameField;

    public void login(ActionEvent event){
    	
        String username = usernameField.getText();
        String password = passwordField.getText();
        if(Worker.login(username,password)){
            try {
        		Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        		scene = new Scene(root);
        		stage.setScene(scene);
        		stage.show();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}
