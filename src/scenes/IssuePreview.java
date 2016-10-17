package scenes;

import java.awt.image.BufferedImage;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import model.Issue;

public class IssuePreview extends HBox{
	private Issue issue;
	private ImageView thumb;
	private Label infoLbl;
	
	public IssuePreview(Issue rhIssue) {
		super();
		
		issue = rhIssue;
		BufferedImage bi = issue.getThumbImg();
		Image image = SwingFXUtils.toFXImage(bi, null);
		thumb = new ImageView(image);
		thumb.setFitHeight(50);
		thumb.setFitWidth(33);
		
		String info = issue.getVolumeName() + " #" + issue.getIssueNum() + " Date: " + issue.getCoverDate(); 
		infoLbl = new Label(info);
		getChildren().addAll(thumb, infoLbl);
		
	}

}
