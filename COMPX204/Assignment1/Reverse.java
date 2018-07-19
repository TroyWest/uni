import java.io.*;
import java.net.*;

public class Reverse {
    public static void main(String args[]) {
        // Check command properly entered and display usage if incorrect
        if (args.length < 1) {
            System.out.println("Usage: Reverse <address1> <address2> ... <addressN>");
        } else {
            // Loop through the entered ip addresses
            for (int i = 0; i < args.length; i++) {
                String address = args[i];
                try {
                    // Get the host name for the IP address and display results
                    InetAddress IP = InetAddress.getByName(address);
                    System.out.println(address + " : " + IP.getHostName());
                } catch (UnknownHostException e) {
                    // Display error for unknown hosts
                    System.err.println(address + " : unknown host");
                }
            }
        }
    }
}
