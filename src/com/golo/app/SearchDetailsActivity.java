package com.golo.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SearchDetailsActivity extends Activity
{
   @Override
   protected void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.search_details);

      ListView moreOptionsList = (ListView) findViewById(R.id.moreOptionsList);
      String[] more = new String[]{"Menu","Directions","More deals and happy hours","Photos","Reviews"};
      ArrayAdapter<String> adapter = new ArrayAdapter(this, R.layout.more_option_textview, R.id.moreOptionText, more);
      moreOptionsList.setAdapter(adapter);

   }
}
