package scenes;

import java.awt.image.BufferedImage;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import model.Issue;
import org.json.JSONException;

public class IssuePreview extends HBox{
	private Issue mIssue;
	private ImageView mThumb;
	private BufferedImage mBufferImage;
	private Label mInfoLabel;
	
        private Integer mHeight = 50;
        private Integer mWidth = 33;
        
	public IssuePreview(Issue rhIssue) throws JSONException {
		super();
		
		mIssue = rhIssue;
		long start = System.currentTimeMillis();
		mBufferImage = mIssue.getThumbImg();
		Image image = SwingFXUtils.toFXImage(mBufferImage, null);
		mThumb = new ImageView(image);
		System.out.println("Image fetch took :" + (System.currentTimeMillis() - start));
		mThumb.setFitHeight(mHeight);
		mThumb.setFitWidth(mWidth);
		
		String info = mIssue.getVolumeName() + " #" + mIssue.getIssueNum() + "\nDate: " + mIssue.getCoverDate(); 
		mInfoLabel = new Label(info);
		getChildren().addAll(mThumb, mInfoLabel);	
	}
	
	public Issue getmIssue(){
		return mIssue;
	}
	
	public BufferedImage getImage(){
		return mBufferImage;
	}
}
