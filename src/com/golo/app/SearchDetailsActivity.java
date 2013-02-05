package com.golo.app;

import org.holoeverywhere.ArrayAdapter;
import org.holoeverywhere.app.Activity;
import org.holoeverywhere.widget.ListView;

import android.os.Bundle;

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
