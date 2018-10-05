import java.lang.*;
import java.net.*;

class ChatSession extends Thread {    
    public void run() {
        System.out.println("Running");
    }

}

class ChatClient {
    public static void main(String[] args) {
        ChatSession session = new ChatSession();
        session.start();
    }

}