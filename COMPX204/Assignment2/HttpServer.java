import java.net.*;
import java.io.*;
import java.util.*;

class HttpServerSession extends Thread {
    private Socket httpServerSessionSocket;
    private BufferedReader reader;
    private BufferedOutputStream writer;

    HttpServerSession(Socket connectionSocket) {
        httpServerSessionSocket = connectionSocket;
        System.out.println("Connection on port " + connectionSocket.getPort());
    }

    public void run() {
        try {
            reader = new BufferedReader(new InputStreamReader(httpServerSessionSocket.getInputStream()));
            writer = new BufferedOutputStream(httpServerSessionSocket.getOutputStream());
            FileInputStream fin;
            String request = reader.readLine();

            String parts[] = request.split(" ");
            if (parts.length != 3) {
                httpServerSessionSocket.close();
                return;
            } else {
                if (parts[0].compareTo("GET") == 0) {
                    System.out.println(parts[1].substring(1));
                    File page = new File(parts[1].substring(1));
                    while (true) {
                        String line = reader.readLine();
                        if (line == null) {

                        }
                        if (line.compareTo("") == 0) {
                            break;
                        }
                    }
                    if (page.exists()) {
                        fin = new FileInputStream(page);

                        System.out.println("Sending Response");
                        println(writer, "HTTP/1.1 200 OK");
                        println(writer, "");
                        
                        SendFile( fin);
                    } else {
                        send404();
                    }
                }
                httpServerSessionSocket.close();
            }

        } catch (IOException e) {
            System.err.println("IO Error" + e.getMessage());
        }
    }

    private void send404() throws IOException {
        println(writer, "HTTP/1.1 404 File Not Found");
        println(writer, "");
        println(writer, "File not found");
    }

    private void SendFile(FileInputStream fin) throws IOException {
        byte[] array = new byte[10240];
        while (fin.read(array) != -1) {
            writer.write(array);
            writer.flush();
            // try {
            //     Thread.sleep(1000);
            // } catch (InterruptedException e) {

            // }
        }

    }

    private void println(BufferedOutputStream bos, String s) throws IOException {
        String news = s + "\r\n";
        byte[] array = news.getBytes();
        for (int i = 0; i < array.length; i++) {
            bos.write(array[i]);

        }
        bos.flush();
        return;
    }

}

class HttpServer {
    public static void main(String args[]) {
        try {
            // write something to the console here
            ServerSocket serverSocket = new ServerSocket(8080);
            while (true) {
                HttpServerSession session = new HttpServerSession(serverSocket.accept());
                session.start();
            }
            // serverSocket.close();
        } catch (IOException e) {

        }

    }
}