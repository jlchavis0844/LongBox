package scenes;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.adapter.ReadOnlyJavaBeanStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import localDB.LocalDB;
import model.Issue;

public class IssueLoadScreen {

	public IssueLoadScreen(ArrayList<Issue> added, ArrayList<Issue> allIssues, List<VolumePreview> volPreviews) {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		Group root = new Group();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Progress Controls");
		Label label = new Label("loading new comics...");

		HBox hb = new HBox();
		hb.setSpacing(5);
		hb.setAlignment(Pos.CENTER);
		hb.getChildren().addAll(label);
		scene.setRoot(hb);
		stage.show();
		// create a Vbox then inside the vbox create an Hbox
		// the Hbox then contain the issue mini icon on far left
		// in central it has the issue text
		// then inside the for loop we call exist in localDB
		// if it does exist then on far right show the NOT ADDED label maybe?
		// else show ADDED label and then add it in the localDb and issuelist volumepre
		// then wait 1000 ms loop around redo. once finished shut down the scene
		// check to see if it already there in DB or not
		ArrayList<Issue> temp = new ArrayList<Issue>();
		ArrayList<Issue> notadd = new ArrayList<Issue>();
		for(Issue i: added){
			if(!(LocalDB.exists(i.getID(), LocalDB.ISSUE))){
				// not in db ok to add
				temp.add(i);
			}
			else{
				notadd.add(i);
			}
		}
		// update the added list so now only
		// the issue that is not in DB 
		added = temp;
		
		// now add them in DB and issue list
		// and the volume preview
		for(Issue i: added){

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
		// create issue preview for added issue and display them
		// so that user can see what is added.
		
		stage.close();
	}
}
