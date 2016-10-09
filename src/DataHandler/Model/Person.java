package DataHandler.Model;

import java.util.Arrays;
import java.util.List;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class Person {

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

    public String getCount_of_issue_appearances() {
        return count_of_issue_appearances;
    }

    public void setCount_of_issue_appearances(String count_of_issue_appearances) {
        this.count_of_issue_appearances = count_of_issue_appearances;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCreated_characters() {
        return created_characters;
    }

    public void setCreated_characters(String created_characters) {
        this.created_characters = created_characters;
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

    public String getDeath() {
        return death;
    }

    public void setDeath(String death) {
        this.death = death;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    private String aliases;
    private String birth;
    private String count_of_issue_appearances;
    private String country;
    private String created_characters;
    private String date_added;
    private String date_last_updated;
    private String death;
    private String deck;
    private String description;
    private String email;
    private String gender;
    private String hometown;
    private String id;
    private String image;
    private String issue_credits;
    private String name;
    private String site_detail_url;
    private String story_arc_credits;
    private String volume_credits;
    private String website;
    
    protected List<String>  listVariables = Arrays.asList("aliases","birth","count_of_issue_appearances","country","created_characters","date_added","date_last_updated","death","deck","description","email","gender","hometown","id","image","issue_credits","name","site_detail_url","story_arc_credits","volume_credits","website");
}
