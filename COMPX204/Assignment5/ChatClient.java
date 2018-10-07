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
            // Create buffer to store incoming messages
            byte[] buf = new byte[1500];

            // Create DatagramPacket to recieve incoming messages
            DatagramPacket dp = new DatagramPacket(buf, buf.length);


            while (true) {
                // Recieve messages from the multicast socket using the datagram packet
                ms.receive(dp);

                // Get the data from the datagram packet into a string
                String out = new String(dp.getData());
                //out.trim();

                // Output the data witht the sender's address prepended
                System.out.println(dp.getAddress().getHostAddress() + ": " + out);
            }
        } catch (Exception e) {
            // Exception hanlding - print stack trace for debugging
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
            // Create a buffer to store the message
            byte[] buf;
            String message;

            while (true) {
                // Get the message from the commandline
                message = readMessage();

                // Convert the message from a string to a byte array and store in the buffer
                buf = message.getBytes();

                // Set up the datagram packet to send the data
                DatagramPacket packet = new DatagramPacket(buf, buf.length, address, ms.getLocalPort());

                // Send the data
                ms.send(packet);
            }
        } catch (Exception e) {
            // Exception hanlding - print stack trace for debugging
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }

    /**
     * Gets input from the command line
     * 
     * @return String - Users input
     * @throws IOException
     */
    public String readMessage() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String message = reader.readLine();

        return message;
    }
}

class ChatClient {
    public static void main(String[] args) {
        try {
            // Create multicast socket linked to group address 239.0.202.1
            // on port 40202
            MulticastSocket socket = new MulticastSocket(40202);
            InetAddress groupAddress = InetAddress.getByName("239.0.202.1");
            socket.joinGroup(groupAddress);

            // Create sender and reciever sessions
            ChatReciever session = new ChatReciever(socket);
            ChatSender sender = new ChatSender(socket, groupAddress);

            // Start sessions
            session.start();
            sender.start();

            // Loop to keep driver program open
            while (true) {

            }

        } catch (Exception e) {
            // Exception hanlding - print stack trace for debugging
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }

}