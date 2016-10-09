package DataHandler.Model;

import java.util.Arrays;
import java.util.List;

public class Team {

    public String getAliases() {
        return aliases;
    }

    public void setAliases(String aliases) {
        this.aliases = aliases;
    }

    public String getCharacter_enemies() {
        return character_enemies;
    }

    public void setCharacter_enemies(String character_enemies) {
        this.character_enemies = character_enemies;
    }

    public String getCharacter_friends() {
        return character_friends;
    }

    public void setCharacter_friends(String character_friends) {
        this.character_friends = character_friends;
    }

    public String getCharacters() {
        return characters;
    }

    public void setCharacters(String characters) {
        this.characters = characters;
    }

    public String getCount_of_issue_appearances() {
        return count_of_issue_appearances;
    }

    public void setCount_of_issue_appearances(String count_of_issue_appearances) {
        this.count_of_issue_appearances = count_of_issue_appearances;
    }

    public String getCount_of_team_members() {
        return count_of_team_members;
    }

    public void setCount_of_team_members(String count_of_team_members) {
        this.count_of_team_members = count_of_team_members;
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

    public String getDisbanded_in_issues() {
        return disbanded_in_issues;
    }

    public void setDisbanded_in_issues(String disbanded_in_issues) {
        this.disbanded_in_issues = disbanded_in_issues;
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

    public String getIssues_disbanded_in() {
        return issues_disbanded_in;
    }

    public void setIssues_disbanded_in(String issues_disbanded_in) {
        this.issues_disbanded_in = issues_disbanded_in;
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

    public String getStory_arc_credits() {
        return story_arc_credits;
    }

    public void setStory_arc_credits(String story_arc_credits) {
        this.story_arc_credits = story_arc_credits;
    }

    public String getVolume_credits() {
        return volume_credits;
    }

    public void setVolume_credits(String volume_credits) {
        this.volume_credits = volume_credits;
    }

    private String aliases;
    private String character_enemies;
    private String character_friends;
    private String characters;
    private String count_of_issue_appearances;
    private String count_of_team_members;
    private String date_added;
    private String date_last_updated;
    private String deck;
    private String description;
    private String disbanded_in_issues;
    private String first_appeared_in_issue;
    private String id;
    private String image;
    private String issue_credits;
    private String issues_disbanded_in;
    private String movies;
    private String name;
    private String publisher;
    private String site_detail_url;
    private String story_arc_credits;
    private String volume_credits;

    protected List<String> listVariables = Arrays.asList("aliases", "character_enemies", "character_friends", "characters", "count_of_issue_appearances", "count_of_team_members", "date_added", "date_last_updated", "deck", "description", "disbanded_in_issues", "first_appeared_in_issue", "id", "image", "issue_credits", "issues_disbanded_in", "movies", "name", "publisher", "site_detail_url", "story_arc_credits", "volume_credits");
}
