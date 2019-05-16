package pers.adlered.blackbug.client;

import java.io.*;
import java.util.Properties;

public class ReadProperties {
    public ReadProperties() {
        Properties properties = new Properties();
        InputStream inputStream = null;

        try {
            inputStream = new BufferedInputStream(new FileInputStream("config.properties"));
        } catch (FileNotFoundException FNFE) {
            FNFE.printStackTrace();
        }

        try {
            properties.load(inputStream);
        } catch (IOException IOE) {
            IOE.printStackTrace();
        }

        pers.adlered.blackbug.client.dao.Properties.setServerIP(properties.getProperty("server.ip"));
        pers.adlered.blackbug.client.dao.Properties.setServerPORT(Integer.parseInt(properties.getProperty("server.port")));
    }
}
