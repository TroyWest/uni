import java.io.*;
import java.net.*;

public class Resolve {
    public static void main(String args[]) {
        if (args.length < 1) {
            System.out.println("Usage: Resolve <name1> <name2> ... <nameN>");
        } else {
            for (int i = 0; i < args.length; i++) {
                String address = args[i];
                try {
                    InetAddress IP = InetAddress.getByName(address);
                    System.out.println(address + " : " + IP.getHostAddress());
                } catch (UnknownHostException e) {
                    System.err.println(address + " : unknown host");
                }
            }
        }
    }
}
