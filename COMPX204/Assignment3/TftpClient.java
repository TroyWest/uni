import java.net.*;
import java.nio.ByteBuffer;
import java.io.*;
import java.util.*;

public class TftpClient {
    byte[] file;

    public static byte[] unpackDataPacket(DatagramPacket p) {
        byte[] packetData = p.getData();
        int length = p.getLength();
        ByteBuffer byteBuffer = ByteBuffer.allocate(length - 2); //ignore packet type and block number
        byteBuffer.put(packetData, 2, length - 2);
        return byteBuffer.array();
    }

    public static byte[] createAckPacket(byte block) {
        byte[] ackPack = new byte[2];
        ackPack[0] = TftpUtility.ACK;
        ackPack[1] = block;
        return ackPack;
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: TftpClient server_ip server_port filename");
            System.exit(0);
        }
        try {
            InetAddress serverAddress = InetAddress.getByName(args[0]);
            int port = Integer.parseInt(args[1]);
            DatagramSocket socket = new DatagramSocket();
            String fileName = args[2];
            System.out.println("Connecting to " + args[0] + ":" + port);

            byte[] buf = new byte[514];

            DatagramPacket p = TftpUtility.packRRQDatagramPacket(fileName.getBytes()); // new DatagramPacket(buf, 1472);
            p.setAddress(serverAddress);
            p.setPort(port);
            socket.send(p);

            FileOutputStream fos = new FileOutputStream("./out/" + fileName);
            int off = 0;

            boolean reading = true;
            System.out.println("Recieving file");
            while (reading) {
                p = new DatagramPacket(buf, 514);
                socket.receive(p);

                Byte response = TftpUtility.checkPacketType(p);

                if (response == TftpUtility.ERROR) {
                    TftpUtility.printErrorString(p);
                } else {
                    byte blockNumber = TftpUtility.extractBlockSeq(p);
                    byte[] data = unpackDataPacket(p);                    
                    fos.write(data);
                    int length = p.getLength();
                    p.setData(createAckPacket(blockNumber));
                    System.out.print(".");                    
                    socket.send(p);
                    if (length < 512) {
                        reading = false;
                    }
                }
            }
            System.out.println("\nTransfer complete");
            fos.flush();
            fos.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        } finally {

        }
    }
}
