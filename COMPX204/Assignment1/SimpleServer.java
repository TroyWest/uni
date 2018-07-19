import java.io.*;
import java.net.*;

public class SimpleServer {
    
    public static void main(String args[]) {
        try {
            // Create ServerSocket to listen for connections
            ServerSocket serverSocket = new ServerSocket(0);
            
            while(true) {
                // Report the allocated port number
                System.out.println("Listening on port: " + serverSocket.getLocalPort());
                
                // Listen for connections
                Socket client = serverSocket.accept();
                
                // Set up output to the client
                PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                //BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                
                // Get the address of the client 
                InetAddress clientAddress = client.getInetAddress();
                System.out.println("Connection from: " + clientAddress.getHostName());
                
                // Send the response to the client
                out.println("Hello, " + clientAddress.getHostName());
                out.println("Your IP address is " + clientAddress.getHostAddress());                

                // Close the connection
                client.close();
            }
        } catch (IOException e) {
            // Display IO error messages
            System.err.println("Error: " + e.getMessage());
        }
        
    }
}