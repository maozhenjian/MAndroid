package mzj.mandroid.ui.android.normal.bundel;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActivityBundelBinding;


public class BundelAct extends BaseActivity<ActivityBundelBinding> implements View.OnClickListener {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_bundel;
    }

    @Override
    protected void initData() {
        setTitle("Bundle1");
        binding.setAct(this);

    }
     //传递   Serializable
    public void putSerializable(){
        Intent intent=new Intent(this,Bundel2Act.class);
        Bundle bundle=new Bundle();
        //Bundle包装数据
        bundle.putString("name", "maozhenjian");
        //用Bundle包装一个类对象
        bundle.putSerializable("person",new Smodel("毛振坚",23));
        intent.putExtras(bundle);
        startActivity(intent);
    }

    //传递   Parcelable
    public void putParcelable(){
        Intent intent=new Intent(this,Bundel2Act.class);
        Bundle bundle=new Bundle();
        Pmodel pmodel=new Pmodel();
        pmodel.setName("杨丽慈");
        pmodel.setId(22);
        //用Bundle包装一个类对象
        bundle.putParcelable("person",pmodel);

        //Bitmap 实现了Parcelable接口，因此也能传递
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        bundle.putParcelable("bitmap".toUpperCase(),bitmap);

        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.SBt:
                putSerializable();

                break;
            case R.id.PBt:
                putParcelable();
                break;
        }
    }
}
