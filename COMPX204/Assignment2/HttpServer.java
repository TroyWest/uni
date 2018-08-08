import java.net.*;
import java.io.*;
import java.util.*;

class HttpServerSession extends Thread {
    private Socket httpServerSessionSocket;

    HttpServerSession(Socket connectionSocket) {
        httpServerSessionSocket = connectionSocket;
        System.out.println("Connection on port " + connectionSocket.getPort());
    }

    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpServerSessionSocket.getInputStream()));

            String header = reader.readLine();
            while(reader.readLine() != null){}
            System.out.println(header);
        } catch (IOException e) {

        }
    }

}

class HttpServer {
    public static void main(String args[]) {
        try {
            // write something to the console here
            ServerSocket serverSocket = new ServerSocket(8080);
            // Socket client = serverSocket.accept();
            while (true) {
                HttpServerSession session = new HttpServerSession(serverSocket.accept());// client);
                session.start();
            }
        } catch (IOException e) {

        }

    }
}