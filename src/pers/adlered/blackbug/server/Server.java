package pers.adlered.blackbug.server;

import pers.adlered.blackbug.server.connection.Connector;
import pers.adlered.blackbug.server.connection.ConsoleListener;
import pers.adlered.blackbug.server.registy.Dispatcher;

public class Server {
    public static void main(String[] args) {
        new ReadProperties();
        new Connector().start();
        new ConsoleListener().start();
        //new AliveChecking().start();
        new Dispatcher().start();
    }

     /*
        try {
            System.out.println("wait client connect...");
            socket = server.accept();

            System.out.println(socket.getInetAddress().getHostAddress() + "SUCCESS TO CONNECT...");
            InputStream in = socket.getInputStream();
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = in.read(buf)) != -1) {
                System.out.println("client saying: " + new String(buf, 0, len));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        */
}