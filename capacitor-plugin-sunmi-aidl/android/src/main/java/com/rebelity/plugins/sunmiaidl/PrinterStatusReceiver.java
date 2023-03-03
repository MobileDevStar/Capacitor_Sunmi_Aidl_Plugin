package com.rebelity.plugins.sunmiaidl;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.Context;

import android.util.Log;

import com.getcapacitor.JSObject;
import com.getcapacitor.PluginCall;


public class PrinterStatusReceiver extends BroadcastReceiver {
  private static final String TAG = "SunmiDeviceReceiver";

  private PluginCall    callbackReceive;
  private boolean isReceiving = true;

  public PrinterStatusReceiver() {

  }

  @Override
  public void onReceive(Context context, Intent data) {
    if (this.isReceiving && this.callbackReceive != null) {
      String action = data.getAction();
      String type = "DeviceStatus";

      JSObject ret = new JSObject();
      try {
        ret.put("type", type);
        ret.put("action", action);

        Log.i(TAG, "RECEIVED STATUS " + action);

        callbackReceive.resolve(ret);
      } catch (Exception e) {
        Log.i(TAG, "ERROR: " + e.getMessage());
      }
    }
  }

  public void startReceiving(PluginCall call) {
    this.callbackReceive = call;
    this.isReceiving = true;

    Log.i(TAG, "Start receiving status");
  }

  public void stopReceiving() {
    this.callbackReceive = null;
    this.isReceiving = false;

    Log.i(TAG, "Stop receiving status");
  }
}
