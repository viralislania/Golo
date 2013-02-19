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
import android.widget.TextView;

import com.golo.app.ApplicationEx;
import com.golo.app.R;
import com.golo.app.model.Home;
import com.golo.app.model.Merchant;
import com.golo.app.service.HomeScreenDetailsService;
import com.golo.app.service.HomeScreenDetailsService.GetHomeScreenDetailsListener;
import com.golo.app.service.MerchantService;
import com.golo.app.service.MerchantService.GetMerchantListener;

public class MainActivity extends BaseActivity implements GetMerchantListener, GetHomeScreenDetailsListener
{

   @Override
   protected void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

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

      HomeScreenDetailsService service = new HomeScreenDetailsService(this);
      ApplicationEx.operationsQueue.execute(service);
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
      ArrayList<Merchant> merchantList;
      public SearchResultAdapter(ArrayList<Merchant> merchantList)
      {
         this.merchantList = merchantList;
      }

      @Override
      public int getCount()
      {
         return merchantList.size();
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

         TextView loyaltyPoints = (TextView) convertView.findViewById(R.id.loyaltyPoints);
         TextView pt = (TextView) convertView.findViewById(R.id.pt);
         TextView vegNonVeg = (TextView) convertView.findViewById(R.id.vgNvg);
         TextView merchantName = (TextView) convertView.findViewById(R.id.merchantName);

         loyaltyPoints.setText(merchantList.get(position).getLoyaltyPoints() + "");
         pt.setText("pt");
         vegNonVeg.setText(merchantList.get(position).isVeg() ? "V" : "NV");
         merchantName.setText(merchantList.get(position).getMerchantName());

         convertView.setTag(merchantList.get(position));


         return convertView;

      }

   }

   @Override
   public void OnSuccessSearchResult(ArrayList<ArrayList<Merchant>> list)
   {
      ApplicationEx.merchantList = list;
      Intent intent = new Intent();
      intent.setClass(MainActivity.this, SearchResultActivity.class);
      //      intent.putParcelableArrayListExtra("merchants", list);
      startActivity(intent);
   }

   @Override
   public void onFailureSearchResult(int errorCode)
   {
      // TODO Auto-generated method stub

   }

   @Override
   public void onSuccessHomeScreenDetails(Home home)
   {
      findViewById(R.id.recentlyVisitedText).setVisibility(View.VISIBLE);
      findViewById(R.id.frequentlyVisitedText).setVisibility(View.VISIBLE);

      GridView recent = (GridView) findViewById(R.id.recentlyTiles);
      SearchResultAdapter adapter = new SearchResultAdapter(home.getRecentlyVisitedMerchantList());
      recent.setAdapter(adapter);

      GridView frequent = (GridView) findViewById(R.id.frequentlyTiles);
      adapter = new SearchResultAdapter(home.getFrequentlyVisitedMerchantList());
      frequent.setAdapter(adapter);
   }

   @Override
   public void onFailureHomeScreenDetails(int errorCode)
   {

   }

}
