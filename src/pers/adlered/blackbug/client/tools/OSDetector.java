package pers.adlered.blackbug.client.tools;

import pers.adlered.blackbug.client.Values;

public class OSDetector {
    public OSDetector() {
        String OS = System.getProperty("os.name").toLowerCase();
        if (OS.indexOf("mac") != -1) {
            System.out.println("[OS Detect] You are running macOS.");
            Values.OS = "macOS";
        } else if (OS.indexOf("windows") != -1) {
            System.out.println("[OS Detect] You are running Windows.");
            Values.OS = "Windows";
        } else {
            System.out.println("[OS Detect] You are running Linux.");
            Values.OS = "Linux";
        }
    }
}
