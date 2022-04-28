package es.ieslavereda.model;

import com.mysql.cj.jdbc.MysqlDataSource;
import es.ieslavereda.properties.MyConfig;
import oracle.jdbc.datasource.impl.OracleDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class MyDataSource {
    public static DataSource getMyOracleDataSource(){
        Properties props = new Properties();

        OracleDataSource oracleDS = null;
        try(FileInputStream fis = new FileInputStream("default.properties")) {
            props.load(fis);

            String host = MyConfig.getInstance().getDBHost();
            String port = MyConfig.getInstance().getDBPort();
            String user = MyConfig.getInstance().getUsername();
            String password = MyConfig.getInstance().getPassword();

            oracleDS = new OracleDataSource();
            oracleDS.setURL("jdbc:oracle:thin:@" + host + ":" + port + ":xe");
            oracleDS.setUser(user);
            oracleDS.setPassword(password);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return oracleDS;
    }

    public static DataSource getMySQLDataSource(){
        MysqlDataSource mysqlDataSource = new MysqlDataSource();

        //jdbc:mysql://<host>:<port>/<schema>
        String host = MyConfig.getInstance().getDBHostSQL();
        String port = MyConfig.getInstance().getDBPortSQL();
        String schema = MyConfig.getInstance().getDBSchema();
        String user = MyConfig.getInstance().getUsernameSQL();
        String password = MyConfig.getInstance().getPasswordSQL();


        //jdbc:mysql://<host>:<port>/<schema>
        mysqlDataSource.setURL("jdbc:mysql://" + host + ":" + port + "/" + schema);
        mysqlDataSource.setUser(user);
        mysqlDataSource.setPassword(password);


        return mysqlDataSource;
    }
}
