package DataHandler.Model;

import java.util.Arrays;
import java.util.List;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class Issue {

    public Issue() {
        
    }
    
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

    public String getCharacters_died_in() {
        return characters_died_in;
    }

    public void setCharacters_died_in(String characters_died_in) {
        this.characters_died_in = characters_died_in;
    }

    public String getConcept_credits() {
        return concept_credits;
    }

    public void setConcept_credits(String concept_credits) {
        this.concept_credits = concept_credits;
    }

    public String getCover_date() {
        return cover_date;
    }

    public void setCover_date(String cover_date) {
        this.cover_date = cover_date;
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

    public String getDisbanded_teams() {
        return disbanded_teams;
    }

    public void setDisbanded_teams(String disbanded_teams) {
        this.disbanded_teams = disbanded_teams;
    }

    public String getFirst_appearance_characters() {
        return first_appearance_characters;
    }

    public void setFirst_appearance_characters(String first_appearance_characters) {
        this.first_appearance_characters = first_appearance_characters;
    }

    public String getFirst_appearance_concepts() {
        return first_appearance_concepts;
    }

    public void setFirst_appearance_concepts(String first_appearance_concepts) {
        this.first_appearance_concepts = first_appearance_concepts;
    }

    public String getFirst_appearance_locations() {
        return first_appearance_locations;
    }

    public void setFirst_appearance_locations(String first_appearance_locations) {
        this.first_appearance_locations = first_appearance_locations;
    }

    public String getFirst_appearance_objects() {
        return first_appearance_objects;
    }

    public void setFirst_appearance_objects(String first_appearance_objects) {
        this.first_appearance_objects = first_appearance_objects;
    }

    public String getFirst_appearance_storyarcs() {
        return first_appearance_storyarcs;
    }

    public void setFirst_appearance_storyarcs(String first_appearance_storyarcs) {
        this.first_appearance_storyarcs = first_appearance_storyarcs;
    }

    public String getFirst_appearance_teams() {
        return first_appearance_teams;
    }

    public void setFirst_appearance_teams(String first_appearance_teams) {
        this.first_appearance_teams = first_appearance_teams;
    }

    public String getHas_staff_review() {
        return has_staff_review;
    }

    public void setHas_staff_review(String has_staff_review) {
        this.has_staff_review = has_staff_review;
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

    public String getIssue_number() {
        return issue_number;
    }

    public void setIssue_number(String issue_number) {
        this.issue_number = issue_number;
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

    public String getSite_detail_url() {
        return site_detail_url;
    }

    public void setSite_detail_url(String site_detail_url) {
        this.site_detail_url = site_detail_url;
    }

    public String getStore_date() {
        return store_date;
    }

    public void setStore_date(String store_date) {
        this.store_date = store_date;
    }

    public String getStory_arc_credits() {
        return story_arc_credits;
    }

    public void setStory_arc_credits(String story_arc_credits) {
        this.story_arc_credits = story_arc_credits;
    }

    public String getTeam_credits() {
        return team_credits;
    }

    public void setTeam_credits(String team_credits) {
        this.team_credits = team_credits;
    }

    public String getTeams_disbanded_in() {
        return teams_disbanded_in;
    }

    public void setTeams_disbanded_in(String teams_disbanded_in) {
        this.teams_disbanded_in = teams_disbanded_in;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }
    
    public String toString() {
        return "(issue toString() method implementation)";
    }

    private String aliases;
    private String character_credits;
    private String characters_died_in;
    private String concept_credits;
    private String cover_date;
    private String date_added;
    private String date_last_updated;
    private String deck;
    private String description;
    private String disbanded_teams;
    private String first_appearance_characters;
    private String first_appearance_concepts;
    private String first_appearance_locations;
    private String first_appearance_objects;
    private String first_appearance_storyarcs;
    private String first_appearance_teams;
    private String has_staff_review;
    private String id;
    private String image;
    private String issue_number;
    private String location_credits;
    private String name;
    private String object_credits;
    private String person_credits;
    private String site_detail_url;
    private String store_date;
    private String story_arc_credits;
    private String team_credits;
    private String teams_disbanded_in;
    private String volume;
    
    protected List<String>  listVariables = Arrays.asList("aliases","character_credits","characters_died_in","concept_credits","cover_date","date_added","date_last_updated","deck","description","disbanded_teams","first_appearance_characters","first_appearance_concepts","first_appearance_locations","first_appearance_objects","first_appearance_storyarcs","first_appearance_teams","has_staff_review","id","image","issue_number","location_credits","name","object_credits","person_credits","site_detail_url","store_date","story_arc_credits","team_credits","teams_disbanded_in","volume");
}
