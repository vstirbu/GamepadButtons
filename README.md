# Gamepad buttons plugin for Cordova/PhoneGap

By [Vlad Stirbu](https://github.com/vstirbu).

This plugin allows you to use Android gamepads in Cordova/PhoneGap applications.

## Adding the plugin to your project

Using Cordova [command line interface](http://cordova.apache.org/docs/en/edge/guide_cli_index.md.html#The%20Command-Line%20Interface):

```bash
cordova plugins add https://github.com/vstirbu/GamepadButtons
```
	
## Using the plugin

The javascript API follows the [W3C Gamepad API](https://dvcs.w3.org/hg/gamepad/raw-file/default/gamepad.html), extended with elements from [Mozilla Gamepad API](https://developer.mozilla.org/en-US/docs/API/Gamepad/Using_Gamepad_API).

### Implementation status

#### Methods

* navigator.[getGamepads](https://dvcs.w3.org/hg/gamepad/raw-file/default/gamepad.html#idl-def-Navigator)()

#### Events

* window.[gamepadconnected](https://dvcs.w3.org/hg/gamepad/raw-file/default/gamepad.html#the-gamepadconnected-event)
* window.[gamepadbutton](https://developer.mozilla.org/en-US/docs/Web/Guide/API/Gamepad?redirectlocale=en-US&redirectslug=API%2FGamepad%2FUsing_Gamepad_API#nslDOMGamepadButtonEvent)

#### TODO

Currently, the plugin supports only the device's incorporated gamepad. External gamepads not supported.

The following events have not been implemented:

* [gamepaddisconnected](https://dvcs.w3.org/hg/gamepad/raw-file/default/gamepad.html#the-gamepaddisconnected-event) (not needed on the incorporated gamepad)
* [gamepadaxismove](https://developer.mozilla.org/en-US/docs/Web/Guide/API/Gamepad?redirectlocale=en-US&redirectslug=API%2FGamepad%2FUsing_Gamepad_API#nslDOMGamepadAxisMoveEvent) (my development device does not have have analog joysticks)

## Device experience

The plugin was tested on the following devices:

* [JXD s5110](http://www.jxd.hk/products.asp?id=611&selectclassid=009006)

## License

The plugin is available under MIT license.
