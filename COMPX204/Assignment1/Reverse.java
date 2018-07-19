import java.io.*;
import java.net.*;

public class Reverse {
    public static void main(String args[]) {
        if (args.length < 1) {
            System.out.println("Usage: Reverse <address1> <address2> ... <addressN>");
        } else {
            for (int i = 0; i < args.length; i++) {
                String address = args[i];
                try {
                    InetAddress IP = InetAddress.getByName(address);
                    System.out.println(address + " : " + IP.getHostName());
                } catch (UnknownHostException e) {
                    System.err.println(address + " : unknown host");
                }
            }
        }
    }
}
