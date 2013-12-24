package jp.co.c_lis.glassradiosdisabler.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.google.android.glass.app.Card;

public class MessageActivity extends Activity {

    public static final String BUNDLE_KEY_DELAY_IN_MILISEC = "key_delay_in_msec";
    public static final String BUNDLE_KEY_MESSAGE = "key_message";

    private final Handler mHandler = new Handler();

    private final Runnable mFinishRunnable = new Runnable() {
        @Override
        public void run() {
            if (!isFinishing()) {
                finish();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        long delay = getIntent().getLongExtra(BUNDLE_KEY_DELAY_IN_MILISEC, 1 * 1000);
        mHandler.postDelayed(mFinishRunnable, delay);

        String message = getIntent().getStringExtra(BUNDLE_KEY_MESSAGE);
        if (message == null) {
            finish();
            return;
        }

        Card card = new Card(this);
        card.setText(message);
        setContentView(card.toView());
    }
}