/**
 * Gamepad API polyfill for Apache Cordova/PhoneGap
 *
 * Copyright (c) 2013-2014 Vlad Stirbu <vlad.stirbu@ieee.org>
 * Available under the terms of the MIT License.
 *
 */

var cordova = require('cordova');

var GamepadPlugin = function (window, navigator) {
  var _gamepads = [],
      _indeces = [];

  function timestamp() {
    // Older Android WebView might not support performance
    //return performance.now ? performance.now() : Date.now();
    return Date.now();
  }

  function GamepadButton() {
    return {
      pressed: false,
      value: 0
    };
  }

  function Gamepad(options) {
    var result = {
      id: options.id || 'builtin',
      index: options.index || 0,
      connected: true,
      timestamp: timestamp(),
      mapping: 'standard',
      axes: [],
      buttons: Array.apply(0, Array(17)).map(function () {
        return GamepadButton();
      })
    };

    return result;
  }

  function getGamepads() {
    var result = [];

    _gamepads.forEach(function (value) {
      if (value && value.connected) {
        result.push(value);
      }
    });

    return result;
  }

  function getNextAvailableIndex() {
    var result,
        available = false;

    available = _indeces.some(function (value, index) {
      if (!value) {
        result = index;
        _indeces[index] = true;
        return true;
      }
    });

    if (!available) {
      _indeces.push(true);
      result = _indeces.length - 1;
    }

    return result;
  }

  function isNewGamepad() {
    return !_gamepads.length;
  }

  function addGamepad() {
    var index = getNextAvailableIndex(),
        gamepad;

    gamepad = Gamepad({
      index: index
    });

    if (index === _gamepads.length) {
      _gamepads.push(gamepad);
    } else {
      _gamepads[index] = gamepad;
    }
  }

  function buttonHandler(e, pressed) {
    var index = 0;

    if (isNewGamepad(e)) {
      addGamepad(e);
      //console.log('gamepad added');

      cordova.fireWindowEvent('gamepadconnected', {
       gamepad: _gamepads[index]
      });
    }

    // update gamepad
    if (_gamepads[index].buttons[e.button].pressed !== pressed) {
      _gamepads[index].buttons[e.button].pressed = pressed;
      _gamepads[index].buttons[e.button].value = pressed ? 1 : 0;
      _gamepads[index].buttons.timestamp = timestamp();

      cordova.fireWindowEvent('gamepadbutton', {
       button: e.button,
       gamepad: _gamepads[index]
      });
    }
  }

  /*
   * Register gamepad events callback as soon as the device is ready
   */
  cordova.channel.onCordovaReady.subscribe(function() {
    cordova.exec(function (e) {
      switch (e.type) {
      case 'GamepadButtonDown':
        buttonHandler(e, true);
        break;
      case 'GamepadButtonUp':
        buttonHandler(e, false);
        break;
      default:
      }
    }, function () {
      // error callback
    }, 'Gamepad', 'register', []);
  }, false);

  navigator.getGamepads = getGamepads;

};

var gamepad = new GamepadPlugin(window, navigator);

module.exports = gamepad;
