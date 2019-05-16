package pers.adlered.blackbug.client.connection;

import pers.adlered.blackbug.client.Pool;

import java.io.IOException;

/**
 * Can't use Threads cause upload must one content per translation otherwise it will be confused
 */

public class Sender extends Thread {
    public static void send(String sendWhat) {
        try {
            Pool.clientDataOutputStream.write(sendWhat.getBytes());
            Pool.clientDataOutputStream.flush();
        } catch (IOException IOE) {
            IOE.printStackTrace();
        }
    }
}
