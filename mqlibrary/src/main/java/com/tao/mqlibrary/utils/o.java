package com.tao.mqlibrary.utils;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class o {

    static  double count =0 ;
    
    public static void e(String tag, String str) {
        Log.e(tag , str) ;
//        System.err.println((++count)+" "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date(System.currentTimeMillis()))+" "+tag + ":" + str);
    }

    public static void d(String tag, String str) {
        System.out.println((++count)+" "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date(System.currentTimeMillis()))+" "+tag + ":" + str);
    }

    public static void d(String str) {
        
        
        System.out.println((++count)+" "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date(System.currentTimeMillis()))+" out>>>" + str);
    }

    public static void e(String str) {
        Log.e("out>" , str);
//        System.err.println((++count)+" "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date(System.currentTimeMillis()))+" out>>>" + str);
    }

 
}
