package pers.adlered.blackbug.server.connection;

import pers.adlered.blackbug.server.connection.storge.StreamStorge;
import pers.adlered.blackbug.server.dao.Properties;
import pers.adlered.blackbug.server.registy.OnQueue;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Connector extends Thread {
    ServerSocket serverSocket;

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(Properties.getServerPORT());
            System.out.println("Service setup succeed. Listening " + serverSocket.getLocalSocketAddress());
        } catch (IOException IOE) {
            IOE.printStackTrace();
        }
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                StreamStorge.sockets.put(StreamStorge.UID, socket);
                System.out.println("UID-" + StreamStorge.UID + " From bug:/" + socket.getRemoteSocketAddress() + " connected.");
                new DataOutputStream(socket.getOutputStream()).write(("Hello! Your UID is: UID-" + StreamStorge.UID).getBytes());
                //Add to queue, and Dispatcher will be process.
                OnQueue.onQueue.add(StreamStorge.UID);
                StreamStorge.UID++;
            } catch (IOException IOE) {
                IOE.printStackTrace();
            } catch (NullPointerException NOE) {
                NOE.printStackTrace();
            }
            //ReceiverListen.flush();
        }
    }
}
