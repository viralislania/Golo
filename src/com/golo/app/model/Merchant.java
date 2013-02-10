package com.golo.app.model;

import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

public class Merchant implements Model, Parcelable
{
   private static final String LOYALTY_POINTS = "loyaltyPoints";
   private static final String VEG = "veg";
   private static final String DEAL = "deal";
   private static final String MERCHANT_ID = "merchantId";
   private static final String MERCHANT_NAME = "merchantName";
   private static final String MERCHANT_ADDRESS = "merchantAddress";

   private int loyaltyPoints;
   private boolean veg;
   private boolean deal;
   private int merchantId;
   private String merchantName;
   private String merchantAddress;

   public Merchant()
   {
   }

   private Merchant(Parcel in)
   {
      readFromParcel(in);
   }

   public int getLoyaltyPoints()
   {
      return loyaltyPoints;
   }

   public void setLoyaltyPoints(int loyaltyPoints)
   {
      this.loyaltyPoints = loyaltyPoints;
   }

   public boolean isVeg()
   {
      return veg;
   }

   public void setVeg(boolean veg)
   {
      this.veg = veg;
   }

   public boolean isDeal()
   {
      return deal;
   }

   public void setDeal(boolean deal)
   {
      this.deal = deal;
   }

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

   @Override
   public JSONObject serializeJSON() throws Exception
   {
      return null;
   }

   @Override
   public void deserializeJSON(JSONObject jsonObject) throws Exception
   {

      if (jsonObject.has(LOYALTY_POINTS))
         setLoyaltyPoints(jsonObject.getInt(LOYALTY_POINTS));
      if (jsonObject.has(VEG))
         setVeg(jsonObject.getBoolean(VEG));
      if (jsonObject.has(DEAL))
         setDeal(jsonObject.getBoolean(DEAL));
      if (jsonObject.has(MERCHANT_ID))
         setMerchantId(jsonObject.getInt(MERCHANT_ID));
      if (jsonObject.has(MERCHANT_NAME))
         setMerchantName(jsonObject.getString(MERCHANT_NAME));
      if (jsonObject.has(MERCHANT_ADDRESS))
         setMerchantAddress(jsonObject.getString(MERCHANT_ADDRESS));

   }

   public static Parcelable.Creator<Merchant> getCreator()
   {
      return CREATOR;
   }

   @Override
   public int describeContents()
   {
      return 0;
   }

   @Override
   public void writeToParcel(Parcel out, int flags)
   {
      out.writeInt(loyaltyPoints);
      out.writeValue(veg);
      out.writeValue(deal);
      out.writeInt(merchantId);
      out.writeString(merchantName);
      out.writeString(merchantAddress);
   }

   public void readFromParcel(Parcel in)
   {
      loyaltyPoints = in.readInt();
      veg = (Boolean) in.readValue(null);
      deal = (Boolean) in.readValue(null);
      merchantId = in.readInt();
      merchantName = in.readString();
      merchantAddress = in.readString();
   }

   public static final Parcelable.Creator<Merchant> CREATOR = new Parcelable.Creator<Merchant>()
         {
      public Merchant createFromParcel(Parcel in)
      {
         return new Merchant(in);
      }

      public Merchant[] newArray(int size)
      {
         return new Merchant[size];
      }
         };
}
