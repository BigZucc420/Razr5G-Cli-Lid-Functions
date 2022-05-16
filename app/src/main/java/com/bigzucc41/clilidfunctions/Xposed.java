package com.bigzucc41.clilidfunctions;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class Xposed implements IXposedHookLoadPackage {
    private static final String logName = "Cli Display Service";
    private static final int GO_TO_SLEEP_REASON_LID_SWITCH = 3;

    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        log("Package loaded (" + lpparam.packageName + ")");
        try {
            XposedHelpers.findAndHookMethod("com.android.server.power.PowerManagerService", lpparam.classLoader, "sleepDisplayGroupNoUpdateLocked", int.class, long.class, int.class, int.class, int.class, new XC_MethodHook() {
                public void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    if ((int) param.args[2] == GO_TO_SLEEP_REASON_LID_SWITCH) {
                        log("Preventing the lid switch from sleeping the display");
                        param.setResult(false);
                    }
                }
            });
        } catch (Throwable t) {
            log("PowerManagerService.sleepDisplayGroupNoUpdateLocked failed to hook (but you can probably ignore this), " + t.getMessage());
        }
    }

    public void log(String text) {
        XposedBridge.log(logName + ": " + text);
    }
}
