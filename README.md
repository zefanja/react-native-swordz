# react-native-swordz

A simple wrapper for JNI bindings of the Sword Project (http://crosswire.org/sword)

## Installation Android
1. `npm install --save react-native-swordz`
2. In `android/setting.gradle`

    ```gradle
    ...
    include ':swordz'
    project(':swordz').projectDir = new File(settingsDir, '../node-modules/swordz/android/')
    ```

3. In `android/app/build.gradle`

    ```gradle
    ...
    dependencies {
        ...
        compile project(':swordz')
    }
    ```

4. Register module (in MainActivity.java) → React Native >= 0.19

    4.2. With RN >= 0.19.0

        ```java
        import org.crosswire.android.sword.SwordZPackage; // <----- import

        public class MainActivity extends ReactActivity {
            ...

            @Override
            protected List<ReactPackage> getPackages() {
              return Arrays.<ReactPackage>asList(
                new MainReactPackage(),
                new SwordZPackage() // <------ add here
              );
            }
        }
        ```

## Usage

```js
var mSwordZ = require('NativeModules').SwordZ;
...

mSwordZ.SWMgr_reInit();
mSwordZ.SWMgr_getModInfoList((modules) => {
  console.log("MODULES: ", modules); //outputs an array of the local installed modules
});

```

## API
Check ```android/src/main/java/org/crosswire/android/sword/SwordZModule.java```. Most of the API calls take a callback.

You have to call ```mSwordZ.SWMgr_reInit();``` first to init the native C++ library!