package gb.cloud.client.service.impl.utils;

import gb.cloud.client.controller.MainController;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ClientProperties {

    public static String cloudDirectory;
    public static String localDirectory;
    public static String bufferLength;
    public static String serverHost;
    public static String serverPort;

    public static String pathClientProps = "cloud-client/src/main/resources/client.properties";

    public static void loadProperties() {
        FileInputStream fis;
        Properties property = new Properties();

        try {
            fis = new FileInputStream(pathClientProps);
            property.load(fis);

            cloudDirectory = property.getProperty("cld.directory");
            localDirectory = property.getProperty("local.directory");
            bufferLength = property.getProperty("buffer.length");
            serverHost = property.getProperty("server.host");
            serverPort = property.getProperty("server.port");

            MainController.log.info(cloudDirectory + " " + localDirectory + " " + bufferLength + " " + serverHost + " " + serverPort);
            System.out.println(cloudDirectory + " " + localDirectory + " " + bufferLength + " " + serverHost + " " + serverPort);

        } catch (IOException e) {
            MainController.log.error("Error, file not found.");
        }
    }


}