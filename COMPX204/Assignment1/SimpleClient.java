import java.io.*;
import java.net.*;

public class SimpleClient {
    public static void main(String[] args) {
        // Check command properly entered and display usage if incorrect
        if (args.length != 2) {
            System.out.println("Usage: SimpleClient <host> <port>");
        } else {
            try {
                // Get the server address as an InetAddress and store the port number as an integer 
                InetAddress serverAddress = InetAddress.getByName(args[0]);
                int port = Integer.parseInt(args[1]);

                // Create a socket using the serverAddress and port variables to connect to the server
                Socket socket = new Socket(serverAddress, port);

                // Set up the input stream to get the response from the server
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                
                // Read the response from the server until a null is recieved indicaing a closed connection
                String response;
                while ((response = in.readLine()) != null) {                    
                    System.out.println(response);
                }

                // close the connection
                socket.close();            
            } catch (IOException e) {
                // Display IO Exception details
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
}