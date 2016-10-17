package application;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Issue;
import scenes.AddComic;
import scenes.IssueResult;
import scenes.VolResult;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;


public class Main extends Application {
	private Stage window;
	private BorderPane layout;
	ListView<VolResult> list;
	ListView<IssueResult> issueList;

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("This is just a test");
		
		

//		Scene scene = new Scene(layout, 1900, 1050);
//		window.setScene(scene);
//		window.show();
		
		new AddComic(new ArrayList<Issue>());

	}

	public static void main(String[] args) {
		launch(args);
	}


}
