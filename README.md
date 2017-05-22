# Jpush

极光推送工具类。基于jpush-android_v3.0.6.jar、jcore-android_v1.1.3.jar。

## 使用方法：

1、引用

在Project的gradle中加入：
```groovy
    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }
```
在Module的gradle中加入：
```groovy
    dependencies {
        compile 'com.github.like5188:Jpush:1.0.0'
        annotationProcessor rootProject.ext.deps.rxbus_compiler
    }
```
2、在AndroidManifest中添加权限
```java
    <permission
        android:name="${applicationId}.permission.JPUSH_MESSAGE"
        android:protectionLevel="signature" />
    <uses-permission android:name="${applicationId}.permission.JPUSH_MESSAGE" />
```
3、在AndroidManifest的application标签下添加appKey
```java
        <!-- Required  . Enable it you can get statistics data with channel -->
        <meta-data
            android:name="JPUSH_CHANNEL"
            android:value="developer-default" />
        <meta-data
            android:name="JPUSH_APPKEY"
            android:value="您应用的Appkey" /> <!--  </>值来自开发者平台取得的AppKey-->
```
4、在AndroidManifest的application标签下添加其他核心代码
```java
    <!-- Required -->
    <receiver
        android:name="cn.jpush.android.service.PushReceiver"
        android:enabled="true">
        <intent-filter android:priority="1000">
            <action android:name="cn.jpush.android.intent.NOTIFICATION_RECEIVED_PROXY" />
            <category android:name="${applicationId}" />
        </intent-filter>
        <intent-filter>
            <action android:name="android.intent.action.USER_PRESENT" />
            <action android:name="android.net.conn.CONNECTIVITY_CHANGE" />
        </intent-filter>
        <!-- Optional -->
        <intent-filter>
            <action android:name="android.intent.action.PACKAGE_ADDED" />
            <action android:name="android.intent.action.PACKAGE_REMOVED" />

            <data android:scheme="package" />
        </intent-filter>
    </receiver>
    
    <!-- Required SDK核心功能-->
    <activity
        android:name="cn.jpush.android.ui.PushActivity"
        android:configChanges="orientation|keyboardHidden"
        android:exported="false">
        <intent-filter>
            <action android:name="cn.jpush.android.ui.PushActivity" />

            <category android:name="android.intent.category.DEFAULT" />
            <category android:name="${applicationId}" />
        </intent-filter>
    </activity>
    
    <!-- User defined. 用户自定义的广播接收器-->
    <receiver
        android:name="com.like.jpush.JpushReceiver"
        android:enabled="true">
        <intent-filter>
            <!--Required 用户注册SDK的intent-->
            <action android:name="cn.jpush.android.intent.REGISTRATION" />
            <!--Required 用户接收SDK消息的intent-->
            <action android:name="cn.jpush.android.intent.MESSAGE_RECEIVED" />
            <!--Required 用户接收SDK通知栏信息的intent-->
            <action android:name="cn.jpush.android.intent.NOTIFICATION_RECEIVED" />
            <!--Required 用户打开自定义通知栏的intent-->
            <action android:name="cn.jpush.android.intent.NOTIFICATION_OPENED" />
            <!--Optional 用户接受Rich Push Javascript 回调函数的intent-->
            <action android:name="cn.jpush.android.intent.ACTION_RICHPUSH_CALLBACK" />
            <!-- 接收网络变化 连接/断开 since 1.6.3 -->
            <action android:name="cn.jpush.android.intent.CONNECTION" />
            <category android:name="${applicationId}" />
        </intent-filter>
    </receiver>
```
5、初始化。用以下两个方法之中的一个。区别是第一个方法会打印日志。
```java
    JpushUtils.getInstance(this).debugAndInit();
    JpushUtils.getInstance(this).init();
```
6、设置标签与别名
```java
    JpushUtils.getInstance(this).setAlias("like");
    JpushUtils.getInstance(this).setTags("like1,like2");
```
6、接收通知单击事件
```java
    在任意一个类中注册
    RxBus.register(this);
    在这个类销毁时反注册
    RxBus.unregister(this);
    
    然后用下面的方法接收事件
    @RxBusSubscribe(JpushReceiver.TAG_CLICK_NOTIFICATION)
    public void onNotificationClick(Intent intent) {
        // intent中的内容是由后台决定的。
    }
```
