package Helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    private Connection connect = null;

    public Connection connectionDB() {
        try {
            this.connect = DriverManager.getConnection(Config.DB_URL, Config.DB_USERNAME, Config.DB_PASSWORD);
        }catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return this.connect;
    }

    public static Connection getInstance(){
        DB db = new DB();
        return db.connectionDB();
    }

}
