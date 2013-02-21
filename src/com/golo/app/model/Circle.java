package com.golo.app.model;

import java.util.ArrayList;

import org.json.JSONObject;

public class Circle implements Model
{
   private int circleId;
   private int userId;
   private String circleName;
   private String description;
   private ArrayList<CircleUser> circleUsers = new ArrayList<CircleUser>();

   public class CircleUser
   {
      private int circleUserId;
      private String circleUserName;

      public int getCircleUserId()
      {
         return circleUserId;
      }

      public void setCircleUserId(int circleUserId)
      {
         this.circleUserId = circleUserId;
      }

      public String getCircleUserName()
      {
         return circleUserName;
      }

      public void setCircleUserName(String circleUserName)
      {
         this.circleUserName = circleUserName;
      }
   }

   public int getCircleId()
   {
      return circleId;
   }

   public void setCircleId(int circleId)
   {
      this.circleId = circleId;
   }

   public int getUserId()
   {
      return userId;
   }

   public void setUserId(int userId)
   {
      this.userId = userId;
   }

   public String getCircleName()
   {
      return circleName;
   }

   public void setCircleName(String circleName)
   {
      this.circleName = circleName;
   }

   public String getDescription()
   {
      return description;
   }

   public void setDescription(String description)
   {
      this.description = description;
   }

   public ArrayList<CircleUser> getCircleUsers()
   {
      return circleUsers;
   }

   public void setCircleUsers(ArrayList<CircleUser> circleUsers)
   {
      this.circleUsers = circleUsers;
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
