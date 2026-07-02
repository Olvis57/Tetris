package application;
	
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;



public class Main extends Application {
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
			Parent root = loader.load();
			Controlls controller = loader.getController();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show(); 
			
			
			scene.setOnKeyPressed(e -> {
				System.out.println(e.getCode());
				switch(e.getCode()) {
				
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
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
