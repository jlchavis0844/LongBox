package DataHandler.JSON;

import DataHandler.Model.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;


public class JsonParser {

    public static void run_json_perser(String fileNam) throws IOException, ParseException, JSONException {

        try {
            ObjectMapper mapper = new ObjectMapper();
            File inputFile = new File("./data/spider_man_issue.json");
            //String jsonString = "{\"name\":\"Mahesh Kumar\",  \"age\":21,\"verified\":false,\"marks\": [100,90,85]}";
            String jsonString = inputFile.toString();
            JsonNode rootNode = mapper.readTree(jsonString);

            JsonNode nameNode = rootNode.path("name");
            String inputName = nameNode.getTextValue();
            //System.out.println("Name: " + nameNode.getTextValue());

            JsonNode ageNode = rootNode.path("age");
            int inputAge = ageNode.getIntValue();
            //System.out.println("Age: " + ageNode.getIntValue());

            JsonNode verifiedNode = rootNode.path("verified");
            boolean inputVerified = (verifiedNode.getBooleanValue() ? true : false);
            //System.out.println("Verified: " + (verifiedNode.getBooleanValue() ? "Yes" : "No"));

            JsonNode marksNode = rootNode.path("marks");
            Iterator<JsonNode> iterator = marksNode.getElements();
            ArrayList<Integer> inputIntArrayList = new ArrayList<Integer>();
            //System.out.print("Marks: [ ");
            while (iterator.hasNext()) {
                JsonNode marks = iterator.next();
                //System.out.print(marks.getIntValue() + " ");
                inputIntArrayList.add(marks.getIntValue());

            }
            //System.out.println("]");

            //RandomPerson person = new RandomPerson(inputName, inputVerified, inputAge, inputIntArrayList);
            //System.out.println(person.toString());
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void run_parse() throws IOException, ParseException, JSONException {
        System.out.println("(start run_parse())");
        try {

            JSONParser parser = new JSONParser();
            java.lang.Object obj = parser.parse(new FileReader("./data/spider_man_issue.json"));
            String input_json_string = obj.toString();
            //System.out.println(input_json_string);

            ObjectMapper mapper = new ObjectMapper();
            MapListOfIssue.ListOfIssue readValue = mapper.readValue(input_json_string, MapListOfIssue.ListOfIssue.class);
            System.out.println(readValue.getResults().size());
            //for (Issue element : readValue.mListOfIssue) {System.out.println("element : " + element.toString());}

        } catch (Exception e) {

        }
        System.out.println("(end run_parse())");
    }

    public static void main(String[] args) throws IOException, ParseException, JSONException {

        run_parse();
    }

}

class MapListOfIssue {
    private ListOfIssue mListOfIssue;
    public ListOfIssue getD() {return mListOfIssue;}
    public void setD(ListOfIssue inputListOfIssue) {this.mListOfIssue = inputListOfIssue;}    
    public static class ListOfIssue{
        private List<Issue> results;
        public List<Issue> getResults() {return results;}
        public void setResults(List<Issue> results) {this.results = results;}
    }
}
