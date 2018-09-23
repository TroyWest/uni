import java.net.*;
import java.nio.ByteBuffer;
import java.io.*;
import java.util.*;

public class TftpClient {
    byte[] file;

    public static byte[] unpackDataPacket(DatagramPacket p) {
        byte[] packetData = p.getData();
        byte[] data = new byte[packetData.length - 2];
        for (int i = 2; i < packetData.length; i++) {
            data[i - 2] = packetData[i];
        }
        return data;
    }

    public static byte[] createAckPacket(byte block){
        byte[] ackPack = new byte[2];
        ackPack[0] =  TftpUtility.ACK;
        ackPack[1] = block;
        return ackPack;
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Nope!");
            return;
        }
        try {
            InetAddress serverAddress = InetAddress.getByName(args[0]);
            int port = Integer.parseInt(args[1]);
            DatagramSocket socket = new DatagramSocket();
            String fileName = args[2];
            System.out.println("Connecting to " + args[0] + ":" + port);
            
            byte[] buf = new byte[1472];

            DatagramPacket p = TftpUtility.packRRQDatagramPacket(fileName.getBytes()); // new DatagramPacket(buf, 1472);
            p.setAddress(serverAddress);
            p.setPort(port);
            socket.send(p);

            p = new DatagramPacket(buf, 1472);

            boolean reading = true;
            while (reading) {
                socket.receive(p);

                Byte response = TftpUtility.checkPacketType(p);
                if (response == TftpUtility.ERROR) {
                    TftpUtility.printErrorString(p);
                } else {                    
                    byte blockNumber = TftpUtility.extractBlockSeq(p);
                    if (blockNumber == -1) {
                        System.out.println("Not a data packet");
                        return;
                    } else {
                        byte[] data = unpackDataPacket(p);                        
                        String s = new String(data);
                        System.out.println(s);
                        
                        p.setData(createAckPacket(blockNumber));
                        socket.send(p);
                        if(p.getLength() < 512) {
                            reading = false;
                        }                        
                    }

                }
            }

            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }
}
