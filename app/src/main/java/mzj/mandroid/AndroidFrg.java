package mzj.mandroid;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

import java.util.ArrayList;
import java.util.List;

import mzj.mandroid.Adapter.TabsAdapter;
import mzj.mandroid.base.BaseFragment;
import mzj.mandroid.base.Config;
import mzj.mandroid.databinding.ActivityHomeBinding;
import mzj.mandroid.service.MusicService;
import mzj.mandroid.ui.android.BasisFrg;
import mzj.mandroid.ui.android.FrameFrg;
import mzj.mandroid.ui.android.NetFrg;
import mzj.mandroid.ui.android.RxFrg;
import mzj.mandroid.ui.android.SystemFrg;
import mzj.mandroid.ui.android.ViewFrg;
import mzj.mandroid.wiget.NetworkImageHolderView;

/**
 * 文本推荐：http://www.diycode.cc/wiki/androidinterview 《Android 开发工程师面试指南》
 *
 */
public class AndroidFrg extends BaseFragment<ActivityHomeBinding> implements View.OnClickListener{
    private Intent musicService;
    private ObjectAnimator anim;
    private float currentValue=0f;
    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void initData() {
        binding.setAct(this);
//        initAndroid();
        setBanner();

        TabsAdapter tabsAdapter = new TabsAdapter(getChildFragmentManager());
        tabsAdapter.addFragment(new BasisFrg(), "一般性问题");
        tabsAdapter.addFragment(new RxFrg(), "Rx系列");
        tabsAdapter.addFragment(new NetFrg(), "网络请求");
        tabsAdapter.addFragment(new ViewFrg(), "自定义View");
        tabsAdapter.addFragment(new FrameFrg(), "第三方框架");
        tabsAdapter.addFragment(new SystemFrg(), "系统相关操作");
        binding.vp.setAdapter(tabsAdapter);
        binding.tabLayout.setupWithViewPager(binding.vp);


    }


    //精灵球转动动画
    private void startAnim() {
        anim = ObjectAnimator.ofFloat(binding.Fab, "rotation",currentValue - 360, currentValue);
        anim.setDuration(5000);
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatCount(ObjectAnimator.INFINITE);
        // 设置动画监听
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // TODO Auto-generated method stub
                // 监听动画执行的位置，以便下次开始时，从当前位置开始
                currentValue = (Float) animation.getAnimatedValue();
            }
        });
        anim.start();
            //注释是补间动画相关内容
//        rotate = AnimationUtils.loadAnimation(this, R.anim.rotate_float);
//        rotate.setFillAfter(true);
//        rotate.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
////                binding.Fab.setClickable(false);
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
////                binding.Fab.setClickable(true);
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
    }
    //轮播图设置
    private void setBanner() {
        List<String> urls= new ArrayList<>();
        urls.add("http://g.hiphotos.baidu.com/zhidao/pic/item/f11f3a292df5e0feb7f0d4a35e6034a85fdf72a3.jpg");
        urls.add("http://img5.duitang.com/uploads/item/201409/24/20140924231431_vswTJ.thumb.700_0.jpeg");
        urls.add("http://tupian.qqjay.com/u/2014/0604/76_102256_1.jpg");
        urls.add("http://f.hiphotos.baidu.com/zhidao/pic/item/77c6a7efce1b9d164054628df7deb48f8d54644f.jpg");
        binding.convenientBanner.setPages(
                new CBViewHolderCreator<NetworkImageHolderView>() {
                    @Override
                    public NetworkImageHolderView createHolder() {
                        return new NetworkImageHolderView();
                    }
                }, urls)
                .startTurning(5000)     //设置自动切换（同时设置了切换时间间隔）
                .setPointViewVisible(true)    //设置指示器是否可见
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT); //设置指示器的方向
    }

    /**
     * 启动服务开启音乐
     */
    private void startMusic() {
         musicService = new Intent(getActivity(), MusicService.class);
        if (Config.MUSIC_PALYING) {
            musicService.putExtra("action".toUpperCase(), Config.STOP);
            getActivity().startService(musicService);
            anim.pause();
        } else {
            musicService.putExtra("action".toUpperCase(), Config.START);
            getActivity().startService(musicService);
            startAnim();
        }

        Config.MUSIC_PALYING = !Config.MUSIC_PALYING;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Fab:
                startMusic();
                break;

        }
    }


    @Override
    public void onDestroy() {
        if (musicService!=null){
            getActivity(). stopService(musicService);
        }
        super.onDestroy();

    }
}
