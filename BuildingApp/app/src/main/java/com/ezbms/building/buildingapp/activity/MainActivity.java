package com.ezbms.building.buildingapp.activity;

import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ezbms.building.buildingapp.R;
import com.ezbms.building.buildingapp.fragment.AccountFragment;
import com.ezbms.building.buildingapp.fragment.ContactFragment;
import com.ezbms.building.buildingapp.fragment.HomeFragment;
import com.ezbms.building.buildingapp.fragment.PayFragment;
import com.ezbms.building.buildingapp.fragment.ServiceFragment;
import com.ezbms.building.buildingapp.util.SoftKeyboard;

public class MainActivity extends MySlidingMenuActivity {

    ImageButton btnService,btnContact,btnHome,btnPay,btnAccount;
    TextView txtCountService,txtCountContact,txtCountHome,txtCountPay,txtCountAccount;

    SoftKeyboard softKeyboard;//dung an hien thanh tabbar
    LinearLayout layout_tab_controller;
    RelativeLayout layoutCountService,layoutCountContact,layoutCountHome,layoutCountPay,layoutCountAccount;
    @Override
    public void refreshData(Intent intent) {

    }

    @Override
    public String getClassName() {
        return getClass().getName();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        keybroadController();
        layout_tab_controller = (LinearLayout) findViewById(R.id.layout_tab_controller);

        btnService = (ImageButton) findViewById(R.id.btnService);
        btnContact = (ImageButton) findViewById(R.id.btnContact);
        btnHome = (ImageButton) findViewById(R.id.btnHome);
        btnPay = (ImageButton) findViewById(R.id.btnPay);
        btnAccount = (ImageButton) findViewById(R.id.btnAccount);

        txtCountService = (TextView) findViewById(R.id.txt_count_service);
        txtCountContact = (TextView) findViewById(R.id.txt_count_contact);
        txtCountHome = (TextView) findViewById(R.id.txt_count_home);
        txtCountPay = (TextView) findViewById(R.id.txt_count_pay);
        txtCountAccount = (TextView) findViewById(R.id.txt_count_account);

        txtCountService.setVisibility(View.GONE);
        txtCountAccount.setVisibility(View.GONE);

        layoutCountService = (RelativeLayout) findViewById(R.id.layout_dichvu);
        layoutCountContact = (RelativeLayout) findViewById(R.id.layout_lienhe);
        layoutCountHome = (RelativeLayout) findViewById(R.id.layout_home);
        layoutCountPay = (RelativeLayout) findViewById(R.id.layout_thanhtoan);
        layoutCountAccount = (RelativeLayout) findViewById(R.id.layout_taikhoan);

        layoutCountAccount.setOnClickListener(this);
        layoutCountPay.setOnClickListener(this);
        layoutCountHome.setOnClickListener(this);
        layoutCountService.setOnClickListener(this);
        layoutCountContact.setOnClickListener(this);

        layoutCountService.performClick();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layout_dichvu:
                actionSevice();
                break;
            case R.id.layout_lienhe:
                actionContact();
                break;
            case R.id.layout_home:
                actionHome();
                break;
            case R.id.layout_thanhtoan:
                actionPay();
                break;
            case R.id.layout_taikhoan:
                actionAccount();
                break;
        }
    }

    private void actionSevice() {
        backAllStackFragment();
        replaceFragment(new ServiceFragment(), R.id.frameMain, false);
        btnService.setImageResource(R.mipmap.dich_vu_click);
        btnContact.setImageResource(R.mipmap.lien_he);
        btnHome.setImageResource(R.mipmap.home);
        btnPay.setImageResource(R.mipmap.thanh_toan);
        btnAccount.setImageResource(R.mipmap.account);

        layoutCountService.setBackgroundColor(getResources().getColor(R.color.click_navbar));
        layoutCountContact.setBackgroundColor(getResources().getColor(R.color.tablayout));
        layoutCountHome.setBackgroundColor(getResources().getColor(R.color.tablayout));
        layoutCountPay.setBackgroundColor(getResources().getColor(R.color.tablayout));
        layoutCountAccount.setBackgroundColor(getResources().getColor(R.color.tablayout));
    }

    private void actionContact() {
        backAllStackFragment();
        replaceFragment(new ContactFragment(), R.id.frameMain, false);
        btnService.setImageResource(R.mipmap.dich_vu);
        btnContact.setImageResource(R.mipmap.lien_he_click);
        btnHome.setImageResource(R.mipmap.home);
        btnPay.setImageResource(R.mipmap.thanh_toan);
        btnAccount.setImageResource(R.mipmap.account);

        layoutCountService.setBackgroundColor(getResources().getColor(R.color.tablayout));
        layoutCountContact.setBackgroundColor(getResources().getColor(R.color.click_navbar));
        layoutCountHome.setBackgroundColor(getResources().getColor(R.color.tablayout));
        layoutCountPay.setBackgroundColor(getResources().getColor(R.color.tablayout));
        layoutCountAccount.setBackgroundColor(getResources().getColor(R.color.tablayout));

    }

    private void actionHome() {
        backAllStackFragment();
        replaceFragment(new HomeFragment(), R.id.frameMain, false);

        btnService.setImageResource(R.mipmap.dich_vu);
        btnContact.setImageResource(R.mipmap.lien_he);
        btnHome.setImageResource(R.mipmap.home_click);
        btnPay.setImageResource(R.mipmap.thanh_toan);
        btnAccount.setImageResource(R.mipmap.account);

        layoutCountService.setBackgroundColor(getResources().getColor(R.color.tablayout));
        layoutCountContact.setBackgroundColor(getResources().getColor(R.color.tablayout));
        layoutCountHome.setBackgroundColor(getResources().getColor(R.color.click_navbar));
        layoutCountPay.setBackgroundColor(getResources().getColor(R.color.tablayout));
        layoutCountAccount.setBackgroundColor(getResources().getColor(R.color.tablayout));
    }

    private void actionPay() {
        backAllStackFragment();
        replaceFragment(new PayFragment(), R.id.frameMain, false);

        btnService.setImageResource(R.mipmap.dich_vu);
        btnContact.setImageResource(R.mipmap.lien_he);
        btnHome.setImageResource(R.mipmap.home);
        btnPay.setImageResource(R.mipmap.thanh_toan_click);
        btnAccount.setImageResource(R.mipmap.account);

        layoutCountService.setBackgroundColor(getResources().getColor(R.color.tablayout));
        layoutCountContact.setBackgroundColor(getResources().getColor(R.color.tablayout));
        layoutCountHome.setBackgroundColor(getResources().getColor(R.color.tablayout));
        layoutCountPay.setBackgroundColor(getResources().getColor(R.color.click_navbar));
        layoutCountAccount.setBackgroundColor(getResources().getColor(R.color.tablayout));
    }

    private void actionAccount() {
        backAllStackFragment();
        replaceFragment(new AccountFragment(), R.id.frameMain, false);

        btnService.setImageResource(R.mipmap.dich_vu);
        btnContact.setImageResource(R.mipmap.lien_he);
        btnHome.setImageResource(R.mipmap.home);
        btnPay.setImageResource(R.mipmap.thanh_toan);
        btnAccount.setImageResource(R.mipmap.account_click);

        layoutCountService.setBackgroundColor(getResources().getColor(R.color.tablayout));
        layoutCountContact.setBackgroundColor(getResources().getColor(R.color.tablayout));
        layoutCountHome.setBackgroundColor(getResources().getColor(R.color.tablayout));
        layoutCountPay.setBackgroundColor(getResources().getColor(R.color.tablayout));
        layoutCountAccount.setBackgroundColor(getResources().getColor(R.color.click_navbar));
    }


    //an thanh tab o duoi
    public void hideTabController(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                layout_tab_controller = (LinearLayout) findViewById(R.id.layout_tab_controller);
                layout_tab_controller.setVisibility(View.GONE);
            }
        });

    }

    //hien thanh tab o duoi
    public void showTabController(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                layout_tab_controller = (LinearLayout) findViewById(R.id.layout_tab_controller);
                layout_tab_controller.setVisibility(View.VISIBLE);
            }
        });

    }

    public void hideKeybroad(){
        softKeyboard.closeSoftKeyboard();
    }

    public void keybroadController(){
        //an hien keybroad
        InputMethodManager im = (InputMethodManager) getSystemService(Service.INPUT_METHOD_SERVICE);

        /*
        Instantiate and pass a callback
        */
        FrameLayout rootlayout = (FrameLayout) findViewById(R.id.activityRoot);
        softKeyboard = new SoftKeyboard(rootlayout, im);
        softKeyboard.setSoftKeyboardCallback(new SoftKeyboard.SoftKeyboardChanged() {

            @Override
            public void onSoftKeyboardHide() {
                // Code here
                showTabController();
                System.out.println("Hide keyboard");
            }

            @Override
            public void onSoftKeyboardShow() {
                // Code here
                hideTabController();
                System.out.println("Show keyboard");
            }
        });
    }

    //popbackStack tat ca truoc khi nhan click tren tab dieu huong
    public void backAllStackFragment(){
        for(int i = 0; i < fragmentManager.getBackStackEntryCount(); ++i) {
            fragmentManager.popBackStack();
        }
    }
    //co ham nay thi khi an backbutton no se thoat het fragment => onBackPressd
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (fragmentManager.getBackStackEntryCount() != 0) {
                fragmentManager.popBackStack();
                return true;
            }
            // If there are no fragments on stack perform the original back
            // button event
        }
        return super.onKeyDown(keyCode, event);
    }

    public void close_app() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn có muốn đóng ứng dụng không ?").setTitle("Thông Báo")
                .setCancelable(false)
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    //back button
    @Override
    public void onBackPressed() {
        close_app();
    }

}
