package mzj.mandroid.ui.android.network.socket;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActSocketBinding;

/**
 * creat by mzj on 2016/10/24 11:46
 */

public class SocketTestAct extends BaseActivity<ActSocketBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.act_socket;
    }

    @Override
    protected void initData() {
        Log.e("TAG","initData");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.e("TAG", "run");
                    Socket socket = new Socket("192.168.11.2", 30000);
                    Log.e("TAG", "socket");
                    //设置10秒之后即认为是超时
                    socket.setSoTimeout(100000);
                    BufferedReader br = new BufferedReader(new InputStreamReader(
                            socket.getInputStream()));
                    String line = br.readLine();
                    Log.e("TAG", "line" + line);


                    socket.close();
                    br.close();


                } catch (UnknownHostException e) {
                    Log.e("UnknownHost", "来自服务器的数据");
                    e.printStackTrace();
                } catch (IOException e) {
                    Log.e("IOException", e.toString());
                    e.printStackTrace();
                }

            }
        }).start();

    }
}
