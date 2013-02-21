package com.golo.app.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class LoyaltyPoints implements Model
{
   private static final String USERID = "userId";
   private static final String TOTAL_LOYALTY_POINTS = "totalLoyaltyPoints";
   private static final String MERCHANT_LOYALTY_POINTS = "merchantLoyaltyPointsList";
   
   private String userId;
   private int totalLoyaltyPoints;
   private ArrayList<MerchantLoyaltyPoints> merchantLoyaltyPointsList;

   public class MerchantLoyaltyPoints implements Model
   {
      private static final String MERCHANT_ID = "merchantId";
      private static final String MERCHANT_NAME = "merchantName";
      private static final String MERCHANT_LOYALTY_POINTS = "merchantLoyaltyPointsList";

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

      @Override
      public JSONObject serializeJSON() throws Exception
      {
         return null;
      }

      @Override
      public void deserializeJSON(JSONObject jsonObject) throws Exception
      {
         if(jsonObject.has(MERCHANT_ID))
            setMerchantId(jsonObject.getInt(MERCHANT_ID));
         if(jsonObject.has(MERCHANT_NAME))
            setMerchantName(jsonObject.getString(MERCHANT_NAME));
         if(jsonObject.has(MERCHANT_LOYALTY_POINTS))
            setMerchantLoyaltyPoints(jsonObject.getInt(MERCHANT_LOYALTY_POINTS));
      }

   }

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

   public ArrayList<MerchantLoyaltyPoints> getMerchantLoyaltyPointsList()
   {
      return merchantLoyaltyPointsList;
   }

   public void setMerchantLoyaltyPointsList(ArrayList<MerchantLoyaltyPoints> merchantLoyaltyPointsList)
   {
      this.merchantLoyaltyPointsList = merchantLoyaltyPointsList;
   }

   @Override
   public JSONObject serializeJSON() throws Exception
   {
      return null;
   }

   @Override
   public void deserializeJSON(JSONObject jsonObject) throws Exception
   {
      if (jsonObject.has(USERID))
         setUserId(jsonObject.getString(USERID));
      if (jsonObject.has(TOTAL_LOYALTY_POINTS))
         setTotalLoyaltyPoints(jsonObject.getInt(TOTAL_LOYALTY_POINTS));
      if (jsonObject.has(MERCHANT_LOYALTY_POINTS))
      {
         JSONArray jsonArray = jsonObject.getJSONArray(MERCHANT_LOYALTY_POINTS);
         for (int i = 0; i < jsonArray.length(); i++)
         {
            JSONObject json = jsonArray.getJSONObject(i);
            MerchantLoyaltyPoints merchantLoyaltyPoints = new MerchantLoyaltyPoints();
            merchantLoyaltyPoints.deserializeJSON(json);
            merchantLoyaltyPointsList.add(merchantLoyaltyPoints);
         }
      }
   }

}
