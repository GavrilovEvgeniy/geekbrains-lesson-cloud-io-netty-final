package gb.cloud.server.service.impl.utils;

import gb.cloud.server.Main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ServerProperties {

    public static String dbconnection;
    public static String dblogin;
    public static String dbpassword;
    public static String serverport;

    public static String loggedName;
    public static String levelUp;
    public static String cloudDir;
    public static String localDir;
    public static String privateDir;
    public static String cloudLen;
    public static String localLen;
    public static String privateLen;

    public static String nameServerProps = "cloud-server/src/main/resources/server.properties";

    private static final Properties property = new Properties();

    public static void loadProperties() {

        try {
            FileInputStream fis = new FileInputStream(nameServerProps);
            property.load(fis);

            dbconnection = property.getProperty("cld.db_connection");
            dblogin = property.getProperty("cld.db_login");
            dbpassword = property.getProperty("cld.db_password");
            serverport = property.getProperty("cld.server_port");

            loggedName = property.getProperty("loggedName");
            levelUp = property.getProperty("levelUp");
            cloudDir = property.getProperty("cloudDir");
            localDir = property.getProperty("localDir");
            privateDir = property.getProperty("privateDir");
            cloudLen = property.getProperty("cloudLen");
            localLen = property.getProperty("localLen");
            privateLen = property.getProperty("privateLen");

            Main.log.info(dbconnection + " " + dblogin + " " + dbpassword + " " + serverport);
            System.out.println(dbconnection + " " + dblogin + " " + dbpassword + " " + serverport);

        } catch (IOException e) {
            Main.log.error("Error, file not found.");
        }
    }


}
