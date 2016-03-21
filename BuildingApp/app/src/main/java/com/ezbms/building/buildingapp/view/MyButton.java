package com.ezbms.building.buildingapp.view;

/**
 * Created by Hoang on 2/27/2016.
 */
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

public class MyButton extends Button {

    public MyButton(Context context) {
        super(context);
        init();
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void init() {

        Typeface myTypeface =
                Typeface.createFromAsset(getContext().getAssets(),
                        "fonts/MYRIADPRO-REGULAR.OTF");
        setTypeface(myTypeface);
    }

}

