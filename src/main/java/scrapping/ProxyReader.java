package scrapping;

import java.io.IOException;

public class ProxyReader implements MyReader{
    private RealReader reader;
    private final String url;

    public ProxyReader(String url) {
        this.url = url;
    }
    @Override
    public String get_data() throws IOException {
        if(reader == null){
            reader = new RealReader(this.url);
        }
        return reader.data_access();
    }
}
