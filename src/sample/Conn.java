package sample;


import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class Conn {
    private final String HOSTNAME = "228.0.0.0";
    private final int PORT = 2280;
    private InetAddress inetAddress;
    private MulticastSocket multicastSocket;

    public String getHOSTNAME() {
        return HOSTNAME;
    }

    public int getPORT() {
        return PORT;
    }

    public InetAddress getInetAddress() {
        return inetAddress;
    }

    public MulticastSocket getMulticastSocket() {
        return multicastSocket;
    }

    public Conn() throws IOException {
        this.inetAddress = InetAddress.getByName(HOSTNAME);
        this.multicastSocket = new MulticastSocket(PORT);
        this.multicastSocket.setLoopbackMode(false);

        //  this.multicastSocket.setTimeToLive(127);

    }



    public void joinGroup() throws IOException {
        multicastSocket.joinGroup(inetAddress);
    }

    public void leaveGroup() throws IOException {
        multicastSocket.leaveGroup(inetAddress);
    }



}
