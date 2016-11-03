package mzj.mandroid.ui.android.view.customview.midview.bounce;


import android.os.Bundle;
import android.util.Log;

import com.nostra13.universalimageloader.core.ImageLoader;

import mzj.mandroid.R;
import mzj.mandroid.base.BaseFragment;
import mzj.mandroid.databinding.FragmentScrollViewBinding;


public class ScrollViewFragment extends BaseFragment<FragmentScrollViewBinding> {


    public ScrollViewFragment() {

    }

    public static ScrollViewFragment newInstance() {
        ScrollViewFragment fragment = new ScrollViewFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_scroll_view;
    }

    @Override
    public void initData() {
        ImageLoader.getInstance().displayImage("http://pic.90sjimg.com/back_pic/qk/back_origin_pic/00/01/41/894cfff10a4c9f136fe794bf90e9de1f.jpg",binding.headIv);

        BounceTouchListener bounceTouchListener = BounceTouchListener.create(binding.scrollView, R.id.content,
                new BounceTouchListener.OnTranslateListener() {
                    @Override
                    public void onTranslate(float translation) {
                        Log.e("TAG","translation:"+(int)translation);
                        if (translation > 0) {
                            float scale = ((2 * translation) / binding.headerImageView.getMeasuredHeight()) + 1;
                            Log.e("TAG","scale::"+scale);
                            binding.headerImageView.setScaleX(scale);
                            binding.headerImageView.setScaleY(scale);
                        }
                    }
                }
        );

        binding.scrollView.setOnTouchListener(bounceTouchListener);
    }



}
