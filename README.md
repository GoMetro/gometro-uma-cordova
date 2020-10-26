## GoMetro UMA SDK

Installation
```bash
cordova plugin add gometro-uma-cordova
```

Usage
You should init the SDK in the deviceReady function, to make sure the native integrations runs. 

```javascript
    function onDeviceReady() {
        var GoMetroUma = cordova.require("gometro-uma-cordova.GoMetroUma");
        GoMetroUma.init("GOMETRO_UMA_USERNAME", "GOMETRO_UMA_PASSWORD");
        
        ...
    }
```

### iOS

If you are not already using Swift in your project, you will recieve a message stating that
```
Unsupported Swift Version

The target “GoMetroUmaSample” contains source code developed with Swift 3.x. This version of Xcode does not support building or migrating Swift 3.x targets.
 
Use Xcode 10.1 to migrate the code to Swift 4.
```

You will need to configure the "Swift Language Version" in 
XCode via **Project -> Build Settings -> Swift Compiler - Language -> Swift Language Version -> Swift 5**
