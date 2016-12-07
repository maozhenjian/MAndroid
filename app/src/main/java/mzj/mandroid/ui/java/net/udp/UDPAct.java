package mzj.mandroid.ui.java.net.udp;

import android.view.View;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActUdpBinding;

/**
 * creat by mzj on 2016/10/23 19:37
 */

public class UDPAct extends BaseActivity<ActUdpBinding> {
    DatagramSocket datagramSocket;
    @Override
    protected int getLayoutId() {
        return R.layout.act_udp;
    }

    @Override
    protected void initData() {

        try {
           datagramSocket=new DatagramSocket(58888);

            binding.sendBt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (binding.etContent.getText().toString().length()>0){

                        uDPTest();
                    }


                }
            });







        } catch (SocketException e) {
            e.printStackTrace();
        }


    }

    private void uDPTest(){
        final String s=binding.etContent.getText().toString();
        new Thread(new Runnable() {
            @Override
            public void run() {
                byte[] buf=s.getBytes();
                try {

                    DatagramPacket datagramPacket=new DatagramPacket(buf,buf.length,InetAddress.getByName("192.168.11.2"),10002);
                    datagramSocket.send(datagramPacket);


                    //接收服务器反馈数据
                    byte[] backbuf = new byte[1024];
                    DatagramPacket backPacket = new DatagramPacket(backbuf, backbuf.length);
                    datagramSocket.receive(backPacket);  //接收返回数据
                    final String backMsg = new String(backbuf, 0, backPacket.getLength());


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            binding.tv.setText("服务器返回的数据为:" + backMsg);
                        }
                    });


                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    datagramSocket.close();
                }
            }
        }).start();
    }
}
