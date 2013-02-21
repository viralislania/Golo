package com.golo.app.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class Circle implements Model
{
   private static final String CIRCLE_ID="circleId";
   private static final String USER_ID="userId";
   private static final String CIRCLE_NAME="circleName";
   private static final String DESCRIPTION="description";
   private static final String CIRCLE_USER="circleUser";
   
   private int circleId;
   private int userId;
   private String circleName;
   private String description;
   private ArrayList<CircleUser> circleUsers = new ArrayList<CircleUser>();

   public class CircleUser implements Model
   {
      private static final String CIRCLE_USER_ID = "circleUserId";
      private static final String CIRCLE_USER_NAME= "circleUserName";
      
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

      @Override
      public JSONObject serializeJSON() throws Exception
      {
         return null;
      }

      @Override
      public void deserializeJSON(JSONObject jsonObject) throws Exception
      {
         if(jsonObject.has(CIRCLE_USER_ID))
            setCircleUserId(jsonObject.getInt(CIRCLE_USER_ID));
         if(jsonObject.has(CIRCLE_USER_NAME))
            setCircleUserName(jsonObject.getString(CIRCLE_USER_NAME));
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
      if(jsonObject.has(CIRCLE_ID))
         setCircleId(jsonObject.getInt(CIRCLE_ID));
      if(jsonObject.has(CIRCLE_NAME))
         setCircleName(jsonObject.getString(CIRCLE_NAME));
      if(jsonObject.has(USER_ID))
         setUserId(jsonObject.getInt(USER_ID));
      if(jsonObject.has(DESCRIPTION))
         setDescription(jsonObject.getString(DESCRIPTION));
      if(jsonObject.has(CIRCLE_USER))
      {
         JSONArray jsonArray = jsonObject.getJSONArray(CIRCLE_USER);
         for(int i=0;i<jsonArray.length();i++)
         {
            JSONObject object = jsonArray.getJSONObject(i);
            CircleUser user = new CircleUser();
            user.deserializeJSON(object);
            circleUsers.add(user);
         }
      }
   }

}
