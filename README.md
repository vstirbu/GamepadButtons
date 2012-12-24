# Gamepad buttons plugin for Cordova/PhoneGap #

By [Vlad Stirbu](https://github.com/vstirbu).

This plugin allows you to use the buttons of Android gamepads in Cordova/PhoneGap applications.

## Adding the plugin to your project ##

1. Create a folder called 'com/vladstirbu/cordova/' within your project's src folder.
2. Copy the GamepadButtons.java into this new folder.
3. In your 'res/xml/config.xml' file add the following line:

	`<plugin name="GamepadButtons" value="com.vladstirbu.cordova.GamepadButtons" onload="true" />`
	
## Using the plugin ##

The plugin emits a 'gamepad.{buttonCode} event when a button is pressed. The 'buttonCode' values are standard Android key code [constants](http://developer.android.com/reference/android/view/KeyEvent.html).

```javascript
document.addEventListener('gamepad.KEYCODE_BUTTON_L1', function() {console.log('L1');}, false);
```

The MIT License

Copyright © 2012 Vlad Stirbu Ltd.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.