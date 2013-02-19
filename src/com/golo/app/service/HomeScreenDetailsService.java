package com.golo.app.service;

import android.os.Handler;
import android.os.Message;

import com.golo.app.model.Home;
import com.golo.app.offline.Offline;

public class HomeScreenDetailsService extends Service implements Runnable
{
   public interface GetHomeScreenDetailsListener
   {
      public void onSuccessHomeScreenDetails(Home home);

      public void onFailureHomeScreenDetails(int errorCode);
   }

   private Home home;
   private final GetHomeScreenDetailsListener listener;

   public HomeScreenDetailsService(GetHomeScreenDetailsListener listener)
   {
      this.listener = listener;
   }

   @Override
   public void run()
   {
      home = Offline.getHomeScreenDetails();
      Message msg = new Message();
      msg.what = Service.SUCCESS;
      handler.sendMessage(msg);
   }

   private final Handler handler = new Handler()
   {
      @Override
      public void handleMessage(android.os.Message msg)
      {
         switch (msg.what)
         {
            case Service.SUCCESS:
               listener.onSuccessHomeScreenDetails(home);
               break;
            case Service.FAILURE:
               listener.onFailureHomeScreenDetails(Service.FAILURE);
               break;
            default:
               break;
         }
      };
   };

}
