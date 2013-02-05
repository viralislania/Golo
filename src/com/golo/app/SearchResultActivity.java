package com.golo.app;

import org.holoeverywhere.app.Activity;
import org.holoeverywhere.widget.GridView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;

public class SearchResultActivity extends Activity
{
   private ViewPager viewPager;

   @Override
   protected void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.search_result);

      // GridView gridView = (GridView) findViewById(R.id.gridTiles);
      // SearchResultAdapter adapter = new SearchResultAdapter(null);
      // gridView.setAdapter(adapter);

      viewPager = (ViewPager) findViewById(R.id.pager);
      SearchResultPagerAdapter adapter = new SearchResultPagerAdapter();
      viewPager.setAdapter(adapter);
   }

   private class SearchResultPagerAdapter extends PagerAdapter
   {

      @Override
      public int getCount()
      {
         return 5;
      }

      @Override
      public boolean isViewFromObject(View arg0, Object arg1)
      {
         return arg0 == ((View) arg1);
      }

      @Override
      public void destroyItem(ViewGroup container, int position, Object object)
      {
         container.removeView((View) object);
      }

      @Override
      public Object instantiateItem(ViewGroup container, int position)
      {

         LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         View layout = inflater.inflate(R.layout.tiles, null);
         GridView gridView = (GridView) layout.findViewById(R.id.gridTiles);
         SearchResultAdapter adapter = new SearchResultAdapter();
         gridView.setAdapter(adapter);

         gridView.setOnItemClickListener(new OnItemClickListener()
         {
            @Override
            public void onItemClick(android.widget.AdapterView< ? > arg0, View arg1, int arg2, long arg3)
            {
               Intent intent = new Intent();
               intent.setClass(SearchResultActivity.this, SearchDetailsActivity.class);
               startActivity(intent);
            }
         });

         gridView.setOnLongClickListener(new OnLongClickListener()
         {

            @Override
            public boolean onLongClick(View v)
            {
               return false;
            }
         });

         container.addView(layout);

         return layout;
      }

      @Override
      public int getItemPosition(Object object)
      {
         return POSITION_NONE;
      }
   }

   private class SearchResultAdapter extends BaseAdapter
   {
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
