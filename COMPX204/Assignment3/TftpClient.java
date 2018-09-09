import java.net.*;
import java.nio.ByteBuffer;
import java.io.*;
import java.util.*;
import Tftp.TftpUtilities;

public class TftpClient {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Nope!");
            return;
        }
        try {
            DatagramSocket socket = new DatagramSocket(6969);
            String fileName = args[0];

            
            byte[] buf = fileName.getBytes();
            //buf. fileName.getBytes();
            
            ByteBuffer bab = ByteBuffer.allocate(buf.length + 1);


            // DatagramPacket packet = new DatagramPacket(buf, length);

            socket.close();
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}