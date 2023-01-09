package scrapping;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String[] urls = {"ucu.edu.ua", "telegram.org", "microsoft.com", "google.com", "twitter.com", "youtube.com", "amazon.com", "reddit.com", "soundcloud.com", "github.com", "instagram.com", "linkedin.com", "pinterest.com", "spotify.com"};
        for (String url : urls) {
            MyReader reader = new ProxyReader(url);
            System.out.println(reader.get_data());
        }
    }
}