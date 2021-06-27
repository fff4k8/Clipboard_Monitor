package sample;

import javafx.collections.ObservableList;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.nio.charset.StandardCharsets;

public class RcvPacket extends Conn{
    public ObservableList<Clipboard> observableList;
    private final int bufSize;

    public RcvPacket(ObservableList<Clipboard> observableList, int bufSize) throws IOException {
        super();
        this.observableList = observableList;
        this.bufSize = bufSize;
    }

    public void receive()  {
        MulticastSocket ms = null;
        try {
            while (true) {
                byte[] buf = new byte[bufSize];
                DatagramPacket dp = new DatagramPacket(buf, buf.length);
                ms = getMulticastSocket();  // get from superclass
                ms.receive(dp);
                String ip = dp.getAddress().toString();
                String message = new String(dp.getData(), StandardCharsets.US_ASCII);
                Clipboard clipboard = new Clipboard(ip, message.trim());
                observableList.add(clipboard);
                Thread.sleep(100);
            }
        } catch (Exception e) {
            assert ms != null;
            try {
                ms.leaveGroup(getInetAddress());
                ms.close(); }
            catch (IOException ioException) { ioException.printStackTrace(); }
            System.err.print("Thread in receive throws IO exception"); e.printStackTrace(); }

          finally { if (ms != null) {
                try {
                    ms.leaveGroup(getInetAddress());
                    ms.close();
                } catch (IOException ex) { System.err.print(ex); } } } }


}
