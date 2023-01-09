package scrapping;

import java.sql.*;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBHandler {
    private static DBHandler dbconnection;

    private final Connection connection;

    @SneakyThrows
    public DBHandler() {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
    }

    @SneakyThrows
    public void executeQuery(String query) {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(query);
        stmt.close();
    }

    @SneakyThrows
    public void insertData(String url, String data) {
        String request = "insert into cached_data (url,data) values (?,?);";
        PreparedStatement ps = connection.prepareStatement(request);
        ps.setString(1, url);
        ps.setString(2, data);
        ps.executeUpdate();
        ps.close();
    }

    public static DBHandler getInstance() {
        if (dbconnection == null) {
            dbconnection = new DBHandler();
        }
        return dbconnection;
    }

    public String getData(String url){
        String sql = "SELECT data FROM cached_data where url = '"+url+"'";
        try {
            Connection conn = connection;
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            if(!rs.next()){
                return "";
            }
            else{
                return rs.getString("data");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }
    public boolean checkData(String url){
        return getData(url).getBytes().length != 0;
    }
    public static void main(String[] args) {
        DBHandler db = new DBHandler();
//        db.executeQuery("insert into cached_data (url,data) values ('my_test_url', 'somedata');");
        System.out.println(db.getData("youtube.com"));
        System.out.println(db.checkData("test5"));
    }
}