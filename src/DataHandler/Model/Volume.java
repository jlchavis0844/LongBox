package DataHandler.Model;

import java.util.Arrays;
import java.util.List;

public class Volume {

    public String getAliases() {
        return aliases;
    }

    public void setAliases(String aliases) {
        this.aliases = aliases;
    }

    public String getCharacter_credits() {
        return character_credits;
    }

    public void setCharacter_credits(String character_credits) {
        this.character_credits = character_credits;
    }

    public String getConcept_credits() {
        return concept_credits;
    }

    public void setConcept_credits(String concept_credits) {
        this.concept_credits = concept_credits;
    }

    public String getCount_of_issues() {
        return count_of_issues;
    }

    public void setCount_of_issues(String count_of_issues) {
        this.count_of_issues = count_of_issues;
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

    public String getFirst_issue() {
        return first_issue;
    }

    public void setFirst_issue(String first_issue) {
        this.first_issue = first_issue;
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

    public String getLast_issue() {
        return last_issue;
    }

    public void setLast_issue(String last_issue) {
        this.last_issue = last_issue;
    }

    public String getLocation_credits() {
        return location_credits;
    }

    public void setLocation_credits(String location_credits) {
        this.location_credits = location_credits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObject_credits() {
        return object_credits;
    }

    public void setObject_credits(String object_credits) {
        this.object_credits = object_credits;
    }

    public String getPerson_credits() {
        return person_credits;
    }

    public void setPerson_credits(String person_credits) {
        this.person_credits = person_credits;
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

    public String getStart_year() {
        return start_year;
    }

    public void setStart_year(String start_year) {
        this.start_year = start_year;
    }

    public String getTeam_credits() {
        return team_credits;
    }

    public void setTeam_credits(String team_credits) {
        this.team_credits = team_credits;
    }

    private String aliases;
    private String character_credits;
    private String concept_credits;
    private String count_of_issues;
    private String date_added;
    private String date_last_updated;
    private String deck;
    private String description;
    private String first_issue;
    private String id;
    private String image;
    private String last_issue;
    private String location_credits;
    private String name;
    private String object_credits;
    private String person_credits;
    private String publisher;
    private String site_detail_url;
    private String start_year;
    private String team_credits;
    protected List<String> listVariables = Arrays.asList("aliases", "character_credits", "concept_credits", "count_of_issues", "date_added", "date_last_updated", "deck", "description", "first_issue", "id", "image", "last_issue", "location_credits", "name", "object_credits", "person_credits", "publisher", "site_detail_url", "start_year", "team_credits");
}
