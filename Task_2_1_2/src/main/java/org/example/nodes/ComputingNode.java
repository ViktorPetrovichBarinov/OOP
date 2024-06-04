package org.example.nodes;

import org.example.prime.calculators.LongCalculator;
import org.example.prime.calculators.PrimeArrayCalculator;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;

public class ComputingNode extends Thread{
    private static final int PORT = 10001;
    private static final String MULTICAST_IP = "230.0.0.0";
    private static final PrimeArrayCalculator calculator = new LongCalculator();

    public ComputingNode() {
    }

    @Override
    public void run() {
        try (MulticastSocket mSocket = new MulticastSocket(PORT)){
            InetAddress group = InetAddress.getByName(MULTICAST_IP);
            mSocket.joinGroup(group);
            System.out.println("Joined to multicast: " + MULTICAST_IP);

            byte[] buffer = new byte[1024];
            int distribPort;
            while(true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                mSocket.receive(packet);
                String message = new String(packet.getData(), 0, packet.getLength());

                try {
                    distribPort = Integer.parseInt(message);
                    System.out.println(message);

                    String hostname = "localhost";

                    try (Socket socket = new Socket(hostname, distribPort);
                         DataInputStream input = new DataInputStream(socket.getInputStream());
                         DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {

                        System.out.println("Connected to the server");

                        while (true) {
                            int number;

                            try {
                                number = input.readInt();
                            } catch (Exception e) {
                                System.out.println("Numbers is ends up");
                                break;
                            }

                            System.out.println("Number:" + number);
                            boolean result = calculator.calculate(number);
                            output.writeBoolean(result);
                        }
                        break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch(Exception ignored) {
                    System.err.println("Bad port, message:" + message);
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ComputingNode node = new ComputingNode();
        node.start();
    }
}
