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
import org.json.JSONException;

public class VolumePreview extends HBox {

    private Volume volume;
    private ImageView thumb;
    private Label infoLbl;

    public VolumePreview(Volume inputVolume, ArrayList<Issue> issues) throws JSONException {
        super();

        volume = inputVolume;
        long start = System.currentTimeMillis();
        BufferedImage bufferimage = volume.getImage("thumb");
        if (bufferimage != null) {
            Image image = SwingFXUtils.toFXImage(bufferimage, null);
            thumb = new ImageView(image);
            System.out.println("Image fetch took :" + (System.currentTimeMillis() - start));
            thumb.setFitHeight(50);
            thumb.setFitWidth(33);

            int counter = 0;
            for (Issue i : issues) {
                if (i.getVolumeID().equals(volume.getID())) {
                    counter++;
                }
            }

            String info = volume.getName() + "\n" + volume.getPublisher() + "     " + volume.getStartYear()
                    + "\n" + counter + " out of " + volume.getCountofIssue() + " in collection";

            infoLbl = new Label(info);
            getChildren().addAll(thumb, infoLbl);
        }

    }

    public String getVolName() {
        return volume.getName();
    }

    public void update(ArrayList<Issue> issues) throws JSONException {
        int counter = 0;
        for (Issue i : issues) {
            if (i.getVolumeID().equals(volume.getID())) {
                counter++;
            }

            String info = volume.getName() + "\n" + volume.getPublisher() + "     " + volume.getStartYear()
                    + "\n" + counter + " out of " + volume.getCountofIssue() + " in collection";
            infoLbl.setText(info);
            getChildren().clear();
            getChildren().addAll(thumb, infoLbl);
        }

    }

    /**
     * @return the vol
     */
    public Volume getVolume() {
        return volume;
    }

    /**
     * @param vol the vol to set
     */
    public void setVolume(Volume vol) {
        this.volume = vol;
    }
}
