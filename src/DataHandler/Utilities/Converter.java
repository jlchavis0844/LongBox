package DataHandler.Utilities;

import DataHandler.Model.*;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Converter {

    /*
    "2008-06-06 11:08:03"
    "yyyy-mm-dd HH:mm:ss"
     */
    private String DATE_FORMAT = "yyyy-mm-dd HH:mm:ss";

    public static Integer stringToInteger(String inputString) {

        return Integer.parseInt(inputString);
    }

    public static String integerToString(Integer inputInteger) {
        return inputInteger.toString();
    }

    public static Date stringtoDate(String inputString) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        Date date = df.parse(inputString);
        return date;
    }

    public static String dateToString(Date inputDate) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        return df.format(inputDate);
    }

    public static URL stringToURL(String inputString) throws MalformedURLException {

        return new URL(inputString);
    }

    public static Publisher stringToPublisher(String inputString) {
        /*must have id, name*/

        return null;
    }

    public static Person stringToPerson(String inputString) {
        /*must have id, name*/

        return null;
    }

    public static Issue stringToIssue(String inputString) {
        /*must have id, name, cover_date, description, image, issue_number, store_detail_url, store_date*/

        return null;
    }

    public static StoryArc stringToStoryArc(String inputString) {
        /*must have id, name*/

        return null;
    }

    public static Volume stringToVolume(String inputString) {
        /*must have id, name, publisher, count_of_issues, description*/

        return null;
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ParseException, MalformedURLException {

        Integer integer = stringToInteger("2");
        System.out.println(integer);

        String intString = integerToString(2);
        System.out.println(intString);

        Date sampleDate = stringtoDate("2008-06-06 15:08:03");
        System.out.println(sampleDate);

        String dateString = dateToString(sampleDate);
        System.out.println(dateString);

        URL sampleRUL = stringToURL("https://www.mkyong.com/java/how-to-convert-string-to-date-java/");
        System.out.println(sampleRUL);

        String urlString = sampleRUL.toString();
        System.out.println(urlString);

    }

}
