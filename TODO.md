https://support.gluonhq.com/browse/SUPFHNW-7

# Java version

* 11 build was OK with GraalVM 22
* 17 has errors with GraalVM 22 -> Gluon could reproduce and is investigating
* attach is not latest in FXGL 17, need to exclude in pom to be able to create native build

# Native on Linux

* OK with Java 11 build

# Native on Android

Crashes at startup

For Android, I've added the patch from the mentioned PR, and the app starts now... until it crashes:

```shell
[JavaFX Application Thread] FATAL FXGLApplication      - Uncaught Exception:
[lun. ene. 24 20:22:13 CET 2022][INFO] [SUB] D/GraalCompiled(13059): com.almasb.fxgl.core.reflect.ReflectionException: java.lang.NoSuchMethodException: com.almasb.fxgl.io.FileSystemService.<init>()
[lun. ene. 24 20:22:13 CET 2022][INFO] [SUB] D/GraalCompiled(13059):    at com.almasb.fxgl.core.reflect.ReflectionUtils.newInstance(ReflectionUtils.java:216)
[lun. ene. 24 20:22:13 CET 2022][INFO] [SUB] D/GraalCompiled(13059):    at com.almasb.fxgl.app.Engine.initServices(Engine.kt:76)
[lun. ene. 24 20:22:13 CET 2022][INFO] [SUB] D/GraalCompiled(13059):    a
```

I've noticed these warnings:

```shell
WARNING: Could not resolve com.almasb.fxgl.event.EventBusService for reflection configuration. Reason: java.lang.ClassNotFoundException: com.almasb.fxgl.event.EventBusService.
WARNING: Could not register com.almasb.fxgl.io.FileSystemService: allDeclaredMethods for reflection. Reason: java.lang.NoClassDefFoundError: com/gluonhq/attach/storage/StorageService.
WARNING: Could not register reflection metadata for com.almasb.fxgl.io.FileSystemService. Reason: java.lang.NoClassDefFoundError: com/gluonhq/attach/storage/StorageService.
```

but I also noticed that you are excluding Attach from the FXGL dependency.

# Native on MacOS

* Can play OK with arrows and space bar
* But error on mouseclick on screen

```shell
Message:  com.sun.glass.ui.mac.MacAccessible.accessibilityAttributeNames()[J
Type:  NoSuchMethodError
Method:  JNIFunctions$Support.getMethodID()
Line:  JNIFunctions.java:1195

java.lang.NoSuchMethodError: com.sun.glass.ui.mac.MacAccessible.accessibilityAttributeNames()[J
at com.oracle.svm.jni.functions.JNIFunctions$Support.getMethodID(JNIFunctions.java:1195)
at com.oracle.svm.jni.functions.JNIFunctions$Support.getMethodID(JNIFunctions.java:1180)
at com.oracle.svm.jni.functions.JNIFunctions.GetMethodID(JNIFunctions.java:416)
at com.sun.glass.ui.mac.MacAccessible._initIDs(MacAccessible.java)
at com.sun.glass.ui.mac.MacAccessible.<clinit>(MacAccessible.java:62)
at com.sun.glass.ui.mac.MacApplication.createAccessible(MacApplication.java:318)
at javafx.scene.Scene.getAccessible(Scene.java:6460)
at javafx.scene.Scene$ScenePeerListener.getSceneAccessible(Scene.java:2886)
at com.sun.javafx.tk.quantum.GlassViewEventHandler.getSceneAccessible(GlassViewEventHandler.java:1390)
at com.sun.glass.ui.View.getAccessible(View.java:1118)
at com.oracle.svm.jni.JNIJavaCallWrappers.jniInvoke_VARARGS_View_getAccessible_208bf9d5e8902251a899fb9f0c818944d597913d(JNIJavaCallWrappers.java:0)
```