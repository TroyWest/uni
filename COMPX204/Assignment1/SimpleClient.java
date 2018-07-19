import java.io.*;
import java.net.*;

public class SimpleClient {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: SimpleClient <host> <port>");
        } else {
            try {
                InetAddress serverAddress = InetAddress.getByName(args[0]);
                int port = Integer.parseInt(args[1]);
                Socket socket = new Socket(serverAddress, port);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String response;
                while ((response = in.readLine()) != null) {                    
                    System.out.println(response);
                }
                socket.close();
            } catch (UnknownHostException e) {

            } catch (IOException e) {

            }
        }
    }
}