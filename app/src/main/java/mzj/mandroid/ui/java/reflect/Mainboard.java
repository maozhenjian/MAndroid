package mzj.mandroid.ui.java.reflect;

import android.util.Log;

/**
 * Creat by mzj on 2016/12/2 14:05
 * Des:
 */

public class Mainboard {

    public void run(){
        Log.e("TAG","run");
    }


    public void usePCI( PCI pci){
        if (pci!=null){
            pci.open();
            pci.close();
        }
    }
}
