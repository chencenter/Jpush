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
        compile 'com.github.like5188:Jpush:2.0.1'
        annotationProcessor 'com.github.like5188.RxBus:rxbus-compiler:2.0.1'// 用于接收返回结果
    }
```
2、在AndroidManifest.xml文件的application标签内添加
```java
    <meta-data
        android:name="JPUSH_APPKEY"
        android:value="你应用的appKey" />  
```
3、初始化。用以下两个方法之中的一个。区别是第一个方法会打印极光推送官方库的日志。
```java
    JpushUtils.getInstance(this).debugAndInit();
    JpushUtils.getInstance(this).init();
```
4、是否打印本工具类的日志。注意：设置这个标记，会影响所有用了Logger库的库。
```java
    Logger.setDebugMode(true);
    Logger.setDebugMode(false);
```
5、设置标签与别名
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
    @RxBusSubscribe(JpushReceiver.TAG_RECEIVE_NOTIFICATION)
    public void onReceiveNotification(Bundle bundle) {
        // bundle中的内容是由后台决定的。
    }
```
# License
```xml
    Copyright 2017 like5188
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
    http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
