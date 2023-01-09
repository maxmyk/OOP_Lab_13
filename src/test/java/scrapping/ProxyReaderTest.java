package scrapping;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

class ProxyReaderTest {

    @org.junit.jupiter.api.Test
    void get_data() throws IOException {
        MyReader reader = new ProxyReader("ucu.edu.ua");
        String data = reader.get_data();
        assertTrue(data.contains("education"));
    }
}