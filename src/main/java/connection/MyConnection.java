package connection;

import org.postgresql.ds.PGSimpleDataSource;
import javax.sql.DataSource;

public class MyConnection {
    private static PGSimpleDataSource pgSimpleDataSource;

    public static DataSource getSingleDataSource(){
        String url = "jdbc:postgresql://localhost:5432/jee";
        String username = "postgres";
        String password = "root";

        if (pgSimpleDataSource == null){
            pgSimpleDataSource = new PGSimpleDataSource();
            pgSimpleDataSource.setUrl(url);
            pgSimpleDataSource.setUser(username);
            pgSimpleDataSource.setPassword(password);

        }
        return pgSimpleDataSource;
    }
}
