
package jp.co.c_lis.glassradiosdisabler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean airplaneMode;
        try {
            airplaneMode = Settings.System.getInt(getContentResolver(),
                    Settings.System.AIRPLANE_MODE_ON) == 1;
            Log.d(TAG, "airplaneMode = " + airplaneMode);
            if (airplaneMode) {
                Toast.makeText(this, "All radios are turned OFF now.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "All radios are turned ON now.", Toast.LENGTH_LONG).show();
            }
            Settings.System.putInt(getContentResolver(), Settings.System.AIRPLANE_MODE_ON,
                    airplaneMode ? 0 : 1);

            Intent intent = new Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED);
            intent.putExtra("state", !airplaneMode);
            sendBroadcast(intent);
        } catch (SettingNotFoundException e) {
            Log.e(TAG, "SettingNotFoundException", e);
        }

        finish();
    }

}
