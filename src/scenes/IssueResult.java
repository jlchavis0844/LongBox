package scenes;

import org.jsoup.Jsoup;
import org.jsoup.examples.HtmlToPlainText;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.scene.web.WebView;
import model.Issue;
import org.json.JSONException;

public class IssueResult extends VBox {
	private Label mText;
	private Issue mIssue;
	
	@FXML
	private WebView desc;
	
	public IssueResult(Issue rhIssue) throws JSONException {
		super();
		this.mIssue = rhIssue;
		
		mText = new Label();
		mText.setWrapText(true);
		String info = "Issue# " + mIssue.getIssueNum()+ "\tDate: "  + mIssue.getCoverDate() + "\n" + mIssue.getName();
		Document doc = Jsoup.parse(mIssue.getDescription());
		Elements element = doc.select("table").remove();
		
		String clean = Jsoup.clean(doc.body().html(), Whitelist.basic());
		
		
		String descript = Jsoup.parse(doc.toString()).text();
		info += "\n" + descript;
		mText.setText(info);
		mText.setTextAlignment(TextAlignment.JUSTIFY);
		mText.setPrefSize(900, 200);
		setMaxHeight(200);
		/*desc = new WebView();
		desc.getEngine().loadContent(mIssue.getDescription());
		desc.setMaxHeight(200);
		desc.setFontScale(0.75);*/
		setPrefHeight(100);
		getChildren().addAll(mText);
		
	}
	
	public Issue getmIssue(){
		return mIssue;
	}

	/**
	 * @return the mText
	 */
	public Label getmText() {
		return mText;
	}

	/**
	 * @param mText the mText to set
	 */
	public void setmText(Label mText) {
		this.mText = mText;
	}
	


}
