package com.sty.ne.aopdb;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by tian on 2019/12/25.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                Log.i("sty", activity.getComponentName().getClassName() + " onActivityCreated");
            }

            @Override
            public void onActivityStarted(Activity activity) {
                Log.i("sty", activity.getComponentName().getClassName() + " onActivityStarted");
            }

            @Override
            public void onActivityResumed(Activity activity) {
                Log.i("sty", activity.getComponentName().getClassName() + " onActivityResumed");
            }

            @Override
            public void onActivityPaused(Activity activity) {
                Log.i("sty", activity.getComponentName().getClassName() + " onActivityPaused");
            }

            @Override
            public void onActivityStopped(Activity activity) {
                Log.i("sty", activity.getComponentName().getClassName() + " onActivityStopped");
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                Log.i("sty", activity.getComponentName().getClassName() + " onActivitySaveInstanceState");
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                Log.i("sty", activity.getComponentName().getClassName() + " onActivityDestroyed");
            }
        });
    }
}
