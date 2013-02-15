package com.golo.app.service;

import com.golo.app.offline.Offline;

public class GetHomeScreenDetailsService extends Service implements Runnable
{
   interface GetHomeScreenDetailsListener
   {
      public void onSuccess();

      public void onFailure();
   }

   private GetHomeScreenDetailsListener listener;

   public GetHomeScreenDetailsService(GetHomeScreenDetailsListener listener)
   {
      this.listener = listener;
   }

   @Override
   public void run()
   {
      Offline.getHomeScreenDetails();
   }

}
