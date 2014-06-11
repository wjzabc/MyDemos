package com.weibo.wjzabc.mydemos;

import android.util.Log;

/**
 * a log helper class，will print the position where the log method was called as the prefix of log info.
 */
public class LogUtility {
    private static final boolean DEBUG = true;

    private static final boolean WARN = true;

    private static final boolean ERROR = true;

    private static final boolean INFO = true;
    
    public static enum TAG{
        DEFAUL("LOG_DEFAULT",true),
        BaseActivity("BaseActivity",true),
        CursorContentObserver("CursorContentObserver",true), 
        SynchronizedActivity("SynchronizedActivity",true), 
        Traceview("Traceview",true), 
        TouchEventDispatchActivity("TouchEventDispatchActivity",true);
        
        TAG(String tag,boolean enabled){
            Tag=tag; 
            IsEnable=enabled;
        }
        public String Tag;
        public boolean IsEnable;
    }
    
    /**
     * 获取打印log的类、方法、行号信息
     * @return
     */
    private static String getCallerInfo() {
        StackTraceElement[] stack = (new Throwable()).getStackTrace();
        StackTraceElement ele = null;
        String className = "";
        String methodName = "";
        int lineNO = 0;
        if (stack.length > 2) {
            ele = stack[2];
            try {
                className = Class.forName(ele.getClassName()).getSimpleName();
                methodName = ele.getMethodName();
                lineNO = ele.getLineNumber();
            } catch (ClassNotFoundException e) {
            }
        }

        String callerInfo = className + ":" + methodName + ":" + lineNO + "=>";
        return callerInfo;
    }
    
    public static void d() {
        if (DEBUG) {
            if(TAG.DEFAUL.IsEnable){
                Log.d(TAG.DEFAUL.Tag, getCallerInfo()); 
            }
        }
    }
    
    public static void d(String msg) {
        if (DEBUG) {
            if(TAG.DEFAUL.IsEnable){
                Log.d(TAG.DEFAUL.Tag, getCallerInfo() + String.valueOf(msg)); 
            }
        }
    }
    
    public static void d(TAG tag) {
        if (DEBUG) {
            if(tag.IsEnable){
                Log.d(tag.Tag, getCallerInfo()); 
            }
        }
    }
    
    public static void d(TAG tag,String msg) {
        if (DEBUG) {
            if(tag.IsEnable){
                Log.d(tag.Tag, getCallerInfo() + String.valueOf(msg));
            }
        }
    }
    
    public static void w(TAG tag) {
        if (WARN) {
            if(tag.IsEnable){
                Log.w(tag.Tag, getCallerInfo()); 
            }
        }
    }
    
    public static void w(TAG tag,String msg) {
        if (WARN) {
            if(tag.IsEnable){
                Log.w(tag.Tag, getCallerInfo() + String.valueOf(msg));
            }
        }
    }
    
    public static void i(TAG tag) {
        if (INFO) {
            if(tag.IsEnable){
                Log.i(tag.Tag, getCallerInfo()); 
            }
        }
    }
    
    public static void i(TAG tag,String msg) {
        if (INFO) {
            if(tag.IsEnable){
                Log.i(tag.Tag, getCallerInfo() + String.valueOf(msg));
            }
        }
    }
    
    public static void e(TAG tag) {
        if (ERROR) {
            if(tag.IsEnable){
                Log.e(tag.Tag, getCallerInfo()); 
            }
        }
    }
    
    public static void e(TAG tag,String msg) {
        if (ERROR) {
            if(tag.IsEnable){
                Log.e(tag.Tag, getCallerInfo() + String.valueOf(msg));
            }
        }
    }
    
}

