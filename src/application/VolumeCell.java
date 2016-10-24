package application;

import java.util.ArrayList;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.TreeItem;
import model.Issue;
import org.json.JSONException;
import scenes.IssuePreview;
import scenes.VolumePreview;

public class VolumeCell extends TreeItem{
	private VolumePreview mVolumePreview;
	private ContextMenu mEditMenu;
	boolean filled = false;

	public VolumeCell(VolumePreview inputVolumePreview) {
		super(inputVolumePreview);
		mVolumePreview = inputVolumePreview;
	}

	public void setIssues(ArrayList<Issue> allIssues) throws JSONException{
		for(Issue elementIssue : allIssues){
			if(elementIssue.getVolumeID().equals(mVolumePreview.getVolume().getID())){					
				getChildren().add(new TreeItem<IssuePreview>(new IssuePreview(elementIssue)));
			}
		}
		filled = true;
	}

	public boolean isFilled(){
		return filled;
	}
}
