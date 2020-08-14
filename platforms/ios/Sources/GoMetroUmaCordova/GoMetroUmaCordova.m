#import "GoMetroUmaCordova.h"
#import <Cordova/CDVAvailability.h>
@import GoMetroUma;

NSString *goMetroUmaToken;

@implementation GoMetroUmaCordova

- (void)pluginInitialize {
     [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(didFinishLaunchingWithOptions:) name:UIApplicationDidFinishLaunchingNotification object:nil];
}

- (void)init:(CDVInvokedUrlCommand *)command {
    *goMetroUmaToken = [command.arguments objectAtIndex:0];
}

- (void)didFinishLaunchingWithOptions:(NSNotification*)notification
{
    [GoMetro.shared initialiseWithToken:@goMetroUmaToken];
}

@end
