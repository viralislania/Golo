package com.golo.app.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class Home implements Model
{
   private static final String TOTAL_LOYALTY_POINTS = "totalLoyaltyPoints";
   private static final String RECENTLY_VISITED_MERCHANT = "recentlyVisitedMerchant";
   private static final String FREQUENTLY_VISITED_MERCHANT = "frequentlyVisitedMerchant";

   private String totalLoyaltyPoints;
   private final ArrayList<Merchant> recentlyVisitedMerchantList = new ArrayList<Merchant>(1);
   private final ArrayList<Merchant> frequentlyVisitedMerchantList = new ArrayList<Merchant>(1);

   public void setTotalLoyaltyPoints(String points)
   {
      totalLoyaltyPoints = points;
   }
   public String getTotalLoyaltyPoints()
   {
      return totalLoyaltyPoints;
   }

   public ArrayList<Merchant> getRecentlyVisitedMerchantList()
   {
      return recentlyVisitedMerchantList;
   }

   public ArrayList<Merchant> getFrequentlyVisitedMerchantList()
   {
      return frequentlyVisitedMerchantList;
   }

   @Override
   public JSONObject serializeJSON() throws Exception
   {
      return null;
   }

   @Override
   public void deserializeJSON(JSONObject jsonObject) throws Exception
   {
      totalLoyaltyPoints = jsonObject.getString(TOTAL_LOYALTY_POINTS);

      JSONArray array = jsonObject.getJSONArray(RECENTLY_VISITED_MERCHANT);
      for(int i=0;i<array.length();i++)
      {
         Merchant recentlyVisitedMerchant = new Merchant();
         recentlyVisitedMerchant.deserializeJSON(array.getJSONObject(i));
         recentlyVisitedMerchantList.add(recentlyVisitedMerchant);
      }

      array = jsonObject.getJSONArray(FREQUENTLY_VISITED_MERCHANT);
      for(int i=0;i<array.length();i++)
      {
         Merchant frequentlyVisitedMerchant = new Merchant();
         frequentlyVisitedMerchant.deserializeJSON(array.getJSONObject(i));
         frequentlyVisitedMerchantList.add(frequentlyVisitedMerchant);
      }

   }

}
