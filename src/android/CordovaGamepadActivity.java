/**
 * Gamepad buttons plugin for Cordova/Phonegap
 *
 * @author Vlad Stirbu
 * Copyright (c) Vlad Stirbu. 2012-2015. All Rights Reserved.
 * Available under the terms of the MIT License.
 *
 */

package com.vladstirbu.cordova;

import android.view.InputDevice;
import android.view.KeyEvent;
import android.view.MotionEvent;

import org.apache.cordova.CordovaActivity;

public class CordovaGamepadActivity extends CordovaActivity {

    @Override
    public boolean dispatchGenericMotionEvent(MotionEvent event) {
        return super.dispatchGenericMotionEvent(event);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if ((event.getSource() & InputDevice.SOURCE_GAMEPAD) == InputDevice.SOURCE_GAMEPAD
                || ((event.getSource() & InputDevice.SOURCE_JOYSTICK) == InputDevice.SOURCE_JOYSTICK)) {

            this.appView.getPluginManager().postMessage("gamepad-plugin", event);
            return true;
        } else {
            return super.dispatchKeyEvent(event);
        }
    }
}
