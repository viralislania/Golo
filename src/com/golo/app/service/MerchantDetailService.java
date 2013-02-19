package com.golo.app.service;

import android.os.Handler;
import android.os.Message;

import com.golo.app.model.MerchantDetail;
import com.golo.app.offline.Offline;

public class MerchantDetailService extends Service implements Runnable
{
   public interface GetMerchantDetailListener
   {
      public void onSuccessMerchantDetails(MerchantDetail merchantDetail);

      public void onFailureMerchantDetails(int errorCode);
   }

   private MerchantDetail merchantDetail;
   private final GetMerchantDetailListener listener;
   private final int merchantId;

   public MerchantDetailService(GetMerchantDetailListener listener, int merchantId)
   {
      this.listener = listener;
      this.merchantId = merchantId;
   }

   @Override
   public void run()
   {
      merchantDetail = Offline.getMerchantDetail(merchantId);
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
               listener.onSuccessMerchantDetails(merchantDetail);
               break;
            case Service.FAILURE:
               listener.onFailureMerchantDetails(Service.FAILURE);
               break;
            default:
               break;
         }
      };
   };

}
