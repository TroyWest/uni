import java.net.*;
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

            byte[] buf = {TftpUtilities.RRQ};
            //buf. fileName.getBytes();
            
            // DatagramPacket packet = new DatagramPacket(buf, length);

            socket.close();
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}