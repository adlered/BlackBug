package pers.adlered.blackbug.server.connection;

import pers.adlered.blackbug.server.connection.storge.StreamStorge;
import pers.adlered.blackbug.server.registy.DoShutdown;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

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
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
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
                    while ((str = bufferedReader.readLine()) != null) {
                        boolean print = true;
                        if (str.startsWith("Information :: Running on :: ")) {
                            String OS = str.replaceFirst("Information :: Running on :: ", "");
                            StreamStorge.detailOfOS.put(UID, OS);
                        }
                        if (str.startsWith("Information :: System detail :: ")) {
                            String OSDetail = str.replaceFirst("Information :: System detail :: ", "");
                            StreamStorge.detailOfOSDetail.put(UID, OSDetail);
                        }
                        if (str.indexOf("Heart :: AliveTime :: ") != -1) {
                            print = false;
                        }
                        if (print) {
                            System.out.println("[UID-" + UID + "] " + str);
                        }
                    }
                } catch (SocketException SE) {
                    DoShutdown.doShutdown.add(UID);
                }
            }

            /*DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
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
