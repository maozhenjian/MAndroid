package mzj.mandroid.ui.android.network.socket;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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

                    Socket socket = new Socket("192.168.11.2", 10004);
                    //设置10秒之后即认为是超时
                    socket.setSoTimeout(100000);
                    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    BufferedWriter bw=   new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                    bw.write("Client");
                    bw.flush();
                    final String line = br.readLine();
                    Log.e("TAG", "line----" + line);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            binding.tv.setText(line);
                        }
                    });

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
