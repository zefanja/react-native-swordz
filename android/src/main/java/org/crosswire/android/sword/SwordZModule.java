package org.crosswire.android.sword;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import android.util.Log;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringEscapeUtils;

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

    @ReactMethod
    public void SWMgr_setGlobalOption(String option, String value) {
        if (mSWMgr == null)
            mSWMgr = new SWMgr();
        mSWMgr.setGlobalOption(option, value);
    }

    @ReactMethod
    public void SWMgr_getGlobalOption(String option, Callback callback) {
        if (mSWMgr == null)
            mSWMgr = new SWMgr();
        callback.invoke(mSWMgr.getGlobalOption(option));
    }

    @ReactMethod
    public void SWMgr_getGlobalOptions(Callback callback) {
        if (mSWMgr == null)
            mSWMgr = new SWMgr();
        String globalOptions = new Gson().toJson(mSWMgr.getGlobalOptions());
        callback.invoke(globalOptions);
    }

    @ReactMethod
    public void SWMgr_getGlobalOptionValues(String option, Callback callback) {
        if (mSWMgr == null)
            mSWMgr = new SWMgr();
        String globalOptionValues = new Gson().toJson(mSWMgr.getGlobalOptionValues(option));
        callback.invoke(globalOptionValues);
    }

    @ReactMethod
    public void SWMgr_getAvailableLocales(Callback callback) {
        if (mSWMgr == null)
            mSWMgr = new SWMgr();
        String availableLocales = new Gson().toJson(mSWMgr.getAvailableLocales());
        callback.invoke(availableLocales);
    }

    @ReactMethod
    public void SWMgr_setDefaultLocale(String name) {
        if (mSWMgr == null)
            mSWMgr = new SWMgr();
        mSWMgr.setDefaultLocale(name);
    }

    /* SWMModule */
    @ReactMethod
    public void SWModule_getKeyChildren(String modName, Callback callback) {
        if (mSWMgr == null)
            mSWMgr = new SWMgr();
        String keyChildren = new Gson().toJson(mSWMgr.getModuleByName(modName).getKeyChildren());
        callback.invoke(keyChildren);
    }

    @ReactMethod
    public void SWModule_getRenderText(String modName, String passage, Callback callback) {
        if (mSWMgr == null)
            mSWMgr = new SWMgr();
        SWModule module = mSWMgr.getModuleByName(modName);
        String[] verseList = module.parseKeyList(passage);
        String renderText = "";
        for (int i = 0; i < verseList.length; i++) {
            module.setKeyText(verseList[i]);

            if (i == 0) renderText += "[";
            renderText += "{\"verseKey\": \"" + verseList[i] + "\", \"text\": \"" + StringEscapeUtils.escapeJava(module.getRenderText()) + "\"}";
            if (i < verseList.length-1) renderText += ", ";
        }
        renderText += "]";

        callback.invoke(renderText);
    }
}
