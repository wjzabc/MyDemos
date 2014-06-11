
package com.weibo.wjzabc.mydemos.java;

import com.weibo.wjzabc.mydemos.LogUtility;
import com.weibo.wjzabc.mydemos.R;
import com.weibo.wjzabc.mydemos.R.layout;
import com.weibo.wjzabc.mydemos.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SynchronizedActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synchronized2);
    }
    
    @Override
    public void onBackPressed() {
        System.out.println("Finally, John's balance is:" + AccountTest.acc.getBalance());
        LogUtility.d(LogUtility.TAG.SynchronizedActivity,"John's balance is:" + AccountTest.acc.getBalance());
        new Thread(){
            public void run() {AccountTest.test(null);};
        }.start();
        
    }
    

    public static class AccountTest{
        private static int NUM_OF_THREAD = 100;
        static Thread[] threads = new Thread[NUM_OF_THREAD];
        private static Account acc = new Account("John", 1000.0f);
        
        public static void test(String[] args){
            
            for (int i = 0; i< NUM_OF_THREAD; i++) {
                threads[i] = new Thread(new Runnable() {
                    public void run() {
                            acc.deposit(100.0f);
                            acc.withdraw(100.0f);
                    }
                });
                threads[i].start();
            }

            for (int i=0; i<NUM_OF_THREAD; i++){
                try {
                    threads[i].join(); //等待所有线程运行结束
                } catch (InterruptedException e) {
                    // ignore
                }
            }
            System.out.println("Finally, John's balance is:" + acc.getBalance());
            LogUtility.d(LogUtility.TAG.SynchronizedActivity,"Finally John's balance is:" + AccountTest.acc.getBalance());
        }

    }
}

