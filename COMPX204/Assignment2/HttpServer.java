import java.net.*;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

class HttpServerSession extends Thread {
    private Socket httpServerSessionSocket;
    private BufferedReader reader;
    private BufferedOutputStream writer;
    private String fileName;
    private Date date;
    private SimpleDateFormat df;
    private FileInputStream fin; 

    HttpServerSession(Socket connectionSocket) {
        httpServerSessionSocket = connectionSocket;
        System.out.println("Connection on port " + connectionSocket.getPort());
        date = new Date();
        df = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z");        
    }

    public void run() {
        try {
            reader = new BufferedReader(new InputStreamReader(httpServerSessionSocket.getInputStream()));
            writer = new BufferedOutputStream(httpServerSessionSocket.getOutputStream());
                      

            Boolean wellFormed = processRequest();

            if (wellFormed) {
                File page = new File(fileName);
                
                sendResponse(page);                                
            } else {

            }
            httpServerSessionSocket.close();

        } catch (

        IOException e) {
            System.err.println("IO Error" + e.getMessage());
        }
    }    

    private Boolean processRequest() throws IOException {
        String request = reader.readLine();
        String parts[] = request.split(" ");
        while (true) {
            String line = reader.readLine();
            if (line == null) {
                return false;
            }
            if (line.compareTo("") == 0) {
                break;
            }
        }
        if (parts.length != 3) {            
            return false;
        } else {
            if (parts[0].compareTo("GET") == 0) {
                if (parts[1].substring(1).isEmpty()) {
                    fileName = "index.html";
                } else {
                    fileName = parts[1].substring(1);
                }
                System.out.println(fileName);
            }
            return true;
        }
    }

    private void sendResponse(File page) throws IOException {
        if (page.exists()) {
            fin = new FileInputStream(page);

            System.out.println("Sending Response");                    
            println(writer, "HTTP/1.1 200 OK");
            println(writer, "Date: " + df.format(date));
            println(writer, "");

            SendFile(fin);
        } else {
            send404();
        }
    }

    private void send404() throws IOException {
        println(writer, "HTTP/1.1 404 File Not Found");
        println(writer, "");
        println(writer, "File not found");
    }

    private void send500() throws IOException {
        println(writer, "HTTP/1.1 500 Internal Server Error");
        println(writer, "");
        println(writer, "<h1>Internal Server Error</h1><p>Please try again later</p>");
    }

    private void SendFile(FileInputStream fin) throws IOException {
        byte[] array = new byte[10240];
        while (fin.read(array) != -1) {
            writer.write(array);
            writer.flush();
            // try {
            // Thread.sleep(1000);
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