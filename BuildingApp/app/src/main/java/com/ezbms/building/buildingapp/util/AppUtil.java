package com.ezbms.building.buildingapp.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ezbms.building.buildingapp.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

/**
 * Created by Hoang on 2/27/2016.
 */
public class AppUtil implements AppConstants{

    public static String TAG = "LienMinh";//ten

    /** The Constant separator. */
    public static final String separator = System.getProperty("line.separator");
    public static final boolean DEBUG = true;
    public static final String KM = " km";
    public static final String M = " m";

    public static void log(String log){
        if(DEBUG){
            Log.e("abc", ""+log);
        }
    }

    public static void deleteFile(final String filePath){
        if(TextUtils.isEmpty(filePath)){
            return;
        }
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try{
                    File file = new File(filePath);
                    if(file != null && file.exists()){
                        file.delete();
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }

    public static void deleteFile(final Context context, final Uri uri){
        if(uri == null){
            return;
        }
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try{
                    File file = new File(getRealPath(context, uri));
                    if(file != null && file.exists()){
                        file.delete();
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }

    /*
    //ham dung de check xem may co PlayService cua gg chua
    //import com.google.android.gms.common.ConnectionResult;
    //import com.google.android.gms.common.GooglePlayServicesUtil;

    public static boolean checkPlayServices(Activity activity) {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, activity,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            }
            return false;
        }
        return true;
    }*/


    //ham dung cho gcm neu da luu vao sharePre...
    public static String getRegistrationId(Context context) {
        String registrationId = getPref(context, PROPERTY_REG_ID, "");
        if (registrationId.isEmpty()) {
            Log.i(TAG, "Registration not found.");
            return "";
        }
        // Check if app was updated; if so, it must clear the registration ID
        // since the existing regID is not guaranteed to work with the new
        // app version.
        int registeredVersion = getPref(context, PROPERTY_APP_VERSION, Integer.MIN_VALUE);
        int currentVersion = getVersionCode(context);
        if (registeredVersion != currentVersion) {
            Log.i(TAG, "App version changed.");
            return "";
        }
        return registrationId;
    }

    //ham dung de requestRefresh khi nhan dc gcm hay 1 so service
    public static void requestRefresh(Context context, Bundle bundle, Class<?>...classes){
        StringBuilder builder = new StringBuilder();
        for(int i=0; i<classes.length; i++){
            builder.append(classes[i].getName() + ",");
        }
        Intent refreshIntent = new Intent(ACTION_REFRESH);
        if(bundle == null){
            bundle = new Bundle();
        }
        bundle.putString(KEY_CLASS_NAME, builder.toString());
        refreshIntent.putExtras(bundle);
        context.sendBroadcast(refreshIntent);
    }

    public static Picasso getPicasso(Context context) {
        return Picasso.with(context);
    }

    public static RequestCreator getPicassoFileOrUrl(Context context, String filePath){
        if(filePath.startsWith("http")){
            return getPicassoUrl(context, filePath);
        }
        else{
            return getPicasso(context).load(new File(filePath));
        }
    }

    public static RequestCreator getPicassoUrl(Context context, String url){
        if(TextUtils.isEmpty(url)){
            return getPicasso(context).load("http://").tag(context);
        }
        return getPicasso(context).load(url).tag(context);
    }

    /*
    //cac ham dung cho thu vien load anh picasso
    //import com.squareup.picasso.Picasso;
    //import com.squareup.picasso.RequestCreator;
    public static Picasso getPicasso(Context context) {
        return Picasso.with(context);
    }

    public static RequestCreator getPicassoUrl(Context context, String url){
        if(TextUtils.isEmpty(url)){
            return getPicasso(context).load("http://").tag(context);
        }
        return getPicasso(context).load(url).tag(context);
    }

    public static RequestCreator getPicassoAsset(Context context, String filePath){
        return getPicasso(context).load(getAssetUri(filePath));
    }

    public static RequestCreator getPicassoUri(Context context, Uri uri){
        return getPicasso(context).load(uri);
    }

    public static RequestCreator getPicassoFile(Context context, String file){
        return getPicasso(context).load(new File(file));
    }

    public static RequestCreator getPicassoFileOrUrl(Context context, String filePath){
        if(filePath.startsWith("http")){
            return getPicassoUrl(context, filePath);
        }
        else{
            return getPicasso(context).load(new File(filePath));
        }
    }*/

    //ham tao file name
    public static String getCurrentTimeFileName(String ext){
        return System.currentTimeMillis() + ext;
    }

    public static Uri createImageUri(Context context){
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, getCurrentTimeFileName(".jpg"));
        return context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
    }



    public static final String getDeviceId(Context ctx) {
        TelephonyManager tm = (TelephonyManager) ctx
                .getSystemService(Context.TELEPHONY_SERVICE);
        String imeiNo = tm.getDeviceId();
        return imeiNo;
    }

    public static String getDistanceText(String distance){
        float value = Float.parseFloat(distance);
        int disI = (int) (value * 1000);
        if(disI < 1000){
            return disI + M;
        }
        return (disI/100)/10f + KM;
    }

    public static void toast(Context context, String text){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void toast(Context context, int textId){
        Toast.makeText(context, textId, Toast.LENGTH_SHORT).show();
    }

    public static String encodeBase64(String text){
        try {
            byte[] data = text.getBytes("UTF-8");
            return Base64.encodeToString(data, Base64.DEFAULT);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return text;
    }

    public static String decodeBase64(String text){
        try {
            byte[] data = Base64.decode(text, Base64.DEFAULT);
            return new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return text;
    }

    public static String toString(ArrayList<Integer> list){
        StringBuilder builder = new StringBuilder();
        for(Integer obj : list){
            builder.append(obj.toString() + ",");
        }
        if(builder.length() > 0){
            builder = builder.deleteCharAt(builder.length()-1);
        }
        return builder.toString();
    }

    public static int[] getIntArray(Context context, int arrayId){
        TypedArray array = context.getResources().obtainTypedArray(arrayId);
        int length = array.length();
        int[] ids = new int[length];
        for(int i=0; i<length; i++){
            ids[i] = array.getResourceId(i, 0);
        }
        array.recycle();
        return ids;
    }

    public static SpannableString createSpannableString(String text, String key, ClickableSpan clickableSpan){
        SpannableString spannable = new SpannableString(text);
        int start = text.indexOf(key);
        spannable.setSpan(clickableSpan, start, start + key.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannable;
    }

    public static String[] getStringArray(Context context, int arrayId){
        return context.getResources().getStringArray(arrayId);
    }

    /**
     * Gets the version code.
     *
     * @param context the context
     * @return the version code
     */
    public static int getVersionCode(Context context) {
        int v = 1;
        try {
            v = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
        }
        return v;
    }
    /**
     * Px to dp.
     *
     * @param context the context
     * @param px the px
     * @return the float
     */
    public static float pxToDp(Context context, float px){
        return px / context.getResources().getDisplayMetrics().density;
    }

    /**
     * Dp to px.
     *
     * @param context the context
     * @param dp the dp
     * @return the float
     */
    public static float dpToPx(Context context, float dp){
        return dp * context.getResources().getDisplayMetrics().density;
    }

    public static int screenWidth(Context context){
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.widthPixels;
    }

    public static int screenHeight(Context context){
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.heightPixels;
    }

    /**
     * Gets the version name.
     *
     * @param context the context
     * @return the version namer
     */
    public static String getVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni != null) {
            return cm.getActiveNetworkInfo().isConnected();
        }
        return false;
    }

    public static boolean isGpsAvailable(Context context){
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE );
        if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) &&
                !locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            return false;
        }
        return true;
    }

    /**
     * Gets the app name.
     *
     * @param context the context
     * @return the app name
     */
    public static String getAppName(Context context){
        return context.getString(R.string.app_name);
    }

    /**
     * Sets the pref.
     *
     * @param context the context
     * @param key the key
     * @param value the value
     */
    public static void setPref(Context context, String key, int value){
        SharedPreferences pref = context.getSharedPreferences(AppUtil.TAG, Context.MODE_PRIVATE);
        pref.edit().putInt(key, value).commit();
    }

    /**
     * Gets the pref.
     *
     * @param context the context
     * @param key the key
     * @param defaultValue the default value
     * @return the pref
     */
    public static int getPref(Context context, String key, int defaultValue){
        SharedPreferences pref = context.getSharedPreferences(AppUtil.TAG, Context.MODE_PRIVATE);
        return pref.getInt(key, defaultValue);
    }

    /**
     * Sets the pref.
     *
     * @param context the context
     * @param key the key
     * @param value the value
     */
    public static void setPref(Context context, String key, String value){
        SharedPreferences pref = context.getSharedPreferences(AppUtil.TAG, Context.MODE_PRIVATE);
        pref.edit().putString(key, value).commit();
    }

    /**
     * Gets the pref.
     *
     * @param context the context
     * @param key the key
     * @param defaultValue the default value
     * @return the pref
     */
    public static String getPref(Context context, String key, String defaultValue){
        SharedPreferences pref = context.getSharedPreferences(AppUtil.TAG, Context.MODE_PRIVATE);
        return pref.getString(key, defaultValue);
    }

    public static void setPref(Context context, String key, boolean value){
        SharedPreferences pref = context.getSharedPreferences(AppUtil.TAG, Context.MODE_PRIVATE);
        pref.edit().putBoolean(key, value).commit();
    }

    public static boolean getPref(Context context, String key, boolean defaultValue){
        SharedPreferences pref = context.getSharedPreferences(AppUtil.TAG, Context.MODE_PRIVATE);
        return pref.getBoolean(key, defaultValue);
    }

    public static boolean clearPref(Context context){
        SharedPreferences pref = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        return pref.edit().clear().commit();
    }

    /**
     * Hide keyboard.
     *
     * @param editText the edit text
     */


    /**
     * Dp to px.
     *
     * @param context the context
     * @param dp the dp
     * @return the int
     */
    public static int dpToPx(Context context, int dp){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
    }

    /**
     * Px to dp.
     *
     * @param context the context
     * @param px the px
     * @return the int
     */
    public static int pxToDp(Context context, int px){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px,
                context.getResources().getDisplayMetrics());
    }

    public static Uri getAssetUri(String filePath){
        return Uri.parse("file:///android_asset/" + filePath);
    }

    public static String getFileDir(){
        return Environment.getExternalStorageDirectory() + "/" + TAG;
    }

    public static File createFile(String dirPath, String fileName){
        File dir = new File(dirPath);
        if(!dir.exists()){
            dir.mkdirs();
        }
        return new File(dir, fileName);
    }

    public static File createImageFile(){
        return createFile(getFileDir(), System.currentTimeMillis() + ".jpg");
    }

    public static Uri captureImage(Activity activity, int requestCode){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri mImageCaptureUri = Uri.fromFile(new File(Environment
                .getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg"));

        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT,
                mImageCaptureUri);
        try {
            intent.putExtra("return-data", true);
            activity.startActivityForResult(intent, requestCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mImageCaptureUri;
    }

    public static Uri captureImage(Fragment fragment, int requestCode){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri mImageCaptureUri = Uri.fromFile(new File(Environment
                .getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg"));

        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT,
                mImageCaptureUri);
        try {
            intent.putExtra("return-data", true);
            fragment.startActivityForResult(intent, requestCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mImageCaptureUri;
    }


    //Pick file co them thu vien chon file nua import com.ipaulpro.afilechooser.utils.FileUtils;
    //public static void pickFile(Activity activity, int requestCode){
        //		Intent intent = new Intent();
        //		intent.setType("*/*");
        //		intent.setAction(Intent.ACTION_GET_CONTENT);
        //		activity.startActivityForResult(Intent.createChooser(intent,
        //				"Complete action using"), requestCode);
      //  Intent getContentIntent = FileUtils.createGetContentIntent();
        //Intent intent = Intent.createChooser(getContentIntent, "Complete action using");
        //activity.startActivityForResult(intent, requestCode);
    //}


    public static void pickImage(Activity activity, int requestCode){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activity.startActivityForResult(Intent.createChooser(intent,
                "Complete action using"), requestCode);
    }

    public static void pickImage(Fragment fragment, int requestCode){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        fragment.startActivityForResult(Intent.createChooser(intent,
                "Complete action using"), requestCode);
    }

    public static File cropImage(Activity activity, Uri uri, int requestCode){
        File tempFile = new File(Environment
                .getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg");
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("outputX", 800);
        intent.putExtra("outputY", 800);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        activity.startActivityForResult(intent, requestCode);
        return tempFile;
    }

    public static File cropImage(Fragment fragment, Uri uri, int requestCode){
        File tempFile = new File(Environment
                .getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg");
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("outputX", 800);
        intent.putExtra("outputY", 800);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        fragment.startActivityForResult(intent, requestCode);
        return tempFile;
    }

    public static void goToMail(Activity activity, String email){
        try{
            Intent intent = activity.getPackageManager().getLaunchIntentForPackage("com.google.android.gm");
            intent.setData(Uri.parse(email));
            activity.startActivity(intent);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public static Intent getIntentCall(String number){
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+number));
        return intent;
    }

    public static Intent getIntentSendSMS(String message){
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
        intent.setType("vnd.android-dir/mms-sms");
        //		intent.putExtra("address","your desired phoneNumber");
        intent.putExtra("sms_body",message);
        return intent;
    }

    public static Intent getIntentSendEmail(String message){
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setType("plain/text");
        intent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] {});
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, message);
        return intent;
    }

    public static Intent getIntentSettingGPS(){
        return new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
    }

    public static Intent getIntentShare(String text){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        intent.setType("text/plain");
        return intent;
    }

    //Pick file co them thu vien chon file nua import com.ipaulpro.afilechooser.utils.FileUtils;
    //public static Intent getIntentOpenFile(String filePath){
      //  File file = new File(filePath);
        //Intent intent = new Intent(Intent.ACTION_VIEW);
        //intent.setDataAndType(Uri.fromFile(file),
          //      FileUtils.getMimeType(file));
        //return Intent.createChooser(intent, "Choose an application to open with:");
    //}

    public static void removeUnderlines(TextView textView) {
        try{
            removeUnderlines((Spannable)textView.getText());
        }catch(Exception e){
        }
    }

    public static void removeUnderlines(EditText editText) {
        try{
            removeUnderlines((Spannable)editText.getText());
        }catch(Exception e){
        }
    }

    public static void removeUnderlines(Spannable spanText) {
        URLSpan[] spans = spanText.getSpans(0, spanText.length(), URLSpan.class);
        for(URLSpan span : spans) {
            int start = spanText.getSpanStart(span);
            int end = spanText.getSpanEnd(span);
            spanText.removeSpan(span);
            span = new URLSpanNoUnderline(span.getURL());
            spanText.setSpan(span, start, end, 0);
        }
    }

    private static class URLSpanNoUnderline extends URLSpan {
        public URLSpanNoUnderline(String url) {
            super(url);
        }

        public void updateDrawState(TextPaint textPaint) {
            super.updateDrawState(textPaint);
            textPaint.setUnderlineText(false);
        }
    }

    public static Uri getImageUri(Context context, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String title = AppUtil.TAG + "_" + System.currentTimeMillis();
        String path = Images.Media.insertImage(context.getContentResolver(), inImage, title, null);
        return Uri.parse(path);
    }

    public static String getRealPath(Context context, Uri uri) {
        Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    public static String getRealPath(Context context, Bitmap inImage) {
        return getRealPath(context, getImageUri(context, inImage));
    }

    public static String[] insertArray(String[] array, int position, String value){
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(array));
        list.add(position, value);
        return list.toArray(new String[list.size()]);
    }

    public static int indexOf(String[] array, String value){
        return Arrays.asList(array).indexOf(value);
    }

    public static void releaseByteArray(byte[] bytes){
        bytes = null;
        System.gc();
    }

    public static void releaseBitmap(Bitmap bm){
        if(bm != null && !bm.isRecycled()){
            bm.recycle();
        }
        bm = null;
        System.gc();
    }

    public static String getFullName(String path){
        int index = path.lastIndexOf("/");
        if(index < path.length()){
            return path.substring(index+1, path.length());
        }
        return path;
    }

    public static String getShortName(String path){
        String fullName = getFullName(path);
        int index = fullName.lastIndexOf(".");
        if(index > 0){
            return fullName.substring(0, index);
        }
        return fullName;
    }



    public static String downloadAndSavePhoto(Context context, String url){
        File file = getOrCreateFile(getFullName(url));
        InputStream input = null;
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setDoInput(true);
            connection.connect();
            input = connection.getInputStream();
            Bitmap bm = BitmapFactory.decodeStream(input);

            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            bm.compress(url.endsWith("png") ? Bitmap.CompressFormat.PNG : Bitmap.CompressFormat.JPEG, 100, bytes);

            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bytes.toByteArray());
            fos.close();
            if(file.exists() && file.canRead()){
                return file.getAbsolutePath();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(input != null){
                try {
                    input.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static File getOrCreateFile(String filename){
        String folder = getFileDir();
        File dir = new File(folder);
        if(!dir.exists()){
            dir.mkdirs();
        }
        return new File(dir, filename);
    }

    public static String downloadAndSavePhoto(Context context, String url, String filename){
        File file = getOrCreateFile(filename);
        InputStream input = null;
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setDoInput(true);
            connection.connect();
            input = connection.getInputStream();
            Bitmap bm = BitmapFactory.decodeStream(input);

            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            bm.compress(url.endsWith("png") ? Bitmap.CompressFormat.PNG : Bitmap.CompressFormat.JPEG, 100, bytes);

            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bytes.toByteArray());
            fos.close();
            if(file.exists() && file.canRead()){
                return file.getAbsolutePath();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(input != null){
                try {
                    input.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static String saveInputStream(InputStream input, String filename){
        String folder = Environment.getExternalStorageDirectory() + "/" + AppUtil.TAG;
        File dir = new File(folder);
        if(!dir.exists()){
            dir.mkdirs();
        }
        File file = new File(dir, filename);
        try {
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while((len = input.read(buf))>0){
                out.write(buf,0,len);
            }
            out.close();
            if(file.exists() && file.canRead()){
                return file.getAbsolutePath();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(input != null){
                try {
                    input.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
/*
            Dung de pick anh
    		imageAvatar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(activity);
				builder.setTitle(R.string.upload_avatar)
				.setNegativeButton(R.string.capture, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						mImageCaptureUri = AppUtil.captureImage(MenuFragment.this, CODE_CAPTURE_IMAGE);
					}
				})
				.setPositiveButton(R.string.gallery, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						AppUtil.pickImage(MenuFragment.this, CODE_PICK_IMAGE);
					}
				});
				AlertDialog alert = builder.create();
				alert.setCanceledOnTouchOutside(true);
				alert.show();
			}
		});


		@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if(resultCode == Activity.RESULT_OK){
			switch (requestCode) {
			case CODE_CAPTURE_IMAGE:
				tempFile = AppUtil.cropImage(this, mImageCaptureUri, CODE_CROP_IMAGE);
				break;
			case CODE_PICK_IMAGE:
				mImageCaptureUri = intent.getData();
				tempFile = AppUtil.cropImage(this, mImageCaptureUri, CODE_CROP_IMAGE);
				break;
			case CODE_CROP_IMAGE:
				if(tempFile != null && tempFile.exists()){
					showAvatar(tempFile.getAbsolutePath());
				}
				break;
			default:
				break;
			}
		}
		super.onActivityResult(requestCode, resultCode, intent);
	}


	show image

	private void showAvatar(String path){
		AppUtil.getPicassoFileOrUrl(activity, path)
		.resize(MyApplication.screenWidth / 2, MyApplication.screenWidth / 2)
		.centerInside()
		.placeholder(R.drawable.right_menu_image_holder)
		.into(imageAvatar);
	}

	//logout ta phai nhu the nay
	Intent intent = new Intent(activity, StartActivity.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent);
					activity.finish();
 */
