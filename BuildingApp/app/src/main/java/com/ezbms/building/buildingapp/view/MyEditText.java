package com.ezbms.building.buildingapp.view;

/**
 * Created by Hoang on 2/27/2016.
 */
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;

public class MyEditText extends EditText {

    public interface EditTextImeBackListener {
        public abstract void onImeBack(MyEditText editText, String text);

        public abstract void onImeSearch(MyEditText editText, String text);
    }

    private EditTextImeBackListener mOnImeBack;

    public MyEditText(Context context) {
        super(context);
        init();
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void init() {
        Typeface myTypeface =
                Typeface.createFromAsset(getContext().getAssets(),
                        "fonts/MYRIADPRO-REGULAR.OTF");
        setTypeface(myTypeface);
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (mOnImeBack != null) {
            if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
                    && event.getAction() == KeyEvent.ACTION_UP) {
                mOnImeBack.onImeBack(this, this.getText().toString());
            }
        }
        return super.dispatchKeyEvent(event);
    }

    public void setOnEditTextImeBackListener(EditTextImeBackListener listener) {
        mOnImeBack = listener;
    }

}

