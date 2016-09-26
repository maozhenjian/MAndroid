package mzj.mandroid.ui.android.normal.bundel;

import android.content.Intent;

import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActivityNextBundleBinding;


public class Bundel2Act extends BaseActivity<ActivityNextBundleBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_next_bundle;
    }

    @Override
    protected void initData() {

        Intent intent=getIntent();
        if(intent!=null){
            if (intent.getParcelableExtra("person")!=null){
                Pmodel person1= (Pmodel) intent.getParcelableExtra("person");
                binding.STv.setText("得到Parcelable传递的数据: "+person1.toString());
            }
            if (intent.getSerializableExtra("person")!=null){
                Smodel person= (Smodel) intent.getSerializableExtra("person");
                binding.STv.setText("得到Serializable传递的数据: "+person.toString());
            }
        }
    }


}
