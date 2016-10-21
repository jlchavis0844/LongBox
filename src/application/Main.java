package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.json.simple.JSONObject;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import localDB.LocalDB;
import model.Issue;
import model.Volume;
import requests.CVrequest;
import scenes.AddComic;
import scenes.DetailView;
import scenes.IssuePreview;
import scenes.IssueResult;
import scenes.VolResult;
import scenes.VolumePreview;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.*;


public class Main extends Application {
	private Stage window;
	private BorderPane layout;
	private ListView<VolResult> list;
	private ListView<IssueResult> issueList;
	private static ArrayList<Issue> added;
	private static ArrayList<Issue> allIssues;
	private static ArrayList<Volume> allVols;
	private static List<IssuePreview> prevs;
	private static List<VolumePreview> volPreviews;
	private static ListView<IssuePreview> leftList;
	private static ListView<VolumePreview> volListView;
	private static ObservableList<IssuePreview> obvRes;
	private static ObservableList<VolumePreview> volObvRes;
	private static ScrollPane leftScroll;
	private HBox hbox;
	private Button addButton;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("Digital Long Box");
		layout = new BorderPane();

		added = new ArrayList<Issue>();
		
		System.out.println("getting all issues");
		Long start;
		start = System.currentTimeMillis();
		allIssues = LocalDB.getAllIssues();
		System.out.println("Done loading after " + (System.currentTimeMillis() - start));
		
//		System.out.println("generating previews");
//		start = System.currentTimeMillis();
//		prevs = new ArrayList<IssuePreview>();
//
//		Long pStart = (long) 0.0;
//		Vector<Long> times = new Vector<Long>();
//		
//		for(Issue i: allIssues){
//			pStart = System.currentTimeMillis();
//			prevs.add(new IssuePreview(i));
//			times.addElement(System.currentTimeMillis() - pStart);
//		}
//		System.out.println("Done loading previews after " + (System.currentTimeMillis() - start));
//		
//		Long total = (long)0.0;
//		for(Long t: times){
//			total = total + t;
//		}
//		
//		System.out.println("Avg preview build time: " +(total / times.size()));
//		
//		
//		
//		System.out.println("building window");
//		start = System.currentTimeMillis();
//		leftList = new ListView<>();
//		leftList.setPrefHeight(1000);
//		obvRes = FXCollections.observableList(prevs);
//		leftList.setItems(obvRes);
//
//		leftScroll = new ScrollPane();
//		leftScroll.setPrefHeight(1000);
//		leftScroll.setContent(leftList);
//		leftScroll.setPadding(new Insets(10));
//		layout.setLeft(leftScroll);

		allVols = LocalDB.getAllVolumes();
		volPreviews = new ArrayList<VolumePreview>();
		
		
		for(Volume v: allVols){
			volPreviews.add(new VolumePreview(v, allIssues));
		}
		
//		volListView = new ListView<>();
//		volListView.setPrefHeight(1000);
//		volObvRes = FXCollections.observableList(volPreviews);
//		volListView.setItems(volObvRes);
				
		TreeItem root = new TreeItem<VolumePreview>();
		
		root.setValue("Volumes");
		root.setExpanded(true);
		for(VolumePreview vp: volPreviews){
			TreeItem temp = new VolumeCell(vp);
			root.getChildren().add(temp);
		}
		TreeView treeView = new TreeView<VolumePreview>(root);
		treeView.setPrefWidth(500);
		
		treeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem>() {

			@Override
			public void changed(ObservableValue<? extends TreeItem> observable, TreeItem oldValue, TreeItem newValue) {
				if(newValue instanceof VolumeCell){
					if(!((VolumeCell) newValue).isFilled())
						((VolumeCell) newValue).setIssues(allIssues);
				} else {
					TreeItem<IssuePreview> ti = (TreeItem<IssuePreview>) treeView.getSelectionModel().getSelectedItem();
					Issue issue = ti.getValue().getIssue();
					layout.setRight(new DetailView(issue));
				}
				boolean expanded = ((TreeItem) treeView.getSelectionModel().getSelectedItem()).isExpanded();
				newValue.setExpanded(!expanded);
			}
			
		});
		leftScroll = new ScrollPane();
		leftScroll.setPrefHeight(1000);
		//leftScroll.setContent(volListView);
		//leftScroll.setContent(treeView);
		leftScroll.setPadding(new Insets(10));
		
		layout.setLeft(treeView);
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
		System.out.println("Done loading after " + (System.currentTimeMillis() - start));
	}

	public static void main(String[] args) {
//		org.json.JSONObject jo = CVrequest.getIssue("488852");
//		Volume test = new Volume(jo.getJSONObject("volume"));
//		LocalDB.addVolume(test);
//		new VolumePreview(test);
	
		
		launch(args);
		//System.out.println("to adjust");
		
		//System.exit(0);
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
