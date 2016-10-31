package mzj.mandroid.base;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import mzj.mandroid.R;


/**
 * Created by mzj on 16/4/14.
 */
public abstract class BaseActivity<BindingType extends ViewDataBinding> extends AppCompatActivity {
    public String TAG="TAG:"+getClass().getSimpleName();
    protected Context mContext;
    public BindingType binding;
    TextView toolbarTitle;
   public Toolbar toolbar;
    View v;
    //生命周期相关------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("TAG",getClass().getSimpleName());
        mContext = this;
        int layoutId = getLayoutId();
        if (layoutId != 0) {
            binding = DataBindingUtil.setContentView(this,layoutId);
            //
        }


        v = findViewById(R.id.toolbar);
        if(v != null)
        {
            toolbar = (Toolbar) v;
//            toolbar.setTitleTextAppearance(this,R.style.TextAppearance.AppCompat.Widget.ActionBar.Title);

            toolbarTitle = (TextView) v.findViewById(R.id.toolbar_title);
            setTitle(getClass().getSimpleName().toString());
            setSupportActionBar(toolbar);
            if(toolbarTitle != null)
            {
                getSupportActionBar().setDisplayShowTitleEnabled(false);
            }

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
            toolbar.setLogoDescription("xxxx");


        }


        preliminary();
    }



    @Override
    protected void onResume() {
        super.onResume();

        if(v != null)
        {

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
        if(toolbarTitle != null)
        {
//         setTitle(getClass().getSimpleName());
        }
    }

    protected void preliminary(){
        initData();
    }
    //生命周期相关==========================
    protected abstract int getLayoutId();
    protected abstract void initData();


    public void getDialogLayout(@LayoutRes int resource){
        new AlertDialog.Builder(mContext)
                .setView(this.getLayoutInflater().inflate(resource, null))
                .show();
    }

}
