import java.net.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.io.IOException;
import java.io.*;

public class UDPServer{
    public static void main(String args[]){
        DatagramSocket aSocket = null;
        try{
            int port = Integer.parseInt(args[0]);
            aSocket = new DatagramSocket(port);
            System.out.println("Server is accepting requests at port: " + port);
            //take a port argument for the server
            byte[] buffer = new byte[1000];
            while(true){
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);
                DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(), request.getAddress(), request.getPort());
                aSocket.send(reply);
                System.out.println("Echoing message: \n" + new String(request.getData()));
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
