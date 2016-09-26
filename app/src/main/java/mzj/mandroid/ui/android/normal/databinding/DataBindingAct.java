package mzj.mandroid.ui.android.normal.databinding;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;



import jp.wasabeef.blurry.Blurry;
import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActDatabingBaseBinding;

/**
 * Created by 振坚 on 2016/8/1.
 */
public class DataBindingAct extends BaseActivity<ActDatabingBaseBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.act_databing_base;
    }

    @Override
    protected void initData() {
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {

                long startMs = System.currentTimeMillis();
                Blurry.with(DataBindingAct.this)
                        .radius(25)
                        .sampling(1)
                        .color(Color.argb(66, 0, 255, 255))
                        .async()
                        .capture(findViewById(R.id.right_top))
                        .into((ImageView) findViewById(R.id.right_top));

                Blurry.with(DataBindingAct.this)
                        .radius(10)
                        .sampling(8)
                        .async()
                        .capture(findViewById(R.id.right_bottom))
                        .into((ImageView) findViewById(R.id.right_bottom));

                Blurry.with(DataBindingAct.this)
                        .radius(25)
                        .sampling(1)
                        .color(Color.argb(66, 255, 255, 0))
                        .async()
                        .capture(findViewById(R.id.left_bottom))
                        .into((ImageView) findViewById(R.id.left_bottom));

                Log.d(getString(R.string.app_name),
                        "TIME " + String.valueOf(System.currentTimeMillis() - startMs) + "ms");
            }
        });

        findViewById(R.id.button).setOnLongClickListener(new View.OnLongClickListener() {

            private boolean blurred = false;
            @Override
             public boolean onLongClick(View v) {
                if (blurred) {
                    Blurry.delete((ViewGroup) findViewById(R.id.content));
                } else {
                    long startMs = System.currentTimeMillis();
                    Blurry.with(DataBindingAct.this)
                            .radius(25)
                            .sampling(1)
                            .async()
                            .animate(500)
                            .onto((ViewGroup) findViewById(R.id.content));



                    Log.i(getString(R.string.app_name),
                            "TIME " + String.valueOf(System.currentTimeMillis() - startMs) + "ms");
                }

                blurred = !blurred;
                return true;
            }
        });
    }
}
