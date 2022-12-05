package application;
	
import java.io.FileInputStream;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
//import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	public static Stage mainStage;
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			VBox root= loader.load(new FileInputStream("src/application/LifeExpectancyView.fxml"));
			Scene scene = new Scene (root, 1050, 400);
			
			LifeExpectancyController controller = (LifeExpectancyController)loader.getController();
			controller.applicationStage = primaryStage;
			
			mainStage = primaryStage;
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Carley Irwin's Final Project");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
