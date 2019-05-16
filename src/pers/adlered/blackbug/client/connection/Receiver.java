package pers.adlered.blackbug.client.connection;

import pers.adlered.blackbug.client.Pool;
import pers.adlered.blackbug.client.Values;

import java.io.IOException;

public class Receiver extends Thread {
    @Override
    public void run() {
        while (true) {
            try {
                byte[] buffer = new byte[65536];
                int length = 0;
                while ((length = Pool.clientInputStream.read(buffer)) != -1) {
                    new CommandHandler(new String(buffer, 0, length)).start();
                }
                Values.connected = false;
            } catch (IOException IOE) {
            } catch (NullPointerException NPE) {
            }
        }
    }
}
