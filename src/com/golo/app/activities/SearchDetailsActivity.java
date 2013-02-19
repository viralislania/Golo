package com.golo.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.golo.app.ApplicationEx;
import com.golo.app.R;
import com.golo.app.model.MerchantDetail;
import com.golo.app.service.MerchantDetailService;
import com.golo.app.service.MerchantDetailService.GetMerchantDetailListener;

public class SearchDetailsActivity extends BaseActivity implements GetMerchantDetailListener
{
   @Override
   protected void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.search_details);

      int merchantId = getIntent().getIntExtra("merchantId", 0);

      MerchantDetailService service = new MerchantDetailService(this, merchantId);
      ApplicationEx.operationsQueue.execute(service);

      //      ListView moreOptionsList = (ListView) findViewById(R.id.moreOptionsList);
      //      String[] more = new String[] { "Menu", "Directions", "More deals and happy hours", "Photos", "Reviews" };
      //      ArrayAdapter<String> adapter = new ArrayAdapter(this, R.layout.more_option_textview, R.id.moreOptionText, more);
      //      moreOptionsList.setAdapter(adapter);

      Button icheckInBtn = (Button) findViewById(R.id.icheckin);
      icheckInBtn.setOnClickListener(new OnClickListener()
      {

         @Override
         public void onClick(View v)
         {
            Intent intent = new Intent();
            intent.setClass(SearchDetailsActivity.this, CheckInSuccessActivity.class);
            startActivity(intent);
         }
      });

   }

   @Override
   public void onSuccessMerchantDetails(MerchantDetail merchantDetail)
   {
      updateUI(merchantDetail);
   }

   private void updateUI(MerchantDetail merchantDetail)
   {
      TextView merchantName = (TextView) findViewById(R.id.merchantName);
      merchantName.setText(merchantDetail.getMerchantName());

      TextView merchantAddr = (TextView) findViewById(R.id.merchantAddr);
      merchantAddr.setText(merchantDetail.getMerchantAddress());

      TextView merchantDetails = (TextView) findViewById(R.id.merchantDetails);
      merchantDetails.setText(merchantDetail.getDescription());

      TextView merchantAdditionalInfo = (TextView) findViewById(R.id.merchantFeatureText);
      merchantAdditionalInfo.setText(merchantDetail.getAdditionalInformation());

      TextView pricing = (TextView) findViewById(R.id.pricing);
      pricing.setText(merchantDetail.getCost());

      TextView loyaltyPoints = (TextView) findViewById(R.id.loyaltyPoints);
      loyaltyPoints.setText(getIntent().getIntExtra("loyaltyPoints", 0) + "");

   }

   @Override
   public void onFailureMerchantDetails(int errorCode)
   {
      // TODO Auto-generated method stub

   }
}
