package DataHandler.Model;

import java.util.Arrays;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class StoryArc {

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

    public String getIssues() {
        return issues;
    }

    public void setIssues(String issues) {
        this.issues = issues;
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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getSite_detail_url() {
        return site_detail_url;
    }

    public void setSite_detail_url(String site_detail_url) {
        this.site_detail_url = site_detail_url;
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
    private String issues;
    private String movies;
    private String name;
    private String publisher;
    private String site_detail_url;

    protected List<String> listVariables = Arrays.asList("aliases", "count_of_issue_appearances", "date_added", "date_last_updated", "deck", "description", "first_appeared_in_issue", "id", "image", "issues", "movies", "name", "publisher", "site_detail_url");
}
