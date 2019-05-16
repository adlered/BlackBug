package pers.adlered.blackbug.client;

import pers.adlered.blackbug.client.connection.AliveChecking;
import pers.adlered.blackbug.client.connection.Connector;
import pers.adlered.blackbug.client.connection.Receiver;
import pers.adlered.blackbug.client.tools.OSDetector;

public class Client {
    public static void main(String[] args) {
        new OSDetector();
        //Read file "config.properties" as JavaBean Class "Properties"
        new ReadProperties();
        //Blocking process: connect to server
        new Connector().run();
        //Before "Connector" executed, start connection alive checking
        new AliveChecking().start();
        //Receiver
        new Receiver().start();
    }
}
