package com.golo.app.activities;

import org.holoeverywhere.app.Activity;

import android.os.Bundle;

import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.facebook.Session;
import com.golo.app.R;

public class BaseActivity extends Activity
{
   @Override
   protected void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
   }

   @Override
   public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu)
   {
      MenuInflater inflater = getSupportMenuInflater();
      inflater.inflate(R.menu.activity_main, menu);
      return true;
   }
   
   @Override
   public boolean onOptionsItemSelected(MenuItem item)
   {
      switch (item.getItemId())
      {
         case R.id.logout:
            onClickLogout();
            return true;
         default:
            return super.onOptionsItemSelected(item);
      }
   }
   
   private void onClickLogout()
   {
      Session session = Session.getActiveSession();
      if (!session.isClosed())
      {
         session.closeAndClearTokenInformation();
      }
   }
}
