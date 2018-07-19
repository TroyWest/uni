import java.io.*;
import java.net.*;

public class SimpleServer {
    
    public static void main(String args[]) {
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            
            while(true) {
                System.out.println("Listening on port: " + serverSocket.getLocalPort());
                Socket client = serverSocket.accept();
                PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                InetAddress clientAddress = client.getInetAddress();
                System.out.println("Connection from: " + clientAddress.getHostName());
                out.println("Hello, " + clientAddress.getHostName());
                out.println("Your IP address is " + clientAddress.getHostAddress());
                Thread.sleep(3000);
                client.close();
            }

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (InterruptedException e) {

        }
        
    }
}