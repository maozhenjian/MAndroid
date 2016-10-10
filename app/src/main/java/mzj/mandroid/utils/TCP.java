package mzj.mandroid.utils;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Arrays;

/**
 * Created by asus on 2016/8/10.
 */
public class TCP extends Thread {

    private final int TCP_PORT = 9981;
    private String targetIP;
    private Socket socket;
    private OutputStream out;
    private InputStream in;
    private boolean isConnecting = false;
    private String TAG = "TCP_receive";


    public TCP(String targetIP) {
        this.targetIP = targetIP;
    }

    @Override
    public void run() {
        int count = 3;
        while (!isConnecting && count-- > 0) {
            try {
                createSocket();
            } catch (IOException e) {
                e.printStackTrace();
                //TODO:创建tcp长连接失败时的处理。
                try {
                    socket.close();
                    Thread.sleep(1000);
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }

            }
        }

        new Thread(new receviceWatcher()).start();

    }

    public void createSocket() throws IOException {
        socket = new Socket();
        SocketAddress address = new InetSocketAddress(targetIP, TCP_PORT);
        socket.connect(address);
        out = socket.getOutputStream();
        in = socket.getInputStream();
        isConnecting = true;

    }

    public void sendTcp(byte[] buf) {
        try {
            if (out == null || socket == null) {
                return;
            }
            out.write(buf);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class receviceWatcher implements Runnable {
        @Override
        public void run() {
            while (isConnecting) {
                byte[] getData = new byte[700];
                int len;
                try {
                    if ((len = in.read(getData)) != -1) {
                        byte[] temp = new byte[len];
                        System.arraycopy(getData,0,temp,0,len);
                        Log.d(TAG, Arrays.toString(temp));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
