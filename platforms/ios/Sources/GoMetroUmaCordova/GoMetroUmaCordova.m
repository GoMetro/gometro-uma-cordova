#import "GoMetroUmaCordova.h"
#import <Cordova/CDVAvailability.h>
@import GoMetroUma;

@implementation GoMetroUmaCordova {
    NSString *goMetroUmaToken;
}

- (void)pluginInitialize
{
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(didFinishLaunchingWithOptions:)
                                                 name:UIApplicationDidFinishLaunchingNotification object:nil];
}

- (void)init:(CDVInvokedUrlCommand *)command
{
    goMetroUmaToken = [command.arguments objectAtIndex:0];
    NSLog(@"[GoMetroUmaCordova] Initialising [%@]", goMetroUmaToken);
    [GoMetroUma.shared initialiseWithToken:goMetroUmaToken];
}

- (void)didFinishLaunchingWithOptions:(NSNotification*)notification
{
    if (goMetroUmaToken != nil) {
        [GoMetroUma.shared initialiseWithToken:goMetroUmaToken];
    }
}

@end
