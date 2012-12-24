/**
 * Gamepad buttons plugin for Cordova/Phonegap
 *
 * @author Vlad Stirbu/www.vladstirbu.com
 * Copyright (c) Vlad Stirbu. 2012. All Rights Reserved.
 * Available under the terms of the MIT License.
 * 
 */

package com.vladstirbu.cordova;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.DroidGap;
import org.apache.cordova.api.CordovaInterface;
import org.apache.cordova.api.CordovaPlugin;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;

public class GamepadButtons extends CordovaPlugin {
	
	/**
	 * Set the mode of triggering key events:
	 * 0 for keyDown
	 * 1 for keyUp
	 */
	private int keyMode = 0;
	
    /**
     * @param cordova The context of the main Activity.
     * @param webView The associated CordovaWebView.
     */
	@Override
	public void initialize(CordovaInterface cordova, CordovaWebView webView) {
		super.initialize(cordova, webView);
		
		this.webView.setOnKeyListener(new OnKeyListener() {
			
			@Override
        	public boolean onKey(View v, int keyCode, KeyEvent event) {
        		Log.v("Key", KeyEvent.keyCodeToString(keyCode));
        		Log.v("Action", String.valueOf(event.getAction()));
        		if (event.getAction() == keyMode) {
        			((DroidGap) v.getContext()).loadUrl(jsString(keyCode));
        		}
        		return true;
        	}
        });	

		Log.v("GamepadButtons", "initialized");
	}

	private String jsString(int keyCode) {
		return "javascript:cordova.fireDocumentEvent('gamepad." + KeyEvent.keyCodeToString(keyCode) + "');"; 
	}
}
