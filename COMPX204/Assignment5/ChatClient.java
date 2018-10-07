import java.io.*;
import java.lang.*;
import java.net.*;

class ChatReciever extends Thread {
    private MulticastSocket ms;

    public ChatReciever(MulticastSocket socket) {
        super();
        ms = socket;
    }

    public void run() {
        try {            
            byte[] buf = new byte[15000];
            DatagramPacket dp = new DatagramPacket(buf, buf.length);
            while (true) {
                ms.receive(dp);
                String out = new String(dp.getData());
                out.trim();

                System.out.println(dp.getAddress().getHostAddress() + ": " + out);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }

}

class ChatSender extends Thread {
    private MulticastSocket ms;
    private InetAddress address;

    public ChatSender(MulticastSocket socket, InetAddress addr) {
        super();
        ms = socket;
        address = addr;
    }

    public void run() {
        try {
            Thread.sleep(1000);
            byte[] buf;   // = new byte[];
            String message = readMessage();
            //message = "Hello world";
            buf = message.getBytes();
            DatagramPacket packet = new DatagramPacket(buf, buf.length, address, ms.getLocalPort());
            
            ms.send(packet);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }

    public String readMessage() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String message = reader.readLine();

        return message;
    }
}

class ChatClient {
    public static void main(String[] args) {
        try {

            MulticastSocket socket = new MulticastSocket(40202);
            InetAddress groupAddress = InetAddress.getByName("239.0.202.1");
            socket.joinGroup(groupAddress);
            
            System.out.println(socket.getInetAddress());
            ChatReciever session = new ChatReciever(socket);
            ChatSender sender = new ChatSender(socket, groupAddress);

            session.start();
            sender.start();
            while (true) {

            }

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }

}