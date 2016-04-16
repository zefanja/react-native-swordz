package org.crosswire.android.sword;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import android.util.Log;
import com.google.gson.Gson;

public class SwordZModule extends ReactContextBaseJavaModule {
    private static final String     REACT_CLASS = "SwordZ";
    private static final String TAG = "SwordZModule";
    private SWMgr            mSWMgr = null;
    private InstallMgr       mInstallMgr = null;

    private ReactApplicationContext mReactContext;

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    public SwordZModule(ReactApplicationContext reactContext) {
        super(reactContext);
        mReactContext = reactContext;
    }

    // public native void             setUserDisclaimerConfirmed();
    // public native int              syncConfig();
    // public native int              uninstallModule(String modName);
    // public native String []        getRemoteSources();
    // public native int              refreshRemoteSource(String sourceName);
    // public native SWMgr.ModInfo [] getRemoteModInfoList(String sourceName);
    // public native int              remoteInstallModule(String sourceName, String modName, InstallProgressReporter progressReporter);
    // public int                     remoteInstallModule(String sourceName, String modName) { return remoteInstallModule(sourceName, modName, null); }
    // public native SWModule         getRemoteModuleByName(String source, String name);

    /* InstallMgr */
    @ReactMethod
    public void InstallMgr_reInit() {
        if (mInstallMgr == null)
            mInstallMgr = new InstallMgr();
        mInstallMgr.reInit();
    }

    @ReactMethod
    public void InstallMgr_setUserDisclaimerConfirmed() {
        if (mInstallMgr == null)
            mInstallMgr = new InstallMgr();
        mInstallMgr.setUserDisclaimerConfirmed();
    }

    @ReactMethod
    public void InstallMgr_syncConfig(Callback callback) {
        if (mInstallMgr == null)
            mInstallMgr = new InstallMgr();
        callback.invoke(mInstallMgr.syncConfig());
    }

    @ReactMethod
    public void InstallMgr_uninstallModule(String modName, Callback callback) {
        if (mInstallMgr == null)
            mInstallMgr = new InstallMgr();
        callback.invoke(mInstallMgr.uninstallModule(modName));
    }

    @ReactMethod
    public void InstallMgr_getRemoteSources(Callback callback) {
        if (mInstallMgr == null)
            mInstallMgr = new InstallMgr();
        String remoteSources = new Gson().toJson(mInstallMgr.getRemoteSources());
        callback.invoke(remoteSources);
    }

    @ReactMethod
    public void InstallMgr_refreshRemoteSource(String sourceName, Callback callback) {
        if (mInstallMgr == null)
            mInstallMgr = new InstallMgr();
        callback.invoke(mInstallMgr.refreshRemoteSource(sourceName));
    }

    @ReactMethod
    public void InstallMgr_getRemoteModInfoList(String sourceName, Callback callback) {
        if (mInstallMgr == null)
            mInstallMgr = new InstallMgr();
        String remoteModules = new Gson().toJson(mInstallMgr.getRemoteModInfoList(sourceName));
        callback.invoke(remoteModules);
    }

    @ReactMethod
    public void InstallMgr_remoteInstallModule(String sourceName, String modName, Callback callback) {
        if (mInstallMgr == null)
            mInstallMgr = new InstallMgr();
        callback.invoke(mInstallMgr.remoteInstallModule(sourceName, modName, null));
    }

    @ReactMethod
    public void InstallMgr_getRemoteModuleByName(String source, String name, Callback callback) {
        if (mInstallMgr == null)
            mInstallMgr = new InstallMgr();
        callback.invoke(mInstallMgr.getRemoteModuleByName(source, name));
    }

    /* SWMgr */
    @ReactMethod
    public void SWMgr_version(Callback callback) {
        if (mSWMgr == null)
            mSWMgr = new SWMgr();
        callback.invoke(mSWMgr.version());
    }

    @ReactMethod
    public void SWMgr_reInit() {
        if (mSWMgr == null)
            mSWMgr = new SWMgr();
        mSWMgr.reInit();
    }

    @ReactMethod
    public void SWMgr_getModInfoList(Callback callback) {
        if (mSWMgr == null)
            mSWMgr = new SWMgr();
        String localModules = new Gson().toJson(mSWMgr.getModInfoList());
        callback.invoke(localModules);
    }
}
