package scenes;

import java.awt.MenuItem;
import java.awt.image.BufferedImage;
import java.beans.EventHandler;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import model.Issue;
//////////////
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.input.ContextMenuEvent;

import javafx.event.*;
/////////////////
public class IssuePreview extends HBox{
	private Issue issue;
	private ImageView thumb;
	private BufferedImage bi;
	private Label infoLbl;
	
	public IssuePreview(Issue rhIssue) {
		super();
		
		issue = rhIssue;
		long start = System.currentTimeMillis();
		bi = issue.getThumbImg();
		Image image = SwingFXUtils.toFXImage(bi, null);
		thumb = new ImageView(image);
		System.out.println("Image fetch took :" + (System.currentTimeMillis() - start));
		thumb.setFitHeight(50);
		thumb.setFitWidth(33);
		
		String info = issue.getVolumeName() + " #" + issue.getIssueNum() + "\nDate: " + issue.getCoverDate(); 
		infoLbl = new Label(info);
		getChildren().addAll(thumb, infoLbl);
		/*
		//http://o7planning.org/en/11115/javafx-contextmenu-tutorial
		
		ContextMenu contextMenu = new ContextMenu();
        MenuItem item1 = new MenuItem("Menu Item 1");
        item1.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                // localDB.remove
            	// we need to add remove into localdb
            	// how to updateleft in this class???
            	// updateleft in the main use added list
            	// we got problem here so for now don't update
            	
            }
        });
        // Add MenuItem to ContextMenu
        contextMenu.getItems().addAll(item1);
        // When user right-click issue preview
        setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
 
            @Override
            public void handle(ContextMenuEvent event) {
 
                contextMenu.show(IssuePreview, event.getScreenX(), event.getScreenY());
            }
        });
        */
	}
	
	public Issue getIssue(){
		return issue;
	}
	
	public BufferedImage getImage(){
		return bi;
	}
}
