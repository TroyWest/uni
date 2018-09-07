import java.net.*;
import java.io.*;
import java.util.*;
import TftpUtilities;

class TftpServerWorker extends Thread {
    private DatagramPacket req;
    private static final byte RRQ = 1;
    private static final byte DATA = 2;
    private static final byte ACK = 3;
    private static final byte ERROR = 4;

    private void sendfile(String filename) {
        /*
         * open the file using a FileInputStream and send it, one block at a time, to
         * the receiver.
         */
        return;
    }

    public void run() {
        /*
         * parse the request packet, ensuring that it is a RRQ and then call sendfile
         */
        return;
    }

    public TftpServerWorker(DatagramPacket req) {
        this.req = req;
    }
}

class TftpServer {
    public void start_server() {
        try {
            DatagramSocket ds = new DatagramSocket(6969);
            System.out.println("TftpServer on port " + ds.getLocalPort());

            for (;;) {
                byte[] buf = new byte[1472];
                DatagramPacket p = new DatagramPacket(buf, 1472);
                ds.receive(p);

                TftpServerWorker worker = new TftpServerWorker(p);
                worker.start();
            }
        } catch (Exception e) {
            System.err.println("Exception: " + e);
        }

        return;
    }

    public static void main(String args[]) {
        TftpServer d = new TftpServer();
        d.start_server();
    }
}