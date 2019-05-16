package pers.adlered.blackbug.server;

import java.util.Map;
import java.util.TreeMap;

public class test {
    public static void main(String[] args) {
        Map treemap = new TreeMap<>();
        treemap.put(1, "hello");
        treemap.put(2, "world");
        treemap.put(3, "to u");
        System.out.println(treemap.get(2));
        treemap.remove(1);
        System.out.println(treemap.get(2));
    }
}
