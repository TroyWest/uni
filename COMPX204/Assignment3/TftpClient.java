import java.net.*;
import java.nio.ByteBuffer;
import java.io.*;
import java.util.*;

public class TftpClient {
    byte[] file;

    public static byte[] unpackDataPacket(DatagramPacket p){
	byte[] packetData = p.getData();
	byte[] data = new byte[packetData.length - 2];
	for(int i = 2; i < packetData.length; i++){
	    data[i-2] = packetData[i];
	}
	return data;
    }
    
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Nope!");
            return;
        }
        try {
	    InetAddress serverAddress = InetAddress.getByName(args[0]);
	    int port = Integer.parseInt(args[1]);
            DatagramSocket socket = new DatagramSocket();
            String fileName = args[2];
	    System.out.println("Connecting to " + args[0] + ":" + port);
	    // socket.connect(serverAddress, port);
	    byte[] buf = new byte[1472];
	    
	    DatagramPacket p = TftpUtility.packRRQDatagramPacket(fileName.getBytes()); //new DatagramPacket(buf, 1472);
	    p.setAddress(serverAddress);
	    p.setPort(port);
	    socket.send(p);  

	    p = new DatagramPacket(buf, 1472);
	    socket.receive(p);

	    Byte response = TftpUtility.checkPacketType(p);
	    if(response == TftpUtility.ERROR){
		TftpUtility.printErrorString(p);		
	    } else {
		byte blockNumber = TftpUtility.extractBlockSeq(p);
		if(blockNumber == -1){
		    System.out.println("Not a data packet");
		    return;
		} else {	    
		    byte[] data = unpackDataPacket(p);
		    String s = new String(data);
		    System.out.println(s);
		}
		
	    }
	    
		
	    
            socket.close();
            
        } catch (Exception e){
	    e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }
}
