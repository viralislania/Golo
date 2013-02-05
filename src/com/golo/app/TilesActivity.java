package com.golo.app;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

public class TilesActivity extends Activity
{
   @Override
   protected void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.tiles);



      GridView gridView = (GridView) findViewById(R.id.gridTiles);
      TilesAdapter adapter = new TilesAdapter(null);
      gridView.setAdapter(adapter);

   }

   private class TilesAdapter extends BaseAdapter
   {
      public TilesAdapter(ArrayList<String> list)
      {
      }

      @Override
      public int getCount()
      {
         return 9;
      }

      @Override
      public Object getItem(int position)
      {
         return null;
      }

      @Override
      public long getItemId(int position)
      {
         return 0;
      }

      @Override
      public View getView(int position, View convertView, ViewGroup parent)
      {
         LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         convertView = inflater.inflate(R.layout.tiles_item, null);

         convertView.findViewById(R.id.loyaltyPoints);
         convertView.findViewById(R.id.pt);
         convertView.findViewById(R.id.vgNvg);
         convertView.findViewById(R.id.merchantName);

         return convertView;

      }

   }
}
