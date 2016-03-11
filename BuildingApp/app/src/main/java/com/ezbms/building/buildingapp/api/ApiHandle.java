package com.ezbms.building.buildingapp.api;


import com.ezbms.building.buildingapp.util.AppConstants;
import com.ezbms.building.buildingapp.util.AppUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Hoang on 2/27/2016.
 */
public class ApiHandle implements ApiConstants,AppConstants {

     //phuong thuc get
    public static String getResponceUserGet(String requestURL) {
        URL url;
        String response = "";
        try {
            url = new URL(requestURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            conn.setDoOutput(true);
            int responseCode = conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
            } else {
                response = "";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    //phuong thuc post
    public static String getResponceEntity(String api, List<NameValuePair> nameValuePairs){
        return getResponceEntity(api, nameValuePairs, true);
    }

    public static String getResponceEntity(String api, List<NameValuePair> nameValuePairs, boolean uploadFile){
        HttpResponse response = null;
        HttpClient client = null;
        HttpEntity httpEntity = null;
        try {
            client = new DefaultHttpClient();
            HttpParams params = client.getParams();
            HttpConnectionParams.setConnectionTimeout(params, 60000);
            HttpConnectionParams.setSoTimeout(params, 60000);
            HttpPost httpPost = new HttpPost(api);

            AppUtil.log("===request api: " + api);
            if(nameValuePairs != null){
                if(uploadFile){
                    AndroidMultiPartEntity entity = new AndroidMultiPartEntity(
                            new AndroidMultiPartEntity.ProgressListener() {

                                @Override
                                public void transferred(long num) {
                                    //publishProgress((int) ((num / (float) totalSize) * 100));
                                }
                            });
                    ContentType contentType = ContentType.create(HTTP.PLAIN_TEXT_TYPE, HTTP.UTF_8);

                    for(NameValuePair pair : nameValuePairs){
                        String name = ""+pair.getName();
                        String value = ""+pair.getValue();
                        AppUtil.log("pair: "+name+"="+value);
                        if(name.equals(IMAGE_FILE)){
                            entity.addPart(name, new FileBody(new File(value)));
                        }
                        else{
                            StringBody stringBody = new StringBody(value, contentType);
                            entity.addPart(name, stringBody);
                        }
                    }
                    httpPost.setEntity(entity);
                }
                else{
                    JSONObject jsonObject = new JSONObject();
                    for(NameValuePair pair : nameValuePairs){
                        String name = ""+pair.getName();
                        String value = ""+pair.getValue();
                        jsonObject.put(name, value);
                    }
                    AppUtil.log("pair: " + jsonObject.toString());
                    httpPost.setEntity(new ByteArrayEntity(jsonObject.toString().getBytes()));
                }
            }
            response = client.execute(httpPost);
            httpEntity = response.getEntity();
            return EntityUtils.toString(httpEntity, HTTP.UTF_8);
        } catch (Exception e) {
            AppUtil.log("request api error: " + e.getMessage());
            e.printStackTrace();
            response = null;
        } finally {
            if (client != null && client.getConnectionManager() != null) {
                client.getConnectionManager().shutdown();
            }
            if (httpEntity != null) {
                try {
                    httpEntity.consumeContent();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.gc();
        }

        return null;
    }

    /*
     public static void logout(final String unid,  final ApiCallback callback){
        new AsyncTask<Void, Void, String>() {
            @Override
            protected void onPreExecute() {
                if(callback != null){
                    callback.onProgress(API_LOGOUT);
                }
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(Void... params) {
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("unique_id", unid));
                return ApiHandle.getResponceEntity(API_LOGOUT, nameValuePairs);
            }

            @Override
            protected void onPostExecute(String result) {
                AppUtil.log("result="+result);
                if(callback != null){
                    callback.onComplete(API_LOGOUT, result, null);
                }
                super.onPostExecute(result);
            }
        }.execute();
    }
    */
}
