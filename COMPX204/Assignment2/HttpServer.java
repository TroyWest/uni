import java.net.*;
import java.io.*;
import java.util.*;

class HttpServerSession extends Thread {
    private Socket httpServerSessionSocket;

    HttpServerSession(Socket connectionSocket) {
        httpServerSessionSocket = connectionSocket;
        System.out.println("Connection on port " + connectionSocket.getPort());
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
            }
        } catch (IOException e) {

        }

    }
}