package com.golo.app.activities;

import org.holoeverywhere.app.Activity;

import android.os.Bundle;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;

public class BaseActivity extends Activity
{
   @Override
   protected void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);

      //startActionMode(new AnActionModeOfEpicProportions());
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu)
   {

      menu.add("123")
      .setShowAsAction(MenuItem.SHOW_AS_ACTION_WITH_TEXT | MenuItem.SHOW_AS_ACTION_ALWAYS );

      SubMenu subMenu = menu.addSubMenu("Options");
      subMenu.add("Redeem my points");
      subMenu.add("Points history");
      subMenu.add("Circles");
      subMenu.add("Favourites");
      subMenu.add("Account details");

      MenuItem subMenuItem = subMenu.getItem();
      subMenuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS );

      return true;
   }
}
