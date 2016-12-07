package mzj.mandroid.ui.java.reflect;

import android.util.Log;

/**
 * Creat by mzj on 2016/12/2 14:10
 * Des:
 */

public class SoundCard implements PCI {
    @Override
    public void open() {
        Log.e("TAG","SoundCard:open");
    }

    @Override
    public void close() {
        Log.e("TAG","SoundCard:close");
    }
}
