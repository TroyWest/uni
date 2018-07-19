import java.io.*;
import java.net.*;

public class Resolveall {
    public static void main(String args[]) {
        // Check command properly entered and display usage if incorrect
        if (args.length < 1) {
            System.out.println("Usage: Resolveall <name1> <name2> ... <nameN>");
        } else {
            // Loop through each of the hostnames entered
            for (int i = 0; i < args.length; i++) {
                String address = args[i];
                try {
                    // Get the INetAddress array containing all the InetAddresses for the current host
                    InetAddress[] IP = InetAddress.getAllByName(address);
                    // Loop through all items in the array and display the IP address
                    for (int j = 0; j < IP.length; j++) {
                        System.out.println(address + " : " + IP[j].getHostAddress());
                    }
                } catch (UnknownHostException e) {
                    // Display error for unknown hosts
                    System.err.println(address + " : unknown host");
                }
            }
        }
    }
}