import java.io.*;
import java.net.*;

public class Resolve {
    public static void main(String args[]) {
        // Check command properly entered and display usage if incorrect
        if (args.length < 1) {
            System.out.println("Usage: Resolve <name1> <name2> ... <nameN>");
        } else {
            // Loop through each of the hostnames entered
            for (int i = 0; i < args.length; i++) {
                String address = args[i];
                try {
                    // Get the IP address from the host address and display results
                    InetAddress IP = InetAddress.getByName(address);
                    System.out.println(address + " : " + IP.getHostAddress());
                } catch (UnknownHostException e) {
                    // Display error for unknown hosts
                    System.err.println(address + " : unknown host");
                }
            }
        }
    }
}
