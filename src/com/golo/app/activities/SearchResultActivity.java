package com.golo.app.activities;

import java.util.List;

import org.holoeverywhere.app.Dialog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.golo.app.ApplicationEx;
import com.golo.app.R;
import com.golo.app.model.Merchant;
import com.golo.app.pageindicator.LinePageIndicator;

public class SearchResultActivity extends BaseActivity
{
   private ViewPager viewPager;

   @Override
   protected void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.search_result);

      //      ArrayList<Merchant> merchantList = getIntent().getParcelableArrayListExtra("merchants");

      viewPager = (ViewPager) findViewById(R.id.pager);
      SearchResultPagerAdapter adapter = new SearchResultPagerAdapter();
      viewPager.setAdapter(adapter);

      LinePageIndicator indicator = (LinePageIndicator) findViewById(R.id.indicator);
      indicator.setViewPager(viewPager);
   }

   private class SearchResultPagerAdapter extends PagerAdapter
   {
      public SearchResultPagerAdapter()
      {
         //         merchantList = list;
      }

      @Override
      public int getCount()
      {
         //         int count = (int) Math.round((float)merchantList.size() / NUMBER_OF_GRIDS + 0.5);
         return ApplicationEx.merchantList.size();
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

         List<Merchant> list = ApplicationEx.merchantList.get(position);

         SearchResultAdapter adapter = new SearchResultAdapter(list);
         gridView.setAdapter(adapter);

         gridView.setOnItemClickListener(new OnItemClickListener()
         {
            @Override
            public void onItemClick(android.widget.AdapterView< ? > arg0, View arg1, int arg2, long arg3)
            {
               Merchant merchant = (Merchant) arg1.getTag();

               Intent intent = new Intent();
               intent.setClass(SearchResultActivity.this, SearchDetailsActivity.class);
               intent.putExtra("merchantId", merchant.getMerchantId());
               intent.putExtra("loyaltyPoints", merchant.getLoyaltyPoints());
               startActivity(intent);
            }
         });

         gridView.setOnItemLongClickListener(new OnItemLongClickListener()
         {

            @Override
            public boolean onItemLongClick(AdapterView< ? > arg0, View arg1, int arg2, long arg3)
            {
               showInstantCheckinDialog();
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

   private void showInstantCheckinDialog()
   {
      final Dialog dialog = new Dialog(this);
      dialog.setContentView(R.layout.instant_checkin);
      Button icheckInBtn = (Button) dialog.findViewById(R.id.icheck_in);
      icheckInBtn.setOnClickListener(new OnClickListener()
      {

         @Override
         public void onClick(View v)
         {
            Intent intent = new Intent();
            intent.setClass(SearchResultActivity.this, CheckInSuccessActivity.class);
            startActivity(intent);
            dialog.dismiss();
         }
      });
      dialog.show();
   }

   private class SearchResultAdapter extends BaseAdapter
   {
      List<Merchant> list;

      public SearchResultAdapter(List<Merchant> list)
      {
         this.list = list;
      }

      @Override
      public int getCount()
      {
         return list.size();
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
         if (convertView == null)
            convertView = inflater.inflate(R.layout.tiles_item, null);

         TextView loyaltyPoints = (TextView) convertView.findViewById(R.id.loyaltyPoints);
         TextView pt = (TextView) convertView.findViewById(R.id.pt);
         TextView vegNonVeg = (TextView) convertView.findViewById(R.id.vgNvg);
         TextView merchantName = (TextView) convertView.findViewById(R.id.merchantName);

         loyaltyPoints.setText(list.get(position).getLoyaltyPoints() + "");
         pt.setText("pt");
         vegNonVeg.setText(list.get(position).isVeg() ? "V" : "NV");
         merchantName.setText(list.get(position).getMerchantName() + "");

         convertView.setTag(list.get(position));

         return convertView;

      }

   }
}
