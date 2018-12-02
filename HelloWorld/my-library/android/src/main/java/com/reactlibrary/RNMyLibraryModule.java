
package com.reactlibrary;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Promise;

public class RNMyLibraryModule extends ReactContextBaseJavaModule {

  private static final String E_HELLO_WORLD_ERROR = "E_HELLO_WORLD_ERROR";

  private final ReactApplicationContext reactContext;

  public RNMyLibraryModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNMyLibrary";
  }

  @ReactMethod
  public void helloWorld(Promise promise) {
      promise.resolve("Hello World!");
  }
}