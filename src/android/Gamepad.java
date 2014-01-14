/**
 * Gamepad buttons plugin for Cordova/Phonegap
 *
 * @author Vlad Stirbu
 * Copyright (c) Vlad Stirbu. 2012-2013. All Rights Reserved.
 * Available under the terms of the MIT License.
 *
 */

package com.vladstirbu.cordova;

import java.util.Hashtable;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import android.view.InputDevice;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;

public class Gamepad extends CordovaPlugin {
    
	private Integer[] buttons = new Integer[17];
	private Hashtable<String, Integer> map = new Hashtable<String, Integer>();
	private CordovaPlugin self = this;
	
    /**
     * @param cordova The context of the main Activity.
     * @param webView The associated CordovaWebView.
     */
	@Override
	public void initialize(CordovaInterface cordova, CordovaWebView webView) {
		super.initialize(cordova, webView);
		
		this.map.put("KEYCODE_BUTTON_B", 0);
		this.map.put("KEYCODE_BUTTON_A", 1);
		this.map.put("KEYCODE_BUTTON_Y", 2);
		this.map.put("KEYCODE_BUTTON_X", 3);
		this.map.put("KEYCODE_BUTTON_L1", 4);
		this.map.put("KEYCODE_BUTTON_R1", 5);
		this.map.put("KEYCODE_BUTTON_L2", 6);
		this.map.put("KEYCODE_BUTTON_R2", 7);
		this.map.put("KEYCODE_SPACE", 8);
		this.map.put("KEYCODE_ENTER", 9);
		this.map.put("KEYCODE_DPAD_UP", 12);
		this.map.put("KEYCODE_DPAD_DOWN", 13);
		this.map.put("KEYCODE_DPAD_LEFT", 14);
		this.map.put("KEYCODE_DPAD_RIGHT", 15);
		this.map.put("KEYCODE_BACK", 16);
		
		
		this.webView.setOnKeyListener(new OnKeyListener() {
			
			@Override
        	public boolean onKey(View v, int keyCode, KeyEvent event) {
				//Log.v("Keyboards", String.valueOf(InputDevice.getDeviceIds().length));
				//Log.v("Input", InputDevice.getDevice(1).getName());
				//Log.v("Input", String.valueOf(InputDevice.getDevice(1).getSources()));
				
				//Log.v("Device id", String.valueOf(event.getDeviceId()));
				//Log.v("Source id", String.valueOf(event.getSource()));
				//Log.v("Input device", String.valueOf(InputDevice.getDevice(event.getDeviceId()).getName()));
				Log.v("KEY", String.valueOf(event.getScanCode()));
				Log.v("KEY", KeyEvent.keyCodeToString(keyCode));
				//Log.v("GamePad", String.valueOf(KeyEvent.isGamepadButton(keyCode)));
				
				String jsStr = jsString(keyCode, event);
				if (!jsStr.isEmpty()) {
					self.webView.sendJavascript(jsStr);
				}
        		return true;
        	}
        });
        
		Log.v("GamepadButtons", "initialized");
	}
    
	/**
	 * Constructs the JavaScript string that triggers the event in Cordova WebView
	 * @param keyCode
	 * @param event
	 * @return
	 */
	private String jsString(int keyCode, KeyEvent event) {
		String eventType;
		JSONObject data = new JSONObject();
        
		if (this.map.containsKey(KeyEvent.keyCodeToString(keyCode))) {
			
			if (event.getAction() == 0) {
				eventType = "GamepadButtonDown";
				this.buttons[this.map.get(KeyEvent.keyCodeToString(keyCode))] = 1;
			} else {
				eventType = "GamepadButtonUp";
				this.buttons[this.map.get(KeyEvent.keyCodeToString(keyCode))] = 0;
			}
			
			try {
				data.put("button", this.map.get(KeyEvent.keyCodeToString(keyCode)));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return "cordova.fireWindowEvent('" + eventType + "', " + data.toString() + ");";
		} else {
			return "";
		}
	}
}
