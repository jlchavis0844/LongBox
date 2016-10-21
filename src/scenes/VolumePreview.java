package scenes;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import model.Issue;
import model.Volume;

public class VolumePreview extends HBox{
	private Volume vol;
	private ImageView thumb;
	private Label infoLbl;

	public VolumePreview(Volume rhVol, ArrayList<Issue> issues) {
		super();
		
		vol = rhVol;
		long start = System.currentTimeMillis();
		BufferedImage bi = vol.getImage("thumb");
		Image image = SwingFXUtils.toFXImage(bi, null);
		thumb = new ImageView(image);
		System.out.println("Image fetch took :" + (System.currentTimeMillis() - start));
		thumb.setFitHeight(50);
		thumb.setFitWidth(33);
		
		int counter = 0;
		for(Issue i : issues){
			if(i.getVolumeName().equals(vol.getName())){
				counter++;
			}
		}
		
		String info = vol.getName() + "\n" + vol.getPublisher()  + "     " + vol.getStartYear() +
					"\n" + counter + " out of " + vol.getCountofIssue() + " in collection";
		
		infoLbl = new Label(info);
		getChildren().addAll(thumb, infoLbl);
	}
	
	public String getVolName(){
		return vol.getName();
	}

}
