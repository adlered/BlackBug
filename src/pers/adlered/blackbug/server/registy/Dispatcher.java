package pers.adlered.blackbug.server.registy;

import pers.adlered.blackbug.server.connection.Receiver;

public class Dispatcher extends Thread {
    @Override
    public void run() {
        int count = 0;
        while (true) {
            ++count;
            for (Integer i : OnQueue.onQueue) {
                OnQueue.onQueue.remove(i);
                Running.running.add(i);
                new Receiver(i).start();
            }
            if (count == 3) {
                //DoShutdown.doShutdown.add(0);
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException IE) {
                IE.printStackTrace();
            }
        }
    }
}
