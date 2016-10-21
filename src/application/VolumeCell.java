package application;

import java.util.ArrayList;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.TreeItem;
import model.Issue;
import scenes.IssuePreview;
import scenes.VolumePreview;

public class VolumeCell extends TreeItem{
	private VolumePreview vp;
	private ContextMenu editMenu;
	boolean filled = false;

	public VolumeCell(VolumePreview vPre) {
		super(vPre);
		vp = vPre;
	}

	
	public void setIssues(ArrayList<Issue> allIssues){
		for(Issue i : allIssues){
			if(i.getVolumeName().equals(vp.getVolName())){					
				getChildren().add(new TreeItem<IssuePreview>(new IssuePreview(i)));
			}
		}
		filled = true;
	}
	
	public boolean isFilled(){
		return filled;
	}
}
