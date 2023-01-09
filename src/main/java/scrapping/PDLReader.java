package scrapping;

import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
public class PDLReader {

    private static String API_KEY = new ApiKey().key;
    public String read(String a_url) throws IOException {
        String query = URLEncoder.encode("SELECT NAME FROM COMPANY WHERE WEBSITE='"+a_url+"'", StandardCharsets.UTF_8);
        URL url = new URL("https://api.peopledatalabs.com/v5/company/search?sql=" + query);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("X-Api-Key", API_KEY);
        connection.connect();
        String text = new Scanner(connection.getInputStream()).useDelimiter("\\Z").next();
        JSONObject jsonObject = new JSONObject(text);
        return jsonObject.toString();
    }
    public static void main(String[] args) throws IOException {
        PDLReader reader = new PDLReader();
        System.out.println(reader.read("ucu.edu.ua"));
    }
}
