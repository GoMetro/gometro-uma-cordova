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
