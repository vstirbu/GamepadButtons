/**
 * Gamepad API polyfill for Apache Cordova/PhoneGap
 *
 * Copyright (c) 2013 Vlad Stirbu <vlad.stirbu@ieee.org>
 * Available under the terms of the MIT License.
 *
 */

(function (window, navigator) {
  var _gamepads = [],
      _indeces = [];
  
  function timestamp() {
    // Older Android WebView might not support performance
    return window.performance ? performance.now() : Date.now();
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
  
  function GamepadConnectedEvent(gamepad) {
    var event = new CustomEvent('gamepadconnected');
    
    event.gamepad = gamepad;
    
    return event;
  }
  
  function GamepadButtonEvent(button, gamepad) {
    var event = new CustomEvent('gamebutton');
    
    event.button = button;
    event.gamepad = gamepad;
    
    return event;
  }
  
  function buttonHandler(e, pressed) {
    var index = 0;
    
    if (isNewGamepad(e)) {
      addGamepad(e);
      console.log('gamepad added');
    }
    
    // update gamepad
    _gamepads[index].buttons[e.button].pressed = pressed;
    _gamepads[index].buttons[e.button].value = pressed ? 1 : 0;
    _gamepads[index].buttons.timestamp = timestamp();
    
    if (isNewGamepad(e)) {
      window.dispatchEvent(GamepadConnectedEvent(_gamepads[index]));
    }
    
    window.dispatchEvent(GamepadButtonEvent(e.button, _gamepads[index]));
    
  }
  
  window.addEventListener('GamepadButtonUp', function (e) {
    buttonHandler(e, false);
  }, false);
  
  window.addEventListener('GamepadButtonDown', function (e) {
    buttonHandler(e, true);
  }, false);
  
  navigator.getGamepads = getGamepads;
  
})(window, navigator);