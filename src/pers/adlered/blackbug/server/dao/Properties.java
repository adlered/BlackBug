package pers.adlered.blackbug.server.dao;

public class Properties {
    public static String serverIP;
    public static int serverPORT;

    public static String getServerIP() {
        return serverIP;
    }

    public static void setServerIP(String serverIP) {
        Properties.serverIP = serverIP;
    }

    public static int getServerPORT() {
        return serverPORT;
    }

    public static void setServerPORT(int serverPORT) {
        Properties.serverPORT = serverPORT;
    }
}
