package pers.adlered.blackbug.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Pool {
    public static Socket clientSocket;

    public static OutputStream clientOutputStream;
    public static DataOutputStream clientDataOutputStream;

    public static InputStream clientInputStream;
    public static DataInputStream clientDataInputStream;
}
