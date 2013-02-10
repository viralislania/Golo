package com.golo.app.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.util.ArrayList;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;

import android.graphics.drawable.Drawable;

/**
 * HTTP class used throughout the app to retrieve data from the server.
 */
public class HTTPRequest {
   private final ArrayList<NameValuePair> params;
   private final ArrayList<NameValuePair> headers;
   private final String url;
   private int responseCode;
   private String message;
   private String responseString;
   private Drawable responseDrawable;
   private int statusCode;
   private String inputData;

   public enum RequestMethod {
      GET, POST, PUT
   }

   public HTTPRequest(String url) {
      this.url = url;
      params = new ArrayList<NameValuePair>();
      headers = new ArrayList<NameValuePair>();
   }

   public void addParam(String name, String value) {
      params.add(new BasicNameValuePair(name, value));
   }

   public void addHeader(String name, String value) {
      headers.add(new BasicNameValuePair(name, value));

   }

   public int execute(RequestMethod method) throws Exception {

      //		SSOCookie[] cookies;
      //
      //			cookies = SSOAuth.getSessionCookies(ApplicationEx.context, "sc133033", "password", "359548049220135", "RV1C31N8QDB");
      //			for (int i = 0; i < cookies.length; i++) {
      //				Log.d("", "===Cookies===1="+i+"::"+cookies[i].getName());
      //				Log.d("", "===Cookies==1=="+i+"::"+cookies[i].getValue());
      //			}
      //			SSOAuth.clearSessionCookies(ApplicationEx.context);

      switch (method) {
         case GET: {
            // Add parameters
            String combinedParams = "";
            if (!params.isEmpty()) {
               combinedParams += "?";
               for (NameValuePair p : params) {
                  String paramString = p.getName() + "=" + URLEncoder.encode(p.getValue(), "UTF-8");
                  if (combinedParams.length() > 1)
                     combinedParams += "&" + paramString;
                  else
                     combinedParams += paramString;
               }
            }
            HttpGet request = new HttpGet(url + combinedParams);

            // Add headers
            for (NameValuePair h : headers)
               request.addHeader(h.getName(), h.getValue());

                  statusCode = executeRequest(request, url);
                  break;
         }
         case POST: {
            HttpPost request = new HttpPost(url);

            // Add headers
            for (NameValuePair h : headers)
               request.addHeader(h.getName(), h.getValue());

                  if (null != getInputData()) {
                     StringEntity se = new StringEntity(getInputData(), HTTP.UTF_8);
                     request.setEntity(se);
                  }
                  if (!params.isEmpty())
                     request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                  statusCode = executeRequest(request, url);
                  break;
         }

         case PUT: {
            HttpPut request = new HttpPut(url);

            // Add headers
            for (NameValuePair h : headers)
               request.addHeader(h.getName(), h.getValue());

                  if (null != getInputData()) {
                     StringEntity se = new StringEntity(getInputData(), HTTP.UTF_8);
                     request.setEntity(se);
                  }
                  if (!params.isEmpty())
                     request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                  statusCode = executeRequest(request, url);
                  break;
         }
      }
      return statusCode;
   }

   private int executeRequest(HttpUriRequest request, String url) {
      HttpClient client = new DefaultHttpClient();
      HttpResponse httpResponse;
      HttpParams params = new BasicHttpParams();
      // params.setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
      HttpConnectionParams.setConnectionTimeout(params, 30000);
      HttpConnectionParams.setSoTimeout(params, 34000);

      try {
         HostnameVerifier hostnameVerifier = org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
         SchemeRegistry registry = new SchemeRegistry();
         SocketFactory sf = PlainSocketFactory.getSocketFactory();
         registry.register(new Scheme("http", sf, 80));

         TrustAllSSLSocketFactory socketFactory;

         socketFactory = new TrustAllSSLSocketFactory();

         socketFactory.setHostnameVerifier((X509HostnameVerifier) hostnameVerifier);
         registry.register(new Scheme("https", socketFactory, 443));

         SingleClientConnManager mgr = new SingleClientConnManager(params, registry);
         DefaultHttpClient httpClient = new DefaultHttpClient(mgr, params);
         // Set verifier
         HttpsURLConnection.setDefaultHostnameVerifier(hostnameVerifier);
         httpResponse = httpClient.execute(request);
         responseCode = httpResponse.getStatusLine().getStatusCode();
         message = httpResponse.getStatusLine().getReasonPhrase();
         HttpEntity entity = httpResponse.getEntity();

         if (entity != null) {
            InputStream instream;
            instream = entity.getContent();
            responseString = convertStreamToString(instream);
            responseDrawable = null;

            // Closing the input stream will trigger connection release
            instream.close();
         }
         return responseCode;

      } catch (KeyManagementException e) {
         client.getConnectionManager().shutdown();
         e.printStackTrace();
         return responseCode;
      } catch (UnrecoverableKeyException e) {
         client.getConnectionManager().shutdown();
         e.printStackTrace();
         return responseCode;
      } catch (NoSuchAlgorithmException e) {
         client.getConnectionManager().shutdown();
         e.printStackTrace();
         return responseCode;
      } catch (KeyStoreException e) {
         client.getConnectionManager().shutdown();
         e.printStackTrace();
         return responseCode;
      } catch (ClientProtocolException e) {
         client.getConnectionManager().shutdown();
         e.printStackTrace();
         return responseCode;
      } catch (IOException e) {
         client.getConnectionManager().shutdown();
         e.printStackTrace();
         return responseCode;
      }

   }

   /** method to convert input stream received from server to string */
   public static String convertStreamToString(InputStream is) {
      BufferedReader reader = new BufferedReader(new InputStreamReader(is));
      StringBuilder sb = new StringBuilder();

      String line = null;
      try {
         while ((line = reader.readLine()) != null)
            sb.append(line + "\n");
      } catch (IOException e) {
         e.printStackTrace();
      } finally {
         try {
            is.close();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
      return sb.toString();
   }

   public String getResponseString() {
      return responseString;
   }

   public Drawable getResponseDrawable() {
      return responseDrawable;
   }

   public String getErrorMessage() {
      return message;
   }

   public int getResponseCode() {
      return responseCode;
   }

   public String getInputData() {
      return inputData;
   }

   public void setInputData(String inputData) {
      this.inputData = inputData;
   }
}