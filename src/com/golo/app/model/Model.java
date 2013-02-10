package com.golo.app.model;

import org.json.JSONObject;

public interface Model
{
   JSONObject serializeJSON() throws Exception;
   void deserializeJSON(JSONObject jsonObject) throws Exception;
}
