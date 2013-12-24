package jp.co.c_lis.glassradiosdisabler.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

import com.google.android.glass.app.Card;
import com.google.android.glass.touchpad.Gesture;
import com.google.android.glass.touchpad.GestureDetector;

import jp.co.c_lis.glassradiosdisabler.R;

public class PatternActivity extends Activity implements GestureDetector.BaseListener {

    public static final String KEY_PATTERN = "key_pattern";

    private GestureDetector mGestureDetector = null;

    private Card mCard = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGestureDetector = new GestureDetector(this);
        mGestureDetector.setBaseListener(this);

        mCard = new Card(this);
        mCard.setFootnote(R.string.draw_your_pattern);
        setContentView(mCard.toView());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        return mGestureDetector.onMotionEvent(event);
    }

    private StringBuffer mBuffer = new StringBuffer();

    @Override
    public boolean onGesture(Gesture gesture) {
        if (gesture == Gesture.TAP) {
            mBuffer.append("○");
        } else if (gesture == Gesture.SWIPE_LEFT) {
            mBuffer.append("←");
        } else if (gesture == Gesture.SWIPE_RIGHT) {
            mBuffer.append("→");
        } else if (gesture == Gesture.LONG_PRESS) {
            Intent intent = new Intent();
            intent.putExtra(KEY_PATTERN, mBuffer.toString());
            setResult(RESULT_OK, intent);
            finish();
        }
        mCard = new Card(this);
        mCard.setText(mBuffer.toString());
        mCard.setFootnote(R.string.draw_your_pattern);
        setContentView(mCard.toView());

        return false;
    }
}
