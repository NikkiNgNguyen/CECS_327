import java.net.*;
import java.util.Scanner;
import java.io.IOException;
import java.io.*;

class UDPClient {
    public static void main(String[] args) {
    	Scanner in = new Scanner(System.in);
    	System.out.println("Enter the message you want to send to the server: ");
        String input = in.next();

        //sets the socket to null initially
        DatagramSocket aSocket = null;
        try
        {
        	//initializes the socket as a new datagramsocket object
            aSocket = new DatagramSocket();
            byte [] m = input.getBytes(); //message content

            //gets the second argument from the console which is the ip address the client wishes to connect to
            InetAddress aHost = InetAddress.getByName(args[0]);

            //creates a datagram packet request that also accepts the socket number
            DatagramPacket request = new DatagramPacket(m, m.length, aHost, Integer.parseInt(args[1]));
            //sends the datagram packet to the server side
            aSocket.send(request);

            //sets a buffer
            byte[] buffer = new byte[1000];

            //relays that the server received the message and displays the reply
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            aSocket.receive(reply);
            System.out.println("Reply: " + new String(reply.getData()));

        }
        //error handling
        catch(SocketException e)
        {
        	System.out.println("Socket: " + e.getMessage());
        }
        catch(IOException e)
        {
        	System.out.println("IO: " + e.getMessage());
        }
        finally{if(aSocket != null) aSocket.close();}
    }
}
