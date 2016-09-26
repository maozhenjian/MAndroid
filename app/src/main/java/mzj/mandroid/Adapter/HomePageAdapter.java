package mzj.mandroid.Adapter;


import java.util.List;

import mzj.mandroid.R;
import mzj.mandroid.databinding.ItemHomePageBinding;
import mzj.mandroid.model.HomePageListModel;

/**
 * Created by 振坚 on 2016/7/28.
 */
public class HomePageAdapter extends BaseRvAdapter {
    private HomePageListModel homePageListModel;
    public HomePageAdapter(HomePageListModel homePageListModel) {
        this.homePageListModel = homePageListModel;
    }

    @Override
    public int getItemLayout() {
        return R.layout.item_home_page;
    }

    @Override
    public List ItemCount() {
        return homePageListModel.list;
    }

    @Override
    public void ItemAction(BaseHolder holder, int position) {
        HomePageListModel.Bean bean = homePageListModel.list.get(position);
        ItemHomePageBinding binding = (ItemHomePageBinding) holder.getBinding();
        //赋值
        binding.setList(bean);
    }


}
