import java.net.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.io.IOException;
import java.io.*;

public class UDPServer{
    public static void main(String args[]){
    	//sets the socket to null initially
    	DatagramSocket aSocket = null;
        try{
        	//sets the socket to a datagramsocket item that intakes the port number
            aSocket = new DatagramSocket(Integer.parseInt(args[0]));
            byte[] buffer = new byte[1000];

            //loops to receive as many messages as necessary from the client
            while(true){
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);
                DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(), request.getAddress(), request.getPort());

                //displays the received message from the client side
                System.out.println("Received: " + new String(request.getData()));
                aSocket.send(reply);

                //sets request to null so the messages do not overlap in the loop
                request = null;
            }
        }
        catch(SocketException e){
            System.out.println("Socket: " + e.getMessage());
        }
        catch(IOException e){
            System.out.println("IO: " + e.getMessage());
        }
        finally{
            if(aSocket != null) aSocket.close();
        }
    }
}
