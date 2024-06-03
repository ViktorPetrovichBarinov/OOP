package org.example;

import org.example.queue.CustomBlockingQueue;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class RequestSender extends Thread{
    private Socket socket;
    private final CustomBlockingQueue<Integer> queue;
    private boolean res = true;
    public RequestSender(Socket socket, CustomBlockingQueue<Integer> queue) {
        this.socket = socket;
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            while(true) {
                int number;
                synchronized (queue) {
                    if (queue.getNumberOfElements() == 0) {
                        break;
                    }
                    number = queue.dequeue();
                }

                System.out.println("Send: " + number);
                output.writeInt(number);
                boolean currentRes = input.readBoolean();
                System.out.println("Get: " + currentRes);
                if (!currentRes) {
                    res = false;
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean getRes() {
        return res;
    }
}
