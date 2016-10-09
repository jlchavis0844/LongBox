package DataHandler.Model;

import java.util.Arrays;
import java.util.List;

public class Concept {

    public String getAliases() {
        return aliases;
    }

    public void setAliases(String aliases) {
        this.aliases = aliases;
    }

    public String getCount_of_issue_appearances() {
        return count_of_issue_appearances;
    }

    public void setCount_of_issue_appearances(String count_of_issue_appearances) {
        this.count_of_issue_appearances = count_of_issue_appearances;
    }

    public String getDate_added() {
        return date_added;
    }

    public void setDate_added(String date_added) {
        this.date_added = date_added;
    }

    public String getDate_last_updated() {
        return date_last_updated;
    }

    public void setDate_last_updated(String date_last_updated) {
        this.date_last_updated = date_last_updated;
    }

    public String getDeck() {
        return deck;
    }

    public void setDeck(String deck) {
        this.deck = deck;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFirst_appeared_in_issue() {
        return first_appeared_in_issue;
    }

    public void setFirst_appeared_in_issue(String first_appeared_in_issue) {
        this.first_appeared_in_issue = first_appeared_in_issue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIssue_credits() {
        return issue_credits;
    }

    public void setIssue_credits(String issue_credits) {
        this.issue_credits = issue_credits;
    }

    public String getMovies() {
        return movies;
    }

    public void setMovies(String movies) {
        this.movies = movies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSite_detail_url() {
        return site_detail_url;
    }

    public void setSite_detail_url(String site_detail_url) {
        this.site_detail_url = site_detail_url;
    }

    public String getStart_year() {
        return start_year;
    }

    public void setStart_year(String start_year) {
        this.start_year = start_year;
    }

    public String getVolume_credits() {
        return volume_credits;
    }

    public void setVolume_credits(String volume_credits) {
        this.volume_credits = volume_credits;
    }

    private String aliases;
    private String count_of_issue_appearances;
    private String date_added;
    private String date_last_updated;
    private String deck;
    private String description;
    private String first_appeared_in_issue;
    private String id;
    private String image;
    private String issue_credits;
    private String movies;
    private String name;
    private String site_detail_url;
    private String start_year;
    private String volume_credits;
    protected List<String> listVariables = Arrays.asList("aliases", "count_of_issue_appearances", "date_added", "date_last_updated", "deck", "description", "first_appeared_in_issue", "id", "image", "issue_credits", "movies", "name", "site_detail_url", "start_year", "volume_credits");

}
