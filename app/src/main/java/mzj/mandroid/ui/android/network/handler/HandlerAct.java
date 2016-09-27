package mzj.mandroid.ui.android.network.handler;

import android.os.Handler;
import android.os.Message;
import android.view.View;

import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActHandlerBinding;

/**
 * creat by mzj on 2016/9/27 10:49
 */

public class HandlerAct extends BaseActivity<ActHandlerBinding> implements View.OnClickListener{
    private android.os.Handler handler=new Handler() {

        public void handleMessage(Message msg){
            switch (msg.what){
                case 0:
                    binding.messageTv.setText(msg.obj+"");
                    break;
            }
        }

    };
    @Override
    protected int getLayoutId() {
        return R.layout.act_handler;
    }

    @Override
    protected void initData() {
        binding.sendBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send_bt:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=0;
                        message.obj="这是Handler传来的信息";
                        handler.sendMessage(message);
                    }
                }).start();

                break;

        }
    }
}
