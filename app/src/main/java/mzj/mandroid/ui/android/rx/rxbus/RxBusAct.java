package mzj.mandroid.ui.android.rx.rxbus;

import android.util.Log;
import android.view.View;

import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActRxbusBinding;
import rx.Subscription;
import rx.functions.Action1;

/**
 * creat by mzj on 2016/11/16 14:25
 *
 * http://www.jianshu.com/p/ca090f6e2fe2    用RxJava实现事件总线(Event Bus)
 */

public class RxBusAct extends BaseActivity<ActRxbusBinding> {
    Subscription rxSubscription;

    @Override
    protected int getLayoutId() {
        return R.layout.act_rxbus;
    }

    @Override
    protected void initData() {
        binding.bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxBus.getDefault().post(new UserEvent (1, "UserEvent"));
            }
        });

        binding.bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxBus.getDefault().post(new UserEvent (2, "MenEvent"));
            }
        });

        rxSubscription=RxBus.getDefault().toObservable(UserEvent.class)
                .subscribe(new Action1<UserEvent>() {
                               @Override
                               public void call(UserEvent userEvent) {
                                   String name = userEvent.getName();
                                   Log.e("TAG", "name:" + name);
                               }
                           }
                     );

        rxSubscription = RxBus.getDefault().toObservable(MenEvent.class)
                .subscribe(new Action1<MenEvent>() {
                               @Override
                               public void call(MenEvent userEvent) {
                                   String name = userEvent.getName();
                                   Log.e("TAG", "name:" + name);
                               }
                           }
                );

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!rxSubscription.isUnsubscribed()) {
            rxSubscription.unsubscribe();
        }
    }




}
