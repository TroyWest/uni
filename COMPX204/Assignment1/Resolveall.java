import java.io.*;
import java.net.*;

public class Resolveall {
    public static void main(String args[]) {
        if (args.length < 1) {
            System.out.println("Usage: Resolveall <name1> <name2> ... <nameN>");
        } else {
            for (int i = 0; i < args.length; i++) {
                String address = args[i];
                try {
                    InetAddress[] IP = InetAddress.getAllByName(address);
                    for (int j = 0; j < IP.length; j++) {
                        System.out.println(address + " : " + IP[j].getHostAddress());
                    }
                } catch (UnknownHostException e) {
                    System.err.println(address + " : unknown host");
                }
            }
        }
    }
}