package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import localDB.LocalDB;
import model.Issue;
import scenes.AddComic;
import scenes.DetailView;
import scenes.IssuePreview;
import scenes.IssueResult;
import scenes.VolResult;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;


public class Main extends Application {
	private Stage window;
	private BorderPane layout;
	private ListView<VolResult> list;
	private ListView<IssueResult> issueList;
	private static ArrayList<Issue> added;
	private static ArrayList<Issue> allIssues;
	private static List<IssuePreview> prevs;
	private static ListView<IssuePreview> leftList;
	private static ObservableList<IssuePreview> obvRes;
	private static ScrollPane leftScroll;
	private HBox hbox;
	private Button addButton;

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("This is just a test");
		layout = new BorderPane();

		added = new ArrayList<Issue>();

		allIssues = LocalDB.getAllIssues();
		prevs = new ArrayList<IssuePreview>();

		for(Issue i: allIssues){
			prevs.add(new IssuePreview(i));
		}

		leftList = new ListView<>();
		leftList.setPrefHeight(1000);
		obvRes = FXCollections.observableList(prevs);
		leftList.setItems(obvRes);

		leftScroll = new ScrollPane();
		leftScroll.setPrefHeight(1000);
		leftScroll.setContent(leftList);
		leftScroll.setPadding(new Insets(10));
		layout.setLeft(leftScroll);

		hbox = new HBox();
		hbox.setPadding(new Insets(10));
		addButton = new Button("Click here to add");
		hbox.getChildren().add(addButton);
		layout.setTop(hbox);
		
		addButton.setOnAction(e -> {
			new AddComic(added);
			updateLeft();
		});

		/*new AddComic(added);

		for(Issue i: added){
			LocalDB.addIssue(i);
		}

		VBox details = new VBox(10);
		Vector<DetailView> vec = new Vector<>();

		for(Issue i: added){
			vec.add(new DetailView(i));
		}

		details.getChildren().addAll(vec);*/

		//Scene scene = new Scene(new ScrollPane(details), 1900, 1050);

		Scene scene = new Scene(layout, 1900, 1050);
		window.setScene(scene);
		window.show();
	}

	public static void main(String[] args) {
		launch(args);
		//System.out.println("to adjust");
	}

	public static void updateLeft(){
		for(Issue i: added){
			LocalDB.addIssue(i);
			prevs.add(new IssuePreview(i));
		}
		obvRes = FXCollections.observableList(prevs);
		leftList.setItems(obvRes);
		leftScroll.setContent(leftList);
		added.clear();		
	}
}
