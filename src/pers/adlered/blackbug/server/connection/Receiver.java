package pers.adlered.blackbug.server.connection;

import pers.adlered.blackbug.server.connection.storge.StreamStorge;
import pers.adlered.blackbug.server.registy.DoShutdown;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

public class Receiver extends Thread {
    private int UID;

    public Receiver(int UID) {
        this.UID = UID;
    }

    @Override
    public void run() {
        Socket socket = StreamStorge.sockets.get(UID);
        //System.out.println(UID);
        try {
            /*byte[] buffer = new byte[65536];
            int length = 0;
            while ((length = socket.getInputStream().read(buffer)) != -1) {
                System.out.println("[UID-" + UID + "] " + new String(buffer, 0, length));
            }*/
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            CHECK:
            while (true) {
                for (Integer i : DoShutdown.doShutdown) {
                    if (i == UID) {
                        DoShutdown.doShutdown.remove(i);
                        StreamStorge.sockets.remove(i);
                        System.out.println("Listener for UID-" + UID + " stopped.");
                        break CHECK;
                    }
                }
                try {
                    String str;
                    while((str = bufferedReader.readLine()) != null){
                        if (str.indexOf("Heart :: AliveTime :: ") == -1) {
                            System.out.println("[UID-" + UID + "] " + str);
                        }
                    }
                } catch (SocketException SE) {
                    DoShutdown.doShutdown.add(UID);
                }
            }

            /*DataInputStream dataInputStream = new DataInpu/tStream(socket.getInputStream());
            byte[] buffer = new byte[65536];
            int length = 0;
            while ((length = socket.getInputStream().read(buffer)) != -1) {
                System.out.println(new String(buffer, 0, length));
            }*/
        } catch (IOException IOE) {
            IOE.printStackTrace();
        } catch (NullPointerException NPE) {
            NPE.printStackTrace();
        }
    }
}
