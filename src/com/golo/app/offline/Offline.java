package com.golo.app.offline;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.golo.app.model.Merchant;
import com.golo.app.model.MerchantDetail;

public class Offline
{
   static String merchantJson = "{\"merchant\":[{\"loyaltyPoints\":12,\"veg\":true,\"deals\":true,\"merchantId\":11,\"merchantName\":\"Sai Palace\",\"merchantAddress\":\"mumbai\"},{\"loyaltyPoints\":22,\"veg\":false,\"deals\":false,\"merchantId\":12,\"merchantName\":\"Westend\",\"merchantAddress\":\"mumbai\"},{\"loyaltyPoints\":10,\"veg\":true,\"deals\":false,\"merchantId\":13,\"merchantName\":\"Delhi darbar\",\"merchantAddress\":\"mumbai\"},{\"loyaltyPoints\":14,\"veg\":false,\"deals\":false,\"merchantId\":15,\"merchantName\":\"Cafe Coffee day\",\"merchantAddress\":\"mumbai\"},{\"loyaltyPoints\":15,\"veg\":false,\"deals\":true,\"merchantId\":16,\"merchantName\":\"Kebab Corner\",\"merchantAddress\":\"mumbai\"},{\"loyaltyPoints\":25,\"veg\":true,\"deals\":false,\"merchantId\":27,\"merchantName\":\"Sai sagar\",\"merchantAddress\":\"mumbai\"},{\"loyaltyPoints\":12,\"veg\":false,\"deals\":true,\"merchantId\":24,\"merchantName\":\"Alredos\",\"merchantAddress\":\"mumbai\"},{\"loyaltyPoints\":15,\"veg\":false,\"deals\":false,\"merchantId\":28,\"merchantName\":\"BBQ\",\"merchantAddress\":\"mumbai\"},{\"loyaltyPoints\":13,\"veg\":false,\"deals\":true,\"merchantId\":30,\"merchantName\":\"Westend\",\"merchantAddress\":\"mumbai\"},{\"loyaltyPoints\":16,\"veg\":false,\"deals\":false,\"merchantId\":31,\"merchantName\":\"Ribbons n ballons\",\"merchantAddress\":\"mumbai\"},{\"loyaltyPoints\":12,\"veg\":false,\"deals\":true,\"merchantId\":32,\"merchantName\":\"Alredos\",\"merchantAddress\":\"mumbai\"},{\"loyaltyPoints\":10,\"veg\":true,\"deals\":true,\"merchantId\":33,\"merchantName\":\"Cafe Coffe day\",\"merchantAddress\":\"mumbai\"},{\"loyaltyPoints\":12,\"veg\":true,\"deals\":true,\"merchantId\":11,\"merchantName\":\"Sai Palace\",\"merchantAddress\":\"mumbai\"},{\"loyaltyPoints\":22,\"veg\":false,\"deals\":false,\"merchantId\":12,\"merchantName\":\"Westend\",\"merchantAddress\":\"mumbai\"},{\"loyaltyPoints\":10,\"veg\":true,\"deals\":false,\"merchantId\":13,\"merchantName\":\"Delhi darbar\",\"merchantAddress\":\"mumbai\"},{\"loyaltyPoints\":14,\"veg\":false,\"deals\":false,\"merchantId\":15,\"merchantName\":\"Cafe Coffee day\",\"merchantAddress\":\"mumbai\"},{\"loyaltyPoints\":15,\"veg\":false,\"deals\":true,\"merchantId\":16,\"merchantName\":\"Kebab Corner\",\"merchantAddress\":\"mumbai\"},{\"loyaltyPoints\":25,\"veg\":true,\"deals\":false,\"merchantId\":27,\"merchantName\":\"Sai sagar\",\"merchantAddress\":\"mumbai\"},{\"loyaltyPoints\":12,\"veg\":false,\"deals\":true,\"merchantId\":24,\"merchantName\":\"Alredos\",\"merchantAddress\":\"mumbai\"},{\"loyaltyPoints\":15,\"veg\":false,\"deals\":false,\"merchantId\":28,\"merchantName\":\"BBQ\",\"merchantAddress\":\"mumbai\"},{\"loyaltyPoints\":13,\"veg\":false,\"deals\":true,\"merchantId\":30,\"merchantName\":\"Westend\",\"merchantAddress\":\"mumbai\"},{\"loyaltyPoints\":16,\"veg\":false,\"deals\":false,\"merchantId\":31,\"merchantName\":\"Ribbons n ballons\",\"merchantAddress\":\"mumbai\"},{\"loyaltyPoints\":12,\"veg\":false,\"deals\":true,\"merchantId\":32,\"merchantName\":\"Alredos\",\"merchantAddress\":\"mumbai\"},{\"loyaltyPoints\":10,\"veg\":true,\"deals\":true,\"merchantId\":33,\"merchantName\":\"Cafe Coffe day\",\"merchantAddress\":\"mumbai\"}]}";
   static String merchantDetailJson12 = "{\"merchantId\":12,\"merchantName\":\"Sai Palace\",\"merchantAddress\":\"S.V road malad west\",\"description\":\"Indian cuisine family restaurant\",\"additionalInformation\":\"Free 500ml coke on every order over Rs 70/-\",\"cost\":\"$$\"}";
   static String merchantDetailJson13 = "{\"merchantId\":13,\"merchantName\":\"Cafe Coffee day\",\"merchantAddress\":\"JVLR powai Hiranandani\",\"description\":\"Simply put, Cafe Coffee Day is India's favourite coffee shop, for the young and the young at heart\",\"additionalInformation\":\"Free 500ml coke on every order over Rs 70/-\",\"cost\":\"$\"}";
   static String merchantDetailJson14 = "{\"merchantId\":14,\"merchantName\":\"BBQ\",\"merchantAddress\":\"Pali Hill,Ground Floor,Om Palace, 403,Dr. Ambedkar Road, Junction, Pali Hill,Khar (West)\",\"description\":\"The Nations's favorite Barbeque destination is an exclusive vegetarian and non-vegetarian restaurant\",\"additionalInformation\":\"Free 500ml coke on every order over Rs 70/-\",\"cost\":\"$$$\"}";


   public static ArrayList<ArrayList<Merchant>> getArrayListOfMerchants()
   {
      return parse(merchantJson);
   }

   public static MerchantDetail getMerchantDetail(int merchantId)
   {
      switch (merchantId)
      {
         case 12:
            return parseMerchantDetails(merchantDetailJson12);
         case 13:
            return parseMerchantDetails(merchantDetailJson13);
         case 14:
            return parseMerchantDetails(merchantDetailJson14);
         default:
            return parseMerchantDetails(merchantDetailJson14);
      }
   }

   private static ArrayList<ArrayList<Merchant>> parse(String merchantJson)
   {
      try
      {
         JSONObject jsonObject = new JSONObject(merchantJson);
         ArrayList<Merchant> list= new ArrayList<Merchant>(1);
         ArrayList<ArrayList<Merchant>> merchantsList = new ArrayList<ArrayList<Merchant>>(1);
         if (jsonObject.has("merchant"))
         {
            JSONArray jsonArray = jsonObject.getJSONArray("merchant");
            for (int i = 0; i < jsonArray.length(); i++)
            {
               JSONObject json = (JSONObject) jsonArray.get(i);
               Merchant merchant = new Merchant();
               try
               {
                  merchant.deserializeJSON(json);
               }
               catch (Exception e)
               {
                  e.printStackTrace();
               }
               list.add(merchant);
               if(list.size() == 9)
               {
                  merchantsList.add(list);
                  list = new ArrayList<Merchant>(1);
               }
            }
            merchantsList.add(list);
            return merchantsList;
         }
      }
      catch (JSONException e)
      {
         e.printStackTrace();
      }
      return null;

   }

   private static MerchantDetail parseMerchantDetails(String merchantDetailsJson)
   {
      MerchantDetail detail = new MerchantDetail();

      try
      {
         JSONObject jsonObject = new  JSONObject(merchantDetailsJson);
         detail.deserializeJSON(jsonObject);
         return detail;
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return null;
   }

}
