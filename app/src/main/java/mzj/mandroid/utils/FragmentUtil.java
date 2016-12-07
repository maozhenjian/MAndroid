package mzj.mandroid.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


/**
 * Created by Tong Maobiao on 2015/11/4.
 *
 *
 */
public class FragmentUtil {


    public static void switchFragment(FragmentManager fragmentManager,int containerViewId, Fragment from,Fragment to) {
        if (from == to) {
            return;
        }
        FragmentTransaction trx = fragmentManager.beginTransaction();
        if (from.isAdded()) {
            trx.hide(from);
        }
        if (!to.isAdded()) {
            trx.add(containerViewId, to);
        }
        trx.show(to).commit();
    }

    /**
     * 切换Fragment
     *
//     * @param containerViewId
     * @param from
     * @param to
     */
    public static void switchFragment(FragmentManager fragmentManager,Fragment from, Fragment to, int id, String tag) {
        if (to == null) {
            return;
        }
//        FragmentManager manager =getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (from == null) {
            transaction.add(id, to, tag);
        } else {
            transaction.hide(from);
            if (to.isAdded()) {
                transaction.show(to);
            } else {
                transaction.add(id, to, tag);
            }
        }
        transaction.commitAllowingStateLoss();
    }




}
