package pers.adlered.blackbug.client.connection;

import pers.adlered.blackbug.client.Pool;
import pers.adlered.blackbug.client.Values;
import pers.adlered.blackbug.client.dao.Properties;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connector extends Thread {
    @Override
    public void run() {
        try {
            Pool.clientSocket = new Socket(Properties.getServerIP(), Properties.getServerPORT());
            //Get OutputStream
            Pool.clientOutputStream = Pool.clientSocket.getOutputStream();
            Pool.clientDataOutputStream = new DataOutputStream(Pool.clientOutputStream);
            //Get InputStream
            Pool.clientInputStream = Pool.clientSocket.getInputStream();
            Pool.clientDataInputStream = new DataInputStream(Pool.clientInputStream);
            //Tell other classes connected
            Values.connected = true;
            System.out.println("\nServer bug:/" + Pool.clientSocket.getRemoteSocketAddress() + " connected.");
            Pool.clientDataOutputStream.write(("Hello! Here client from bug:/" + Pool.clientSocket.getLocalSocketAddress() + "\r\n").getBytes());
            Pool.clientDataOutputStream.flush();
            Pool.clientDataOutputStream.write(("Information :: Running on :: " + Values.OS + "\r\n").getBytes());
            Pool.clientDataOutputStream.flush();
            Pool.clientDataOutputStream.write(("Information :: System detail :: " + System.getProperty("os.name") + "\r\n").getBytes());
            Pool.clientDataOutputStream.flush();
        } catch (UnknownHostException UHE) {
            //UHE.printStackTrace();
        } catch (IOException IOE) {
            //IOE.printStackTrace();
        }
    }
}
