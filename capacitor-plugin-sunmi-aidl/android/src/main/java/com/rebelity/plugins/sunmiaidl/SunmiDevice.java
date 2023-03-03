package com.rebelity.plugins.sunmiaidl;

import org.json.JSONArray;
import org.json.JSONException;

import woyou.aidlservice.jiuiv5.ICallback;
import woyou.aidlservice.jiuiv5.IWoyouService;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ComponentName;
import android.content.ServiceConnection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.os.IBinder;
import android.os.RemoteException;

import android.util.Base64;
import android.util.Log;

import com.rebelity.plugins.utils.BitmapUtils;
import com.rebelity.plugins.utils.ThreadPoolManager;

import com.getcapacitor.JSObject;
import com.getcapacitor.NativePlugin;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;

public class SunmiDevice extends Plugin {
  private static final String TAG = "SunmiAidlDevice";

  private BitmapUtils bitMapUtils;
  private IWoyouService woyouService;
  private PrinterStatusReceiver printerStatusReceiver = new PrinterStatusReceiver();

  private ServiceConnection connService = new ServiceConnection() {
    @Override
    public void onServiceDisconnected(ComponentName name) {
      woyouService = null;
      Log.d(TAG, "Service disconnected");
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
      woyouService = IWoyouService.Stub.asInterface(service);
      Log.d(TAG, "Service connected");
    }
  };

  public final static String OUT_OF_PAPER_ACTION = "woyou.aidlservice.jiuv5.OUT_OF_PAPER_ACTION";
  public final static String ERROR_ACTION = "woyou.aidlservice.jiuv5.ERROR_ACTION";
  public final static String NORMAL_ACTION = "woyou.aidlservice.jiuv5.NORMAL_ACTION";
  public final static String COVER_OPEN_ACTION = "woyou.aidlservice.jiuv5.COVER_OPEN_ACTION";
  public final static String COVER_ERROR_ACTION = "woyou.aidlservice.jiuv5.COVER_ERROR_ACTION";
  public final static String KNIFE_ERROR_1_ACTION = "woyou.aidlservice.jiuv5.KNIFE_ERROR_ACTION_1";
  public final static String KNIFE_ERROR_2_ACTION = "woyou.aidlservice.jiuv5.KNIFE_ERROR_ACTION_2";
  public final static String OVER_HEATING_ACITON = "woyou.aidlservice.jiuv5.OVER_HEATING_ACITON";
  public final static String FIRMWARE_UPDATING_ACITON = "woyou.aidlservice.jiuv5.FIRMWARE_UPDATING_ACITON";

  @Override
  public void load() {

    Context applicationContext = getActivity().getApplicationContext();

    bitMapUtils = new BitmapUtils(applicationContext);

    Intent intent = new Intent();
    intent.setPackage("woyou.aidlservice.jiuiv5");
    intent.setAction("woyou.aidlservice.jiuiv5.IWoyouService");

    applicationContext.startService(intent);
    applicationContext.bindService(intent, connService, Context.BIND_AUTO_CREATE);

    IntentFilter mFilter = new IntentFilter();
    mFilter.addAction(OUT_OF_PAPER_ACTION);
    mFilter.addAction(ERROR_ACTION);
    mFilter.addAction(NORMAL_ACTION);
    mFilter.addAction(COVER_OPEN_ACTION);
    mFilter.addAction(COVER_ERROR_ACTION);
    mFilter.addAction(KNIFE_ERROR_1_ACTION);
    mFilter.addAction(KNIFE_ERROR_2_ACTION);
    mFilter.addAction(OVER_HEATING_ACITON);
    mFilter.addAction(FIRMWARE_UPDATING_ACITON);

    applicationContext.registerReceiver(printerStatusReceiver, mFilter);
  }

  @PluginMethod
  public void echo(PluginCall call) {
    String value = call.getString("value");

    JSObject ret = new JSObject();
    ret.put("value", value);
    call.resolve(ret);
  }

  private void openCashDrawer(PluginCall call) {
      // PrintLine
      if(woyouService == null){
          call.reject("Cash Drawer Error: Service not ready");
          return;
      }

      try {
          woyouService.openDrawer(null);
          JSObject ret = new JSObject();
          ret.put("status", "OK");
          call.resolve(ret);
      } catch (RemoteException e) {
          call.reject("Cash Drawer Error: " + e.toString());
      }
  }
}
