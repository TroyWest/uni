package Tftp;
import java.nio.ByteBuffer;

public class TftpUtilities {
    //read request packet 
	public static final byte RRQ = 1;
	//data packet
	public static final byte DATA = 2;
	//ack packet
	public static final byte ACK = 3;
	//error packet
	public static final byte ERROR = 4;
	//the maximum number of resent
	public static final int MAX_RESEND = 5; 	
	//data buffer size: 512
	public static int DATA_BUFFER_SIZE = 512;
	//packet buffer size: 2 bytes header + 512 bytes file data;
	public static int PACKET_BUFFER_SIZE = DATA_BUFFER_SIZE + 2; 
}