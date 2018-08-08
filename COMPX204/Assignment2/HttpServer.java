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

            String request = reader.readLine();

            String parts[] = request.split(" ");
            if (parts.length != 3) {
                httpServerSessionSocket.close();
                return;
            } else {
                if (parts[0].compareTo("GET") == 0) {
                    System.out.println(parts[1]);
                }
                httpServerSessionSocket.close();
            }

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