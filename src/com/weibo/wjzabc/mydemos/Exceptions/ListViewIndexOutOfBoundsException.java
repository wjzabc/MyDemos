/**
 * 
 */
package com.weibo.wjzabc.mydemos.Exceptions;

import com.weibo.wjzabc.mydemos.BaseActivity;
import com.weibo.wjzabc.mydemos.LogUtility;
import com.weibo.wjzabc.mydemos.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 *test IndexOutOfBoundsException cause by listview
 */
public class ListViewIndexOutOfBoundsException extends BaseActivity {
    
    private ListView mListview;
    private ArrayAdapter<String> mAdapter;
    private ArrayAdapter<String> mAdapter2;
    private ArrayList<String> adapterData;
    private ArrayList<String> adapterData2;
    private Button footer;

    /* (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        mListview=new ListView(this);

        
        /* 我们要在listView上面没条显示的数据，放到一个数组中 */ 
        adapterData = new ArrayList<String>();
        adapterData.add("Afghanistan");
        adapterData.add("Afghanistan");
        adapterData.add("Afghanistan");
        adapterData.add("Afghanistan");
        adapterData.add("Afghanistan");
        adapterData.add("Afghanistan");
        adapterData.add("Afghanistan");
        adapterData.add("Afghanistan");
        adapterData.add("Afghanistan");
        adapterData.add("Afghanistan");
        adapterData.add("Afghanistan");
        adapterData.add("Afghanistan");
        adapterData.add("Afghanistan");
        adapterData.add("Afghanistan");
        mAdapter=new ArrayAdapter<String>(this, 
                android.R.layout.simple_list_item_1, adapterData);
        
        adapterData2 = new ArrayList<String>();
        adapterData.add("Afghanistan2");
        adapterData.add("Afghanistan2");
        adapterData.add("Afghanistan2");
        adapterData.add("Afghanistan2");
        adapterData.add("Afghanistan2");
        adapterData.add("Afghanistan2");
        adapterData.add("Afghanistan2");
        mAdapter2=new ArrayAdapter<String>(this, 
                android.R.layout.simple_list_item_1, adapterData2);
        /* 这个是数组string类型的数组 */ 
        // ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>( 
        // ArrayListDemo.this, android.R.layout.simple_list_item_1, 
        // adapterData); 
 
        /* 设置ListView的Adapter */ 
//        mListview.set
        mListview.addHeaderView(new Button(this));
        footer=new Button(this);
        footer.setText("footer");
        mListview.addFooterView(footer);
        mListview.setAdapter(mAdapter);
        setContentView(mListview);
//        adapterData.
        Handler hand=new Handler(){
            public void handleMessage(android.os.Message msg) {
//                mListview.setAdapter(mAdapter2);
                
//                mAdapter.clear();
//                mAdapter.addAll(adapterData2);
//                mAdapter.notifyDataSetChanged();
//                
//                Class c=ListViewIndexOutOfBoundsException.this.getClass();
//               LogUtility.debug("c.getName()="+c.getName());
                adapterData.clear();
//                mAdapter.notifyDataSetChanged();
//                mListview.setAdapter(adapter)
//                mListview.removeFooterView(footer);
            };
        };
        hand.sendEmptyMessageDelayed(0, 2000);
    }

}
