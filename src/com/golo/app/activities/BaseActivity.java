package com.golo.app.activities;

import org.holoeverywhere.app.Activity;

import android.os.Bundle;

import com.actionbarsherlock.view.MenuInflater;
import com.golo.app.R;

public class BaseActivity extends Activity
{
   @Override
   protected void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);

      //startActionMode(new AnActionModeOfEpicProportions());
   }

   @Override
   public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu)
   {
      MenuInflater inflater = getSupportMenuInflater();
      inflater.inflate(R.menu.activity_main, menu);
      return true;
   }
}
