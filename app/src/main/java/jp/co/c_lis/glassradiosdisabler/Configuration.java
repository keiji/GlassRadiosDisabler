package jp.co.c_lis.glassradiosdisabler;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.util.Log;

public class Configuration {
    private static final String TAG = "Configuration";

    public static boolean getAirplaneModeState(Context context) {
        boolean airplaneMode = false;
        try {
            airplaneMode = Settings.System.getInt(context.getContentResolver(),
                    Settings.System.AIRPLANE_MODE_ON) == 1;
        } catch (Settings.SettingNotFoundException e) {
            Log.e(TAG, "SettingNotFoundException", e);
        }
        return airplaneMode;
    }

    public static void setAirplaneMode(Context context, boolean enable) {
        Settings.System.putInt(context.getContentResolver(), Settings.System.AIRPLANE_MODE_ON,
                enable ? 1 : 0);

        Intent intent = new Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        intent.putExtra("state", enable);
        context.sendBroadcast(intent);
    }

    public static boolean getWifiState(Context context) {
        WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        return (wm.getWifiState() == WifiManager.WIFI_STATE_ENABLED
                || wm.getWifiState() == WifiManager.WIFI_STATE_ENABLING);
    }

    public static void setWifiState(Context context, boolean enable) {
        WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        wm.setWifiEnabled(enable);
    }

    public static boolean getBluetoothState(Context context) {
        BluetoothAdapter ba = BluetoothAdapter.getDefaultAdapter();
        return (ba.getState() == BluetoothAdapter.STATE_ON);
    }

    public static void setBluetoothState(Context context, boolean enable) {
        BluetoothAdapter ba = BluetoothAdapter.getDefaultAdapter();
        if (enable) {
            ba.enable();
        } else {
            ba.disable();
        }
    }

}
