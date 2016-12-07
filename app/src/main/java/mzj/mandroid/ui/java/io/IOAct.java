package mzj.mandroid.ui.java.io;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActIoBinding;

/**
 * creat by mzj on 2016/10/17 20:28
 */
public class IOAct extends BaseActivity<ActIoBinding> {

    /**
     * 字节流
     */
    InputStream inputStream;
    OutputStream outputStream;


    /**
     * 字符流
     */
    Reader reader;
    Writer writer;

    @Override
    protected int getLayoutId() {
        return R.layout.act_io;
    }

    @Override
    protected void initData() {
        getPath();
    }

    private void getPath() {

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Log.e("TAG", "Environment.getExternalStorageDirectory().getAbsolutePath():" + Environment.getExternalStorageDirectory().getAbsolutePath());
            try {
                String filePath = getFilesDir().getPath() + "/demo.txt";
                Log.e("TAG", "filePath：" + filePath);
//                File f = new File(filePath);

                FileWriter fileWriter = new FileWriter(filePath);
                fileWriter.write("maommadcsjcbsjdjbcskdj");
                fileWriter.close();



                FileReader fr =  new FileReader (filePath);

                BufferedReader br = new BufferedReader (fr);

                String s;

                while ((s = br.readLine() )!=null) {
                    Log.e("TAG",s);
                    binding.tv.setText(s);

                }



            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            Log.e("change user icon ==>>", "there is not sdcard!");
        }


    }
}
