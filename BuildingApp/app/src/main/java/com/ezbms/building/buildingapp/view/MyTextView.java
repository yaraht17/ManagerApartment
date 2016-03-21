package com.ezbms.building.buildingapp.view;

/**
 * Created by Hoang on 2/27/2016.
 */
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class MyTextView extends TextView {

    public MyTextView(Context context) {
        super(context);
        init();
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyle) {
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
