package mzj.mandroid.ui.java.collection;

import android.util.Log;
import android.view.View;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActArraylistBinding;
import mzj.mandroid.databinding.ActMapBinding;

/**
 * creat by mzj on 2016/10/9 11:13
 */

public class MapAct extends BaseActivity<ActMapBinding> implements View.OnClickListener {
    @Override
    protected int getLayoutId() {
        return R.layout.act_map;
    }

    @Override
    protected void initData() {
        binding.linkMapBt.setOnClickListener(this);
        binding.lruLinkedHashMapBt.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.link_map_bt:
                linkedHashMap();
                break;
            case R.id.lru_linkedHashMap_bt:
                LRULinkedHashMap();
                break;
        }
    }

    private void linkedHashMap(){
        Map<Integer,Integer> map=new LinkedHashMap<>(10,0.75f,true);
        map.put(9,3);
        map.put(7,4);
        map.put(5,9);
        map.put(3,4);
        //现在遍历的话顺序肯定是9,7,5,3
        //下面访问了一下9,3这个键值对，输出顺序就变喽~
        map.get(9);
        for(Iterator<Map.Entry<Integer,Integer>> it = map.entrySet().iterator(); it.hasNext();){
            Log.e(TAG,it.next().getKey()+"");
        }
    }


    private void LRULinkedHashMap(){
        //指定缓存最大容量为4
        Map<Integer,Integer> map=new LRULinkedHashMap<>(4);
        map.put(9,3);
        map.put(7,4);
        map.put(5,9);
        map.put(3,4);
        map.put(6,6);
        map.put(1,8);
        //总共put了5个元素，超过了指定的缓存最大容量
        //遍历结果
        for(Iterator<Map.Entry<Integer,Integer>> it=map.entrySet().iterator();it.hasNext();){
          Log.e(TAG,""+it.next().getKey());
        }
    }

    class LRULinkedHashMap<K,V> extends LinkedHashMap<K,V>{
        //定义缓存的容量
        private int capacity;
        private static final long serialVersionUID = 1L;
        //带参数的构造器
        LRULinkedHashMap(int capacity){
            //调用LinkedHashMap的构造器，传入以下参数
            super(16,0.75f,true);
            //传入指定的缓存最大容量
            this.capacity=capacity;
        }
        //实现LRU的关键方法，如果map里面的元素个数大于了缓存最大容量，则删除链表的顶端元素
        @Override
        public boolean removeEldestEntry(Map.Entry<K, V> eldest){
          Log.e(TAG,eldest.getKey() + "=" + eldest.getValue());
            return size()>capacity;
        }
    }


}
