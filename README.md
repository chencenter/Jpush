# Jpush

极光推送工具类

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
    }
```
2、添加权限
```java
    <permission
        android:name="${applicationId}.permission.JPUSH_MESSAGE"
        android:protectionLevel="signature" />
    <uses-permission android:name="${applicationId}.permission.JPUSH_MESSAGE" />
```
3、添加appKey
```java
        <!-- Required  . Enable it you can get statistics data with channel -->
        <meta-data
            android:name="JPUSH_CHANNEL"
            android:value="developer-default" />
        <meta-data
            android:name="JPUSH_APPKEY"
            android:value="您应用的Appkey" /> <!--  </>值来自开发者平台取得的AppKey-->
```