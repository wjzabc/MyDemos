package com.weibo.wjzabc.mydemos.java;

import com.weibo.wjzabc.mydemos.LogUtility;

public class Account {
    String name;
    float amount;
    
    
    public Account(String name, float amount) {
        this.name = name;
        this.amount = amount;
    }

    /**
     * 存款
     * @param amt
     */
    public synchronized  void deposit(float amt) {
        float tmp = amount;
        tmp += amt;
        LogUtility.d(LogUtility.TAG.SynchronizedActivity,"thread:"+Thread.currentThread().getId()
                +">>> deposit amount="+amount);
        try {
            Thread.sleep(10);//模拟其它处理所需要的时间，比如刷新数据库等
        } catch (InterruptedException e) {
            // ignore
        }
        
        amount = tmp;
        LogUtility.d(LogUtility.TAG.SynchronizedActivity,"thread:"+Thread.currentThread().getId()
                +"<<< deposit amount="+amount);
    }

    /**
     * 取款
     * @param amt
     */
    public   void withdraw(float amt) {
        synchronized(Account.class){
            LogUtility.d(LogUtility.TAG.SynchronizedActivity,"thread:"+Thread.currentThread().getId()
                    +">>> withdraw amount="+amount);
        float tmp = amount;
        tmp -= amt;

        try {
            Thread.sleep(10);//模拟其它处理所需要的时间，比如刷新数据库等
        } catch (InterruptedException e) {
            // ignore
        }        

        amount = tmp;
        LogUtility.d(LogUtility.TAG.SynchronizedActivity,"thread:"+Thread.currentThread().getId()
                +"<<< withdraw amount="+amount);
        }
    }

    public float getBalance() {
        return amount;
    }
}