package scenes;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;
import requests.CVrequest;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.json.JSONException;

public class AddComic {
	private Button srchButton;
	private Button addButton;
	private Button backButton;
	private Button deleteButton;
	private Button removeButton;
	private BorderPane layout;
	private HBox topBox;
	private TextField input;
	private TextField pubName;
	private ScrollPane scPane;
	ListView<VolResult> list;
	ListView<IssueResult> issueList;
	ArrayList<Issue> addList;


	public AddComic(ArrayList<Issue> added) {
		addList = added;
		Stage window = new Stage();
		window.setTitle("Add Comics!");
		window.initModality(Modality.APPLICATION_MODAL);
		window.setOnCloseRequest(e ->{
			e.consume();
			boolean temp = ConfirmBox.display("Close Add Comic?", "Are you done adding comics?");
			if(temp){
				window.close();
			}
		});

		layout = new BorderPane();
		topBox = new HBox(10);
		list = new ListView<VolResult>();
		list.setMinWidth(1000);
		list.setMaxWidth(1200);
		list.setMinHeight(800);

		issueList = new ListView<IssueResult>();
		issueList.setMinWidth(1000);
		issueList.setMaxWidth(1200);

		list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<VolResult>() {
			@Override
			public void changed(ObservableValue<? extends VolResult> observable, VolResult oldValue, VolResult newValue) {
				String vID = null;
				VolResult temp = list.getSelectionModel().getSelectedItem();

				if(temp != null){
                                    try {
                                        vID = temp.getVolID();
                                    } catch (JSONException ex) {
                                        Logger.getLogger(AddComic.class.getName()).log(Level.SEVERE, null, ex);
                                    }
				}

				System.out.println("Fetching " + vID);
                            try {		
                                getIssues(vID);
                            } catch (JSONException ex) {
                                Logger.getLogger(AddComic.class.getName()).log(Level.SEVERE, null, ex);
                            }
			}
		});

		//leftBox = new VBox();
		//scPane = new ScrollPane(leftBox);

		scPane = new ScrollPane();
		scPane.setMaxWidth(1200);
		scPane.setMinWidth(1000);
		scPane.setMinHeight(800);
		scPane.setFitToWidth(true);

		input = new TextField();
		input.setPromptText("Enter Search Terms");
		input.setMinWidth(600);

		pubName = new TextField();
		pubName.setPromptText("Publisher Name");
		pubName.setMinWidth(200);

		srchButton = new Button("Search");
		addButton = new Button("Add Issue");
		backButton = new Button("Back");
		removeButton = new Button("Remove");
		deleteButton = new Button("Delete");
		addButton.setVisible(false);
		backButton.setVisible(false);
		removeButton.setVisible(false);
		deleteButton.setVisible(false);
		
		addButton.setOnAction(e -> {
			Issue iSel = issueList.getSelectionModel().getSelectedItem().getIssue();
			if(iSel != null){
                            try {
                                System.out.println("Adding "+ iSel.getVolumeName() + " #" + iSel.getIssueNum());
                            } catch (JSONException ex) {
                                Logger.getLogger(AddComic.class.getName()).log(Level.SEVERE, null, ex);
                            }
			} else System.out.println("issue is null");

			addList.add(iSel);
			addButton.setVisible(false);
            removeButton.setVisible(true);
            backButton.setVisible(true);
		});


		srchButton.setOnAction(e -> {
			addButton.setVisible(false);
			scPane.setContent(list);
			System.out.println(input.getText());
                    try {
                        volSearch(input.getText(), pubName.getText());
                    } catch (JSONException ex) {
                        Logger.getLogger(AddComic.class.getName()).log(Level.SEVERE, null, ex);
                    }
		});
		
		backButton.setOnAction(e -> {
			addButton.setVisible(false);
			scPane.setContent(list);
			backButton.setVisible(false);
			removeButton.setVisible(false);
			deleteButton.setVisible(false);
		});
		
		// get the issue that user selected
		// loop through the addList and add all issue to tempList
		// except the user selected issue. 
		deleteButton.setOnAction(e -> {		
			Issue iSel = issueList.getSelectionModel().getSelectedItem().getIssue();
			ArrayList<Issue> tempList = new ArrayList<Issue>();
			for(int i = 0; i < addList.size(); i++){
				if(iSel.equals(addList.get(i))){
					// we skip because this item should not be in new list
					continue;
				}
				tempList.add(addList.get(i));
			}
			addList = tempList;		
			deleteButton.setVisible(false);
			backButton.setVisible(true);
		});
		
		removeButton.setOnAction(e -> {		
			addList.remove(addList.size()-1);
            removeButton.setVisible(false);
            addButton.setVisible(true);
            backButton.setVisible(true);
		});

		topBox.getChildren().addAll(input, pubName, srchButton, addButton, backButton, removeButton, deleteButton);
		//topBox.getChildren().addAll(input, pubName, srchButton, addButton);

		//layout.setPadding(new javafx.geometry.Insets(10));
		layout.setTop(topBox);
		layout.setLeft(scPane);
		BorderPane.setMargin(scPane, new javafx.geometry.Insets(10));
		BorderPane.setMargin(topBox, new javafx.geometry.Insets(10));

		Scene scene = new Scene(layout, 1900, 1050);
		window.setScene(scene);
		window.showAndWait();
	}

	private void volSearch(String term, String pub) throws JSONException{
		ArrayList<Volume> vols;

		if(pub.equals("") || pub == null){
			vols = CVrequest.searchVolume(term);
		} else {
			vols = CVrequest.searchVolume(term, pub);
		}

		List<VolResult> results = new ArrayList<VolResult>();

		for(Volume v: vols){
			//leftBox.getChildren().add(new VolumeButton(v, this));
			results.add(new VolResult(v));
		}

		ObservableList<VolResult> obvRes = FXCollections.observableList(results);
		list.setItems(obvRes);

	}

	public void  getIssues(String volID) throws JSONException{
		ArrayList<Issue> issues = CVrequest.getVolumeIDs(volID);
		List<IssueResult> results = new ArrayList<IssueResult>();

		for(Issue i: issues){
			results.add(new IssueResult(i));
		}
		// try to sort the results list here
		ObservableList<IssueResult> obvRes = FXCollections.observableList(results);

		issueList = new ListView<IssueResult>();
		issueList.setItems(obvRes);
		issueList.setMinWidth(scPane.getWidth());
		issueList.setMinHeight(700);
		issueList.setPrefSize(600, 900);
		issueList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<IssueResult>() {

			@Override
			public void changed(ObservableValue<? extends IssueResult> observable, IssueResult oldValue,
					IssueResult newValue) {
                            try {
                                layout.setCenter(new DetailView(newValue.getIssue()));
                            } catch (JSONException ex) {
                                Logger.getLogger(AddComic.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                Issue iSel = issueList.getSelectionModel().getSelectedItem().getIssue();
                if(addList.contains(iSel)){
                	deleteButton.setVisible(true);
                	addButton.setVisible(false);
                }
                else if(!addList.contains(iSel)){
                	deleteButton.setVisible(false);
                	addButton.setVisible(true);
                }
			}
		});
		scPane.setContent(issueList);
		System.out.println("scPane: " + scPane.getHeight() + "\tissueList " + issueList.getHeight());
	}

}
