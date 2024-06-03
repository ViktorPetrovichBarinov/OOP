package org.example;

import org.example.queue.CustomBlockingQueue;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

public class ConnectingNode extends Thread{
    private static final int PORT = 10001;
    private static final String MULTICAST_IP = "230.0.0.0";
    private final CustomBlockingQueue<Integer> queue;

    private final int distributionPort;
    ConnectingNode(int distributionPort, CustomBlockingQueue<Integer> queue) {
        this.distributionPort = distributionPort;
        this.queue = queue;
    }

    @Override
    public void run() {
        try (DatagramSocket dSocket = new DatagramSocket(distributionPort)){
            InetAddress group = InetAddress.getByName(MULTICAST_IP);
            byte[] buffer = Integer.toString(this.distributionPort).getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, group, PORT);

            while(true) {
                dSocket.send(packet);
                Date date = new Date(System.currentTimeMillis());
                System.out.println("(" + date + ")" + "Multicast message sent: " + distributionPort);
                Thread.sleep(1000);
                if (queue.getNumberOfElements() == 0) {
                    break;
                }
            }
        } catch(IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
