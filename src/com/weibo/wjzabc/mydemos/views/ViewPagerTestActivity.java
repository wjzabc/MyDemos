package com.weibo.wjzabc.mydemos.views;

import java.util.ArrayList;
import java.util.List;

import com.weibo.wjzabc.mydemos.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class ViewPagerTestActivity extends Activity {
    private ViewPager myViewPager;

    private MyPagerAdapter myAdapter;

    private LayoutInflater mInflater;
    private List<View> mListViews;
    private View layout1 = null;
    private View layout2 = null;
    private View layout3 = null;

    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_layout);
        img=(ImageView)findViewById(R.id.img);
        myAdapter = new MyPagerAdapter();
//      myViewPager = (ViewPager) findViewById(R.id.viewpagerLayout1);
//      myViewPager.setAdapter(myAdapter);//不知道为什么，放到这里会报空指针异常，估计是ViewPager还没有实例

        mListViews = new ArrayList<View>();
        mInflater = getLayoutInflater();
//        layout1 = mInflater.inflate(R.layout.layout1, null);
//        layout2 = mInflater.inflate(R.layout.layout2, null);
//        layout3 = mInflater.inflate(R.layout.layout3, null);
        layout1 = mInflater.inflate(R.layout.layout1, null);
        layout2 = mInflater.inflate(R.layout.layout1, null);
        layout3 = mInflater.inflate(R.layout.layout1, null);

        mListViews.add(layout1);
        mListViews.add(layout2);
        mListViews.add(layout3);

        
        myViewPager = (ViewPager) findViewById(R.id.viewpagerLayout1);
        myViewPager.setAdapter(myAdapter);
        // 初始化当前显示的view
        myViewPager.setCurrentItem(0);

//        // 初始化第二个view的信息
//        EditText v2EditText = (EditText) layout2.findViewById(R.id.editText1);
//        v2EditText.setText("动态设置第二个view的值");

        myViewPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
//                Log.d("k", "onPageSelected - " + arg0);
//                // activity从1到2滑动，2被加载后掉用此方法
//                View v = mListViews.get(arg0);
//                EditText editText = (EditText) v.findViewById(R.id.editText1);
//                editText.setText("动态设置#" + arg0 + "edittext控件的值");
                switch (arg0) {
                case 0:
                    img.setImageResource(R.drawable.p1);
                    break;
                case 1:
                    img.setImageResource(R.drawable.p2);
                    break;
                    
                default:
                    img.setImageResource(R.drawable.p3);
                    break;
                }
                
                
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                Log.d("k", "onPageScrolled - " + arg0);
                // 从1到2滑动，在1滑动前调用
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                Log.d("k", "onPageScrollStateChanged - " + arg0);
                // 状态有三个0空闲，1是增在滑行中，2目标加载完毕
                /**
                 * Indicates that the pager is in an idle, settled state. The
                 * current page is fully in view and no animation is in
                 * progress.
                 */
                // public static final int SCROLL_STATE_IDLE = 0;
                /**
                 * Indicates that the pager is currently being dragged by the
                 * user.
                 */
                // public static final int SCROLL_STATE_DRAGGING = 1;
                /**
                 * Indicates that the pager is in the process of settling to a
                 * final position.
                 */
                // public static final int SCROLL_STATE_SETTLING = 2;

            }
        });

    }
    
    public void onclick(View v){
        switch (v.getId()) {
            case R.id.btn1:
                myViewPager.setCurrentItem(0,false);
                break;
            case R.id.btn2:
                myViewPager.setCurrentItem(1,false);
                break;
            case R.id.btn3:
                myViewPager.setCurrentItem(2,false);
                break;

            default:
                break;
        }
    }
    
    @Override
    protected void onResume() {
        super.onResume();
//      myViewPager.setAdapter(myAdapter);
    }

    private class MyPagerAdapter extends PagerAdapter {

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            Log.d("k", "destroyItem");
//            ((ViewPager) arg0).removeView(mListViews.get(arg1));
        }

//        @Override
//        public void finishUpdate(View arg0) {
//            Log.d("k", "finishUpdate");
//        }

        @Override
        public int getCount() {
//          Log.d("k", "getCount");
            return mListViews.size();
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            Log.d("k", "instantiateItem");
            ViewPager pager=(ViewPager) arg0;
            if(pager.getChildCount()==0){
                for(int i=0;i<mListViews.size();i++){
                    pager.addView(mListViews.get(i),i);
                }
            }
//            ((ViewPager) arg0).addView(mListViews.get(arg1), 0);
            return mListViews.get(arg1);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            Log.d("k", "isViewFromObject");
            return arg0 == (arg1);
        }

//        @Override
//        public void restoreState(Parcelable arg0, ClassLoader arg1) {
//            Log.d("k", "restoreState");
//        }
//        
//        
//
//        @Override
//        public Parcelable saveState() {
//            Log.d("k", "saveState");
//            return null;
//        }
//
//        @Override
//        public void startUpdate(View arg0) {
//            Log.d("k", "startUpdate");
//        }       

    }
}