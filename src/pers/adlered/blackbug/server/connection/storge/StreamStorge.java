package pers.adlered.blackbug.server.connection.storge;

import java.net.Socket;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StreamStorge {
    public static int UID = 0;

    public static TreeMap<Integer, Socket> sockets = new TreeMap<Integer, Socket>();

    public static ExecutorService receiverPool = Executors.newCachedThreadPool();
}
