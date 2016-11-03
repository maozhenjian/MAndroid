package mzj.mandroid.ui.android.view.customview.midview.bounce;


import android.support.v4.app.Fragment;
import android.view.View;


import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.base.BaseFragment;
import mzj.mandroid.databinding.ActBounceBinding;


/**
 * creat by mzj on 2016/11/2 10:24
 */
public class BounceAct extends BaseActivity<ActBounceBinding> implements View.OnClickListener {
    private Fragment exampleFragment;
    @Override
    protected int getLayoutId() {
        return R.layout.act_bounce;
    }

    @Override
    protected void initData() {
        exampleFragment = RecyclerViewFragment.newInstance();
//        exampleFragment = ListViewFragment.newInstance();
//        exampleFragment = ScrollViewFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,exampleFragment).commit();
    }


    @Override
    public void onClick(View v) {

    }
}
