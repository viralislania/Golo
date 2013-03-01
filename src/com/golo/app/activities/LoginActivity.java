package com.golo.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.facebook.LoggingBehavior;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.Settings;
import com.golo.app.R;

public class LoginActivity extends FragmentActivity
{

   private static final String URL_PREFIX_FRIENDS = "https://graph.facebook.com/me/friends?access_token=";

   private Button loginBtn;
   private Session.StatusCallback statusCallback = new SessionStatusCallback();

   @Override
   protected void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.login_activity);
      loginBtn = (Button) findViewById(R.id.loginBtn);

      Settings.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS);

      Session session = Session.getActiveSession();
      if (session == null)
      {
         if (savedInstanceState != null)
         {
            session = Session.restoreSession(this, null, statusCallback, savedInstanceState);
         }
         if (session == null)
         {
            session = new Session(this);
         }
         Session.setActiveSession(session);
         if (session.getState().equals(SessionState.CREATED_TOKEN_LOADED))
         {
            session.openForRead(new Session.OpenRequest(this).setCallback(statusCallback));
         }
      }

      loginBtn.setOnClickListener(new OnClickListener()
         {
            @Override
            public void onClick(View v)
            {
               Session session = Session.getActiveSession();
               if (!session.isOpened() && !session.isClosed())
               {
                  session.openForRead(new Session.OpenRequest(LoginActivity.this).setCallback(statusCallback));
               }
               else
               {
                  Session.openActiveSession(LoginActivity.this, true, statusCallback);
               }
            }
         });
      
      updateView();
   }

   @Override
   public void onStart()
   {
      super.onStart();
      Session.getActiveSession().addCallback(statusCallback);
   }

   @Override
   public void onStop()
   {
      super.onStop();
      Session.getActiveSession().removeCallback(statusCallback);
   }

   @Override
   public void onActivityResult(int requestCode, int resultCode, Intent data)
   {
      super.onActivityResult(requestCode, resultCode, data);
      Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
   }

   @Override
   public void onSaveInstanceState(Bundle outState)
   {
      super.onSaveInstanceState(outState);
      Session session = Session.getActiveSession();
      Session.saveSession(session, outState);
   }

   private void updateView()
   {
      Session session = Session.getActiveSession();
      if (session.isOpened())
      {
         loginBtn.setText("logout");
         loginBtn.setOnClickListener(new View.OnClickListener()
            {
               public void onClick(View view)
               {
                  onClickLogout();
               }
            });
         
         startMainActivity();
         finish();
      }
      else
      {
         loginBtn.setText("Login");
         loginBtn.setOnClickListener(new View.OnClickListener()
            {
               public void onClick(View view)
               {
                  onClickLogin();
               }
            });
      }
   }

   private void startMainActivity()
   {
      Intent intent = new Intent(LoginActivity.this, MainActivity.class);
      startActivity(intent);
   }

   private void onClickLogin()
   {
      Session session = Session.getActiveSession();
      if (!session.isOpened() && !session.isClosed())
      {
         session.openForRead(new Session.OpenRequest(this).setCallback(statusCallback));
      }
      else
      {
         Session.openActiveSession(this, true, statusCallback);
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

   private class SessionStatusCallback implements Session.StatusCallback
   {
      @Override
      public void call(Session session, SessionState state, Exception exception)
      {
         updateView();
      }
   }
}
