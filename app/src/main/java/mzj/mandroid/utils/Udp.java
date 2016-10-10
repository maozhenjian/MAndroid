package mzj.mandroid.utils;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.Arrays;

/**
 * Created by asus on 2016/8/10.
 */
public class Udp extends Thread {


    private final int UDP_PORT = 9982;
    private final byte[] QUERY_HEADER = {0x69, 0x70};
    public static final byte[] DEFAULT_PASSWD = {(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF,
            (byte) 0xFF};
    private DatagramSocket socket;
    private final int COUNT = 3;

    private DatagramPacket packet;
    private String TAG = "UDP_receive";

    public Udp() {
        try {
            ByteArrayOutputStream byteout = new ByteArrayOutputStream();
            byteout.write(QUERY_HEADER);
            byteout.write(DEFAULT_PASSWD);
            byte[] data = byteout.toByteArray();
            socket = new DatagramSocket();
            packet = new DatagramPacket(data, data.length, new InetSocketAddress("255.255.255.255", UDP_PORT));

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        int count = COUNT;
        while (count-- > 0) {
            try {
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        byte[] data = new byte[128];
        DatagramPacket receivePacket = new DatagramPacket(data, data.length);
        while (true) {
            if (socket != null) {
                try {
                    socket.receive(receivePacket);
                    byte[] receiveData = ByteUtils.subBytes(receivePacket.getData(),0,receivePacket.getLength());
                    Log.d(TAG, "数据长度："+receivePacket.getLength()+"数据字节数组："+ Arrays.toString(receiveData)+"数据字符："+new String(receiveData));
                    CommConst.DeviceIP  = new String(ByteUtils.subBytes(receiveData, 17, receiveData.length - 17), "ISO-8859-1");
                    CommConst.DeviceID = new String(ByteUtils.subBytes(receiveData, 0, 17), "ISO-8859-1");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
