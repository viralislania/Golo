package com.golo.app;

import org.holoeverywhere.app.Activity;

import android.os.Bundle;


public class GoloActivity extends Activity {
   /** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.main);
   }
}