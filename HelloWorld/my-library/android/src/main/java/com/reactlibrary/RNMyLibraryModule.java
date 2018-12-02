
package com.reactlibrary;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Promise;

public class RNMyLibraryModule extends ReactContextBaseJavaModule {

  private HelloWorld cppApi;

  private final ReactApplicationContext reactContext;

  static {
    System.loadLibrary("helloworld");
  }

  public RNMyLibraryModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
    cppApi = HelloWorld.create();
  }

  @Override
  public String getName() {
    return "RNMyLibrary";
  }

  @ReactMethod
  public void helloWorld(Promise promise) {
    String myString = cppApi.getHelloWorld();
    promise.resolve(myString);
  }
}