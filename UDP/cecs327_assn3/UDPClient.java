import java.net.*;
import java.io.IOException;
import java.io.*;

class UDPClient {
    public static void main(String[] args) {
        //args give message contents and server hostname
        DatagramSocket aSocket = null;
        try {
            aSocket = new DatagramSocket();
            //args[0] instead of m2 but we aren't running this on cli
            byte[] m = args[0].getBytes();
            //String tempAddress = "127.0.0.1";
            InetAddress aHost = InetAddress.getByName(args[1]);
            int serverPort = Integer.parseInt(args[2]);
            DatagramPacket request = new DatagramPacket(m, m.length, aHost,serverPort);
            aSocket.send(request);
            byte[] buffer = new byte[1000];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            aSocket.receive(reply);
            System.out.println("Reply: " + new String(reply.getData()));
        
        }
        catch(SocketException e){System.out.println("Socket: " + e.getMessage());}
        catch(IOException e){System.out.println("IO: " + e.getMessage());}
        finally{if(aSocket != null) aSocket.close();}
    }
}


