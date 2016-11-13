package scenes;

import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import localDB.LocalDB;
import model.Issue;


import org.json.JSONException;


import application.Main;
import requests.CVImage;

public class DetailView extends BorderPane{
	private Button editButton;
	public DetailView(Issue issue) throws JSONException {
		super();
		
		if(!issue.isFull())
			issue.populate();
		
		//VBox left = new VBox();
		Label nameLbl = new Label("name");
		Label issueNumLbl = new Label("Issue Number#");
		Label cDateLbl = new Label("Cover Date");
		Label volNameLbl = new Label("Volume");
		Label writerLbl = new Label("Writers");
		//left.getChildren().addAll(volNameLbl,issueNumLbl,nameLbl,cDateLbl, writerLbl);
		
		//VBox center = new VBox();
		TextField name = new TextField(issue.getName());
		TextField issueNum = new TextField(issue.getIssueNum());
		TextField cDate = new TextField(issue.getCoverDate());
		TextField volName = new TextField(issue.getVolumeName());
		TextField writer = new TextField(issue.getPerson("writer"));
		writer.setEditable(false);
		//center.getChildren().addAll(volName,issueNum,name,cDate, writer);
		//name.getSelectedText();
		
		this.setPadding(new Insets(10));
		
		GridPane grid = new GridPane();
		grid.setVgap(30);
		grid.setHgap(10);
		
		grid.add(volNameLbl, 0, 1);
		grid.add(volName, 1, 1);
		
		grid.add(issueNumLbl, 0, 2);
		grid.add(issueNum, 1, 2);
		
		grid.add(cDateLbl, 0, 3);
		grid.add(cDate, 1, 3);
		
		grid.add(nameLbl, 0, 4);
		grid.add(name, 1, 4);
		
		
		grid.add(writerLbl, 0, 5);
		grid.add(writer, 1, 5);
		
		grid.add(new Label("artist"), 0, 6);
		grid.add(new TextField(issue.getPerson("art")), 1, 6);
		
		WebView descBox = new WebView();
		String desc = issue.getDescription();
		Document doc = Jsoup.parse(desc);
		doc.select("table").remove();
		desc = doc.toString();
		descBox.getEngine().loadContent(desc);
		descBox.setMaxHeight(300);
		//descBox.setFontScale(0.75);
		
		BufferedImage bi = issue.getMediumImg();
		Image image = SwingFXUtils.toFXImage(bi, null);
		ImageView imageView = new ImageView(image);
		
		imageView.setFitWidth(520);
		imageView.setFitHeight(800);
		
		setBottom(descBox);
		setMargin(descBox, new Insets(10));
		setLeft(grid);
		//setCenter(center);
		setRight(imageView);	
		
		editButton = new Button("Edit Issue");
		editButton.setVisible(true);
		editButton.setOnAction(e -> {
			LocalDB.update(issue.getID(), "name", name.getSelectedText().toString(), 0);
			LocalDB.update(issue.getID(), "issue_number", issueNum.getSelectedText().toString(), 0);
			LocalDB.update(issue.getID(), "cover_date", cDate.getSelectedText().toString(), 0);
			LocalDB.update(issue.getID(), "volume", volName.getSelectedText().toString(), 0);
		});
		grid.add(editButton, 0, 7);
	}

}
