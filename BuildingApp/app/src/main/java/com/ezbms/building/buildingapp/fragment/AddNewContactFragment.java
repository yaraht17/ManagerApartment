package com.ezbms.building.buildingapp.fragment;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ezbms.building.buildingapp.R;
import com.ezbms.building.buildingapp.util.AppConstants;
import com.ezbms.building.buildingapp.util.AppUtil;
import com.squareup.picasso.Picasso;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Hoang on 3/20/2016.
 */
public class AddNewContactFragment extends MyFragment implements AppConstants {
    ImageView btnItemLeft;//header
    TextView txt_title;
    ImageButton btnItemRight;

    CircleImageView imageView;

    private Uri mImageCaptureUri;
    private File tempFile;

    private static int RESULT_LOAD_IMAGE = 1;

    @Override
    public void refreshData(Intent intent) {

    }

    @Override
    public String getClassName() {
        return getClass().getName();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return createView(inflater, container, R.layout.add_contact_online);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //header
        txt_title = (TextView) findViewById(R.id.txt_title);
        txt_title.setText("THÊM LIÊN LẠC MỚI");
        btnItemLeft = (ImageView) findViewById(R.id.btnItemLeft);
        btnItemLeft.setImageResource(R.mipmap.back);
        btnItemLeft.setVisibility(View.VISIBLE);
        btnItemLeft.setOnClickListener(this);

        btnItemRight = (ImageButton) findViewById(R.id.btnItemRight);
        btnItemRight.setImageResource(R.mipmap.xac_nhan);
        btnItemRight.setVisibility(View.VISIBLE);
        btnItemRight.setBackgroundColor(getResources().getColor(R.color.trans));
        btnItemRight.setOnClickListener(this);

        imageView = (CircleImageView) findViewById(R.id.imageView);
        imageView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnItemLeft){
            getActivity().getSupportFragmentManager().popBackStack();
        }else if(v.getId() == R.id.btnItemRight){

        }else if(v.getId() == R.id.imageView){
            Intent i = new Intent(
                    Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

            startActivityForResult(i, RESULT_LOAD_IMAGE);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == Activity.RESULT_OK
                && null != intent) {
            Uri selectedImage = intent.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        }
    }

}
