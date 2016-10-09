package DataHandler.Model;

import java.util.Arrays;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class Publisher {

    public String getAliases() {
        return aliases;
    }

    public void setAliases(String aliases) {
        this.aliases = aliases;
    }

    public String getCharacters() {
        return characters;
    }

    public void setCharacters(String characters) {
        this.characters = characters;
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

    public String getLocation_address() {
        return location_address;
    }

    public void setLocation_address(String location_address) {
        this.location_address = location_address;
    }

    public String getLocation_city() {
        return location_city;
    }

    public void setLocation_city(String location_city) {
        this.location_city = location_city;
    }

    public String getLocation_state() {
        return location_state;
    }

    public void setLocation_state(String location_state) {
        this.location_state = location_state;
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

    public String getStory_arcs() {
        return story_arcs;
    }

    public void setStory_arcs(String story_arcs) {
        this.story_arcs = story_arcs;
    }

    public String getTeams() {
        return teams;
    }

    public void setTeams(String teams) {
        this.teams = teams;
    }

    public String getVolumes() {
        return volumes;
    }

    public void setVolumes(String volumes) {
        this.volumes = volumes;
    }

    private String aliases;
    private String characters;
    private String date_added;
    private String date_last_updated;
    private String deck;
    private String description;
    private String id;
    private String image;
    private String location_address;
    private String location_city;
    private String location_state;
    private String name;
    private String site_detail_url;
    private String story_arcs;
    private String teams;
    private String volumes;

    protected List<String> listVariables = Arrays.asList("aliases", "characters", "date_added", "date_last_updated", "deck", "description", "id", "image", "location_address", "location_city", "location_state", "name", "site_detail_url", "story_arcs", "teams", "volumes");
}
