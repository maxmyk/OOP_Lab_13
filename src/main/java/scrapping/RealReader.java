package scrapping;

import java.io.IOException;

public class RealReader {

    private final DBHandler dbhandler = new DBHandler();
    private final String url;
    private PDLReader pdl;

    public String data_access() throws IOException {
        if(pdl == null){
            pdl = new PDLReader();
        }
        if (dbhandler.checkData(this.url)) {
            return dbhandler.getData(this.url);
        }
        else{
            String data = pdl.read(this.url);
            dbhandler.insertData(this.url, data);
            return data;
        }
    }
    public RealReader(String url) {
        this.url = url;
    }
}
