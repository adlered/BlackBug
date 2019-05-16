package pers.adlered.blackbug.client.connection;

/**
 * To check "Pool.connected" if it is false, reset the connection until reconnected.
 */

import pers.adlered.blackbug.client.Pool;
import pers.adlered.blackbug.client.Values;
import pers.adlered.blackbug.client.dao.Properties;

import java.io.IOException;

public class AliveChecking extends Thread {
    @Override
    public void run() {
        while (true) {
            try {
                Pool.clientSocket.sendUrgentData(0xFF);
            } catch (IOException IOE) {
                reconnect();
            } catch (NullPointerException NPE) {
                reconnect();
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException IE) {
            }
            /*if (!Values.connected) {
                System.out.print("Unable connection " + Properties.getServerIP() + ":" + Properties.getServerPORT() + ". Reconnecting");
                while (!Values.connected) {
                    try {
                        new Connector().start();
                        System.out.print(".");
                        Thread.sleep(3500);
                    } catch (InterruptedException IE) {
                    }
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException IE) {
            }*/
        }
    }

    public static void reconnect() {
        Values.connected = false;
        System.out.print("Unable connection " + Properties.getServerIP() + ":" + Properties.getServerPORT() + ". Reconnecting");
        while (!Values.connected) {
            try {
                new Connector().start();
                System.out.print(".");
                Thread.sleep(3500);
            } catch (InterruptedException IE) {
            }
        }
    }
}
