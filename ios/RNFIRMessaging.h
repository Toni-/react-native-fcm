

#import <UIKit/UIKit.h>

@import FirebaseCore;

#import "RCTBridgeModule.h"
#import <FirebaseInstanceID/FirebaseInstanceID.h>
#import <FirebaseCore/FirebaseCore.h>
#import <FirebaseMessaging/FirebaseMessaging.h>


extern NSString *const FCMNotificationReceived;

@interface RNFIRMessaging : NSObject <RCTBridgeModule, FIRMessagingDelegate>

@property (nonatomic, assign) bool connectedToFCM;

@end
