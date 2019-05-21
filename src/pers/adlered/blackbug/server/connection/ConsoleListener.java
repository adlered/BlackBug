package pers.adlered.blackbug.server.connection;

import pers.adlered.blackbug.server.Temp;
import pers.adlered.blackbug.server.connection.storge.StreamStorge;

import java.io.*;
import java.net.SocketException;
import java.util.Scanner;

public class ConsoleListener extends Thread {
    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (true) {
            input = scanner.nextLine();
            if (input.equals("/setuid ")) {
                System.out.println("Please specific an UID!");
            } else {
                String result = ConsoleHandler.handle(input);
                if (result.isEmpty()) {
                    //Standard message send
                    if (Temp.currentUID < 0) {
                        System.out.println("Please specific UID in \"/setuid [UID]\" first.");
                    } else {
                        boolean dontOutput = false;
                        try {
                            //DataOutputStream dataOutputStream = new DataOutputStream(StreamStorge.sockets.get(Temp.currentUID).getOutputStream());
                            //dataOutputStream.write(input.getBytes());
                            //dataOutputStream.flush();
                            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(StreamStorge.sockets.get(Temp.currentUID).getOutputStream(), "UTF-8"));
                            bufferedWriter.write(input);
                            bufferedWriter.flush();
                        } catch (NullPointerException NPE) {
                            System.out.println("[Failed] Client not exists. (UID-" + Temp.currentUID + ")");
                            dontOutput = true;
                        } catch (SocketException SE) {
                            System.out.println("[Failed] Client " + Temp.currentUID + " offline.");
                            StreamStorge.sockets.remove(Temp.currentUID);
                            dontOutput = true;
                        } catch (IOException IOE) {
                            IOE.printStackTrace();
                        }
                        if (!dontOutput) {
                            System.out.println("[Trans2Client UID-" + Temp.currentUID + "] " + input);
                        }
                    }
                } else {
                    System.out.println(result);
                }
            }
        }
    }
}
