package com.golo.app.model;

import java.util.ArrayList;

import org.json.JSONObject;

public class LoyaltyPoints implements Model
{
   public class MerchantLoyaltyPoints
   {
      private int merchantId;
      private String merchantName;
      private int merchantLoyaltyPoints;

      public int getMerchantId()
      {
         return merchantId;
      }

      public void setMerchantId(int merchantId)
      {
         this.merchantId = merchantId;
      }

      public String getMerchantName()
      {
         return merchantName;
      }

      public void setMerchantName(String merchantName)
      {
         this.merchantName = merchantName;
      }

      public int getMerchantLoyaltyPoints()
      {
         return merchantLoyaltyPoints;
      }

      public void setMerchantLoyaltyPoints(int merchantLoyaltyPoints)
      {
         this.merchantLoyaltyPoints = merchantLoyaltyPoints;
      }

   }

   private String userId;
   private int totalLoyaltyPoints;
   private ArrayList<MerchantLoyaltyPoints> merchantLoyaltyPoints;
   
   public int getTotalLoyaltyPoints()
   {
      return totalLoyaltyPoints;
   }

   public void setTotalLoyaltyPoints(int totalLoyaltyPoints)
   {
      this.totalLoyaltyPoints = totalLoyaltyPoints;
   }
   
   public String getUserId()
   {
      return userId;
   }

   public void setUserId(String userId)
   {
      this.userId = userId;
   }

   public ArrayList<MerchantLoyaltyPoints> getMerchantLoyaltyPoints()
   {
      return merchantLoyaltyPoints;
   }

   public void setMerchantLoyaltyPoints(ArrayList<MerchantLoyaltyPoints> merchantLoyaltyPoints)
   {
      this.merchantLoyaltyPoints = merchantLoyaltyPoints;
   }

   @Override
   public JSONObject serializeJSON() throws Exception
   {
      return null;
   }

   @Override
   public void deserializeJSON(JSONObject jsonObject) throws Exception
   {
   }

}
