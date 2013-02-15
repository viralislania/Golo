package com.golo.app.activities;

import java.util.ArrayList;

import org.holoeverywhere.widget.GridView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.golo.app.ApplicationEx;
import com.golo.app.R;
import com.golo.app.model.Merchant;
import com.golo.app.service.MerchantService;
import com.golo.app.service.MerchantService.GetMerchantListener;



public class MainActivity extends BaseActivity implements GetMerchantListener
{

   @Override
   protected void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      GridView recent = (GridView) findViewById(R.id.recentlyTiles);
      SearchResultAdapter adapter = new  SearchResultAdapter();
      recent.setAdapter(adapter);

      GridView frequent = (GridView) findViewById(R.id.frequentlyTiles);
      adapter = new  SearchResultAdapter();
      frequent.setAdapter(adapter);

      ImageView searchBtn = (ImageView) findViewById(R.id.searchBtn);
      searchBtn.setOnClickListener(new OnClickListener()
      {

         @Override
         public void onClick(View v)
         {
            //service call
            MerchantService service = new MerchantService(MainActivity.this);
            ApplicationEx.operationsQueue.execute(service);
         }
      });
      
      GetHomeScreenDetailsService service = new GetHomeScreenDetailsService(this);
      service.
   }

   //   @Override
   //   public boolean onCreateOptionsMenu(Menu menu)
   //   {
   //      // Inflate the menu; this adds items to the action bar if it is present.
   //      getSupportMenuInflater().inflate(R.menu.activity_main, menu);
   //      return true;
   //   }

   private class SearchResultAdapter extends BaseAdapter
   {
      @Override
      public int getCount()
      {
         return 3;
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

   @Override
   public void OnSuccess(ArrayList<ArrayList<Merchant>> list)
   {
      ApplicationEx.merchantList = list;
      Intent intent = new Intent();
      intent.setClass(MainActivity.this, SearchResultActivity.class);
      //      intent.putParcelableArrayListExtra("merchants", list);
      startActivity(intent);
   }

   @Override
   public void OnFailure(int errorCode)
   {
      // TODO Auto-generated method stub

   }

}
