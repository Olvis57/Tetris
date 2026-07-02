package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameOverControlls {
	
	@FXML
	private AnchorPane rootEnd;
	@FXML
	private Text pointsEnd;
	@FXML
	private Text highscore;
	@FXML
	private Button tryagain;
	
	
	
	public void initialize(){	
		tryagain.setFocusTraversable(false);
	}
	
	public void setScore(int points) {
		pointsEnd.setText(String.valueOf(points));
	}
	
	public void setHighscore(int points) {
		highscore.setText(String.valueOf(points));
	}
	
	public void newGame(ActionEvent e) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
		Parent root = loader.load();
		Controlls controller = loader.getController();
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage stage = (Stage) tryagain.getScene().getWindow();
		stage.close();
		stage.setScene(scene);
		stage.show();
		
		scene.setOnKeyPressed(a -> {
			System.out.println(a.getCode());
			switch(a.getCode()) {
			
			case LEFT:
				controller.leftB();
				break;
			case RIGHT:
				controller.rightB();
				break;
			case DOWN:
				controller.downB();
				break;
			case UP:
				controller.turn();
				break;
			case SPACE:
				controller.space();
				break;
			case C:
				controller.hold();
				break;
			}
		});
		scene.getRoot().requestFocus();
		
	}
	
	
}
