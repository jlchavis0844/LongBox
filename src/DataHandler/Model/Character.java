package DataHandler.Model;

import java.util.Arrays;
import java.util.List;

public class Character {

    public String getAliases() {
        return aliases;
    }

    public void setAliases(String aliases) {
        this.aliases = aliases;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
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

    public String getCount_of_issue_appearances() {
        return count_of_issue_appearances;
    }

    public void setCount_of_issue_appearances(String count_of_issue_appearances) {
        this.count_of_issue_appearances = count_of_issue_appearances;
    }

    public String getCreators() {
        return creators;
    }

    public void setCreators(String creators) {
        this.creators = creators;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getIssues_died_in() {
        return issues_died_in;
    }

    public void setIssues_died_in(String issues_died_in) {
        this.issues_died_in = issues_died_in;
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

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getPowers() {
        return powers;
    }

    public void setPowers(String powers) {
        this.powers = powers;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
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

    public String getTeam_enemies() {
        return team_enemies;
    }

    public void setTeam_enemies(String team_enemies) {
        this.team_enemies = team_enemies;
    }

    public String getTeam_friends() {
        return team_friends;
    }

    public void setTeam_friends(String team_friends) {
        this.team_friends = team_friends;
    }

    public String getTeams() {
        return teams;
    }

    public void setTeams(String teams) {
        this.teams = teams;
    }

    public String getVolume_credits() {
        return volume_credits;
    }

    public void setVolume_credits(String volume_credits) {
        this.volume_credits = volume_credits;
    }

    private String aliases;
    private String birth;
    private String character_enemies;
    private String character_friends;
    private String count_of_issue_appearances;
    private String creators;
    private String date_added;
    private String date_last_updated;
    private String deck;
    private String description;
    private String first_appeared_in_issue;
    private String gender;
    private String id;
    private String image;
    private String issue_credits;
    private String issues_died_in;
    private String movies;
    private String name;
    private String origin;
    private String powers;
    private String publisher;
    private String real_name;
    private String site_detail_url;
    private String story_arc_credits;
    private String team_enemies;
    private String team_friends;
    private String teams;
    private String volume_credits;

    protected List<String> listVariables = Arrays.asList("aliases", "birth", "character_enemies", "character_friends", "count_of_issue_appearances", "creators", "date_added", "date_last_updated", "deck", "description", "first_appeared_in_issue", "gender", "id", "image", "issue_credits", "issues_died_in", "movies", "name", "origin", "powers", "publisher", "real_name", "site_detail_url", "story_arc_credits", "team_enemies", "team_friends", "teams", "volume_credits");
}
