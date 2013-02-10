package com.golo.app;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import android.app.Application;

import com.golo.app.model.Merchant;

public class ApplicationEx extends Application
{
   /**
    * used to set core number of threads
    */
   private static final int CORE_POOL_SIZE = 6;

   /**
    * used to set the maximum allowed number of threads.
    */
   private static final int MAXIMUM_POOL_SIZE = 6;
   /**
    * executes each submitted task using one of possibly several pooled threads
    */
   public static ThreadPoolExecutor operationsQueue;
   public static ArrayList<ArrayList<Merchant>> merchantList;

   @Override
   public void onCreate()
   {
      super.onCreate();
      operationsQueue = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, 100000L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());
   }
}
