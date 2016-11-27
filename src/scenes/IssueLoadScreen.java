package scenes;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.adapter.ReadOnlyJavaBeanStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import localDB.LocalDB;
import model.Issue;
import javafx.scene.paint.Color;
//
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
//
public class IssueLoadScreen{
	private BorderPane layout;

	public IssueLoadScreen(ArrayList<Issue> added, ArrayList<Issue> allIssues, List<VolumePreview> volPreviews) {

		// 1 verticle box for the whole thing
		// for each issue add an Horizontal box
		// the box have the issue icon on the left
		// text in central and add or notadd label on right.
		VBox myVBox = new VBox(10);
		//myVBox.getChildren().add(myHBox);
		ArrayList<HBox> myHBoxArr = new ArrayList<HBox>();
		
		// check for the issue in DB and figure out whether 
		// we need to add them or not then create IssueHbox 
		// for each issue and put them in an arraylist of HBox
		// so that we can add all of the HBox in the VBox later on
		ArrayList<Issue> temp = new ArrayList<Issue>();
		ArrayList<Issue> notadd = new ArrayList<Issue>();
		for(Issue i: added){
			if(!(LocalDB.exists(i.getID(), LocalDB.ISSUE))){
				// not in db ok to add
				temp.add(i);
				HBox myhbox = new IssueHbox(i,true);
				myHBoxArr.add(myhbox);
			}
			else{
				notadd.add(i);
				HBox myhbox = new IssueHbox(i,false);
				myHBoxArr.add(myhbox);
			}
		}
		myVBox.getChildren().addAll(myHBoxArr);
		
		Stage window = new Stage();
		window.setTitle("Progress Controls");
		window.initModality(Modality.APPLICATION_MODAL);
		layout = new BorderPane();

		layout.setTop(myVBox);
		BorderPane.setMargin(myVBox, new javafx.geometry.Insets(10));
		
		Scene scene = new Scene(layout, 900, 500);
		window.setScene(scene);
		window.showAndWait();
		
		window.setOnCloseRequest(e ->{
			for(Issue i: temp){

				LocalDB.addIssue(i);
				allIssues.add(i);
				int foundIndex = -1;
				for(int j = 0; j < volPreviews.size(); j++){
					if(volPreviews.get(j).getVolName().equals(i.getVolumeName())){
						foundIndex = j;
						volPreviews.get(j).update(allIssues);
					}
				}

				if(foundIndex == -1){
					volPreviews.add(new VolumePreview(i.getVolume(), allIssues));
				}
				for(VolumePreview pv: volPreviews){
					pv.setImage();
				}

			}		
		});

	}
	
}
