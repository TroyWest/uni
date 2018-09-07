import java.net.*;
import java.io.*;
import java.util.*;
import Tftp.TftpUtilities;

class TftpClient {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Nope!");
            return;
        }
        try {
            DatagramSocket socket = new DatagramSocket(6969);

            socket.close();
            // DatagramPacket packet = new DatagramPacket(buf, length);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}