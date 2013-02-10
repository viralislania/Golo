package com.golo.app.service;

import java.util.ArrayList;

import android.os.Handler;
import android.os.Message;

import com.golo.app.model.Merchant;
import com.golo.app.offline.Offline;

public class MerchantService extends Service implements Runnable
{
   public interface GetMerchantListener
   {
      public void OnSuccess(ArrayList<ArrayList<Merchant>> list);

      public void OnFailure(int errorCode);
   }

   private ArrayList<ArrayList<Merchant>> list;
   private final GetMerchantListener listener;

   public MerchantService(GetMerchantListener listener)
   {
      this.listener = listener;
   }

   @Override
   public void run()
   {
      list = Offline.getArrayListOfMerchants();
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
               listener.OnSuccess(list);
               break;
            case Service.FAILURE:
               listener.OnFailure(Service.FAILURE);
               break;
            default:
               break;
         }
      };
   };

}
