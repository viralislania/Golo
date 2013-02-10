package com.golo.app.model;

import org.json.JSONObject;

public class MerchantDetail implements Model
{
   private static final String MERCHANT_ID = "merchantId";
   private static final String MERCHANT_NAME = "merchantName";
   private static final String MERCHANT_ADDRESS = "merchantAddress";
   private static final String DESCRIPTION = "description";
   private static final String ADDITIONAL_INFORMATION = "additionalInformation";
   private static final String COST = "cost";

   private int merchantId;
   private String merchantName;
   private String merchantAddress;
   private String description;
   private String additionalInformation;
   private String cost;

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

   public String getMerchantAddress()
   {
      return merchantAddress;
   }

   public void setMerchantAddress(String merchantAddress)
   {
      this.merchantAddress = merchantAddress;
   }

   public String getDescription()
   {
      return description;
   }

   public void setDescription(String description)
   {
      this.description = description;
   }

   public String getAdditionalInformation()
   {
      return additionalInformation;
   }

   public void setAdditionalInformation(String additionalInformation)
   {
      this.additionalInformation = additionalInformation;
   }

   public String getCost()
   {
      return cost;
   }

   public void setCost(String cost)
   {
      this.cost = cost;
   }

   @Override
   public JSONObject serializeJSON() throws Exception
   {
      return null;
   }

   @Override
   public void deserializeJSON(JSONObject jsonObject) throws Exception
   {
      if(jsonObject.has(MERCHANT_NAME))
         setMerchantName(jsonObject.getString(MERCHANT_NAME));
      if(jsonObject.has(MERCHANT_ID))
         setMerchantId(jsonObject.getInt(MERCHANT_ID));
      if(jsonObject.has(MERCHANT_ADDRESS))
         setMerchantAddress(jsonObject.getString(MERCHANT_ADDRESS));
      if(jsonObject.has(DESCRIPTION))
         setDescription(jsonObject.getString(DESCRIPTION));
      if(jsonObject.has(ADDITIONAL_INFORMATION))
         setAdditionalInformation(jsonObject.getString(ADDITIONAL_INFORMATION));
      if(jsonObject.has(COST))
         setCost(jsonObject.getString(COST));
   }

}
