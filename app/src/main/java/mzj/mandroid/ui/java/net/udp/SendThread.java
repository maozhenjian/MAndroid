package mzj.mandroid.ui.java.net.udp;

import java.net.DatagramSocket;

/**
 * creat by mzj on 2016/11/18 13:36
 */

public class SendThread implements Runnable {
    DatagramSocket datagramSocket;
    public SendThread( DatagramSocket datagramSocket) {
        this.datagramSocket=datagramSocket;
    }

    @Override
    public void run() {

    }
}
