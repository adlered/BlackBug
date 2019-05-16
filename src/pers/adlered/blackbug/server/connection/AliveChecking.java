package pers.adlered.blackbug.server.connection;

import pers.adlered.blackbug.server.connection.storge.StreamStorge;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

public class AliveChecking extends Thread {
    @Override
    public void run() {
        while (true) {
            //Iterate all UID and remove if connection is not alive
            TreeMap<Integer, Socket> sockets = StreamStorge.sockets;
            List<Integer> removelist = new ArrayList<Integer>();
            Iterator<Integer> iterator = sockets.keySet().iterator();
            while (iterator.hasNext()) {
                int key = iterator.next();
                try {
                    sockets.get(key).sendUrgentData(0xFF);
                } catch (IOException IOE) {
                    System.out.println("[Offline] UID-" + key);
                    removelist.add(key);
                }
            }
            //Remove key which in the removeList
            for (int i : removelist) {
                //StreamStorge.sockets.remove(i);
            }
            if (removelist.size() != 0) {
                //ReceiverListen.flush();
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException IE) {
            }
        }
    }
}
