package DataHandler.Model;

import java.util.Arrays;
import java.util.List;

public class Origin {

    public String getCharacter_set() {
        return character_set;
    }

    public void setCharacter_set(String character_set) {
        this.character_set = character_set;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfiles() {
        return profiles;
    }

    public void setProfiles(String profiles) {
        this.profiles = profiles;
    }

    public String getSite_detail_url() {
        return site_detail_url;
    }

    public void setSite_detail_url(String site_detail_url) {
        this.site_detail_url = site_detail_url;
    }

    private String character_set;
    private String id;
    private String name;
    private String profiles;
    private String site_detail_url;
    protected List<String> listVariables = Arrays.asList("character_set", "id", "name", "profiles", "site_detail_url");
}
