package pers.adlered.blackbug.server;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TempServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(7426);
            Socket socket = serverSocket.accept();
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            byte[] buffer = new byte[65536];
            int length = 0;
            while ((length = socket.getInputStream().read(buffer)) != -1) {
                System.out.println(new String(buffer, 0, length));
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
    }
}
