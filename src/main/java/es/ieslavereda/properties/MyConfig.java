package es.ieslavereda.properties;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class MyConfig {

    private static MyConfig instance;
    private final String DEFAULT_PROPERTIES = "default.properties";
    private final String CUSTOM_PROPERTIES = "custom.properties";
    private Properties appProperties;

    private MyConfig(){

        Properties defaultProperties = new Properties();

        try(FileInputStream fis = new FileInputStream(DEFAULT_PROPERTIES)){
            defaultProperties.load(fis);
        }catch (IOException e){
            e.printStackTrace();
        }

        appProperties = new Properties(defaultProperties);

        try(FileInputStream fis = new FileInputStream(CUSTOM_PROPERTIES)){
            appProperties.load(fis);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static MyConfig getInstance(){
        if(instance == null){
            instance = new MyConfig();
        }
        return instance;
    }

    private void save(){
        try(FileOutputStream fos = new FileOutputStream(CUSTOM_PROPERTIES)){
            appProperties.store(fos, "UTF-8");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public String getUsername(){
        return appProperties.getProperty("USERNAMEORACLE");
    }
    public String getPassword(){
        return appProperties.getProperty("PASSWORDORACLE");
    }

    public String getUsernameSQL(){
        return appProperties.getProperty("USERNAME");
    }
    public String getPasswordSQL(){
        return appProperties.getProperty("PASSWORD");
    }


/*    public void setPassword(String password){
        appProperties.setProperty("PASSWORD", password);
        save();
    }

   public void setUsername(String username){
        appProperties.setProperty("USERNAME", username);
        save();
    }*/


    public String getDBHost(){
        return appProperties.getProperty("DB_HOSTORACLE");
    }

    public String getDBPort(){
        return appProperties.getProperty("DB_PORTORACLE");
    }

    public String getDBHostSQL(){
        return appProperties.getProperty("DB_HOSTLOCAL");
    }

    public String getDBPortSQL(){
        return appProperties.getProperty("DB_PORTSQL");
    }

    public String getDBSchema(){
        return appProperties.getProperty("DB_SCHEMA");
    }


}
