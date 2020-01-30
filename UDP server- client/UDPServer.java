
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer{

    public final static int MAC_PORT =50001;
    public static void main(String[] args){

try{

    //create datagram socket
    DatagramSocket serverSocket=new DatagramSocket(MAC_PORT);

    //create buffer for storing data in datagramPacket object
    byte[] buffReceiveData=new byte[1024];
    byte[] buffSendData =new byte[1024];

    DatagramPacket packetIn = new DatagramPacket(buffReceiveData,buffReceiveData.length);

    try{
        serverSocket.receive(packetIn);

    }catch(IOException e){
        e.printStackTrace();
    }

    //get data from receive packets
    String strInData=new String(packetIn.getData());
    System.out.println("Server recieved data:"+ strInData );
    buffSendData=strInData.toUpperCase().getBytes();

    //find sender's address and the port from the received packets
    InetAddress inAddress=packetIn.getAddress();
    int inPort=packetIn.getPort();

    //create datagram to send
    DatagramPacket packetOut =new DatagramPacket(bufSendData, buffSendData.length,inAddress,inPort);

    //send the response datagram
    try{
        serverSocket.send(packetOut);
    }catch(IOException e){
        e.printStackTrace();
    }

    //close the DatagramSocket
    serverSocket.close();
}catch(SocketException e){
    e.printStackTrace();
}
    }
}