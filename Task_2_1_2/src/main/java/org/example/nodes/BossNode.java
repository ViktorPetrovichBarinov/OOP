package org.example.nodes;

import org.example.queue.CustomBlockingQueue;
import org.example.queue.MyBlockingQueue;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class BossNode{
    private final static int SERVER_PORT = 10010;
    private final CustomBlockingQueue<Integer> queue;
    private Boolean result = true;
    private static final int TIMEOUT = 5000;

    public BossNode(int[] request) {
        queue = new MyBlockingQueue<>(request.length);
        for (int i = 0; i < request.length; i++) {
            try {
                synchronized (queue) {
                    queue.enqueue(request[i]);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }


    public void start(){
        ConnectingNode connectingNode = new ConnectingNode(SERVER_PORT, queue);
        connectingNode.start();
        ArrayList<RequestSender> senders = new ArrayList<>();

        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
            System.out.println("Server is listening on port " + SERVER_PORT);
            System.out.println("Server timeout: " + TIMEOUT);
            serverSocket.setSoTimeout(TIMEOUT);

            while(true) {
                synchronized (queue) {
                    if (queue.getNumberOfElements() == 0) {
                        break;
                    }
                }
                Socket socket;
                try {
                    socket = serverSocket.accept();
                } catch(IOException e) {
                    continue;
                }


                System.out.println("New connection");
                RequestSender requestSender = new RequestSender(socket, queue);
                requestSender.start();
                senders.add(requestSender);
            }
        } catch (IOException e) {

        }

        for (RequestSender sender : senders) {
            try {
                sender.join();
                if (!sender.getRes()) {
                    result = false;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean getResult() {
        return result;
    }


    public static void main(String[] args) {
        final int[] primes = {
                1000000007,
                1000000009,
                1000000021,
                1000000033,
                1000000087,
                1000000093,
                1000000097,
                1000000103,
                1000000123,
                1000000181, //10
                1000000207,
                1000000223,
                1000000241,
                1000000271,
                1000000289,
                1000000297,
                1000000321,
                1000000349,
                1000000363,
                1000000403, //20
                1000000409,
                1000000411,
                1000000427,
                1000000433,
                1000000439,
                1000000447,
                1000000453,
                1000000459,
                1000000483,
                1000000513, //30
                1000000531,
                1000000579,
                1000000607,
                1000000613,
                1000000637,
                1000000663,
                1000000711,
                1000000753,
                1000000787,
                1000000801, //40
                1000000829,
                1000000861,
                1000000871,
                1000000891,
                1000000901,
                1000000919,
                1000000931,
                1000000933,
                1000000993}; //49
        BossNode bossNode = new BossNode(primes);
        bossNode.start();

        System.out.println("Result:" + bossNode.getResult());
    }
}
